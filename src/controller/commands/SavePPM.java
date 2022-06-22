package controller.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import view.ImageProcessingView;
import view.ImageProcessingViewGUI;

/**
 * Represents the save class which helps create a ppm image by saving it.
 */
public class SavePPM {
  String image;
  ImageProcessingController controller;

  ImageProcessingModel model;
  String path;
  protected ImageProcessingView view;

  /**
   * Constructs a {@code SavePPM} with its fields initialized to themselves.
   *
   * @param controller the controller.
   * @param path       the path name.
   * @param image      the image name.
   */
  public SavePPM(String image, ImageProcessingModel model,
                 ImageProcessingController controller, String path) {
    this.image = image;
    this.model = model;
    this.controller = controller;
    this.path = path;
  }

  public SavePPM(String path, ImageProcessingModel model,
                 ImageProcessingView view) {
    this.view = view;
    this.model = model;
    this.path = path;
    this.image = "xyz";
  }

  /**
   * Helps to save the image.
   */
  public void saveFile() {
    ImageModel image = model.getImages().get(this.image);
    if (image == null) {
      controller.printMessage("This image does not exist.");
    }

    File file = new File(this.path);
    FileOutputStream fs;

    try {
      fs = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      System.out.println("File " + this.path + " not found!");
      return;
    }

    OutputStreamWriter ow = new OutputStreamWriter(fs);
    BufferedWriter writer = new BufferedWriter(ow);

    try {
      writer.write("P3");
      writer.newLine();
      writer.write(image.getWidth() + " " + image.getHeight());
      writer.newLine();
      writer.write(String.format(image.getMax() + ""));
      writer.newLine();
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          Pixel thePixel = image.getPixelAt(row, col);
          writer.write(String.format(thePixel.getColor(0) + ""));
          writer.newLine();
          writer.write(String.format(thePixel.getColor(1) + ""));
          writer.newLine();
          writer.write(String.format(thePixel.getColor(2) + ""));
          writer.newLine();
        }
      }
      writer.flush();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
