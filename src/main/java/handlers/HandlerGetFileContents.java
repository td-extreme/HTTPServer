package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetFileContents implements Ihandler {
  private IFileIO fileIO;
  private ResponseHeadersForContent responseHeaders;
  private String path;

  public HandlerGetFileContents(String path, IFileIO fileIO, ResponseHeadersForContent responseHeaders) {
    this.path = path;
    this.responseHeaders = responseHeaders;
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      byte[] body = fileIO.getContent(path);
      rtnResponse.setBody(body);
      rtnResponse.addHeaders(responseHeaders.getHeaders(body, path));
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException");
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
  }
}
