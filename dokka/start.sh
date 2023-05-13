#!/bin/bash

java -jar dokka-cli-1.8.10.jar -sourceSet '-src ../app/src/ -analysisPlatform android -classpath dokka-android-gradle-plugin-0.9.18.jar' -outputDir '../Docs/' -pluginsClasspath "./dokka-base-1.8.10.jar;./dokka-analysis-1.8.10.jar;./kotlin-analysis-intellij-1.8.10.jar;./kotlin-analysis-compiler-1.8.10.jar;./kotlinx-html-jvm-0.8.0.jar;./freemarker-2.3.31.jar;./android-documentation-plugin-1.8.10.jar;" -moduleName 'Automan'
