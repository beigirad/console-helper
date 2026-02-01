#!/bin/sh

set -e            # exit on error
set -u            # treat unset variables as errors
set -o  pipefail  # if a pipeline fails at any point, the whole pipeline fails
set -x            # print each command before executing it

./gradlew assemble
./gradlew publishToMavenLocal
kotlin test.main.kts