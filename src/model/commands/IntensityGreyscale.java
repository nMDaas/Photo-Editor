package model.commands;

import model.Pixel;

public class IntensityGreyscale extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setIntensityComponent();
  }
}
