import com.td.HttpServer.HttpRequestParser;
import com.td.HttpServer.HttpVerifier;
import com.td.HttpServer.InvalidHttpRequestException;
import com.td.HttpServer.HttpRequest;
import java.util.HashMap;

public class HttpRequestParserTest extends junit.framework.TestCase {

  private HttpRequestParser parser;
  private String rawRequest;
  private HttpRequest request;

  protected void setUp() throws InvalidHttpRequestException {
    parser = new HttpRequestParser();
    rawRequest = "GET / HTTP/1.1\nHeader: value\nHeader2: value2\n\n";
    request = parser.parseRequest(rawRequest);
  }

  public void testParsingRequestLine() {
    String testValue = request.requestLine();
    assertEquals(testValue, "GET / HTTP/1.1");
  }
}
