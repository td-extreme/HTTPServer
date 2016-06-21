import com.td.HttpServer.*;

public class HttpHandlerSelectorTest extends junit.framework.TestCase {

  HttpHandlerSelector handler;
  HttpRequest request;
  HttpResponse response;
  HttpRequestBuilder builder;

  protected void setUp() {
    handler = new HttpHandlerSelector();
    String rawStrRequest = "GET / HTTP/1.1";
    builder = new HttpRequestBuilder(new HttpVerifier());
    try {
      request = builder.createRequest(rawStrRequest);
    }
    catch (InvalidHttpRequestException e) {

    }
  }

  public void testGenerateOkResponse() {
    response = handler.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.toString());
  }
}
