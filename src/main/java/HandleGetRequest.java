package com.td.HttpServer;

import java.util.*;
import java.io.IOException;

public class HandleGetRequest extends HttpHandleRequest {

  private IFileIO fileIO;
  private HashMap<String, String> contentTypeMap;

  public HandleGetRequest(IFileIO fileIO) {
    super();
    this.fileIO = fileIO;
    buildContentTypeMap();
  }

  private void buildContentTypeMap() {
    contentTypeMap = new HashMap<String, String>();
    contentTypeMap.put(".htm", "text/html");
    contentTypeMap.put(".html", "text/html");
    contentTypeMap.put(".txt", "text/html");
    contentTypeMap.put("/", "text/html");
    contentTypeMap.put(".jpg", "image/jpeg");
    contentTypeMap.put(".jpeg", "image/jpeg");
    contentTypeMap.put(".gif", "image/gif");
    contentTypeMap.put(".pdf", "application/pdf");
  }

  public HttpResponse generateResponse(HttpRequest request) {
    String path = request.path();
    if (path.equals("/")) {
      return defaultResponse();
    }
    byte[] body;
    try {
      body = fileIO.getContent(path);
    }
    catch (IOException e) {
      return notFound(request);
    }
    HashMap<String, String> headers = buildHeaders(body, path);
    return new HttpResponse(CODE_200, body, headers);
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
   return contentTypeMap.getOrDefault(getFileExtension(fileName), "application/force-download");
  }

  private String getFileExtension(String fileName) {
    int extensionPosition = fileName.lastIndexOf('.');
    if (extensionPosition == -1) {
      return "/";
    }
    return fileName.substring(extensionPosition);
  }
}
