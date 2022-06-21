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
import view.ImageProcessingViewGUI;

/**
 * Represents the save class which helps create a ppm image by saving it.
 */
public class SavePPM {

  ImageProcessingModel model;
  String path;
  protected ImageProcessingViewGUI view;

  public SavePPM(String path, ImageProcessingModel model,
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
      writer.write(theImage.getWidth() + " " + theImage.getHeight());
      writer.newLine();
      writer.write(String.format(theImage.getMax() + ""));
      writer.newLine();
      for (int row = 0; row < theImage.getHeight(); row++) {
        for (int col = 0; col < theImage.getWidth(); col++) {
          Pixel thePixel = theImage.getPixelAt(row, col);
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
