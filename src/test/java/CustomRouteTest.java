import com.td.HttpServer.CustomRoute;
import com.td.Mocks.MockHandler;

public class CustomRouteTest extends junit.framework.TestCase {

  private CustomRoute testRoute;

  protected void setUp() {
    testRoute = new CustomRoute("GET", "/this", new MockHandler());
  }

  public void testMatchMethodReturnsTrueForMatch() {
    assert(testRoute.match("GET", "/this"));
  }

  public void testMatchMethodReturnsFalseForNonMatchMethod() {
    assertFalse(testRoute.match("POST", "/this"));
  }

  public void testMatchMethodReturnsFalseForNonMatchPath() {
    assertFalse(testRoute.match("GET", "/that"));
  }
}
