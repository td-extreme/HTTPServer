package com.td.HttpServer;
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

  public HttpResponse generateResponse() {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      String file = getFileToWrite(path);
      fileIO.writeContent(file, body);
      rtnResponse.setResponseCode(201);
      rtnResponse.addHeader("Location", path);
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("IOException", ContentType.text);
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
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
