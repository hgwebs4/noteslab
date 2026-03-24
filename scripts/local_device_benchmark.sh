#!/bin/bash

adb uninstall org.hgwebs.mdrenderbenchmark.test

set -e

./gradlew clean
./gradlew :mdrenderbenchmark:assembleReleaseAndroidTest

adb install mdrenderbenchmark/build/outputs/apk/androidTest/release/mdrenderbenchmark-release-androidTest.apk
adb shell am instrument -w org.hgwebs.mdrenderbenchmark.test/androidx.benchmark.junit4.AndroidBenchmarkRunner

adb pull /storage/emulated/0/Android/media/org.hgwebs.mdrenderbenchmark.test/org.hgwebs.mdrenderbenchmark.test-benchmarkData.json
