import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageGUIController;
//import controller.ImageProcessingControllerImpl;
import controller.ImageGUIControllerImpl;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewGUI;
import view.ImageProcessingViewImpl;
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
    //System.out.println("starting program");

    if (args.length == 0) {
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageProcessingViewGUI view = new ViewGUIImpl(model);
      ImageGUIController controller = new ImageGUIControllerImpl(model, view);
      controller.go();
    }
    else if (args[0].equals("-text")) {
      Reader in = new InputStreamReader(System.in);
      ImageProcessingView view = new ImageProcessingViewImpl();
      ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
      controller.process();
    }
    else if (args[0].equals("-file")) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(args[1]));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File " + args[1] + " not found!");
      }

      String script = "";
      while (sc.hasNext()) {
        String s = sc.next();
        script = script + " " + s;
      }

      Readable in = new StringReader(script);
      ImageProcessingView view = new ImageProcessingViewImpl();
      ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
      controller.process();
    }


   /* Reader in = new InputStreamReader(System.in);
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingViewGUI view = new ViewGUIImpl(model);
    ImageGUIController controller = new ImageGUIControllerImpl(model, view);
    controller.go();*/
    //Readable in = new StringReader("load pics/themSepia.jpg sepia2\n");
    //ImageProcessingView view = new ImageProcessingViewImpl();
    //ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
  }
}