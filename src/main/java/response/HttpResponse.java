package com.td.HttpServer;

import java.util.*;
import java.io.*;

public class HttpResponse {

  private int responseCode;
  private byte[] body;
  private HashMap<String, String> headers;
  private final byte[] NEWLINE = "\r\n".getBytes();
  private HashMap<Integer, String> responseLines;

  public HttpResponse() {
    buildResponseLineMap();
    this.responseCode = 200;
    this.body = NEWLINE;
    this.headers = new HashMap<String, String>();
  }

  public void setCode(int code) {
    this.responseCode = code;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  public void setBody(String body) {
    this.body = body.getBytes();
  }

  public void addHeader(String key, String value) {
    headers.put(key, value);
  }

  public void addHeaders(HashMap<String, String> headers) {
    this.headers.putAll(headers);
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

  public String responseLine() {
    return responseLines.get(responseCode);
  }

  public byte[] body() {
    return body;
  }

  public String headers() {
    StringBuilder builder = new StringBuilder();
    headers.forEach((key, value)-> builder.append(key + ": " + value + "\r\n"));
    return builder.toString();
  }

  private void buildResponseLineMap() {
    responseLines = new HashMap<Integer, String>();
    responseLines.put(200, "HTTP/1.1 200 OK");
    responseLines.put(400, "HTTP/1.1 400 Bad Request");
    responseLines.put(404, "HTTP/1.1 404 Not Found");
  }
}
