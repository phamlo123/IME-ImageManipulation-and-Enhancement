package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import model.ImageRepresentation.ImageImplPPM;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.Images;

/**
 * Represents a controller that can take user input (in the form of a file or interactive
 * text in the console) and process images accordingly.
 */
public class SimpleImageController implements ImageController {

  private final MultiLayers model;
  private final Readable in;

  /**
   * Constructs a SimpleImageController used to process images according to user input commands
   * @param model  the model to call image process functionality from
   * @param in     the Readable object to take in data
   */
  public SimpleImageController(MultiLayers model, Readable in) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(in);

    this.model = model;
    this.in = in;
  }

  @Override
  public void processInteractive() throws IllegalArgumentException {
    Scanner scanner = new Scanner(in);
    if (scanner.hasNext()) {
      this.process(scanner.next());
    }
  }

  @Override
  public void processFile(File fileName) throws FileNotFoundException, IllegalArgumentException {
    Scanner scanner = new Scanner(fileName);
    if (scanner.hasNext()) {
      this.process(scanner.next());
    }
  }

  private void process(String command) throws IllegalArgumentException {
    ImageCommand cmd = null;
    switch (command) {
      case "blur": cmd = new BlurCommand();
      break;
      case "sharpen": cmd = new SharpenCommand();
      break;
      case "gray": cmd = new GrayCommand();
      break;
      case "sepia": cmd = new SepiaCommand();
      break;
      case "create": cmd = new CreateCommand();
      break;
      case "load": cmd = new LoadCommand();
      break;
      case "save": cmd = new SaveCommand();
      break;
      case "visible": cmd = new VisibleCommand();
      break;
      case "invisible": cmd = new VisibleCommand(...,false);
      break;
      case "current": cmd = new CurrentCommand();
    }
    if (cmd != null) {
      cmd.go(model);
    }
  }

  private ImageCommand getCommand() {
    Scanner scanner = new Scanner(in);
    if (scanner.hasNext()) {

    }
  }
}
