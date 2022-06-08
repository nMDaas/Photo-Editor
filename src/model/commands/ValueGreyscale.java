package model.commands;

import model.pixel.Pixel;

public class ValueGreyscale extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setValueComponent();
  }
}
