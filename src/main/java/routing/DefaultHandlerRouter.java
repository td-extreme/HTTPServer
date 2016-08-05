package com.td.HttpServer;

import java.util.HashMap;

public class DefaultHandlerRouter {

  private IFileIO fileIO;
  private DirListHtml dirListHtml;

  public DefaultHandlerRouter(IFileIO fileIO) {
    this.dirListHtml = new DirListHtml();
    this.fileIO = fileIO;
  }

  public IHandler selectHandler(HttpRequest request) {
    if (request.method().equals("GET")) {
      return selectGetHandler(request.path());
    } else if (request.method().equals("POST")) {
      return selectPostHandler(request);
    }
    return new HandlerMethodNotAllowed();
  }

  private IHandler selectGetHandler(String path) {
    if (fileIO.isFile(path)) {
      return new HandlerGetFileContents(fileIO);
    } else if (fileIO.isDirectory(path)) {
      return new HandlerGetDirectoryContents( fileIO, dirListHtml);
    } else {
      return new HandlerFileNotFound();
    }
  }

  private IHandler selectPostHandler(HttpRequest request) {
    if (request.body().length == 0 || request.path().equals("/")) {
      return new HandlerMethodNotAllowed();
    } else {
    return new HandlerPostFileContents(fileIO);
    }
  }
}
