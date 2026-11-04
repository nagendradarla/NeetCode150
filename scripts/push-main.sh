#!/usr/bin/env bash
# Stage all changes, commit, and push to origin/main.
# Usage: ./scripts/push-main.sh [commit message]
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  echo "Not a git repository: $ROOT" >&2
  exit 1
fi

branch="$(git rev-parse --abbrev-ref HEAD)"
if [[ "$branch" != "main" ]]; then
  echo "Current branch is '$branch'. Switch to main first." >&2
  exit 1
fi

git add -A

if git diff --cached --quiet; then
  echo "Nothing to commit."
else
  message="${*:-Save local changes}"
  git commit -m "$message"
fi

if git rev-parse --abbrev-ref --symbolic-full-name @{u} >/dev/null 2>&1; then
  if git status -sb | grep -q '\[ahead '; then
    git push origin main
  else
    echo "main is already up to date with origin/main."
  fi
else
  git push -u origin main
fi

git status -sb
