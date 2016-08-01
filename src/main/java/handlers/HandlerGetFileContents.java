package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetFileContents implements IHandler {
  private ContentTypeForFileExtension contentTypeForFileExtension;
  private IFileIO fileIO;

  public HandlerGetFileContents(IFileIO fileIO) {
    this.contentTypeForFileExtension = new ContentTypeForFileExtension();
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      byte[] body = fileIO.getContent(request.path());
      rtnResponse.setBody(body, contentTypeForFileExtension.getContentType(request.path()));
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("Internal Server Error", ContentType.text);
      rtnResponse.setResponseCode(500);
    }
    return rtnResponse;
  }
}
