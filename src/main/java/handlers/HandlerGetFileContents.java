package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetFileContents implements IHandler {
  private ContentTypeForFileExtension contentTypeForFileExtension;
  private IFileIO fileIO;
  private String path;

  public HandlerGetFileContents(String path, IFileIO fileIO) {
    this.contentTypeForFileExtension = new ContentTypeForFileExtension();
    this.path = path;
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      byte[] body = fileIO.getContent(path);
      rtnResponse.setBody(body, contentTypeForFileExtension.getContentType(path));
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException", ContentType.text);
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
  }
}
