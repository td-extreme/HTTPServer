package com.td.HttpServer;
import java.io.IOException;

public class HandlerPutFileContents implements IHandler {

  private IFileIO fileIO;

  public HandlerPutFileContents(IFileIO fileIO) {
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      fileIO.writeContent(request.path(), request.body());
      rtnResponse.setResponseCode(200);
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("Internal Server Error", ContentType.text);
      rtnResponse.setResponseCode(500);
    }
    return rtnResponse;
  }
}
