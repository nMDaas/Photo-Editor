package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Scanner;

import model.ImageProcessingModel;
import model.Pixel;

public class Save implements ImageProcessingCommand {

  String image;
  ImageProcessingController controller;
  String path;

  public Save(ImageProcessingController controller, String path, String image) {
    this.image = image;
    this.controller = controller;
    this.path = path;
  }

  @Override
  public void go() {
    ImageProcessingModel image = controller.getImages().get(this.image);

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
      Pixel[][] pixels = image.getPixels();
      for(int row=0;row< image.getHeight(); row++){
        for(int column=0;column < image.getWidth(); column++){
          writer.write(String.format(pixels[row][column].getRed() + ""));
          writer.newLine();
          writer.write(String.format(pixels[row][column].getGreen() + ""));
          writer.newLine();
          writer.write(String.format(pixels[row][column].getBlue() + ""));
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
