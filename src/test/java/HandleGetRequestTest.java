import com.td.HttpServer.*;
import java.util.*;
import java.io.IOException;

public class HandleGetRequestTest extends junit.framework.TestCase {

  HandleGetRequest getRequest;
  HttpRequest request;
  HttpResponse response;

  public class MockFileIO implements IFileIO {
    public String workingDirectory() { return "./"; }
    public byte[] getContent(String fileName) throws IOException {
      if (fileName.equals("/throwIOException")) { throw new IOException(); }
      return new byte[1];
    }
  }

  protected void setUp() {
    MockFileIO mockFileIO = new MockFileIO();
    getRequest = new HandleGetRequest(mockFileIO);
  }

  public void testGenerateOkResponseDefaultPath() {
    request = new HttpRequest("GET / HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.responseLine());
  }

  public void testGenerateOkResponseValidFile() {
    request = new HttpRequest("GET /something.txt HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertEquals("HTTP/1.1 200 OK", response.responseLine());
  }

  public void testGenerateNotFoundResponseforInvalidFile() {
    request = new HttpRequest("GET /throwIOException HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertEquals("HTTP/1.1 404 Not Found", response.responseLine());
  }

  public void testContentTypeIsTextHtmlForTxtFile() {
    request = new HttpRequest("GET /something.txt HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertTrue(response.headers().contains("text/html"));
  }

  public void testContentTypeIsImageJpegForJpg() {
    request = new HttpRequest("GET /something.jpg HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertTrue(response.headers().contains("image/jpeg"));
  }

  public void testContentTypeIsImageGifForGif() {
    request = new HttpRequest("GET /something.gif HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertTrue(response.headers().contains("image/gif"));
  }

  public void testContentTypeIsPdfForPdf() {
    request = new HttpRequest("GET /something.pdf HTTP/1.1");
    response = getRequest.generateResponse(request);
    assertTrue(response.headers().contains("application/pdf"));
  }
}
