package model.commands;

import model.pixel.Pixel;

public class ChangeBrightness extends PixelWiseProcessor {
  int val;

  public ChangeBrightness(int val){
    this.val = val;
  }

  @Override
  public void doCommand(Pixel p) {
    p.brighten(this.val);
  }
}
