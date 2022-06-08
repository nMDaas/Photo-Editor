package model.commands;

import model.Pixel;

public class MakeBlue extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setBlueComponent();
  }
}
