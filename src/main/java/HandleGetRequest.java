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
    if (isPathFile(path)) {
      return responseWithFileContents(path);
    } else {
      return responseWithDirContents(path);
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

  private HttpResponse responseWithFileContents(String path) {
    byte[] body;
    HashMap<String, String> headers;
    try {
      body = fileIO.getContent(path);
    }
    catch (IOException e) {
      return notFound();
    }
    headers = buildHeaders(body, path);
    return new HttpResponse(CODE_200, body, headers);
  }

  private HttpResponse responseWithDirContents(String path) {
    byte[] body;
    HashMap<String, String> headers;
    try {
      body = dirListHtml.buildHtmlPage(path, fileIO.getFiles(path));
    }
    catch (IOException e) {
      return notFound();
    }
    headers = buildHeaders(body, "/");
    return new HttpResponse(CODE_200, body, headers);
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
