package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class VerticalFlip extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public VerticalFlip(String image, ImageProcessingController controller, String newImage) {
    super (image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created by vertically flipping " + image + ".");
    return model.flipVertical();
  }
}
