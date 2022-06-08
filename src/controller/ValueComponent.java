package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class ValueComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public ValueComponent(String image, ImageProcessingController controller, String newImage) {
    super (image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through value greyscale of " + image + ".");
    return model.valueComponent();
  }
}
