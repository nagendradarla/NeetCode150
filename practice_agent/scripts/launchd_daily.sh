#!/usr/bin/env bash
# Launchd / manual entrypoint for daily NeetCode practice alert.
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
AGENT="$ROOT/practice_agent"
LOG_DIR="$AGENT/logs"
mkdir -p "$LOG_DIR"

cd "$AGENT"

set -a
if [[ -f "$AGENT/.env" ]]; then
  # shellcheck disable=SC1091
  source "$AGENT/.env"
elif [[ -f "$HOME/job-agent/.env" ]]; then
  # shellcheck disable=SC1091
  source "$HOME/job-agent/.env"
fi
set +a

PY="${AGENT}/venv/bin/python"
if [[ ! -x "$PY" ]]; then
  PY="$(command -v python3)"
fi

echo "[$(date '+%Y-%m-%d %H:%M:%S')] daily_practice start" >>"$LOG_DIR/launchd.log"
"$PY" "$AGENT/daily_practice.py" >>"$LOG_DIR/launchd.log" 2>&1
echo "[$(date '+%Y-%m-%d %H:%M:%S')] daily_practice done" >>"$LOG_DIR/launchd.log"
