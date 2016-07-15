package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketIO implements IMessageIO {

  ServerSocket server;
  Socket clientSocket;

  public SocketIO(ServerSocket server) throws IOException {
    this.server = server;
  }

  public String getMessage() throws IOException {
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

  public void sendMessage(String message) throws IOException {
    clientSocket.getOutputStream().write(message.getBytes("UTF-8"));
  }

  public void openClientConnection() throws IOException {
    clientSocket = server.accept();
  }

  public void closeClientConnection() throws IOException {
    clientSocket.close();
  }

  public void sendBytes(byte[] message) throws IOException {
    clientSocket.getOutputStream().write(message);
  }
}
