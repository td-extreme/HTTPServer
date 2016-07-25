import com.td.HttpServer.CustomHandlerRouter;
import com.td.HttpServer.DefaultHandlerRouter;
import com.td.HttpServer.HttpRequest;
import com.td.HttpServer.IHandler;
import com.td.HttpServer.HandlerGetDirectoryContents;
import com.td.Mocks.MockHandler;
import com.td.Mocks.MockFileIO;
import java.util.HashMap;

public class CustomHandlerRouterTest extends junit.framework.TestCase {

  private DefaultHandlerRouter defaultHandlerRouter;
  private CustomHandlerRouter customHandlerRouter;
  private MockHandler mockHandler;
  private HashMap<String, String> headers;
  private HttpRequest request;
  private MockFileIO mockFileIO;

  protected void setUp() {
    mockFileIO = new MockFileIO();
    defaultHandlerRouter = new DefaultHandlerRouter(mockFileIO);
    customHandlerRouter = new CustomHandlerRouter(defaultHandlerRouter);
    mockHandler = new MockHandler();
    headers = new HashMap<String, String>();
    customHandlerRouter.addRoute("GET", "/myRoute", mockHandler);
  }

  public void testGetsCustomRoute() {
    request = new HttpRequest("GET /myRoute HTTP/1.1", headers);
    IHandler testHandler = customHandlerRouter.selectHandler(request);
    assertEquals(mockHandler, testHandler);
  }

  public void testGetsDefaultHandlerIfCustomRouteNotDefined() {
    mockFileIO.setIsDirectoryTrue();
    request = new HttpRequest("GET / HTTP/1.1", headers);
    IHandler testHandler = customHandlerRouter.selectHandler(request);
    assertEquals("HandlerGetDirectoryContents", testHandler.getClass().getSimpleName());
  }
}
