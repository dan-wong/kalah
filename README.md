# kalah
SOFTENG701 - A3

## Setup
Add `kalah.jar` and `junit-3.8.2.jar` to your classpath.

In Intellij, `File -> Project Structure -> Modules -> Dependencies -> Plus Sign`

## Building to jar
In Intellij, `File -> Project Structure -> Artifacts -> Plus Sign -> Jar -> From Modules with Dependencies`

Then remove `junit-3.8.2` and `kalah.jar` from compiled sources in the output layout.

## Running the Tests in `kalah.jar`
`java -cp junit-3.8.2.jar;kalah.jar;dwon184_softeng701_a3.jar junit.textui.TestRunner kalah.test.TestKalah`
