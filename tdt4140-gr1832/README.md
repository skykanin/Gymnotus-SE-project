The project is built using maven.

TODO: explain more about the java project

## How to build

#### Setup once

1. Install maven with a package manager (apt, brew, etc.)

	* $ sudo apt-get install maven
	* $ brew install maven

#### Everytime

In the `32/tdt4140-gr1832` folder use the following commands:

1. Use `$ mvn clean install` to cleanly compile to source code
2. Later on you should be able to use `$ mvn exec:java -Dexec.mainClass="tdt4140.gr1832.app.core.Main"` to execute the program.
