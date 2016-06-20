import com.td.HttpServer.*;

public class HttpHandlerSelectorTest extends junit.framework.TestCase {

  HttpHandlerSelector handler;
  HttpRequest request;
  HttpResponse response;
  HttpRequestBuilder builder;

  protected void setUp() {
    handler = new HttpHandlerSelector();
    String rawStrRequest = "GET / HTTP/1.1";
    builder = new HttpRequestBuilder();
    request = builder.createRequest(rawStrRequest);
  }

  public void testGenerateOkResponse() {
    response = handler.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.toString());
  }
}
