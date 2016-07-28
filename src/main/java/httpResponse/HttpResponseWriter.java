package com.td.HttpServer;
import java.util.HashMap;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.lang.StringBuilder;

public class HttpResponseWriter implements IResponseWriter {

  private final byte[] NEWLINE = "\r\n".getBytes();
  private HashMap<Integer, String> responseLines;

  public HttpResponseWriter() {
    buildResponseLineMap();
  }

  public void sendHttpResponse(IClientSocketOutput client, HttpResponse response) throws BadConnectionException {
    byte[] responseBytes = responseAsBytes(response);
    try {
      client.sendBytes(responseBytes);
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new BadConnectionException(e);
    }
  }

  private byte[] responseAsBytes(HttpResponse response) {
    ByteArrayOutputStream rtnStream = new ByteArrayOutputStream();
    writeToByteArrayOS(rtnStream, responseLine(response.responseCode()));
    writeToByteArrayOS(rtnStream, NEWLINE);
    writeToByteArrayOS(rtnStream, headers(response.headers()));
    writeToByteArrayOS(rtnStream, NEWLINE);
    writeToByteArrayOS(rtnStream, response.body());
    return rtnStream.toByteArray();
  }

  private void writeToByteArrayOS(ByteArrayOutputStream os, String toWrite) {
    byte[] bytesToWrite = toWrite.getBytes();
    writeToByteArrayOS(os, bytesToWrite);
  }

  private void writeToByteArrayOS(ByteArrayOutputStream os, byte[] bytesToWrite) {
    os.write(bytesToWrite, 0, bytesToWrite.length);
  }

  private String responseLine(int responseCode) {
    StringBuilder returnString = new StringBuilder();
    returnString.append("HTTP/1.1");
    returnString.append(" ");
    returnString.append(responseCode);
    returnString.append(" ");
    returnString.append(responseLines.getOrDefault(responseCode, ""));
    return returnString.toString();
  }

  private String headers(HashMap<String, String> headers) {
    StringBuilder builder = new StringBuilder();
    headers.forEach((key, value)-> builder.append(key + ": " + value + "\r\n"));
    return builder.toString();
  }

  private void buildResponseLineMap() {
    responseLines = new HashMap<Integer, String>();
    responseLines.put(100, "Continue");
    responseLines.put(101, "Switching Protocols");
    responseLines.put(102, "Processing");

    responseLines.put(200, "OK");
    responseLines.put(201, "Created");
    responseLines.put(202, "Accepted");
    responseLines.put(203, "Non-Authoritative Information");
    responseLines.put(204, "No Content");
    responseLines.put(205, "Reset Content");
    responseLines.put(206, "Partial Content");
    responseLines.put(207, "Multi-Status");
    responseLines.put(208, "Already Reported");

    responseLines.put(300, "Multiple Choices");
    responseLines.put(301, "Moved Permanently");
    responseLines.put(302, "Found");
    responseLines.put(303, "See Other");
    responseLines.put(304, "Not Modified");
    responseLines.put(305, "Use Proxy");
    responseLines.put(306, "Switch Proxy");
    responseLines.put(307, "Temporary Redirect");
    responseLines.put(308, "Permanent Redirect");

    responseLines.put(400, "Bad Request");
    responseLines.put(401, "Unauthorized");
    responseLines.put(402, "Payment Required");
    responseLines.put(403, "Forbidden");
    responseLines.put(404, "Not Found");
    responseLines.put(405, "Method Not Allowed");
    responseLines.put(406, "Not Acceptable");
    responseLines.put(407, "Proxy Authentication Required");
    responseLines.put(408, "Request Timeout");
    responseLines.put(409, "Conflict");
    responseLines.put(410, "Gone");


    responseLines.put(411, "Length Required");
    responseLines.put(412, "Precondition Failed");
    responseLines.put(413, "Payload Too Large");
    responseLines.put(414, "URI Too Long");
    responseLines.put(415, "Unsupported Media Type");
    responseLines.put(416, "Range Not Satisfiable");
    responseLines.put(417, "Expectation Failed");
    responseLines.put(418, "I'm a teapot");


    responseLines.put(421, "Misdirected Request");
    responseLines.put(422, "Unprocessable Entity");
    responseLines.put(423, "Locked");
    responseLines.put(424, "Failed Dependency");
    responseLines.put(426, "Upgrade Required");
    responseLines.put(428, "Precondition Required");
    responseLines.put(429, "Too Many Requests");

    responseLines.put(431, "Request Header Fields Too Large");
    responseLines.put(451, "Unavailable For Legal Reasons");


    responseLines.put(500, "Internal Server Error");
    responseLines.put(501, "Not Implemented");
    responseLines.put(502, "Bad Gateway");
    responseLines.put(503, "Service Unavailable");
    responseLines.put(504, "Gateway Timeout");
    responseLines.put(505, "HttpVersion Not Supported");
    responseLines.put(506, "Variant Also Negotiates");
    responseLines.put(507, "Insufficient Storage");
    responseLines.put(508, "Loop Detected");
    responseLines.put(510, "Not Extended");
    responseLines.put(511, "Network Authentication Required");
  }
}
