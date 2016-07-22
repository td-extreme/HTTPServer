package com.td.HttpServer;

import java.io.IOException;

public class HandlerGetDirectoryContents implements Ihandler {
  private IFileIO fileIO;
  private DirListHtml dirListHtml;
  private String path;

  public HandlerGetDirectoryContents(String path, IFileIO fileIO, DirListHtml dirListHtml) {
    this.path = path;
    this.fileIO = fileIO;
    this.dirListHtml = dirListHtml;
  }

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      String[] fileList = fileIO.getFiles(path);
      byte[] body = dirListHtml.buildHtmlPage(path, fileList);
      rtnResponse.setBody(body, ContentType.html);
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException", ContentType.text);
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
  }
}
