package com.td.HttpServer;

import java.util.*;
import java.io.*;

public class HttpResponseFormatter {

  private final byte[] NEWLINE = "\r\n".getBytes();
  private HashMap<Integer, String> responseLines;

  public HttpResponseFormatter() {
    buildResponseLineMap();
  }

  public byte[] responseAsBytes(HttpResponse response) {
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
    return responseLines.get(responseCode);
  }

  private String headers(HashMap<String, String> headers) {
    StringBuilder builder = new StringBuilder();
    headers.forEach((key, value)-> builder.append(key + ": " + value + "\r\n")); 
    return builder.toString();
  }

  private void buildResponseLineMap() {
    responseLines = new HashMap<Integer, String>();
    responseLines.put(200, "HTTP/1.1 200 OK");
    responseLines.put(201, "HTTP/1.1 201 Created");
    responseLines.put(400, "HTTP/1.1 400 Bad Request");
    responseLines.put(404, "HTTP/1.1 404 Not Found");
  }
}
