package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageModel;
import model.ImageProcessingModel;
import model.Pixel;
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
            scan -> new Load(scan.next(), images, scan));
    knownCommands.put("brighten",
            scan -> new Brighten(scan.next(), images, scan.nextInt(), scan.next()));
    knownCommands.put("horizontal-flip",
            scan -> new HorizontalFlip(scan.next(), images, scan.next()));
    knownCommands.put("vertical-flip",
            scan -> new VerticalFlip(scan.next(), images, scan.next()));
    knownCommands.put("red-component",
            scan -> new RedComponent(scan.next(), images, scan.nextInt(), scan.next()));
    knownCommands.put("green-component",
            scan -> new GreenComponent(scan.next(), images, scan.next()));
    knownCommands.put("blue-component",
            scan -> new BlueComponent(scan.next(), images, scan.next()));
    knownCommands.put("value-component",
            scan -> new ValueComponent(scan.next(), images, scan.next()));
    knownCommands.put("intensity-component",
            scan -> new IntensityComponent(scan.next(), images, scan.next()));
    knownCommands.put("luma-component",
            scan -> new LumaComponent(scan.next(), images, scan.next()));

  }

  @Override
  public void process() throws IllegalStateException {
    String userInstruction = scan.next();

    Function<Scanner, ImageProcessingCommand> cmd =
            knownCommands.getOrDefault(userInstruction, null);

    if (cmd == null) {
      throw new IllegalArgumentException("Invalid command.");
    } else {
      ImageProcessingCommand c = cmd.apply(scan);
      c.go();
    }
  }

  private void printMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }


}
