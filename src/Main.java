import java.io.InputStreamReader;
import java.io.Reader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

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
    //Readable in = new StringReader("load pics/themSepia.jpg sepia2\n");
    ImageProcessingView view = new ImageProcessingViewImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
  }
}