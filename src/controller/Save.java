package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Scanner;

import model.ImageProcessingModel;

public class Save implements ImageProcessingCommand {

  String image;
  Map<String, ImageProcessingModel> images;
  String path;

  public Save(String image, Map<String, ImageProcessingModel> images, int value, String path) {
    this.image = image;
    this.images = images;
    this.path = path;
  }

  @Override
  public void go() {

    Scanner sc;

    try {
      sc = new Scanner(new FileOutputStream(this.path));
    }
    catch (
            FileNotFoundException e) {
      System.out.println("File "+ this.path + " not found!");
      return;
    }

    BufferedImage bImage = null;
    try {
      File initialImage = new File("C://Users/Rou/Desktop/image.jpg");
      bImage = ImageIO.read(initialImage);

      ImageIO.write(bImage, "gif", new File("C://Users/Rou/Desktop/image.gif"));
      ImageIO.write(bImage, "jpg", new File("C://Users/Rou/Desktop/image.png"));
      ImageIO.write(bImage, "bmp", new File("C://Users/Rou/Desktop/image.bmp"));

    } catch (IOException e) {
      System.out.println("Exception occured :" + e.getMessage());
    }
    System.out.println("Images were written succesfully.");

  }
}
