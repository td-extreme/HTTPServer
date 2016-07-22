package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetFileContents implements Ihandler {
  private IFileIO fileIO;
  private ContentTypeForFileExtension contentType;
  private String path;

  public HandlerGetFileContents(String path, IFileIO fileIO, ContentTypeForFileExtension contentType) {
    this.path = path;
    this.contentType = contentType;
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      byte[] body = fileIO.getContent(path);
      rtnResponse.setBody(body, contentType.getContentType(path));
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException", "text/plain");
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
  }
}
