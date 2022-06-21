package view;

import java.awt.*;

import javax.swing.*;

import model.ImageModel;
import model.ImageProcessingModel;

public class HistogramPanel extends JPanel {
  ImageProcessingViewGUI view;
  ImageProcessingModel model;

  public HistogramPanel(ImageProcessingViewGUI view, ImageProcessingModel model) {
    this.view = view;
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.black);
    g.drawLine(30, 500, 30, 100);
    g.drawLine(30, 500, 285, 500);

    ImageModel image = model.getImages()[0];

    if (image != null) {

      Histogram histogram = new Histogram(image);

      int[] red = histogram.returnHistogram(0);
      g.setColor(Color.red);
      this.drawHistogram(g, red);

      int[] green = histogram.returnHistogram(1);
      g.setColor(Color.green);
      this.drawHistogram(g, green);

      int[] blue = histogram.returnHistogram(2);
      g.setColor(Color.blue);
      this.drawHistogram(g, blue);
    }
  }

  protected void drawHistogram(Graphics g, int[] data) {
    int xPos = 31;
    for (int i = 0; i < data.length; i ++) {
      int dataScale = (int) Math.round(data[i] * 0.1);
      g.drawLine(xPos, 500, xPos, 500 - dataScale);
      xPos = xPos + 2;
    }
  }
}
