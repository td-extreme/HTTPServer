package com.td.HttpServer;

public class InvalidHttpRequestException extends Exception {
  public InvalidHttpRequestException() { }
  public InvalidHttpRequestException(Throwable cause) {
    super(cause);
  }
}
