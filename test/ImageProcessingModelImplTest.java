import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.*;

public class ImageProcessingModelImplTest {

  @Test
  public void testInitialization() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    assertEquals(0, model.getImages().size());
  }

  @Test
  public void testGetImages() {
    Reader in = new StringReader("load pics/icons.png icons\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewImpl view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    assertEquals(1, controller.getModelImages().size());
  }

}