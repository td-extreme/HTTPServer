package com.td.HttpServer;
import java.util.HashMap;

public interface IRequestParser {

  public String parseRequestLine(String rawRequest) throws InvalidHttpRequestException;
  public HashMap<String, String> parseHeaders(String rawRequest) throws InvalidHttpRequestException;

}
