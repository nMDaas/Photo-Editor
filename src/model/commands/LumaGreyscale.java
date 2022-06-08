package model.commands;

import model.Pixel;

public class LumaGreyscale extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setLumaComponent();
  }
}
