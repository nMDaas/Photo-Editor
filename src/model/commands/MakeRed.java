package model.commands;

import model.pixel.Pixel;

public class MakeRed extends PixelWiseProcessor {
  @Override
  public void doCommand(Pixel p) {
    p.setColor(0);
  }
}
