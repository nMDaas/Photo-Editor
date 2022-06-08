package controller.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import controller.ImageProcessingController;
import model.ImageProcessingModel;
import model.pixel.Pixel;

public class Save implements ImageProcessingCommand {

  String image;
  ImageProcessingController controller;
  String path;

  public Save(ImageProcessingController controller, String path, String image) {
    if (path.equals("")) {
      throw new IllegalArgumentException("Invalid file path..");
    }
    if (image.equals("")) {
      throw new IllegalArgumentException("Image name cannot be empty.");
    }
    if (controller == null) {
      throw new IllegalArgumentException("Controller and scanner cannot be null.");
    }
    this.image = image;
    this.controller = controller;
    this.path = path;
  }

  @Override
  public void go() {
    ImageProcessingModel image = controller.getImages().get(this.image);
    if (image == null) {
      throw new IllegalArgumentException("This image does not exist.");
    }

    File file = new File(this.path);
    FileOutputStream fs;

    try {
      fs = new FileOutputStream(file);
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+ this.path + " not found!");
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
      for(int row=0;row< image.getHeight(); row++){
        for(int col=0;col < image.getWidth(); col++){
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

    controller.printMessage("Saved image " + this.image + ".");

  }
}
