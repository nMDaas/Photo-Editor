package model.commands;

import model.pixel.Pixel;

public class IntensityGreyscale extends PixelWiseProcessor {
  @Override
  public void doCommand(Pixel p) {
    p.setIntensityComponent();
  }
}
