package com.td.HttpServer;

import java.util.*;
import java.io.*;

public class HttpResponse {

  private String responseLine;
  private byte[] body;
  private HashMap<String, String> headers;
  private final byte[] NEWLINE = "\r\n".getBytes();

  public HttpResponse(String responseLine, byte[] body, HashMap<String, String> headers) {
    this.responseLine = responseLine;
    this.body = body;
    this.headers = headers;
  }

  public byte[] responseBytes() {
    ByteArrayOutputStream rtnStream = new ByteArrayOutputStream();
    writeToByteArrayOS(rtnStream, responseLine());
    writeToByteArrayOS(rtnStream, NEWLINE);
    writeToByteArrayOS(rtnStream, headers());
    writeToByteArrayOS(rtnStream, NEWLINE);
    writeToByteArrayOS(rtnStream, body());
    return rtnStream.toByteArray();
  }

  private void writeToByteArrayOS(ByteArrayOutputStream os, String toWrite) {
    byte[] bytesToWrite = toWrite.getBytes();
    writeToByteArrayOS(os, bytesToWrite);
  }

  private void writeToByteArrayOS(ByteArrayOutputStream os, byte[] bytesToWrite) {
    os.write(bytesToWrite, 0, bytesToWrite.length);
  }

  public byte[] body() {
    return body;
  }

  public String responseLine() {
    return responseLine;
  }

  public String headers() {
    StringBuilder builder = new StringBuilder();
    headers.forEach((key, value)-> builder.append(key + ": " + value + "\r\n"));
    return builder.toString();
  }
}
