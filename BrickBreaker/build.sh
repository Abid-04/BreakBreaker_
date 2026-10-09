#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build/classes dist
find src/brickBreakerGame -name '*.java' > build/sources.txt
java com.sun.tools.javac.Main --release 17 -d build/classes @build/sources.txt
java sun.tools.jar.Main --create --file dist/BrickBreaker.jar --main-class brickBreakerGame.Main -C build/classes .
