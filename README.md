# Eos2
Einfache Objektbasierte Sprache

Das Projekt ist der Nachfolger von Eos. (http://www.pabst-software.de)

Es ist gedacht für den Einsatz im Informatikunterricht der Klassen 6 bis 12.

Das Projekt ist aufgeteilt in verschiedene Module, welche jeder Jahrgangsstufe einen anderen Zugang erlauben.


# Eos2 
simple objectbased language

The project is the successor of Eos. (http://www.pabst-software.de)

It's intention is to support the computer science lessons for 12 to 18 year old pupils.

The project is devided into several modules, that allow a different approve for each grade.


## developer instructions

The whole project uses Maven as dependency and build system. Thus the most of the required external dependencies are resolved by Maven instead of storing the jars within this project repo. Nonetheless there are still 3rd party jars in ```3rdParty``` directory which are currently not available at maven center.
The [GitHub action workflow file](./.github/workflows/maven.yml) shows the automated CI/CD pipeline for this project.

### install Maven

Goto [Maven downloads](https://maven.apache.org/download.cgi) and get the latest version of Maven. Check all the given requirements (e.g. JDK). Make sure Maven is in your PATH by typing ```mvn -version``` in a terminal of your choice.

#### integrate Maven into Eclipse

Maven can easily be included into Eclipse by installing the m2e extension via Eclipse Marketplace or update sites. Check [m2e project page](https://projects.eclipse.org/projects/technology.m2e/downloads) for more information.
With the m2e integration the handling of Maven based projects is very similar to common Eclipse projects but CI/CD automation is much easier. 

### build the whole project

Have a look at the ```build.bat``` or ```build.sh``` for required steps.
Normally the common ```mvn install``` and/or ```mvn package``` steps are enough for building the whole project.
Finally the ```appassembler:assemble``` goal is used to assemble all artifacts and generated bin scripts for the Eos application.
