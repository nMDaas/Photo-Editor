package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import view.ImageProcessingViewGUI;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Represents the save class which helps create a jpeg image by saving it.
 */
public class SaveJPEG {
  ImageProcessingModel model;
  String path;
  protected ImageProcessingViewGUI view;


  public SaveJPEG(String path, ImageProcessingModel model,
                  ImageProcessingViewGUI view) {
    this.view = view;
    this.model = model;
    this.path = path;
  }

  /**
   * Helps to save the image.
   */
  public void saveFile() {
    ImageModel theImage = model.getImages()[0];
    if (theImage == null) {
      view.showErrorMessage("This image does not exist.");
    }

    BufferedImage image = new BufferedImage(theImage.getWidth(),
            theImage.getHeight(), TYPE_INT_RGB);

    for (int row = 0; row < theImage.getHeight(); row++) {
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
      ImageIO.write(image, "jpg", file);

    } catch (IOException e) {
      System.out.println("Exception occurred while writing:" + e.getMessage());
    }
  }

}
