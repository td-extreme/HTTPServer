package com.td.HttpServer;

import java.util.*;

public class ResponseHeaders {

  private HashMap<String, String> contentTypeMap;

  public ResponseHeaders() {
    buildContentTypeMap();
  }

  public HashMap<String, String> buildHeadersFromGetRequest(byte[] body, String path) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    rtnMap.putAll(contentHeaders(body, path));
    return rtnMap;
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
