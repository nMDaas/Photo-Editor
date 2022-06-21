package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.ImageGUIController;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ImagePanel extends JPanel {
  ImageProcessingViewGUI view;
  ImageProcessingModel model;

  public ImagePanel(ImageProcessingViewGUI view, ImageProcessingModel model) {
    this.view = view;
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (!(view.getFilePath().equals("File path will appear here"))) {
      BufferedImage image;
      ImageModel currentImage = model.getImages()[0];
      try {
        image = new BufferedImage(currentImage.getWidth(),
                currentImage.getHeight(), TYPE_INT_RGB);

        for (int row = 0; row <  currentImage.getHeight(); row++) {
          for (int col = 0; col < currentImage.getWidth(); col++) {
            Pixel thePixel = currentImage.getPixelAt(row, col);
            Color color = new Color(thePixel.getColor(0),
                    thePixel.getColor(1),
                    thePixel.getColor(2));
            image.setRGB(col, row, color.getRGB());
          }
        }

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      g.drawImage(image, 0, 0, currentImage.getWidth(), currentImage.getHeight(), null);
    }
  }
}
