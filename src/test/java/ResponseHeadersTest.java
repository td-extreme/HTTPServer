import com.td.HttpServer.*;
import java.util.HashMap;

public class ResponseHeadersTest extends junit.framework.TestCase {

  ResponseHeaders responseHeaders;
  byte[] body;

  protected void setUp() {
    responseHeaders = new ResponseHeaders();
    body = "123".getBytes();
  }

  public void testContentLength() {
    String path = ".txt";
    HashMap<String, String> headers = responseHeaders.buildHeadersFromGetRequest(body, path);
    String contentLength = headers.get("Content-Length");
    assertEquals(contentLength, "3");
  }

  public void testContentTypeIsTextHtmlForHtmFile() {
   String contentType = getContentType(".htm");
   assertEquals(contentType, "text/html");
  }

  public void testContentTypeIsTextHtmlForHtmlFile() {
   String contentType = getContentType(".html");
   assertEquals(contentType, "text/html");
  }

  public void testContentTypeIsTextHtmlForTextFile() {
   String contentType = getContentType(".txt");
   assertEquals(contentType, "text/html");
  }

  public void testContentTypeIsTextHtmlForRootPath() {
   String contentType = getContentType("/");
   assertEquals(contentType, "text/html");
  }

  public void testContentTypeIsImageJpegforJpg() {
   String contentType = getContentType(".jpg");
   assertEquals(contentType, "image/jpeg");
  }

  public void testContentTypeIsImageJpegforJpeg() {
   String contentType = getContentType(".jpeg");
   assertEquals(contentType, "image/jpeg");
  }

  public void testContentTypeIsImageGifForGif() {
   String contentType = getContentType(".gif");
   assertEquals(contentType, "image/gif");
  }

  public void testContentTypeIsApplicationPdfForPdf() {
   String contentType = getContentType(".pdf");
   assertEquals(contentType, "application/pdf");
  }

  public void testContentTypeIsApplicationForceDownloadForOther() {
   String contentType = getContentType(".zip");
   assertEquals(contentType, "application/force-download");
  }

  private String getContentType(String path) {
    HashMap<String, String> headers = responseHeaders.buildHeadersFromGetRequest(body, path);
    return headers.get("Content-Type");
  }
}
