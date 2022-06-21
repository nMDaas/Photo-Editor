package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModelImpl;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;
import view.ImageProcessingViewGUI;

/**
 * Represents the load class which helps create a jpeg or png image by loading it.
 */
public class LoadPPM {
  String filename;
  ImageProcessingModel model;
  protected ImageProcessingViewGUI view;

  public LoadPPM(String filename, ImageProcessingModel model,
                 ImageProcessingViewGUI view) {
    this.filename = filename;
    this.model = model;
    this.view = view;
  }

  /**
   * Helps to load the image.
   */
  public void loadFile() {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(this.filename));
    } catch (
    FileNotFoundException e) {
      view.showErrorMessage("File " + this.filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      view.showErrorMessage("Invalid PPM file: plain RAW file should begin with P3");
    }

    try {

      //String imageName = scan.next();

      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      //System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

      Pixel[][] imagePixels = new Pixel[height][width];

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          imagePixels[row][col] = new RGBPixel(r, b, g);
        }
      }

      ImageModel newModel = new ImageModelImpl(height, width, imagePixels, maxValue);
      model.getImages()[0] = newModel;
      //controller.printMessage("Loaded file as " + imageName + ".");
    } catch (IllegalStateException e) {
      view.showErrorMessage("Ran out of input");
    } catch (NumberFormatException e) {
      view.showErrorMessage("Height, Width, Max and Pixel RGB values must all be int.");
    }
  }

}
