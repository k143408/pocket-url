#!/usr/bin/env bash
# Fail on a single failed command
set -e pipefail

startup() {
  # Initialize environment
  local args
  args="-jar /deployment/app.jar -XX:NativeMemoryTracking=summary -Xjsr305=strict -Xms364M"
  exec java  ${args} $*
}

startup $*
