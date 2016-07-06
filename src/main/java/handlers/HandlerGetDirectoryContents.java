package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetDirectoryContents implements Ihandler {
  IFileIO fileIO;
  DirListHtml dirListHtml;

  public HandlerGetDirectoryContents(IFileIO fileIO, DirListHtml dirListHtml) {
    this.fileIO = fileIO;
    this.dirListHtml = dirListHtml;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      String[] fileList = fileIO.getFiles(request.path());
      byte[] body = dirListHtml.buildHtmlPage(request.path(), fileList);
      rtnResponse.setBody(body);
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException");
      rtnResponse.setCode(404);
    }
    return rtnResponse;
  }
}
