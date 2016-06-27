package com.td.HttpServer;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class HttpHandleRequest {

  private HashMap<String, String> standardHeaders;

  protected final String CODE_200 = "HTTP/1.1 200 OK";
  protected final String CODE_404 = "HTTP/1.1 404 Not Found";
  protected final String CODE_400 = "HTTP/1.1 400 Bad Request";

  public abstract HttpResponse generateResponse(HttpRequest request);

  public HttpHandleRequest() {
    standardHeaders = new HashMap<String, String>();
    standardHeaders.put("Server", "Simple Http Server written in Java");
  }

  protected HashMap<String, String> standardHeaders () {
    HashMap<String, String> rtnHeaders = standardHeaders;
    rtnHeaders.put("Date", getDateTime());
    return rtnHeaders;
  }

  protected String getDateTime() {
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String strDate = dateFormat.format(date);
    return strDate;
  }
}
