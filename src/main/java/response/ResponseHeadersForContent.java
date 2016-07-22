package com.td.HttpServer;

import java.util.*;

public class ResponseHeadersForContent {

  private HashMap<String, String> contentTypeMap;

  public ResponseHeadersForContent() {
    buildContentTypeMap();
  }

  public HashMap<String, String> getHeaders (byte[] body, String path) {
    HashMap<String, String> rtnHeaders = new HashMap<String, String>();
    rtnHeaders.put("Content-Type", contentType(path));
    return rtnHeaders;
  }

  private void buildContentTypeMap() {
    contentTypeMap = new HashMap<String, String>();
    contentTypeMap.put(".htm", "text/html");
    contentTypeMap.put(".html", "text/html");
    contentTypeMap.put(".txt", "text/plain");
    contentTypeMap.put(".jpg", "image/jpeg");
    contentTypeMap.put(".jpeg", "image/jpeg");
    contentTypeMap.put(".gif", "image/gif");
    contentTypeMap.put(".pdf", "application/pdf");
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
