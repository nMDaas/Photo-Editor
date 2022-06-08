package model.commands;

import model.Pixel;

public class MakeGreen extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setGreenComponent();
  }
}
