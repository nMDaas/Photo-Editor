import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

public class Main {
  //demo main
  public static void main(String []args) {
    Reader in = new InputStreamReader(System.in);
    //Reader in = new StringReader("load pics/smallNew.ppm small red-component small smallRed
    // save pics/red.ppm smallRed" +
           // " horizontal-flip small hor save pics/hor.ppm hor\n");

    ImageProcessingView view = new ImageProcessingViewImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
  }
}