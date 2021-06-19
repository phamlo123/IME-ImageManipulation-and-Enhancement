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
  public void processInteractive() throws IllegalArgumentException {
    this.renderCommands();
    Scanner scanner = new Scanner(in);
    while (scanner.hasNext()) {
      this.process(scanner.next(), scanner);
    }
  }

  @Override
  public void processFile(File fileName) throws FileNotFoundException, IllegalArgumentException {
    this.renderCommands();
    Scanner scanner = new Scanner(fileName);
    while (scanner.hasNext()) {
      this.process(scanner.next(), scanner);
    }
  }

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
    System.out.println("current (layerIndex) //set current layer\n");
  }

  private void process(String command, Scanner scanner) throws IllegalArgumentException {
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
      case "load":
        cmd = new LoadCommand(new Image(this.getNext(scanner)),
            this.toInt(this.getNext(scanner)));
        break;
      case "save":
        cmd = new SaveCommand(this.getNext(scanner), this.toFormat(this.getNext(scanner)));
        break;
      case "visible":
        cmd = new VisibleCommand(this.toInt(this.getNext(scanner)), true);
        break;
      case "invisible":
        cmd = new VisibleCommand(this.toInt(this.getNext(scanner)), false);
        break;
      case "current":
        cmd = new CurrentCommand(this.toInt(this.getNext(scanner)));
    }
    if (cmd != null) {
      cmd.go(model);
    } else {
      throw new IllegalArgumentException("Illegal command");
    }
  }

  private String getNext(Scanner scanner) throws IllegalArgumentException {
    if (scanner.hasNext()) {
      return scanner.next();
    } else {
      throw new IllegalArgumentException();
    }
  }

  private FileFormat toFormat(String s) throws IllegalArgumentException {
    switch (s) {
      case "png":
        return FileFormat.PNG;
      case "jpg":
      case "jpeg":
        return FileFormat.JPEG;
      case "ppm":
        return FileFormat.PPM;
      default:
        throw new IllegalArgumentException("Invalid file format");
    }
  }

  private int toInt(String s) throws IllegalArgumentException {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Cannot parse String to int");
    }
  }

  public static void main(String[] args) {
    /*
    List<ImageFormat> images = new ArrayList<>(
        Arrays.asList(new Image("Koala.ppm"), new Image("diagram.png"), new Image("abc.jpg")));
    ImageController controller = new SimpleImageController(new MultiLayeredImages(images),
        new InputStreamReader(System.in));
    controller.processInteractive();

     */
    List<ImageFormat> images = new ArrayList<>(
        Arrays.asList(new Image("Koala.ppm"), new Image("diagram.png"), new Image("abc.jpg")));
    MultiLayers multiLayers = new MultiLayeredImages(images);
    multiLayers.createMonochrome();
    multiLayers.getCurrentLayer().getConverter().exportImage("hello",FileFormat.JPEG);
  }
}
