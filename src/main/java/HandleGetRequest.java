package com.td.HttpServer;

import java.util.*;
import java.io.IOException;

public class HandleGetRequest extends HttpHandleRequest {

  private IFileIO fileIO;

  public HandleGetRequest(IFileIO fileIO) {
    super();
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    String path = request.path();
    if (path.equals("/")) {
      return defaultResponse();
    }
    try {
      byte[] body = fileIO.getContent(path);
      HashMap<String, String> headers = buildHeaders(body, path);
      return new HttpResponse(CODE_200, body, headers);
    }
    catch (IOException e) {
      return notFound(request);
    }
  }

  private HttpResponse defaultResponse() {
    byte[] body = "Default path / ".getBytes();
    HashMap<String, String> headers = buildHeaders(body, "/");
    return new HttpResponse(CODE_200, body, headers);
  }

  private HttpResponse notFound(HttpRequest request) {
    byte[] body = "File not Found".getBytes();
    HashMap<String, String> headers = buildHeaders(body, request.path());
    return new HttpResponse(CODE_404, body, headers);
  }

  private HashMap<String, String> buildHeaders(byte[] body, String path) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    rtnMap.putAll(standardHeaders());
    rtnMap.putAll(contentHeaders(body, path));
    return rtnMap;
  }

  private HashMap<String, String> contentHeaders(byte[] body, String path) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    rtnMap.put("Content-Type", contentType(path));
    rtnMap.put("Content-Length", contentLength(body));
    return rtnMap;
  }

 private String contentLength(byte[] body) {
    return Integer.toString(body.length);
  }

  private String contentType(String fileName) {
    if (fileName.endsWith(".htm") || fileName.endsWith(".html")
        || fileName.endsWith(".txt")) {
      return "text/html";
    } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
      return "image/jpeg";
    } else if (fileName.endsWith(".gif")) {
      return "image/gif";
    } else if (fileName.endsWith(".pdf")) {
      return "application/pdf";
    } else {
      return "application/force-download";
    }
  }
}
