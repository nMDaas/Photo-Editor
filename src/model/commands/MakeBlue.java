package model.commands;

import model.pixel.Pixel;

public class MakeBlue extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setColor(2);
  }
}
