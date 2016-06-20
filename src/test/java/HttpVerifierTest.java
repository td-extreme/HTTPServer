import com.td.HttpServer.*;

public class HttpVerifierTest extends junit.framework.TestCase {

  HttpVerifier checker;


protected void setUp() {
    checker = new HttpVerifier();
  }

  // Tesing invalid requests
  public void testInvalidRequestType() {
    String request =  "NOT / HTTP/1.1";
    assertFalse(checker.isValid(request));
  }

  public void testInvalidVersion() {
    String request =  "GET / FTP/1.1";
    assertFalse(checker.isValid(request));
  }
  public void testInvalidPath() {
    String request =  "GET G HTTP/1.1";
    assertFalse(checker.isValid(request));
  }

  // Testing for each valid request type
  public void testForGetRequest() {
    String request =  "GET / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForHeadRequest() {
    String request =  "HEAD / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForPostRequest() {
    String request =  "POST / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForPutRequest() {
    String request =  "PUT / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForDeleteRequest() {
    String request =  "DELETE / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForTraceRequest() {
    String request =  "TRACE / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForOptionsRequest() {
    String request =  "OPTIONS / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForConnectRequest() {
    String request =  "CONNECT / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }

  public void testForPatchRequest() {
    String request =  "PATCH / HTTP/1.1";
    assertTrue(checker.isValid(request));
  }
}
