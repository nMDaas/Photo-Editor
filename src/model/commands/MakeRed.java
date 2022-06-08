package model.commands;

import model.Pixel;
import model.commands.AbstractModelCommand;

public class MakeRed extends AbstractModelCommand {
  @Override
  public void doCommand(Pixel p) {
    p.setRedComponent();
  }
}
