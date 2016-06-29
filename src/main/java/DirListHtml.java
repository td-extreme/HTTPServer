package com.td.HttpServer;

import java.lang.*;
import java.util.*;

public class DirListHtml {

  public byte[] buildHtmlPage(String path, String[] dirContents) {
    return (htmlHeader() + linksToFiles(path, dirContents) + htmlFooter()).getBytes();
  }

  private String linksToFiles(String path, String[] dirContents) {
    StringBuilder builder = new StringBuilder();
    for (String file: dirContents) {
      builder.append(htmlLink(path, file));
    }
    return builder.toString();
  }

  private String htmlLink(String path, String file) {
    String pathSlash = "";
    if (!path.endsWith("/")) {
      pathSlash = "/";
    }
    return "<a href=\"" + path + pathSlash + file + "\">" + file + "</a><br>";
  }

  private String htmlHeader() {
    return "<!DOCTYPE html><html><body>";
  }

  private String htmlFooter() {
    return "</body></html>";
  }
}
