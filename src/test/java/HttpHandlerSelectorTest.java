import com.td.HttpServer.*;

public class HttpHandlerSelectorTest extends junit.framework.TestCase {

  HttpHandlerSelector handler;
  HttpRequest request;
  HttpResponse response;

  protected void setUp() {
    handler = new HttpHandlerSelector();
    String strArray[] = { "GET", "/", "HTTP/1.1" };
    request = new HttpRequest(strArray);
  }

  public void testGenerateOkResponse() {
    response = handler.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.toString());
  }
}
