package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetFileContents implements Ihandler {
  IFileIO fileIO;
  ResponseHeadersForContent responseHeaders;

  public HandlerGetFileContents(IFileIO fileIO, ResponseHeadersForContent responseHeaders) {
    this.responseHeaders = responseHeaders;
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      byte[] body = fileIO.getContent(request.path());
      rtnResponse.setBody(body);
      rtnResponse.addHeaders(responseHeaders.getHeaders(body, request.path()));
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException");
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
  }
}
