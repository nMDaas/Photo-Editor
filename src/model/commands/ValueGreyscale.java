package model.commands;

import model.pixel.Pixel;

public class ValueGreyscale extends PixelWiseProcessor {
  @Override
  public void doCommand(Pixel p) {
    p.setValueComponent();
  }
}
