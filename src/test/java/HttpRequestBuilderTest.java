import com.td.Mocks.MockClientSocketIO;
import com.td.HttpServer.HttpRequestBuilder;
import com.td.HttpServer.HttpRequestParser;
import com.td.HttpServer.HttpVerifier;
import com.td.HttpServer.HttpRequest;
import com.td.HttpServer.InvalidHttpRequestException;
import java.io.IOException;

public class HttpRequestBuilderTest extends junit.framework.TestCase {

  HttpRequestParser parser;
  MockClientSocketIO mockSocketIO;
  HttpRequestBuilder builder;

  protected void setUp() {
    parser = new HttpRequestParser(new HttpVerifier());
  }

  public void testThatRequestLineGetsSet() throws InvalidHttpRequestException, IOException {
    mockSocketIO = new MockClientSocketIO("GET / HTTP/1.1\n\n", new byte[0]);
    builder = new HttpRequestBuilder(parser);
    HttpRequest request = builder.getNextRequest(mockSocketIO);
    assertEquals(request.requestLine(), "GET / HTTP/1.1");
  }

  public void testThatBodyGetsSetWhenContentLengthIsSupplied() throws InvalidHttpRequestException, IOException {
    mockSocketIO = new MockClientSocketIO("POST /this.txt HTTP/1.1\nContent-Length: 5\n\n", "12321".getBytes());
    builder = new HttpRequestBuilder(parser);
    HttpRequest request = builder.getNextRequest(mockSocketIO);
    String body = new String(request.body(), "UTF-8");
    assertEquals(body, "12321");
  }
}
