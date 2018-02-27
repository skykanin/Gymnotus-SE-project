In this directory you will find the code that runs the server and most of the backend.

## Build and run the server

In the `32/tdt4140-gr1832` folder use the following commands:

1. Use `$ mvn clean install -pl web.server` to cleanly build the server source code
2. Run `$ mvn exec:java -pl web.server -Dexec.mainClass=ServerThread` to spawn a server on port 8080.
