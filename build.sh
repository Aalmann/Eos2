#!/bin/bash

mvn clean install
mvn package 
mvn appassembler:assemble --file Eoe/pom.xml