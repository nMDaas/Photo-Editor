import java.io.InputStreamReader;
import java.io.Reader;

import controller.ImageGUIController;
//import controller.ImageProcessingControllerImpl;
import controller.ImageGUIControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingViewGUI;
import view.ViewGUIImpl;

/**
 * Represents the main method.
 */
public class Main {
  /**
   * Program can be run through String input.
   *
   * @param args String input
   */
  public static void main(String[] args) {
    System.out.println("starting program");
    Reader in = new InputStreamReader(System.in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    //Readable in = new StringReader("load pics/themSepia.jpg sepia2\n");
    //ImageProcessingView view = new ImageProcessingViewImpl();
    ImageProcessingViewGUI view = new ViewGUIImpl(model);
    //ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    ImageGUIController controller = new ImageGUIControllerImpl(model, view);
    controller.go();
  }
}