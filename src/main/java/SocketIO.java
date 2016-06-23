package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketIO implements IMessageIO {

  ServerSocket server;
  Socket clientSocket;

  public SocketIO(int portNumber) throws IOException {
    server = new ServerSocket(portNumber);
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
