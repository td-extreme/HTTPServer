package com.td.HttpServer;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;

// TODO This class is too big, and has multiple responsibilities.
//      It needs to be broken up into multiple classs.
//      Two classes that could be pulled out of this are
//         HttpResponseBuilder & HeaderBuilder

public class HttpHandlerSelector {

  IFileIO fileIO;
  HashMap<String, String> standardHeaders;

  private final String CODE_200 = "HTTP/1.1 200 OK";
  private final String CODE_404 = "HTTP/1.1 404 Not Found";
  private final String CODE_400 = "HTTP/1.1 400 Bad Request";

  private final String HTML = ".html";

  public HttpHandlerSelector(IFileIO fileIO) {
    this.fileIO = fileIO;
    standardHeaders = new HashMap<String, String>();
    standardHeaders.put("Server", "Simple Http Server written in Java");
  }

  public HttpResponse generateResponse(HttpRequest request) {
    if (request.method().equals("GET")) {
      return getRequest(request);
    }
    return notFound(request);
  }

  public HttpResponse generate400Response() {
    byte[] body = "400 : Bad Request".getBytes();
    return new HttpResponse(CODE_400, body, buildHeaders(body, HTML));
  }

  private HttpResponse getRequest(HttpRequest request) {
    String path = request.path();
    if (path.equals("/")) {
      return defaultResponse();
    }
    try {
      byte[] body = fileIO.getContent(path);
      HashMap<String, String> headers = buildHeaders(body, path);
      return new HttpResponse(CODE_200, body, headers);
    }
    catch (IOException e) {
      return notFound(request);
    }
  }

  private HttpResponse defaultResponse() {
    byte[] body = "Default path / ".getBytes();
    HashMap<String, String> headers = buildHeaders(body, "/");
    return new HttpResponse(CODE_200, body, headers);
  }

  private HttpResponse notFound(HttpRequest request) {
    byte[] body = "File not Found".getBytes();
    HashMap<String, String> headers = buildHeaders(body, request.path());
    return new HttpResponse(CODE_404, body, headers);
  }

  private HashMap<String, String> buildHeaders(byte[] body, String path) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    rtnMap.putAll(standardHeaders);
    rtnMap.put("Date", getDateTime());
    rtnMap.putAll(contentHeaders(body, path));
    return rtnMap;
  }

  private String getDateTime() {
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String strDate = dateFormat.format(date);
    return strDate;
  }

  private HashMap<String, String> contentHeaders(byte[] body, String path) {
    HashMap<String, String> rtnMap = new HashMap<String, String>();
    rtnMap.put("Content-Type", contentType(path));
    rtnMap.put("Content-Length", contentLength(body));
    return rtnMap;
  }

  private String contentDisposition(String fileName) {
    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
      return "inline";
    }
    return " ";
  }
  private String contentLength(byte[] body) {
    return Integer.toString(body.length);
  }

  private String contentType(String fileName) {
    if (fileName.endsWith(".htm") || fileName.endsWith(".html")
        || fileName.endsWith(".txt")) {
      return "text/html";
    } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
      return "image/jpeg";
    } else if (fileName.endsWith(".gif")) {
      return "image/gif";
    } else if (fileName.endsWith(".pdf")) {
      return "application/pdf";
    } else {
      return "test/html";
    }
  }
}
