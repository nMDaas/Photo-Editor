import org.junit.Test;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageGUIControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingViewGUI;
import view.ViewGUIImpl;

import static org.junit.Assert.*;

public class ImageGUIControllerImplTest {

  @Test
  public void testSuccessfulInitialization() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingViewGUI view = new ViewGUIImpl(model);
    ImageGUIController controller = new ImageGUIControllerImpl(model, view);
    assertEquals(0, view.getBrightenValue());
    assertEquals("File path will appear here", view.getFilePath());
    assertEquals("File path will appear here", view.saveToFilePath());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelNull() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingViewGUI view = new ViewGUIImpl(model);
    ImageGUIController controller = new ImageGUIControllerImpl(null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewNull() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingViewGUI view = new ViewGUIImpl(model);
    ImageGUIController controller = new ImageGUIControllerImpl(model, null);
  }

  @Test
  public void testRenderError() throws IOException {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewGUI view = new MockViewGUI(dontCareOutput);
    ImageGUIController controller = new ImageGUIControllerImpl(model, view);
    controller.renderError("correct");
    String logString = dontCareOutput.toString();
    String[] logArray = logString.split("\n", 2);
    String finalLog = logArray[0];
    assertEquals("message = correct", logString);
  }

  @Test
  public void testRenderMessage() throws IOException {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewGUI view = new MockViewGUI(dontCareOutput);
    ImageGUIController controller = new ImageGUIControllerImpl(model, view);
    controller.printMessage("correct");
    String logString = dontCareOutput.toString();
    String[] logArray = logString.split("\n", 2);
    String finalLog = logArray[0];
    assertEquals("message = correct", logString);
  }

  @Test
  public void testInvalidProcess() throws IOException {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewGUI view = new MockViewGUI(dontCareOutput);
    ImageGUIController controller = new ImageGUIControllerImpl(model, view);
    controller.processCommand("invalid");
    String logString = dontCareOutput.toString();
    String[] logArray = logString.split("\n", 2);
    String finalLog = logArray[0];
    assertEquals("message = Invalid command.", logString);
  }


}