package model.commands;

import model.pixel.Pixel;

public class LumaGreyscale extends PixelWiseProcessor {
  @Override
  public void doCommand(Pixel p) {
    p.setLumaComponent();
  }
}
