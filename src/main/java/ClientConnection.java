package com.td.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ClientConnection {

  ServerSocket server;
  Socket clientSocket;

  public ClientConnection(int portNumber) throws IOException {
    server = new ServerSocket(portNumber);
  }

  public String getRequest() throws IOException {
    StringBuilder request = new StringBuilder();
    clientSocket = server.accept();
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

  public void sendResponse(String response) throws IOException {
    clientSocket.getOutputStream().write(response.getBytes("UTF-8"));
    clientSocket.close();
  }
}
