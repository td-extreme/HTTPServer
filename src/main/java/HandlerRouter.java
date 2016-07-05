package com.td.HttpServer;

import java.util.*;

public class HandlerRouter {

  private IFileIO fileIO;
  private ResponseHeadersForContent responseHeadersForContent;
  private DirListHtml dirListHtml;

  public HandlerRouter(IFileIO fileIO) {
    responseHeadersForContent = new ResponseHeadersForContent();
    dirListHtml = new DirListHtml();
    this.fileIO = fileIO;
  }

  public Ihandler selectHandler(HttpRequest request) {
    if (request.method().equals("GET")) {
      return selectGetHandler(request.path());
    }
    return new HandlerFileNotFound(); }

  private Ihandler selectGetHandler(String path) {
    if (fileIO.isFile(path)) {
      return new HandlerGetFileContents(fileIO, responseHeadersForContent);
    } else if (fileIO.isDirectory(path)) {
      return new HandlerGetDirectoryContents(fileIO, dirListHtml);
    } else {
      return new HandlerFileNotFound();
    }
  }
}
