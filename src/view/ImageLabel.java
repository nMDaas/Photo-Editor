package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ImageLabel extends JLabel {
  ImageProcessingViewGUI view;
  ImageProcessingModel model;

  public ImageLabel(ImageProcessingViewGUI view, ImageProcessingModel model) {
    this.view = view;
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!(view.getFilePath().equals("File path will appear here"))) {
      BufferedImage image;
      ImageModel currentImage = model.getImages().get("xyz");
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

      //System.out.print("test");
      this.setIcon(new ImageIcon(image));
      //this.repaint();
      //Icon icon = this.getIcon();
      //icon.paintIcon(null, image, 0, 0);
    }
  }
}
