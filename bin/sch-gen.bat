@echo off
java -Dlogback.configurationFile=./logback.xml -classpath .;.\lib\* schemagenerator.application.Application %1


