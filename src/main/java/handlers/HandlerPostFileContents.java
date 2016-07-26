package com.td.HttpServer;

import java.util.HashMap;
import java.io.IOException;

public class HandlerPostFileContents implements IHandler {

  private String path;
  private byte[] body;
  private IFileIO fileIO;

  public HandlerPostFileContents(String path, byte[] body, IFileIO fileIO) {
    this.path = path;
    this.body = body;
    this.fileIO = fileIO;
  }

  public Object[] call() {
    HashMap<String, String> headers = new HashMap<String, String>();
    Object[] returnArray = new Object[3];
    try {
      String file = getFileToWrite(path);
      fileIO.writeContent(file, body);
      headers.put("Content-Type", ContentType.text);
      headers.put("Location", path);
      returnArray[0] = 201;
      returnArray[2] = new byte[0];
    } catch (IOException e) {
      e.printStackTrace();
      returnArray[0] = 404;
      returnArray[2] = "IOException".getBytes();
      headers.put("Content-Tyep", ContentType.text);
    }
    returnArray[1] = headers;
    return returnArray;
  }

  private String getFileToWrite(String path) throws IOException {
    int duplicateFileIndex = 1;
    String fileName = path;
    while(fileIO.exists(fileName)) {
      fileName = generateDuplicateFileName(path, duplicateFileIndex);
      ++duplicateFileIndex;
    }
    return fileName;
  }

  private String generateDuplicateFileName(String path, int duplicateFileIndex) {
    int extentionPosition = path.lastIndexOf('.');
    String fileName;
    String fileExtention;
    if (extentionPosition == -1) {
      fileName = path;
      fileExtention = "";
    } else {
      fileName = path.substring(0, extentionPosition);
      fileExtention = path.substring(extentionPosition);
    }
    return fileName + "-" + duplicateFileIndex + fileExtention;
  }
}
