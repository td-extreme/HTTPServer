import com.td.HttpServer.HttpRequestParser;
import com.td.HttpServer.HttpVerifier;
import com.td.HttpServer.InvalidHttpRequestException;
import com.td.HttpServer.HttpRequest;
import java.util.HashMap;

public class HttpRequestParserTest extends junit.framework.TestCase {

  private HttpVerifier verifier;
  private HttpRequestParser parser;
  String rawRequest;
  HttpRequest request;

  protected void setUp() throws InvalidHttpRequestException {
    verifier = new HttpVerifier();
    parser = new HttpRequestParser(verifier);
    rawRequest = "GET / HTTP/1.1\nHeader: value\nHeader2: value2\n\n";
    request = parser.parseRequest(rawRequest);
  }

  public void testParsingRequestLine() {
    String testValue = request.requestLine();
    assertEquals(testValue, "GET / HTTP/1.1");
  }
}
