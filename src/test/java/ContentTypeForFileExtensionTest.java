import com.td.HttpServer.ContentTypeForFileExtension;

public class ContentTypeForFileExtensionTest extends junit.framework.TestCase {

  private ContentTypeForFileExtension contentType;

  protected void setUp() {
    contentType = new ContentTypeForFileExtension();
  }

  public void testContentTypeIsTextHtmlForHtml() {
    assertEquals("text/html", contentType.getContentType("/something.html"));
  }

  public void testContentTypeIsTextHtmlForHtm() {
    assertEquals("text/html", contentType.getContentType("/something.htm"));
  }

  public void testContentTypeIsTextPlainForTxtFile() {
    assertEquals("text/plain", contentType.getContentType("/something.txt"));
  }

  public void testContentTypeIsImageJpegForJpg() {
    assertEquals("image/jpeg", contentType.getContentType("/something.jpg"));
  }

  public void testContentTypeIsImageJpegForJpeg() {
    assertEquals("image/jpeg", contentType.getContentType("/something.jpeg"));
  }

  public void testContentTypeIsImageGifForGif() {
    assertEquals("image/gif", contentType.getContentType("/something.gif"));
  }

  public void testContentTypeIsPdfForPdf() {
    assertEquals("application/pdf", contentType.getContentType("something.pdf"));
  }

  public void testContentTypeIsApplicationForceDownlaodForDefault() {
    assertEquals("application/force-download", contentType.getContentType("/something.other"));
  }


}
