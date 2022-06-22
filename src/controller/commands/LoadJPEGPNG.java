package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
public class LoadJPEGPNG {
  String filename;
  ImageProcessingModel model;
  protected ImageProcessingView view;

  Scanner scan;
  String imageName;

  /**
   * Constructs {@code LoadJPEGPNG} with its fields initialized to themselves.
   *
   * @param filename   the name of the file path.
   * @param view the controller.
   * @param scan       the scanner.
   */
  public LoadJPEGPNG(String filename, ImageProcessingModel model,
                     ImageProcessingView view, Scanner scan) {
    this.filename = filename;
    this.view = view;
    this.model = model;
    if (scan != null) {
      this.scan = scan;
      imageName = scan.next();
    }
    else {
      imageName = "image";
    }

  }

  /**
   * Helps to load the image.
   */
  public void loadFile() throws IOException {
    File file = null;
    BufferedImage image = null;

    try {
      String path = this.filename;
      file = new File(path);
      image = ImageIO.read(file);
    } catch (FileNotFoundException e) {
      view.renderError("File " + this.filename + " not found!");
      return;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      int width = image.getWidth();
      int height = image.getHeight();
      Pixel[][] imagePixels = new Pixel[height][width];

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int pixel = image.getRGB(col,row);
          //Creating a Color object from pixel value
          Color color = new Color(pixel, true);
          //Retrieving the R G B values
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          imagePixels[row][col] = new RGBPixel(red, blue, green);
        }
      }

      int maxValue = 0;

      ImageModel newModel = new ImageModelImpl(height, width, imagePixels, maxValue);
      maxValue = newModel.getMax();
      newModel = new ImageModelImpl(height, width, imagePixels, maxValue);

      model.getImages().put(imageName, newModel);
      view.renderMessage("Loaded file as " + imageName + ".\n");
    } catch (IllegalStateException e) {
      view.renderError("Ran out of input");
    } catch (NumberFormatException e) {
      view.renderError("Height, Width, Max and Pixel RGB values must all be int.");
    }
  }
}
