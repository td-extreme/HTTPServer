package com.td.HttpServer;

import java.util.HashMap;

public class ContentTypeForFileExtension {

  private HashMap<String, String> contentTypeMap;

  public ContentTypeForFileExtension() {
    buildContentTypeMap();
  }
  public String getContentType(String fileName) {
    return contentTypeMap.getOrDefault(getFileExtension(fileName), "application/force-download");
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

  private String getFileExtension(String fileName) {
    int extensionPosition = fileName.lastIndexOf('.');
    if (extensionPosition == -1) {
      return "/";
    }
    return fileName.substring(extensionPosition);
  }
}
