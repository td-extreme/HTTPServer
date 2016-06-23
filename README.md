# HTTP Server
An HTTP Server written in Java.

[![Build Status](https://travis-ci.org/td-extreme/HTTPServer.svg?branch=master)](https://travis-ci.org/td-extreme/HTTPServer)
----

If you have gradle installed on your machine:

To run tests :  gradle test

To run the program: gradle run

## Passing in Arguments

To pass in arguments add the flag -P after run. Followed by args=" " with any arguments you want to pass in inside the quotation marks.

#### Options

-p [number] , --port [number] : Sets the port to [number] instead of default of 8080

-d [path] , --dir [path] : Sets working directory to [path] instead of default “./”

Example using port 1234 and directory “./site”

gradle run -P args=”-p 1234 -d ./site”


###If you do not already have gradle installed on your machine:

To run tests: gradlew test

To run the program: gradlew run
