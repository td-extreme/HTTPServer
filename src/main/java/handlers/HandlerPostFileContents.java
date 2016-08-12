package com.td.HttpServer;
import java.io.IOException;

public class HandlerPostFileContents implements IHandler {

  private IFileIO fileIO;

  public HandlerPostFileContents(IFileIO fileIO) {
    this.fileIO = fileIO;
  }

  public HttpResponse generateResponse(HttpRequest request) {
    HttpResponse rtnResponse = new HttpResponse();
    try {
      String file = getFileToWrite(request.path());
      fileIO.writeContent(file, request.body());
      rtnResponse.setResponseCode(200);
      rtnResponse.addHeader("Location", request.path());
    }
    catch (IOException e) {
      e.printStackTrace();
      rtnResponse.setBody("Internal Server Error", ContentType.text);
      rtnResponse.setResponseCode(500);
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
