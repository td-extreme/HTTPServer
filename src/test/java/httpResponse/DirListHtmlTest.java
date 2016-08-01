import com.td.HttpServer.*;

public class DirListHtmlTest extends junit.framework.TestCase {

  DirListHtml dirListHtml;
  String pathRoot;
  String path;
  String[] mockFiles;
  String builtHtmlPage;
  String builtHtmlPageRootDirForPath;

  protected void setUp() {
    dirListHtml = new DirListHtml();
    pathRoot = "/";
    path = "myPath";
    mockFiles = new String[] { "File01.txt", "directory01" };
    builtHtmlPage = new String(dirListHtml.buildHtmlPage(path, mockFiles));
  }

  public void testPageHasDocTypeTag() {
    assert(builtHtmlPage.contains("<!DOCTYPE html>"));
  }

  public void testPageHasHtmlTag() {
    assert(builtHtmlPage.contains("<html>"));
  }

  public void testPageHasHtmlClosingTag() {
    assert(builtHtmlPage.contains("</html>"));
  }

  public void testPageHasBodyTag() {
    assert(builtHtmlPage.contains("<body>"));
  }

  public void testPageHasBodyClosingTag() {
    assert(builtHtmlPage.contains("</body>"));
  }

  public void testPageHasLinkToFile() {
    assert(builtHtmlPage.contains("<a href=\"myPath/File01.txt\">File01.txt</a>"));
  }

  public void testWhenPathIsRootDirectory() {
    builtHtmlPageRootDirForPath = new String(dirListHtml.buildHtmlPage(pathRoot, mockFiles));
    assert(builtHtmlPageRootDirForPath.contains("<a href=\"/File01.txt\">File01.txt</a>"));
  }
}
