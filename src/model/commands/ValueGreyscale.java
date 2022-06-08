package model.commands;

import model.Pixel;

public class ValueGreyscale extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setValueComponent();
  }
}
