import org.jmock.Mockery;
import org.jmock.Expectations;
import com.td.HTTPServer.VerifierInterface;

public class RequestHandlerTest extends junit.framework.TestCase {
  Mockery context = new Mockery();
  protected void setUp() {
    VerifierInterface mockVerifier = context.mock(VerifierInterface.class);
  }

  public void testNothing() {

  }
}
