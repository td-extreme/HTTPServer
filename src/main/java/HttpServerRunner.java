package com.td.HttpServer;
import java.io.IOException;
import java.net.ServerSocket;

public class HttpServerRunner {
  private ServerSocket serverSocket;
  private HandlerRouter handlerRouter;
  private IRequestBuilder httpRequestBuilder;
  private HttpResponseWriter httpResponseWriter;

  public HttpServerRunner(ServerSocket serverSocket, HandlerRouter handlerRouter, IRequestBuilder httpRequestBuilder, HttpResponseWriter httpResponseWriter) {
    this.serverSocket = serverSocket;
    this.handlerRouter = handlerRouter; 
    this.httpRequestBuilder = httpRequestBuilder;
    this.httpResponseWriter = httpResponseWriter;
  }

  public void run() {
    while (true) {
      ClientSocketIO client = new ClientSocketIO(serverSocket);
      try {
        client.openClientConnection();
      }
      catch (BadConnectionException e) {
        e.printStackTrace();
        continue;
      }
      HttpServerThread httpServerThread = new HttpServerThread(client, handlerRouter, httpRequestBuilder, httpResponseWriter);
      Thread thread = new Thread(httpServerThread);
      thread.start();
    }
  }
}
