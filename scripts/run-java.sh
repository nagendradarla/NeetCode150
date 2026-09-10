#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 src/LeetCode{num}/Solution.java" >&2
  exit 1
fi

FILE="$1"
if [[ ! -f "$FILE" ]]; then
  echo "File not found: $FILE" >&2
  exit 1
fi

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
export JAVA_HOME="/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"

PKG="$(grep -E '^package ' "$FILE" | head -1 | sed 's/package[[:space:]]*//;s/;[[:space:]]*$//' || true)"
CLASS="$(basename "$FILE" .java)"
if [[ -n "$PKG" ]]; then
  MAIN="${PKG}.${CLASS}"
else
  MAIN="$CLASS"
fi

mkdir -p "$ROOT/bin"
javac -d "$ROOT/bin" -g --release 17 "$FILE"
exec java -cp "$ROOT/bin" "$MAIN" "${@:2}"
