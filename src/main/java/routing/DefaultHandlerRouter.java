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
    return new HandlerFileNotFound();
  }

  private IHandler selectGetHandler(String path) {
    if (fileIO.isFile(path)) {
      return new HandlerGetFileContents(path, fileIO);
    } else if (fileIO.isDirectory(path)) {
      return new HandlerGetDirectoryContents(path, fileIO, dirListHtml);
    } else {
      return new HandlerFileNotFound();
    }
  }

  private IHandler selectPostHandler(HttpRequest request) {
    if (request.body().length == 0 || request.path().equals("/")) {
      return new HandlerUnprocessableEntity("POST Method must supply a Body and a Path");
    } else {
    return new HandlerPostFileContents(request.path(), request.body(), fileIO);
    }
  }
}