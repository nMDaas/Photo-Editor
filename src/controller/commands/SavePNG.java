package controller.commands;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageProcessingController;
import model.ImageProcessingModel;
import model.pixel.Pixel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Represents the save class which helps create a png image by saving it.
 */
public class SavePNG {
  String image;
  ImageProcessingController controller;
  String path;

  /**
   * Constructs a {@code SavePNG} with its fields initialized to themselves.
   *
   * @param controller the controller.
   * @param path       the path name.
   * @param image      the image name.
   */
  public SavePNG(String image, ImageProcessingController controller, String path) {
    this.image = image;
    this.controller = controller;
    this.path = path;
  }

  /**
   * Helps to save the image.
   */
  public void saveFile() {
    ImageProcessingModel theImage = controller.getImages().get(this.image);
    if (image == null) {
      controller.printMessage("This image does not exist.");
    }

    BufferedImage image = new BufferedImage(theImage.getWidth(),
            theImage.getHeight(), TYPE_INT_RGB);

    for (int row = 0; row <  theImage.getHeight(); row++) {
      for (int col = 0; col < theImage.getWidth(); col++) {
        Pixel thePixel = theImage.getPixelAt(row, col);
        Color color = new Color(thePixel.getColor(0),
                thePixel.getColor(1),
                thePixel.getColor(2));
        image.setRGB(col, row, color.getRGB());
      }
    }

    File file = new File(this.path);
    FileOutputStream fs;

    try {
      fs = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      System.out.println("File " + this.path + " not found!");
      return;
    }

    try {
      ImageIO.write(image, "png", file);

    } catch (IOException e) {
      System.out.println("Exception occurred :" + e.getMessage());
    }
  }
}
