# HTTP Server
An HTTP Server written in Java.

[![Build Status](https://travis-ci.org/td-extreme/HTTPServer.svg?branch=master)](https://travis-ci.org/td-extreme/HTTPServer)
----

If you have gradle installed on your machine:

To run just unit tests :  gradle test

To run unit tests and end to end (blackbox test) : ./runAllTests.sh

To run the program: gradle run

## Passing in Arguments

To pass in arguments add the flag -P after run. Followed by args=" " with any arguments you want to pass in inside the quotation marks.

#### Options

-p [number] , --port [number] : Sets the port to [number] instead of default of 8080

-d [path] , --dir [path] : Sets working directory to [path] instead of default “./”

Example using port 1234 and directory “./site”

gradle run -P args=”-p 1234 -d ./site”


###If you do not already have gradle installed on your machine:

To run unit tests: gradlew test

To run unit, and blackbox test: ./runAllTests.sh 

To run the program: gradlew run

----
## Extending this Server into your own project 

To create a jar file: gradle jar

  The jar file should be created in ./build/libs/
  
This HttpServer can be extended by including the JAR file in your own project. The constructor for HttpServer takes two parameters, the port to run on and the directory the server should use. You will have the ability to create your own custom handlers and add them to the list of handlers the server can use. Your custom handler must implement HttpServer’s IHandler interface, which contains a single method `generateResponse()` which returns an instance of the class HttpResposne. 

After you have created your own custom handler you can add a route to this handler by using the HttpServer’s method `addRoute(String path, String method, IHanlder handler)`

Example of how to create an instance of the HttpServer, add a route to your custom handler and start the server.
```
HttpServer myServer = new HttpServer(8080, “./path_to_server”);
myServer.addRoute(“GET”, “/myRoute”, new CustomHandler());
myServer.run();
```

HttpResponse methods used to create a response.

The constructor takes zero arguments and creates an instance of HttpResponse with an empty body, no headers and a responseCode of 200. The following methods are available:

`void addHeader(String key, String value)`

- adds the header to the response.

`void addHeaders(HashMap<String, String> headers)`
 
- adds the headers inside a HashMap to the response.

`void setResponseCode(int code)`

- sets the response code.

`void setBody(String body, String contentType)`

- sets the response body and adds a ContentType header as specified by the second argument. This method will also calculate the ContentLength and add that header automatically.

`void setBody(byte[] body, String contentType)`

- sets the response body and adds a ContentType header as specified by the second argument. This method will also calculate the ContentLength and add that header automatically.


### Constants for content type.
The following constant variables exists that you can use to set the content type. 

`ContentType.html` for text/html

`ContentType.text` for text/plain

`ContentType.jpg` for image/jpeg

`ContentType.gif` for image/gif

`ContentType.pdf` for application/pdf

`ContentType.download` for application/force-download


An example of what a custom handler might look like.

```
public class CustomHandler implements IHandler {
  public HttpResponse generateResponse() {
    HttpResponse myResponse = new HttpResponse();
    myResponse.setResponseCode(200);
    myResponse.setBody(“This is my body”, “text/plain”);
    myResponse.addHeader(“Last-Modified”, “Tue, 15 Nov 1994 12:45:10 GMT”);
    return myResponse;
  }
}
```
