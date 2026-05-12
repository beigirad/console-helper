# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
./gradlew build          # compile + test
./gradlew test           # run all tests (JUnit 5)
./gradlew clean          # delete build/
./gradlew publishConsoleHelperPublicationToPegahPublicRepository  # publish to Artifactory
```

## Architecture

This is a Kotlin library (`com.github.beigirad:console-helper`) that provides high-level console utilities for Kotlin scripts, wrapping [JLine 3](https://github.com/jline/jline3) for terminal control.

**Package:** `ir.beigirad.consolehelper`

### Core Components

- **`EventWriter`** — The main I/O class. Wraps a JLine `Terminal` to support concurrent progress indicators (animated spinner + elapsed time) alongside regular print output. Progress lines are ANSI-cleared and redrawn so they stay pinned below normal output. Only renders progress when running in an interactive terminal (`System.console() != null`).

- **`Table`** — Renders ASCII tables with dynamic column widths. Has a DSL builder (`table { ... }`). Delimiter style is configurable.

- **`Tree`** — Renders hierarchical trees using Unicode box-drawing characters (├──, └──, │). Has a DSL builder (`tree { ... }`). Rendering is recursive.

- **`ArgProcessor`** — Parses `--key=value` CLI arguments. Handles values containing spaces, `=`, `--`, and newlines.

- **`Storage`** — File-backed list of strings (one per line). Lazy-loaded on first read; `+=` and `-=` operators update the file atomically.

### Sub-packages

- **`shell/Shell.kt`** — `runCommand()` / async variants wrapping `kotlin-shell-core`.
- **`file/File.kt`** — `File / "subpath"` operator overload and git root directory detection (uses `runCommand` to call `git rev-parse`).

### Publishing

Distributed via [JitPack](https://jitpack.io/#beigirad/console-helper) and also published to an internal Pegah Artifactory instance. Version is set in `build.gradle.kts`. The API surface is tracked with the `metalava-extended` plugin; the reference file is `console-helper-*.api.txt`.
