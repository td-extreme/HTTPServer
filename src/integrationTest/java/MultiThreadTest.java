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
  HttpResponseWriter httpResponseWriter;

  protected void setUp() {
    fileIO = new MockFileIO();
    handlerRouter = new HandlerRouter(fileIO);
    httpRequestParser = new HttpRequestParser();
    httpRequestBuilder = new HttpRequestBuilder(httpRequestParser);
    httpResponseWriter = new HttpResponseWriter();

  }

  public void testThreadWithNoDelayFinishedBeforeThreadWithLongDelay() throws InterruptedException {
    MockClientSocket clientLongDelay = new MockClientSocket(1000);
    MockClientSocket clientNoDelay = new MockClientSocket(0);

    HttpServerThread serverThreadNoDealy = new HttpServerThread(clientNoDelay, handlerRouter, httpRequestBuilder, httpResponseWriter);
    HttpServerThread serverThreadLongDelay = new HttpServerThread(clientLongDelay, handlerRouter, httpRequestBuilder, httpResponseWriter);
    Thread threadNoDealy = new Thread(serverThreadNoDealy);
    Thread threadLongDelay = new Thread(serverThreadNoDealy);
    threadLongDelay.start();
    threadNoDealy.start();
    Thread.sleep(1500);
    assert(clientNoDelay.getClosedTime() > clientLongDelay.getClosedTime());
  }
}
