package controller;

import controller.command.BlurCommand;
import controller.command.CreateCommand;
import controller.command.CurrentCommand;
import controller.command.ExportAllCommand;
import controller.command.GrayCommand;
import controller.command.ImageCommand;
import controller.command.LoadCommand;
import controller.command.RemoveCommand;
import controller.command.SaveCommand;
import controller.command.SepiaCommand;
import controller.command.SharpenCommand;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.ImageRepresentation.multiLayers.MultiLayeredImages;
import model.enumTypes.FileFormat;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Represents a controller that can take user input (in the form of a file or interactive text in
 * the console) and process images accordingly.
 */
public class SimpleImageController implements ImageController {

  private final MultiLayers model;
  private final Readable in;
  private final Appendable out;

  /**
   * Constructs a SimpleImageController used to process images according to user input commands
   *
   * @param model the model to call image process functionality from
   * @param in    the Readable object to take in data
   * @param out   the Appendable object used for printing
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public SimpleImageController(MultiLayers model, Readable in, Appendable out)
      throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    ImageUtil.checkNull(in);
    ImageUtil.checkNull(out);

    this.model = model;
    this.in = in;
    this.out = out;
  }

  @Override
  public void processInteractive() throws IllegalStateException {
    this.renderCommands();
    Scanner scanner = new Scanner(in);
    String next;
    while (scanner.hasNext()) {
      next = scanner.next();
      if (next.equals("end")) {
        this.output("Program ended");
        return;
      }
      this.process(next, scanner);
    }
  }

  @Override
  public void processFile(File fileName)
      throws FileNotFoundException, IllegalStateException, IllegalArgumentException {
    ImageUtil.checkNull(fileName);
    String next;
    Scanner scanner;
    try {
      scanner = new Scanner(fileName);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    while (scanner.hasNext()) {
      next = scanner.next();
      if (next.equals("end")) {
        this.output("Program ended");
        return;
      }
      this.process(next, scanner);
    }
  }

  /**
   * Prints the available commands to the console for the user to see.
   *
   * @throws IllegalStateException if the appendable has an error appending
   */
  private void renderCommands() throws IllegalStateException {
    this.output("Available commands include:\n");
    this.output("blur //blur the image\n");
    this.output("sharpen //sharpen the image\n");
    this.output("gray //make the image monochrome\n");
    this.output("sepia //make the image sepia\n");
    this.output("create //adds a new layer\n");
    this.output("remove (layerIndex) //remove the given layer");
    this.output("load (imageFormat) (layerIndex) //loads images\n");
    this.output("save (fileName) (fileFormat) //save images\n");
    this.output("export //export all the images");
    this.output("visible (layerIndex) //set to visible\n");
    this.output("invisible (layerIndex) //set to invisible\n");
    this.output("current (layerIndex) //set current layer\n");
    this.output("end //ends the program\n\n");
  }

  /**
   * Method to handle all the commands available to the user for image processing.
   *
   * @param command the command to be executed, if it exists. If it does not exist, the method asks
   *                the user to enter a valid command
   * @param scanner the scanner used to read input from the user
   * @throws IllegalStateException if the appendable has an error appending
   */
  private void process(String command, Scanner scanner) {
    ImageCommand cmd = null;
    switch (command) {
      case "blur":
        cmd = new BlurCommand();
        break;
      case "sharpen":
        cmd = new SharpenCommand();
        break;
      case "gray":
        cmd = new GrayCommand();
        break;
      case "sepia":
        cmd = new SepiaCommand();
        break;
      case "create":
        cmd = new CreateCommand();
        break;
      case "remove":
        cmd = new RemoveCommand(this.toInt(scanner));
        break;
      case "load":
        cmd = new LoadCommand(new Image(this.getNext(scanner)),
            this.toInt(scanner));
        break;
      case "save":
        cmd = new SaveCommand(this.getNext(scanner), this.toFormat(scanner));
        break;
      case "export":
        cmd = new ExportAllCommand(this.getNext(scanner));
        break;
      case "visible":
        cmd = new VisibleCommand(this.toInt(scanner), true);
        break;
      case "invisible":
        cmd = new VisibleCommand(this.toInt(scanner), false);
        break;
      case "current":
        cmd = new CurrentCommand(this.toInt(scanner));
        break;
    }
    if (cmd != null) {
      try {
        cmd.go(model);
      } catch (IllegalArgumentException e) {
        this.output(e.getMessage() + ", Please enter a valid command\n");
      }
    } else {
      this.output("Please enter a valid command\n");
    }
  }

  /**
   * Appends the given message to the appendable object.
   *
   * @param message the String to be appended to the appendable
   * @throws IllegalStateException if the appendable has an error appending
   */
  private void output(String message) throws IllegalStateException {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * Gets the next thing the user inputs as a String.
   *
   * @param scanner the scanner used to retrieve the user input
   * @return the next piece of user input as a String
   * @throws IllegalStateException if there is nothing for the scanner to read
   */
  private String getNext(Scanner scanner) {
    if (scanner.hasNext()) {
      return scanner.next();
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * Converts the next piece of user input from a String into a valid file format type. If the input
   * is not a valid file format type, the method asks the user to input a valid file format type.
   *
   * @param scanner the scanner used to retrieve the user input
   * @return the correct FileFormat that corresponds to the user input
   * @throws IllegalStateException if the appendable has an error appending
   */
  private FileFormat toFormat(Scanner scanner) throws IllegalStateException {
    switch (this.getNext(scanner)) {
      case "png":
        return FileFormat.PNG;
      case "jpg":
      case "jpeg":
        return FileFormat.JPEG;
      case "ppm":
        return FileFormat.PPM;
      default:
        this.output("Please enter a valid file format\n");
        return this.toFormat(scanner);
    }
  }

  /**
   * Converts the next piece of user input to an integer, if it can be. If not, the method asks the
   * user to input a valid number.
   *
   * @param scanner the scanner used to retrieve the user input
   * @return the integer converted from the given String user input
   * @throws IllegalStateException if the appendable has an error appending
   */
  private int toInt(Scanner scanner) throws IllegalStateException {
    try {
      return Integer.parseInt(this.getNext(scanner));
    } catch (NumberFormatException e) {
      this.output("Please enter a valid number\n");
      return toInt(scanner);
    }
  }
}
