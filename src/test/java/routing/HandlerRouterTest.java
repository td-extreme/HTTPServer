import com.td.HttpServer.*;
import com.td.Mocks.*;
import java.util.HashMap;

public class HandlerRouterTest extends junit.framework.TestCase {

  MockFileIO mockFileIO;
  DefaultHandlerRouter defaultHandlerRouter;
  HandlerRouter handlerRouter;
  HttpRequest request;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    defaultHandlerRouter = new DefaultHandlerRouter(mockFileIO);
    handlerRouter = new HandlerRouter(defaultHandlerRouter);
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
    assertEquals(getType(), "HandlerMethodNotAllowed");
  }

  public void testRouterForPostWithOutPath() {
    buildRequest("POST / HTTP/1.1");
    request.setBody("This".getBytes());
    assertEquals(getType(), "HandlerMethodNotAllowed");
  }

  public void testRouterForPost() {
    buildRequest("POST /file.txt HTTP/1.1");
    request.setBody("This".getBytes());
    assertEquals(getType(), "HandlerPostFileContents");
  }

  public void testInvalidMethodType() {
    buildRequest("NOT /file.txt HTTP/1.1");
    request.setBody("This".getBytes());
    assertEquals(getType(), "HandlerMethodNotAllowed");
  }

  public void testPutToNonExsitantResourse() {
    buildRequest("PUT /nothere.txt HTTP/1.1");
    request.setBody("This".getBytes());
    assertEquals(getType(), "HandlerMethodNotAllowed");
  }

  public void testPutWithOutBody() {
    mockFileIO.addToDirectoryContents("/file.txt");
    buildRequest("PUT /file.txt HTTP/1.1");
    assertEquals(getType(), "HandlerMethodNotAllowed");
  }

  public void testRouterForPut() {
    mockFileIO.addToDirectoryContents("/file.txt");
    buildRequest("PUT /file.txt HTTP/1.1");
    request.setBody("this".getBytes());
    assertEquals(getType(), "HandlerPutFileContents");
  }

  private void buildRequest(String requestLine) {
    request = new HttpRequest(requestLine, new HashMap<String, String>());
  }

  private String getType() {
    return handlerRouter.selectHandler(request).getClass().getSimpleName();
  }
}
