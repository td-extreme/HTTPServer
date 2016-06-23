import com.td.HttpServer.*;

public class FileIOTest extends junit.framework.TestCase {

  FileIO textFileServer;
  String directory = "./site";

  protected void setUp() {
    textFileServer = new FileIO(directory);
  }

  public void testSetWorkingDirectory() {
    assertEquals(textFileServer.workingDirectory(), directory);
  }
}
