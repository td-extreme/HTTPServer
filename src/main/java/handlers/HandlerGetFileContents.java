package com.td.HttpServer;

import java.util.HashMap;
import java.io.IOException;

public class HandlerGetFileContents implements IHandler {
  private ContentTypeForFileExtension contentTypeForFileExtension;
  private IFileIO fileIO;
  private String path;

  public HandlerGetFileContents(String path, IFileIO fileIO) {
    this.contentTypeForFileExtension = new ContentTypeForFileExtension();
    this.path = path;
    this.fileIO = fileIO;
  }

  public Object[] call() {
    Object[] returnArray = new Object[3];
    HashMap<String, String> headers = new HashMap<String, String>();
    try {
      returnArray[0] = 200;
      returnArray[2] = fileIO.getContent(path);
      headers.put("Content-Type", contentTypeForFileExtension.getContentType(path));
    }
    catch (IOException e) {
      e.printStackTrace();
      returnArray[0] = 404;
      returnArray[2] = "IOException".getBytes();
      headers.put("Content-Type", ContentType.text);
    }
    returnArray[1] = headers;
    return returnArray;
  }
}
