package com.td.HttpServer;

import java.util.HashMap;

public class ContentTypeForFileExtension {

  private HashMap<String, String> contentTypeMap;

  public ContentTypeForFileExtension() {
    buildContentTypeMap();
  }
  public String getContentType(String fileName) {
    return contentTypeMap.getOrDefault(getFileExtension(fileName), ContentType.download);
  }

  private void buildContentTypeMap() {
    contentTypeMap = new HashMap<String, String>();
    contentTypeMap.put(".htm", ContentType.html);
    contentTypeMap.put(".html", ContentType.html);
    contentTypeMap.put(".txt", ContentType.text);
    contentTypeMap.put(".jpg", ContentType.jpg);
    contentTypeMap.put(".jpeg", ContentType.jpg);
    contentTypeMap.put(".gif", ContentType.gif);
    contentTypeMap.put(".pdf", ContentType.pdf);
  }

  private String getFileExtension(String fileName) {
    int extensionPosition = fileName.lastIndexOf('.');
    if (extensionPosition == -1) {
      return "/";
    }
    return fileName.substring(extensionPosition);
  }
}
