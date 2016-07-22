package com.td.HttpServer;

public interface IResponseWriter {
  public void sendHttpResponse(IClientSocketOutput client, HttpResponse response) throws BadConnectionException;
}
