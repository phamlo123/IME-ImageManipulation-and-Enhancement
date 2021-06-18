package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import model.ImageRepresentation.ImageImplPPM;
import model.Images;

/**
 * Represents a controller that can take user input (in the form of a file or interactive
 * text in the console) and process images accordingly.
 */
public class SimpleImageController implements ImageController {

  private Images model;
  private final Appendable out;
  private final Readable in;

  /**
   * Constructs a SimpleImageController used to process images according to user input commands
   * @param model  the model to call image process functionality from
   * @param out    the Appendable object to output data
   * @param in     the Readable object to take in data
   */
  public SimpleImageController(Images model, Appendable out, Readable in) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(out);
    Objects.requireNonNull(in);

    this.model = model;
    this.out = out;
    this.in = in;
  }

  @Override
  public void processInteractive() {
    Scanner scanner = new Scanner(in);
    if (scanner.hasNext()) {
      this.process(scanner.next());
    }
  }

  @Override
  public void processFile(File fileName) throws FileNotFoundException {
    Scanner scanner = new Scanner(fileName);
    if (scanner.hasNext()) {
      this.process(scanner.next());
    }
  }

  private void process(String command) {
    switch (command) {

    }
  }
}
