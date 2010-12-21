@echo off
java -Dlogback.configurationFile=./logback.xml -classpath .;.\lib\* jp.tricreo.schemagenerator.application.Application %1



