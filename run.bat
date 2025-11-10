@echo off
chcp 65001
javac -encoding UTF-8 *.java
java -Dfile.encoding=UTF-8 Main
pause
