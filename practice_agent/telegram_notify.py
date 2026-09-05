"""Send Telegram alerts (same approach as ~/job-agent/telegram_notify.py)."""

from __future__ import annotations

import json
import os
import subprocess
from typing import Any

import certifi
import urllib3

TELEGRAM_HOST = "api.telegram.org"
TELEGRAM_FALLBACK_IP = "149.154.166.110"
UMBRELLA_PREFIX = "146.112."
TELEGRAM_MAX_CHARS = 4000


def _resolve_telegram_ip() -> str:
    for resolver in ("8.8.8.8", "1.1.1.1"):
        try:
            out = subprocess.check_output(
                ["dig", "+short", TELEGRAM_HOST, f"@{resolver}"],
                text=True,
                timeout=5,
            ).strip()
            for line in out.splitlines():
                ip = line.strip()
                if ip and not ip.startswith(UMBRELLA_PREFIX):
                    return ip
        except (subprocess.SubprocessError, OSError, ValueError):
            continue
    return TELEGRAM_FALLBACK_IP


def _post_via_ip(token: str, payload: dict[str, Any]) -> urllib3.HTTPResponse:
    ip = _resolve_telegram_ip()
    url = f"https://{ip}/bot{token}/sendMessage"
    http = urllib3.PoolManager(
        cert_reqs="CERT_REQUIRED",
        ca_certs=certifi.where(),
        server_hostname=TELEGRAM_HOST,
    )
    return http.request(
        "POST",
        url,
        body=json.dumps(payload).encode("utf-8"),
        headers={
            "Host": TELEGRAM_HOST,
            "Content-Type": "application/json",
        },
        timeout=urllib3.Timeout(connect=10, read=30),
    )


def send_telegram(message: str) -> bool:
    token = os.getenv("TELEGRAM_BOT_TOKEN")
    chat_id = os.getenv("TELEGRAM_CHAT_ID")
    if not token or not chat_id:
        print("Telegram not configured (TELEGRAM_BOT_TOKEN / TELEGRAM_CHAT_ID); skipping.")
        return False

    text = (message or "").strip()
    if not text:
        return False
    if len(text) > TELEGRAM_MAX_CHARS:
        text = text[: TELEGRAM_MAX_CHARS - 20] + "\n…(truncated)"

    payload = {"chat_id": chat_id, "text": text, "disable_web_page_preview": False}
    try:
        resp = _post_via_ip(token, payload)
        if resp.status >= 400:
            body = (resp.data or b"").decode("utf-8", errors="replace")[:300]
            print(f"Telegram alert failed: HTTP {resp.status} {body}")
            return False
        print("Telegram alert sent.")
        return True
    except Exception as exc:
        print(f"Telegram alert failed: {exc}")
        return False


def send_telegram_chunks(message: str) -> bool:
    """Split on blank lines if over limit."""
    text = (message or "").rstrip()
    if len(text) <= TELEGRAM_MAX_CHARS:
        return send_telegram(text)

    parts: list[str] = []
    current = ""
    for block in text.split("\n\n"):
        candidate = (current + "\n\n" + block).strip() if current else block
        if len(candidate) <= TELEGRAM_MAX_CHARS:
            current = candidate
        else:
            if current:
                parts.append(current)
            current = block
    if current:
        parts.append(current)

    ok = True
    for i, part in enumerate(parts, 1):
        header = f"(part {i}/{len(parts)})\n" if len(parts) > 1 else ""
        ok = send_telegram(header + part) and ok
    return ok
