package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import model.FileFormat;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.multiLayers.MultiLayeredImages;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Represents a controller that can take user input (in the form of a file or interactive text in
 * the console) and process images accordingly.
 */
public class SimpleImageController implements ImageController {

  private final MultiLayers model;
  private final Readable in;

  /**
   * Constructs a SimpleImageController used to process images according to user input commands
   *
   * @param model the model to call image process functionality from
   * @param in    the Readable object to take in data
   */
  public SimpleImageController(MultiLayers model, Readable in) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(in);

    this.model = model;
    this.in = in;
  }

  @Override
  public void processInteractive() throws IllegalStateException {
    this.renderCommands();
    Scanner scanner = new Scanner(in);
    while (scanner.hasNext()) {
      this.process(scanner.next(), scanner);
    }
  }

  @Override
  public void processFile(File fileName) throws FileNotFoundException, IllegalStateException {
    Scanner scanner = new Scanner(fileName);
    while (scanner.hasNext()) {
      this.process(scanner.next(), scanner);
    }
  }

  /**
   * Prints the available commands to the console for the user to see.
   */
  private void renderCommands() {
    System.out.println("Available commands include:");
    System.out.println("blur //blur the image");
    System.out.println("sharpen //sharpen the image");
    System.out.println("gray //make the image monochrome");
    System.out.println("sepia //make the image sepia");
    System.out.println("create //adds a new layer");
    System.out.println("load (imageFormat) (layerIndex) //loads images");
    System.out.println("save (fileName) (fileFormat) //save images");
    System.out.println("visible (layerIndex) //set to visible");
    System.out.println("invisible (layerIndex) //set to invisible");
    System.out.println("current (layerIndex) //set current layer");
    System.out.println("end //ends the program\n");
  }

  /**
   * Method to handle all the commands available to the user for image processing.
   *
   * @param command the command to be executed, if it exists. If it does not exist, the method asks
   *                the user to enter a valid command
   * @param scanner the scanner used to read input from the user
   * @throws IllegalStateException if the scanner has no more input to read
   */
  private void process(String command, Scanner scanner) throws IllegalStateException {
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
      case "getCurrent":
        cmd = new GetCurrentCommand();
        break;
      case "load":
        cmd = new LoadCommand(new Image(this.getNext(scanner)),
            this.toInt(scanner));
        break;
      case "save":
        cmd = new SaveCommand(this.getNext(scanner), this.toFormat(scanner));
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
      case "end":
        System.exit(0);
    }
    if (cmd != null) {
      try {
        cmd.go(model);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } else {
      System.out.println("Please enter a valid command");
    }
  }

  /**
   * Gets the next thing the user inputs as a String.
   * @param scanner the scanner used to retrieve the user input
   * @return the next piece of user input as a String
   * @throws IllegalStateException if there is nothing for the scanner to read
   */
  private String getNext(Scanner scanner) throws IllegalStateException {
    if (scanner.hasNext()) {
      return scanner.next();
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * Converts the next piece of user input from a String into a valid file format type. If the
   * input is not a valid file format type, the method asks the user to input a valid
   * file format type.
   * @param scanner the scanner used to retrieve the user input
   * @return the correct FileFormat that corresponds to the user input
   * @throws IllegalStateException if there is nothing for the scanner to read
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
        System.out.println("Please enter a valid file format");
        return this.toFormat(scanner);
    }
  }

  /**
   * Converts the next piece of user input to an integer, if it can be. If not, the method
   * asks the user to input a valid number.
   * @param scanner the scanner used to retrieve the user input
   * @return the integer converted from the given String user input
   * @throws IllegalStateException
   */
  private int toInt(Scanner scanner) throws IllegalStateException {
    try {
      return Integer.parseInt(this.getNext(scanner));
    } catch (NumberFormatException e) {
      System.out.println("Please enter a valid number");
      return toInt(scanner);
    }
  }

  public static void main(String[] args) {

    List<ImageFormat> images = new ArrayList<>(
        Arrays.asList(new Image("Koala.ppm"), new Image("diagram.png"), new Image("abc.jpg")));
    ImageController controller = new SimpleImageController(new MultiLayeredImages(images),
        new InputStreamReader(System.in));
    controller.processInteractive();

    ImageCommand load = new LoadCommand(null,2);

  }
}
