import com.td.HttpServer.*;

public class FileIOTest extends junit.framework.TestCase {

  FileIO textFileServer;
  String directory = "./site/textFiles/";

  protected void setUp() {
    textFileServer = new FileIO(directory);
  }

  public void testConstructorDefault() {
    FileIO textFileServerDefault = new FileIO();
    assertEquals("./", textFileServerDefault.workingDirectory());
  }

  public void testConstructorPathGiven() {
    assertEquals(textFileServer.workingDirectory(), directory);
  }

  public void testGetFileContents() {
    assertEquals("This is a text file.\n", textFileServer.getFileContents("testFile01.txt"));
  }

  public void testGetFileContentsMultilineFile() {
    String fileShouldContain = "This is a multiline text file.\nIt has three lines of text.\nThis is the last line.\n";
    assertEquals(fileShouldContain, textFileServer.getFileContents("testFile02.txt"));
  }

  public void testFileDoesNotExsist() {
    assertEquals("Error: File Not Found", textFileServer.getFileContents("badName.txt"));
  }
}

