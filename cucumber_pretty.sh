#!/usr/bin/env bash
javac -cp "jars/*:." step_definitions/*.java \
                     implementation/*.java
                   
java -cp "jars/*:." cucumber.api.cli.Main -p pretty --snippets camelcase \
                                          -g step_definitions features
