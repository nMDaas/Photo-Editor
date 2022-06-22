package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import controller.ImageProcessingController;
import model.ImageModelImpl;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;
import view.ImageProcessingView;
import view.ImageProcessingViewGUI;

/**
 * Represents the load class which helps create a jpeg or png image by loading it.
 */
public class LoadPPM {
  String filename;
  ImageProcessingModel model;
  ImageProcessingView view;

  Scanner scan;
  String imageName;

  public LoadPPM(String filename, ImageProcessingModel model,
                 ImageProcessingView view, Scanner scan) {
    this.filename = filename;
    this.view = view;
    this.model = model;
    if (scan != null) {
      this.scan = scan;
      imageName = scan.next();
    }
    else {
      imageName = "xyz";
    }

  }

  /**
   * Helps to load the image.
   */
  public void loadFile() throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(this.filename));
    } catch (
    FileNotFoundException e) {
      view.renderError("File " + this.filename + " not found!");
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
      view.renderError("Invalid PPM file: plain RAW file should begin with P3");
    }

    try {

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
      maxValue = newModel.getMax();
      newModel = new ImageModelImpl(height, width, imagePixels, maxValue);

      model.getImages().put(imageName, newModel);
      view.renderMessage("Loaded file as " + imageName + ".\n");
    } catch (IllegalStateException e) {
      view.renderError("Ran out of input");
    } catch (NumberFormatException e) {
      view.renderMessage("Height, Width, Max and Pixel RGB values must all be int.");
    }
  }

}
