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
    String filename;

    if (args.length>0) {
      filename = args[0];
    }
    else {
      filename = "src/Koala.ppm";
    }

    Reader in = new InputStreamReader(System.in);
    //Reader in = new StringReader("load pics/Koala.ppm koala red-component koala koala-red save pics/koalaRed.ppm koala-red");

    ImageProcessingView view = new ImageProcessingViewImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
  }
}