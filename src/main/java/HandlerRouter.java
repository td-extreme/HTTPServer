package com.td.HttpServer;

import java.util.*;

public class HandlerRouter {

  private IFileIO fileIO;
  private ResponseHeadersForContent responseHeadersForContent;
  private DirListHtml dirListHtml;

  public HandlerRouter(IFileIO fileIO) {
    this.responseHeadersForContent = new ResponseHeadersForContent();
    this.dirListHtml = new DirListHtml();
    this.fileIO = fileIO;
  }

  public Ihandler selectHandlerBadRequest() {
    return new HandlerBadRequest();
  }

  public Ihandler selectHandler(HttpRequest request) {
    if (request.method().equals("GET")) {
      return selectGetHandler(request.path());
    }
    return new HandlerFileNotFound();
  }

  private Ihandler selectGetHandler(String path) {
    if (fileIO.isFile(path)) {
      return new HandlerGetFileContents(path, fileIO, responseHeadersForContent);
    } else if (fileIO.isDirectory(path)) {
      return new HandlerGetDirectoryContents(path, fileIO, dirListHtml);
    } else {
      return new HandlerFileNotFound();
    }
  }
}
