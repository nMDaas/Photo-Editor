package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import model.ImageModel;
import model.ImageProcessingModel;
import model.Pixel;

public class Load implements ImageProcessingCommand {
  String filename;
  ImageProcessingController controller;
  Scanner scan;

  public Load(String filename, ImageProcessingController controller, Scanner scan) {
    if (filename.equals("")) {
      throw new IllegalArgumentException("Invalid file path.");
    }
    if (controller == null || scan == null) {
      throw new IllegalArgumentException("Controller and scanner cannot be null.");
    }
    this.filename = filename;
    this.controller = controller;
    this.scan = scan;
  }

  @Override
  public void go() {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(this.filename));
    }
    catch (
            FileNotFoundException e) {
      System.out.println("File "+ this.filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    String imageName = scan.next();

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
        imagePixels[row][col] = new Pixel(r, g, b);
      }
    }

    ImageProcessingModel newModel = new ImageModel(height, width, imagePixels, maxValue);
    controller.getImages().put(imageName, newModel);
    controller.printMessage("Loaded file as " + imageName + ".");
  }
}
