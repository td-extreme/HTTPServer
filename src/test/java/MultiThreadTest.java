import com.td.HttpServer.*;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.HashMap;
import com.td.Mocks.*;

public class MultiThreadTest extends junit.framework.TestCase {

  MockFileIO fileIO;
  HandlerRouter handlerRouter;
  HttpRequestParser httpRequestParser;
  HttpRequestBuilder httpRequestBuilder;
  HttpResponseBuilder httpResponseBuilder;
  HttpResponseWriter httpResponseWriter;

  protected void setUp() {
    fileIO = new MockFileIO();
    handlerRouter = new HandlerRouter(fileIO);
    httpRequestParser = new HttpRequestParser();
    httpRequestBuilder = new HttpRequestBuilder(httpRequestParser);
    httpResponseBuilder = new HttpResponseBuilder();
    httpResponseWriter = new HttpResponseWriter();

  }

  public void testThreadWithNoDelayFinishedBeforeThreadWithLongDelay() throws InterruptedException {
    MockClientSocket clientLongDelay = new MockClientSocket(1000);
    MockClientSocket clientNoDelay = new MockClientSocket(0);

    HttpConnectionToProcess connectionNoDealy = new HttpConnectionToProcess(clientNoDelay, handlerRouter, httpRequestBuilder, httpResponseBuilder, httpResponseWriter);
    HttpConnectionToProcess connectionLongDelay = new HttpConnectionToProcess(clientLongDelay, handlerRouter, httpRequestBuilder, httpResponseBuilder, httpResponseWriter);
    ConnectionProcessRunnerMultiThread connectionProcessRunnerMutliThread = new ConnectionProcessRunnerMultiThread();
    connectionProcessRunnerMutliThread.execute(connectionLongDelay);
    connectionProcessRunnerMutliThread.execute(connectionNoDealy);
    Thread.sleep(1500);
    assert(clientNoDelay.getClosedTime() < clientLongDelay.getClosedTime());
  }
}
