package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketInput implements IClientSocketInput {

  private Socket clientSocket;
  private InputStream inputStream;
  private Scanner scanner;

  public ClientSocketInput(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    this.inputStream = clientSocket.getInputStream();
    this.scanner = new Scanner(inputStream, "UTF8");
  }

  public String getRawRequestString() throws IOException {
    StringBuilder request = new StringBuilder();
    String line = scanner.nextLine();
    while (!line.isEmpty()) {
      request.append(line);
      request.append("\n");
      line = scanner.nextLine();
    }
    return request.toString();
  }

  public byte[] getBytes(int length) throws IOException {
    scanner.useDelimiter("");
    String data = "";
    for (int x = 0; x < length; x++) {
      data += scanner.next();
    }
    return data.getBytes();
  }
}
