import com.td.Mocks.MockHttpRequestReader;
import com.td.Mocks.DummyIO;
import com.td.HttpServer.HttpRequestBuilder;
import com.td.HttpServer.HttpRequestParser;
import com.td.HttpServer.HttpVerifier;
import com.td.HttpServer.InvalidHttpRequestException;
import java.io.IOException;

public class HttpRequestBuilderTest extends junit.framework.TestCase {

  HttpRequestParser parser;
  MockHttpRequestReader mockReader;
  HttpRequestBuilder builder;

  protected void setUp() {
    parser = new HttpRequestParser(new HttpVerifier());
  }

  public void testThatReaderGetBodyIsNotCalledIfNoContentLength() {
    mockReader = new MockHttpRequestReader("GET / HTTP/1.1\n\n", new byte[0]);
    builder = new HttpRequestBuilder(mockReader, parser);
    try {
    builder.createRequest(new DummyIO());
    }
    catch (InvalidHttpRequestException e) {}
    catch (IOException e) {}
    assertFalse(mockReader.wasGetBytesCalled());
  }

  public void testThatReaderGetBodyIsCalledIfContentLengthIsSupplied() {
    mockReader = new MockHttpRequestReader("POST /this.txt HTTP/1.1\nContent-Length: 5\n\n", "12321".getBytes());
    builder = new HttpRequestBuilder(mockReader, parser);
    try {
    builder.createRequest(new DummyIO());
    }
    catch (InvalidHttpRequestException e) {}
    catch (IOException e) {}
    assert(mockReader.wasGetBytesCalled());
  }
}
