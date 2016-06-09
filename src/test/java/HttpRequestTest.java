import com.td.HttpServer.*;

public class HttpRequestTest extends junit.framework.TestCase {

  HttpRequest request;

  protected void setUp() {
    request = new HttpRequest("GET / HTTP/1.1\nMore Stuff");
  }

  public void testMethodIsSetToGet() {
    assertEquals("GET", request.method());
  }

  public void testPathIsSetToFowardSlash() {
    assertEquals("/", request.path());
  }

  public void testVersionIsHTTP1point1() {
    assertEquals("HTTP/1.1", request.version());
  }

  public void testRequestLine() {
    assertEquals("GET / HTTP/1.1", request.requestLine());
  }
}
