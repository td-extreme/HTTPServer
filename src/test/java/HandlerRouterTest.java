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
    request = new HttpRequest("GET /file.txt HTTP/1.1", new HashMap<String, String>());
  }

  public void testRouteForGetFile() {
    mockFileIO.setIsFileTrue();
    String type = getType();
    assertEquals(type, "HandlerGetFileContents");
  }

  public void testRouteForGetDirContents() {
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryTrue();
    String type = getType();
    assertEquals(type, "HandlerGetDirectoryContents");
  }

   public void testRouteForFileNotFound() {
    mockFileIO.setIsFileFalse();
    mockFileIO.setIsDirectoryFalse();
    String type = getType();
    assertEquals(type, "HandlerFileNotFound");
  }

  public void testRouterForPostWithOutBody() {
    request = new HttpRequest("POST /file.txt HTTP/1.1", new HashMap<String, String>());
    String type = getType();
    assertEquals(type, "HandlerUnprocessableEntity");
  }

  public void testRouterForPostWithOutPath() {
    request = new HttpRequest("POST / HTTP/1.1", new HashMap<String, String>());
    request.setBody("This".getBytes());
    String type = getType();
    assertEquals(type, "HandlerUnprocessableEntity");
  }

  public void testRouterForPost() {
    request = new HttpRequest("POST /file.txt HTTP/1.1", new HashMap<String, String>());
    request.setBody("This".getBytes());
    String type = getType();
    assertEquals(type, "HandlerPostFileContents");
  }

  private String getType() {
    return handlerRouter.selectHandler(request).getClass().getSimpleName();
  }
}
