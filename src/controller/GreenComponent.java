package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class GreenComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public GreenComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through green channel of " + image + ".");
    return model.greenComponent();
  }
}
