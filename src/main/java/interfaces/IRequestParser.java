package com.td.HttpServer;
import java.util.HashMap;

public interface IRequestParser {
  public HttpRequest parseRequest(String rawRequest) throws InvalidHttpRequestException;
}
