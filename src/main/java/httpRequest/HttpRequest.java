package com.td.HttpServer;
import java.util.HashMap;

public class HttpRequest implements IHttpRequest {

  String requestLine;
  String method;
  String path;
  String version;
  String parameters;
  HashMap<String, String> headers;
  byte[] body;

  public HttpRequest(String requestLine, HashMap<String, String> headers) {
    this.requestLine = requestLine;
    this.method = requestLine.split(" ")[0];
    String pathAndParameters[] = requestLine.split(" ")[1].split("&");
    this.path = pathAndParameters[0];
    if (pathAndParameters.length > 1 ) {
      this.parameters = pathAndParameters[1];
    } else {
      this.parameters = "";
    }

    this.version = requestLine.split(" ")[2];
    this.headers = headers;
    this.body = new byte[0];
  }

  public String requestLine() {
    return requestLine;
  }

  public String method() {
    return method;
  }

  public String path() {
    return path;
  }

  public String version() {
    return version;
  }

  public String parameters() {
    return parameters;
  }

  public HashMap<String, String> headers() {
    return headers;
  }

  public int contentLength() {
    String length = headers.getOrDefault("Content-Length", "0");
    return Integer.parseInt(length);
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  public byte[] body() {
   return body;
  }
}
