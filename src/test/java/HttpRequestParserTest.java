import com.td.HttpServer.HttpRequestParser;
import com.td.HttpServer.HttpVerifier;
import com.td.HttpServer.InvalidHttpRequestException;
import java.util.HashMap;

public class HttpRequestParserTest extends junit.framework.TestCase {

  private HttpVerifier verifier;
  private HttpRequestParser parser;
  String rawRequest;

  protected void setUp() {
    verifier = new HttpVerifier();
    parser = new HttpRequestParser(verifier);
    rawRequest = "GET / HTTP/1.1\nHeader: value\nHeader2: value2\n\n";
  }

  public void testParsingRequestLine() {
    String testValue;
    try {
      testValue = parser.parseRequestLine(rawRequest);
    }
    catch (InvalidHttpRequestException e)
    {
      testValue = "Unexpected InvalidRequestException";
    }
    assertEquals(testValue, "GET / HTTP/1.1");
  }

  public void testParsingHeaders() {
    HashMap<String, String> map = new HashMap<String, String>();
    try {
      map = parser.parseHeaders(rawRequest);
    }
    catch (InvalidHttpRequestException e) {}
    assertEquals(2, map.size());
  }
}
