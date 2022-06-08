package model.commands;

import model.Pixel;

public class ChangeBrightness extends AbstractModelCommand {
  int val;

  public ChangeBrightness(int val){
    this.val = val;
  }

  @Override
  public void doCommand(Pixel p) {
    p.brighten(this.val);
  }
}
