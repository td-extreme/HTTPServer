package com.td.HttpServer;

import java.lang.*;
import java.util.*;

public class DirListHtml {

  byte[] buildHtmlPage(String path, ArrayList<String> dirContents) {
    StringBuilder builder = new StringBuilder();
    builder.append("<!DOCTYPE html>\n<html>\n<body>\n");
    for (String file: dirContents) {
      builder.append("<a href=\"");
      builder.append(path);
      if ( !path.endsWith("/") ) {
        builder.append("/");
      }
      builder.append(file);
      builder.append("\">");
      builder.append(file);
      builder.append("</a><br>");
    }
    builder.append("</body>\n</html>");
    return builder.toString().getBytes();
  }
}
