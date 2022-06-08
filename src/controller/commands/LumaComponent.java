package controller.commands;

import controller.ImageProcessingController;
import controller.commands.AbstractCommand;
import model.ImageProcessingModel;

public class LumaComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public LumaComponent(String image, ImageProcessingController controller, String newImage) {
    super (image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through luma greyscale of " + image + ".");
    return model.lumaComponent();
  }
}
