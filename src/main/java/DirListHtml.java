package com.td.HttpServer;

import java.lang.*;
import java.util.*;

public class DirListHtml {

  public byte[] buildHtmlPage(String path, String[] dirContents) {
    String rtnString = htmlHeader() + linksToFiles(path, dirContents) + htmlFooter();
    return rtnString.getBytes();
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
    String rtnString = "<a href=\"" + path + pathSlash + file + "\">" + file + "</a><br>";
    return rtnString;
  }

  private String htmlHeader() {
    return "<!DOCTYPE html>\n\t<html>\n\t<body>\n";
  }

  private String htmlFooter() {
    return "\n</body>\n</html>";
  }
}
