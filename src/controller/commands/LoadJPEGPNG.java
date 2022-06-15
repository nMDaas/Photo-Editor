package controller.commands;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;

public class LoadJPEGPNG {
  String filename;
  ImageProcessingController controller;
  Scanner scan;

  public LoadJPEGPNG(String filename, ImageProcessingController controller, Scanner scan) {
    this.filename = filename;
    this.controller = controller;
    this.scan = scan;
  }

  public void loadFile() {
    File file = null;
    BufferedImage image = null;

    try {
      String path = this.filename;
      file = new File(path);
       image = ImageIO.read(file);
    } catch (FileNotFoundException e) {
      controller.printMessage("File " + this.filename + " not found!");
      return;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {

      String imageName = scan.next();

      int width = image.getWidth();
      int height = image.getHeight();
      //System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

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

      ImageProcessingModel newModel = new ImageModel(height, width, imagePixels, maxValue);
      maxValue = newModel.getMax();
      newModel = new ImageModel(height, width, imagePixels, maxValue);

      controller.getImages().put(imageName, newModel);
      controller.printMessage("Loaded file as " + imageName + ".");
    } catch (IllegalStateException e) {
      controller.printMessage("Ran out of input");
    } catch (NumberFormatException e) {
      controller.printMessage("Height, Width, Max and Pixel RGB values must all be int.");
    }
  }
}
