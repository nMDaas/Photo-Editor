import org.junit.Test;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageGUIControllerImpl;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;
import view.ViewGUIImpl;

import static org.junit.Assert.*;

public class ViewGUIImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testModelNull() throws IOException {
    ImageProcessingView view = new ViewGUIImpl(null);
  }

  @Test
  public void testSetUp() {
    ViewGUIImpl view = new ViewGUIImpl(new ImageProcessingModelImpl());
    ImageGUIController controllerImpl = new ImageGUIControllerImpl(new ImageProcessingModelImpl(), view);
    assertEquals(0, view.getBrightenValue());
    assertEquals("File path will appear here", view.getFilePath());
    assertEquals("File path will appear here", view.saveToFilePath());
  }

  @Test
  public void testGetBrightenValue() {
    ViewGUIImpl view = new ViewGUIImpl(new ImageProcessingModelImpl());
    ImageGUIController controllerImpl = new ImageGUIControllerImpl(new ImageProcessingModelImpl(), view);
    assertEquals("File path will appear here", view.saveToFilePath());
  }

  @Test
  public void testGetFilePath() {
    ViewGUIImpl view = new ViewGUIImpl(new ImageProcessingModelImpl());
    ImageGUIController controllerImpl = new ImageGUIControllerImpl(new ImageProcessingModelImpl(), view);
    assertEquals("File path will appear here", view.getFilePath());
  }

  @Test
  public void testSaveToFilePath() {
    ViewGUIImpl view = new ViewGUIImpl(new ImageProcessingModelImpl());
    ImageGUIController controllerImpl = new ImageGUIControllerImpl(new ImageProcessingModelImpl(), view);
    assertEquals("File path will appear here", view.saveToFilePath());
  }

  @Test
  public void testSetController() {
    ViewGUIImpl view = new ViewGUIImpl(new ImageProcessingModelImpl());
    ImageGUIController controllerImpl = new ImageGUIControllerImpl(new ImageProcessingModelImpl(), view);
    assertEquals(0, view.getBrightenValue());
    assertEquals("File path will appear here", view.getFilePath());
    assertEquals("File path will appear here", view.saveToFilePath());
  }

}