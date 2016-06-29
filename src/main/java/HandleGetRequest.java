package com.td.HttpServer;

import java.util.*;
import java.io.IOException;

public class HandleGetRequest extends HttpHandleRequest {

  private IFileIO fileIO;
  private DirListHtml dirListHtml;

  private ResponseHeaders responseHeaders;

  public HandleGetRequest(IFileIO fileIO, DirListHtml dirListHtml) {
    this.fileIO = fileIO;
    this.dirListHtml = dirListHtml;
    responseHeaders = new ResponseHeaders();
  }

  public HttpResponse generateResponse(HttpRequest request) {
    String path = request.path();
    if (fileIO.exists(path)) {
      return responseWithContents(path);
    } else {
      return notFound();
    }
  }

  private HttpResponse responseWithContents(String path) {
    byte[] body;
    try {
      if (isPathFile(path)) {
        body = fileIO.getContent(path);
      } else {
        body = dirListHtml.buildHtmlPage(path, fileIO.getFiles(path));
        path = "/";
      }
      HashMap<String, String> headers = buildHeaders(body, path);
      return new HttpResponse(CODE_200, body, headers);
    }
    catch (IOException e) {
      return notFound();
    }
  }

  private boolean isPathFile(String path) {
    try {
      return fileIO.isPathFile(path);
    }
    catch (IOException e) {
      return false;
    }
  }
  private HttpResponse notFound() {
    byte[] body = "File not Found".getBytes();
    HashMap<String, String> headers = buildHeaders(body, "/");
    return new HttpResponse(CODE_404, body, headers);
  }

  private HashMap<String, String> buildHeaders(byte[] body, String path) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    rtnMap.putAll(standardHeaders());
    rtnMap.putAll(responseHeaders.buildHeadersFromGetRequest(body, path));
    return rtnMap;
  }
}
