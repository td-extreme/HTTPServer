import com.td.HttpServer.*;
import java.util.*;
import java.io.IOException;

public class HandleGetRequestTest extends junit.framework.TestCase {

  HandleGetRequest getRequest;
  HttpRequest request;
  HttpResponse response;
  DirListHtml dirListHtml;

  public class MockFileIO implements IFileIO {
    public String workingDirectory() { return "./"; }
    public boolean exists(String path) { return true; }
    public boolean isPathFile(String path) {
      if (path.equals("/")) {
        return false;
      } else {
        return true;
      }
    }

    public byte[] getContent(String fileName) throws IOException {
      if (fileName.equals("/throwIOException")) { throw new IOException(); }
      return new byte[0];
    }

    public String[] getFiles(String directory) throws IOException {
      if (directory.equals("/throwIOException")) { throw new IOException(); }
      return new String[] { "file01", "file02" };
    }
  }

  protected void setUp() {
    MockFileIO mockFileIO = new MockFileIO();
    DirListHtml dirListHtml = new DirListHtml();
    getRequest = new HandleGetRequest(mockFileIO, dirListHtml);
  }

  public void testRequestForDirectoryReturnsDirListHtmlPage() {
    request = new HttpRequest("GET / HTTP/1.1");
    response = getRequest.generateResponse(request);
    String body = new String(response.body());
    assert(body.contains("file01"));
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
