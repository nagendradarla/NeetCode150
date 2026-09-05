#!/usr/bin/env bash
# One-time per clone: use githooks that strip Cursor co-author trailers.
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"
git config core.hooksPath .githooks
chmod +x .githooks/prepare-commit-msg
echo "Git hooks enabled: core.hooksPath=.githooks"
