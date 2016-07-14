import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.HashMap;

public class HandlerRouterTest extends junit.framework.TestCase {

  MockFileIO mockFileIO;
  HandlerRouter handlerRouter;
  HttpRequest request;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    handlerRouter = new HandlerRouter(mockFileIO);
  }

  public void testRouteForGetFile() {
    buildRequest("GET /file.txt HTTP/1.1");
    mockFileIO.setIsFileTrue();
    assertEquals(getType(), "HandlerGetFileContents");
  }

  public void testRouteForGetDirContents() {
    buildRequest("GET /file.txt HTTP/1.1");
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryTrue();
    assertEquals(getType(), "HandlerGetDirectoryContents");
  }

   public void testRouteForFileNotFound() {
    buildRequest("GET /file.txt HTTP/1.1");
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryFalse();
    assertEquals(getType(), "HandlerFileNotFound");
  }

  public void testRouterForPostWithOutBody() {
    buildRequest("POST /file.txt HTTP/1.1");
    assertEquals(getType(), "HandlerUnprocessableEntity");
  }

  public void testRouterForPostWithOutPath() {
    buildRequest("POST / HTTP/1.1");
    request.setBody("This".getBytes());
    assertEquals(getType(), "HandlerUnprocessableEntity");
  }

  public void testRouterForPost() {
    buildRequest("POST /file.txt HTTP/1.1");
    request.setBody("This".getBytes());
    assertEquals(getType(), "HandlerPostFileContents");
  }

  private void buildRequest(String requestLine) {
    request = new HttpRequest(requestLine, new HashMap<String, String>());
  }

  private String getType() {
    return handlerRouter.selectHandler(request).getClass().getSimpleName();
  }
}
