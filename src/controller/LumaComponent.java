package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class LumaComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public LumaComponent(String image, ImageProcessingController controller, String newImage) {
    super (image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model) {
    controller.printMessage(newImage + " created through luma greyscale of " + image + ".");
    return model.lumaComponent();
  }
}
