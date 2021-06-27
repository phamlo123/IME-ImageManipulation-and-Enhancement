package controller;

import static java.lang.String.valueOf;

import gui.ImageViewImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import model.enums.FileFormat;
import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.multilayers.MultiLayers;

/**
 * Represents a controller that takes input from the graphical user interface and delegates that
 * input to the model accordingly.
 */
public class NewController implements ActionListener, NewControllerInterface {

  private final ImageViewImpl imageViewImpl;
  private final MultiLayers model;

  /**
   * Creates a new NewController object used for processing images based on User input.
   *
   * @param model              the model used for processing the images
   * @param imageViewImpl the view used to take user input and display images
   * @throws IllegalArgumentException if the parameters are null
   */
  public NewController(MultiLayers model, ImageViewImpl imageViewImpl)
      throws IllegalArgumentException {
    if (model == null || imageViewImpl == null) {
      throw new IllegalArgumentException("Cannot pass null parameters");
    }
    this.imageViewImpl = imageViewImpl;
    this.model = model;
    imageViewImpl.setListener(this);
    imageViewImpl.setImage(model.getTopmost());
    setCurrentDisplay();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    this.actionHelper(arg0.getActionCommand());
  }

  @Override
  public void actionHelper(String command) {
    switch (command) {
      case "Blur":
        model.blurringImage();
        imageViewImpl.setImage(model.getTopmost());
        imageViewImpl.setText("Blur Filtering has been successfully performed");
        break;
      case "Sharpen":
        model.sharpeningImage();
        imageViewImpl.setImage(model.getTopmost());
        imageViewImpl.setText("Sharpen Filtering has been successfully performed");
        break;
      case "Gray":
        model.createMonochrome();
        imageViewImpl.setImage(model.getTopmost());
        imageViewImpl.setText("GrayScaling has been successfully performed");
        break;
      case "Sepia":
        model.createSepia();
        imageViewImpl.setImage(model.getTopmost());
        imageViewImpl.setText("Sepia Filtering has been successfully performed");
        break;
      case "Import":
        String load = imageViewImpl.getText("Please enter image file name");
        Scanner loadScanner = new Scanner(load);
        String load2 = imageViewImpl.getText("Please enter layer index");
        Scanner loadScanner2 = new Scanner(load2);
        try {
          model.loadImages(new Image(loadScanner.next()), Integer.parseInt(loadScanner2.next()));
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText("Import successful");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Import All":
        String importAll = imageViewImpl.getText("Please enter text file name");
        try {
          model.importAll(importAll);
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText("Import All successful");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Export":
        String export = imageViewImpl
            .getText("Please enter name for this image");
        Scanner exportScanner = new Scanner(export);
        String export2 = imageViewImpl.getText("Please enter file format");
        Scanner scannerExport2 = new Scanner(export2);
        try {
          model.saveImages(exportScanner.next(), this.toFormat(scannerExport2.next()));
          imageViewImpl.setText("Export successfully");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Export All":
        String exportAll = imageViewImpl
            .getText("Please enter base name for the images");
        Scanner exportAllScanner = new Scanner(exportAll);

        String exportAll2 = imageViewImpl.getText("Please enter file format");
        Scanner scannerExportAll2 = new Scanner(exportAll2);

        try {
          model.exportAll(exportAllScanner.next(), this.toFormat(scannerExportAll2.next()));
          imageViewImpl.setText("Export All successfully");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Add Layer":
        model.addLayer();
        imageViewImpl.setText("New layer added successfully");
        break;
      case "Remove Layer":
        String remove = imageViewImpl.getText("Please enter a layer index");
        try {
          model.removeLayer(Integer.parseInt(remove));
          imageViewImpl.setText("Remove layer" + remove + " successfully");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Visible":
        String visible = imageViewImpl.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(visible), true);
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText(visible + "is set to visible");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Invisible":
        String invisible = imageViewImpl.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(invisible), false);
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText(invisible + "is set to invisible");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Set Current":
        String current = imageViewImpl.getText("Please enter a layer index");
        try {
          model.setCurrent(Integer.parseInt(current));
          imageViewImpl.setText(current + "is set to current");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Checker":
        String checker = imageViewImpl.getText("Please enter a height");
        Scanner checkerScanner = new Scanner(checker);
        String checker2 = imageViewImpl.getText("Please enter a width");
        Scanner checkerScanner2 = new Scanner(checker2);

        try {
          ImageFormat image = new Image(Integer.parseInt(checkerScanner.next()),
              Integer.parseInt(checkerScanner2.next()));
          model.addLayer();
          model.loadImages(image, model.getCurrentLayerIndex());
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText("Custom CheckerBoard successfully created");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Default":
        ImageFormat image = new Image();
        model.addLayer();
        model.loadImages(image, model.getCurrentLayerIndex());
        imageViewImpl.setImage(model.getTopmost());
        imageViewImpl.setText("CheckerBoard successfully created");
        break;
      case "Mosaic":
        String mosaic = imageViewImpl
            .getText("Please enter the number of seeds");
        try {
          model.createMosaic(Integer.parseInt(mosaic));
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText("Mosaic Filtering has been successfully performed with " +
              mosaic + " seeds");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Downsize":
        String downsize = imageViewImpl
            .getText("Please enter desired relative ratio of new height as doubles");
        Scanner downsizeScanner = new Scanner(downsize);
        String downsize2 = imageViewImpl
            .getText("Please enter desired relative ratio of new width as doubles");
        Scanner downsizeScanner2 = new Scanner(downsize2);

        try {
          model.downSize(Double.parseDouble(downsizeScanner.next()),
              Double.parseDouble(downsizeScanner2.next()));
          imageViewImpl.setImage(model.getTopmost());
          imageViewImpl.setText("Downsizing successfully");
        } catch (IllegalArgumentException e) {
          imageViewImpl.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
    }
    setCurrentDisplay();
  }

  /**
   * Asks the view to display to the user the current layer being operated on and the number of
   * layers in the model.
   */
  private void setCurrentDisplay() {
    imageViewImpl
        .setCurrentDisplay(valueOf(model.getCurrentLayerIndex()), valueOf(model.getNumLayers()));
  }

  /**
   * Converts the next piece of user input from a String into a valid file format type. If the input
   * is not a valid file format type, the method asks the user to input a valid file format type.
   *
   * @param toFormat String to be converted to file type
   * @return the correct FileFormat that corresponds to the user input
   * @throws IllegalStateException if the appendable has an error appending
   */
  private FileFormat toFormat(String toFormat) throws IllegalStateException {
    switch (toFormat) {
      case "png":
        return FileFormat.PNG;
      case "jpg":
      case "jpeg":
        return FileFormat.JPEG;
      case "ppm":
        return FileFormat.PPM;
      default:
        throw new IllegalArgumentException();
    }
  }
}




