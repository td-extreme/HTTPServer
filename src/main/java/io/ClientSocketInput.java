package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;

public class ClientSocketInput implements IClientSocketInput {

  private Socket clientSocket;

  public ClientSocketInput(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  public String getRawRequestString() throws IOException {
    StringBuilder request = new StringBuilder();
    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
    BufferedReader reader = new BufferedReader(isr);
    String line = reader.readLine();
    while (!line.isEmpty()) {
      request.append(line);
      request.append("\n");
      line = reader.readLine();
    }
    return request.toString();
  }

  public byte[] getBytes(int length) throws IOException {
    InputStream stream = clientSocket.getInputStream();
    byte[] data = new byte[length];
    stream.read(data);
    return data;
  }
}
