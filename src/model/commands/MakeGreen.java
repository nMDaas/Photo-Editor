package model.commands;

import model.pixel.Pixel;

public class MakeGreen extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setColor(1);
  }
}
