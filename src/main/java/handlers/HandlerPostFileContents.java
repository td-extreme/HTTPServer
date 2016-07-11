package com.td.HttpServer;
import java.io.IOException;
public class HandlerPostFileContents implements Ihandler {

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
      rtnResponse.setBody("IOException");
      rtnResponse.setResponseCode(404);
    }
    return rtnResponse;
  }

  private String getFileToWrite(String path) throws IOException {
    if (!fileIO.exists(path)) {
      return path;
    }
    int duplicateFileIndex = 1;
    String rtnFile;
    do {
      rtnFile = createDuplicateFile(path, duplicateFileIndex);
      ++duplicateFileIndex;
    } while (fileIO.exists(rtnFile));
    return rtnFile;
  }

  private String createDuplicateFile(String path, int duplicateFileIndex) {
    int extensionPosition = path.lastIndexOf('.');
    String fileName;
    String fileExtention;
    if (extensionPosition == -1) {
      fileName = path;
      fileExtention = "";
    } else {
      fileName = path.substring(0, extensionPosition);
      fileExtention = path.substring(extensionPosition);
    }
    return fileName + "(" + duplicateFileIndex + ")" + fileExtention;
  }
}
