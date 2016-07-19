package com.td.HttpServer;
import java.util.*;

public class HttpRequestParser implements IRequestParser {

  private IValidator requestVerifier;
  public HttpRequestParser(IValidator requestVerifier) {
    this.requestVerifier = requestVerifier;
  }

  public HttpRequest parseRequest(String rawRequest) throws InvalidHttpRequestException {
    return new HttpRequest(parseRequestLine(rawRequest), parseHeaders(rawRequest));
  }

  private String parseRequestLine(String rawRequest) throws InvalidHttpRequestException {
    String requestLine = rawRequest.split("\r?\n")[0];
    if (!requestVerifier.isValid(requestLine)) {
      throw new InvalidHttpRequestException();
    }
    return requestLine;
  }

  private HashMap<String, String> parseHeaders(String rawRequest) {
    String rawHeaders = rawRequest.split("\n", 2)[1];
    HashMap<String, String> headers = new HashMap<String, String>();
    String headerLines[] = rawHeaders.split("\n");
    for (String line : headerLines ) {
      String lineSplit[] = line.split(": ", 2);
      if (lineSplit.length == 2) {
        headers.put(lineSplit[0], lineSplit[1]);
      }
    }
    return headers;
  }
}
