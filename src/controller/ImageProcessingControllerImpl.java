package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.BlueComponent;
import controller.commands.Brighten;
import controller.commands.GreenComponent;
import controller.commands.HorizontalFlip;
import controller.commands.ImageProcessingCommand;
import controller.commands.IntensityComponent;
import controller.commands.Load;
import controller.commands.LumaComponent;
import controller.commands.RedComponent;
import controller.commands.Save;
import controller.commands.ValueComponent;
import controller.commands.VerticalFlip;
import model.ImageProcessingModel;
import view.ImageProcessingView;

public class ImageProcessingControllerImpl implements ImageProcessingController {

  public final ImageProcessingView view;
  public final Readable in;
  public final Scanner scan;

  Map<String, ImageProcessingModel> images = new HashMap<>();

  Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands = new HashMap<>();


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
            scan -> new Load(scan.next(), this, scan));
    knownCommands.put("brighten",
            scan -> new Brighten(scan.next(), this, scan.nextInt(), scan.next()));
    knownCommands.put("horizontal-flip",
            scan -> new HorizontalFlip(scan.next(), this, scan.next()));
    knownCommands.put("vertical-flip",
            scan -> new VerticalFlip(scan.next(), this, scan.next()));
    knownCommands.put("red-component",
            scan -> new RedComponent(scan.next(), this, scan.next()));
    knownCommands.put("green-component",
            scan -> new GreenComponent(scan.next(), this, scan.next()));
    knownCommands.put("blue-component",
            scan -> new BlueComponent(scan.next(), this, scan.next()));
    knownCommands.put("value-component",
            scan -> new ValueComponent(scan.next(), this, scan.next()));
    knownCommands.put("intensity-component",
            scan -> new IntensityComponent(scan.next(), this, scan.next()));
    knownCommands.put("luma-component",
            scan -> new LumaComponent(scan.next(), this, scan.next()));
    knownCommands.put("save",
            scan -> new Save(this, scan.next(), scan.next()));

  }

  @Override
  public void process() throws IllegalStateException {

    while (scan.hasNext()) {

      String userInstruction = scan.next();

      Function<Scanner, ImageProcessingCommand> cmd =
              knownCommands.getOrDefault(userInstruction, null);

      if (cmd == null) {
        throw new IllegalArgumentException("Invalid command.");
      } else {
        try {
          ImageProcessingCommand c = cmd.apply(scan);
          c.go();
        }
        catch (NoSuchElementException e) {
          throw new IllegalStateException();
        }
      }
    }
  }

  public void printMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  public Map<String, ImageProcessingModel> getImages() {
    return this.images;
  }


}
