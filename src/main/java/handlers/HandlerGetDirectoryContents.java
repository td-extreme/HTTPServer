package com.td.HttpServer;
import java.util.HashMap;
import java.io.IOException;

public class HandlerGetDirectoryContents implements IHandler {
  private IFileIO fileIO;
  private DirListHtml dirListHtml;
  private String path;

  public HandlerGetDirectoryContents(String path, IFileIO fileIO, DirListHtml dirListHtml) {
    this.path = path;
    this.fileIO = fileIO;
    this.dirListHtml = dirListHtml;
  }

  public Object[] call() {
    Object[] returnArray = new Object[3];
    HashMap<String, String> headers = new HashMap<String, String>();
    try {
      String[] fileList = fileIO.getFiles(path);
      returnArray[0] = 200;
      returnArray[2] = dirListHtml.buildHtmlPage(path, fileList);
      headers.put("Content-Type", ContentType.html);
    } catch (IOException e) {
      returnArray[0] = 404;
      returnArray[2] = "IOExceptoin".getBytes();
      headers.put("Content-Type", ContentType.text);
    }
    returnArray[1] = headers;
    return returnArray;
  }
}
