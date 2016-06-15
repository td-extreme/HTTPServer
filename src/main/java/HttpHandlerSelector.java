// TODO break this up into sepearte classes.
// Handler should determine the type
// Then send the request to a Http<TYPE>Handler class
//
package com.td.HttpServer;

public class HttpHandlerSelector {

  FileIO fileIO;

  public HttpResponse generateResponse(HttpRequest request) {
    if (request.method().contains("GET")) {
      return getRequest(request);
    }
    return new HttpResponse("HTTP/1.1 200 OK");
  }

  private HttpResponse getRequest(HttpRequest request) {
    String path = request.path();
    if (path.contains(".txt")) {
      return getRequestTextFile(request);
    }
    return new HttpResponse("HTTP/1.1 200 OK");
  }

  private HttpResponse getRequestTextFile(HttpRequest request) {
    fileIO = new FileIO("./site/textFiles");
    String contents = fileIO.getFileContents(request.path());
   StringBuilder strBuilder = new StringBuilder();
   strBuilder.append("HTTP/1.1 200 OK\n\r\n\r");
   strBuilder.append(contents);
   return new HttpResponse(strBuilder.toString());
  }
}
