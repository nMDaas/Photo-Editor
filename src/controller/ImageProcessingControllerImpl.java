/*
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.BlueComponent;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.GreenComponent;
import controller.commands.HorizontalFlip;
import controller.commands.ImageProcessingCommand;
import controller.commands.IntensityComponent;
import controller.commands.Load;
import controller.commands.LumaComponent;
import controller.commands.MakeGreyscale;
import controller.commands.MakeSepia;
import controller.commands.RedComponent;
import controller.commands.Save;
import controller.commands.Sharpen;
import controller.commands.ValueComponent;
import controller.commands.VerticalFlip;
import model.ImageModel;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;

*/
/**
 * Represents a ImageProcessingControllerImpl, which helps the user interact with the image.
 * This is an implementation of ImageProcessingController and acts as the controller. It only
 * implements its inherited methods.
 *//*

public class ImageProcessingControllerImpl implements ImageProcessingController {

  private final ImageProcessingView view;
  public final Readable in;
  private final Scanner scan;
  private final ImageProcessingModel model = new ImageProcessingModelImpl();

  //Map<String, ImageModel> images = new HashMap<>();

  Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands = new HashMap<>();

  */
/**
   * Constructs a {@code ImageProcessingControllerImpl} with its fields initialized to themselves.
   *
   * @param in   the string the user inputs.
   * @param view the view.
   * @throws IllegalArgumentException if readable or view are null.
   *//*

  public ImageProcessingControllerImpl(Readable in, ImageProcessingView view)
          throws IllegalArgumentException {
    if (in == null) {
      throw new IllegalArgumentException("readable is null");
    }
    if (view == null) {
      throw new IllegalArgumentException("view is null");
    }

    this.in = in;
    this.view = view;
    scan = new Scanner(this.in);

    knownCommands.put("load",
        scan -> new Load(scan.next(), model, this, scan));
    knownCommands.put("brighten",
        scan -> new Brighten(scan.next(), model, this, scan.nextInt(), scan.next()));
    knownCommands.put("horizontal-flip",
        scan -> new HorizontalFlip(scan.next(), model, this, scan.next()));
    knownCommands.put("vertical-flip",
        scan -> new VerticalFlip(scan.next(), model, this, scan.next()));
    knownCommands.put("red-component",
        scan -> new RedComponent(scan.next(), model, this, scan.next()));
    knownCommands.put("green-component",
        scan -> new GreenComponent(scan.next(), model, this, scan.next()));
    knownCommands.put("blue-component",
        scan -> new BlueComponent(scan.next(), model, this, scan.next()));
    knownCommands.put("value-component",
        scan -> new ValueComponent(scan.next(), model, this, scan.next()));
    knownCommands.put("intensity-component",
        scan -> new IntensityComponent(scan.next(), model, this, scan.next()));
    knownCommands.put("luma-component",
        scan -> new LumaComponent(scan.next(), model, this, scan.next()));
    knownCommands.put("save",
        scan -> new Save(model, this, scan.next(), scan.next()));
    knownCommands.put("blur",
        scan -> new Blur(scan.next(), model, this, scan.next()));
    knownCommands.put("sharpen",
        scan -> new Sharpen(scan.next(), model, this, scan.next()));
    knownCommands.put("make-greyscale",
        scan -> new MakeGreyscale(scan.next(), model, this, scan.next()));
    knownCommands.put("make-sepia",
        scan -> new MakeSepia(scan.next(), model, this, scan.next()));
  }

  */
/**
   * Processes the user's input and applies the respective command.
   *//*

  @Override
  public void process() {

    while (scan.hasNext()) {

      String userInstruction = scan.next();

      Function<Scanner, ImageProcessingCommand> cmd =
              knownCommands.getOrDefault(userInstruction, null);

      if (cmd == null) {
        this.printMessage("Invalid command.");
      } else {
        try {
          ImageProcessingCommand c = cmd.apply(scan);
          c.execute();
        } catch (NoSuchElementException e) {
          this.printMessage("More input required.");
        } catch (NumberFormatException e) {
          this.printMessage("Invalid number input.");
        }
      }
    }
  }

  */
/**
   * Prints the message according the input.
   *
   * @param message the message.
   * @throws IllegalStateException if unable to print the message.
   *//*

  public void printMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  */
/**
   * Returns the hashmap of stored images.
   *
   * @return the hashmap of stored images.
   *//*

  public Map<String, ImageModel> getModelImages() {
    return this.model.getImages();
  }


}
*/
