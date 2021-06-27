package controller;

import static java.lang.String.valueOf;
import gui.SwingFeaturesFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import model.enums.FileFormat;
import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.multilayers.MultiLayers;

/**
 * Represents a controller that takes input from the graphical user interface and delegates
 * that input to the model accordingly.
 */
public class NewController implements ActionListener {

  private final SwingFeaturesFrame swingFeaturesFrame;
  private final MultiLayers model;

  /**
   * Creates a new NewController object used for processing images based on User input.
   * @param model              the model used for processing the images
   * @param swingFeaturesFrame the view used to take user input and display images
   * @throws IllegalArgumentException if the parameters are null
   */
  public NewController(MultiLayers model, SwingFeaturesFrame swingFeaturesFrame)
      throws IllegalArgumentException {
    if (model == null || swingFeaturesFrame == null) {
      throw new IllegalArgumentException("Cannot pass null parameters");
    }
    this.swingFeaturesFrame = swingFeaturesFrame;
    this.model = model;
    swingFeaturesFrame.setListener(this);
    swingFeaturesFrame.setImage(model.getTopmost());
    setCurrentDisplay();
  }


  /**
   * Method that delegates to the model the desired functionality based on the press of a button
   * from the view.
   * @param arg0 the action from the view (button pressed)
   */
  public void actionPerformed(ActionEvent arg0) {
    this.actionHelper(arg0.getActionCommand());
  }

  /**
   * Helper for actionPerformed that takes the String version of the ActionEvent given to
   * actionPerformed. This method is public for easier testing.
   * @param command the command to be executed.
   */
  public void actionHelper(String command) {
    switch (command) {
      case "Blur":
        model.blurringImage();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Blur Filtering has been successfully performed");
        break;
      case "Sharpen":
        model.sharpeningImage();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Sharpen Filtering has been successfully performed");
        break;
      case "Gray":
        model.createMonochrome();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("GrayScaling has been successfully performed");
        break;
      case "Sepia":
        model.createSepia();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Sepia Filtering has been successfully performed");
        break;
      case "Import":
        String load = swingFeaturesFrame.getText("Please enter image file name");
        Scanner loadScanner = new Scanner(load);
        String load2 = swingFeaturesFrame.getText("Please enter layer index");
        Scanner loadScanner2 = new Scanner(load2);
        try {
          model.loadImages(new Image(loadScanner.next()), Integer.parseInt(loadScanner2.next()));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Import successful");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Import All":
        String importAll = swingFeaturesFrame.getText("Please enter text file name");
        try {
          model.importAll(importAll);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Import All successful");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Export":
        String export = swingFeaturesFrame
            .getText("Please enter name for this image");
        Scanner exportScanner = new Scanner(export);
        String export2 = swingFeaturesFrame.getText("Please enter file format");
        Scanner scannerExport2 = new Scanner(export2);
        try {
          model.saveImages(exportScanner.next(), this.toFormat(scannerExport2.next()));
          swingFeaturesFrame.setText("Export successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Export All":
        String exportAll = swingFeaturesFrame
            .getText("Please enter base name for the images");
        Scanner exportAllScanner = new Scanner(exportAll);

        String exportAll2 = swingFeaturesFrame.getText("Please enter file format");
        Scanner scannerExportAll2 = new Scanner(exportAll2);

        try {
          model.exportAll(exportAllScanner.next(), this.toFormat(scannerExportAll2.next()));
          swingFeaturesFrame.setText("Export All successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Add Layer":
        model.addLayer();
        swingFeaturesFrame.setText("New layer added successfully");
        break;
      case "Remove Layer":
        String remove = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.removeLayer(Integer.parseInt(remove));
          swingFeaturesFrame.setText("Remove layer" + remove + " successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Visible":
        String visible = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(visible), true);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText(visible + "is set to visible");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Invisible":
        String invisible = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(invisible), false);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText(invisible + "is set to invisible");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Set Current":
        String current = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setCurrent(Integer.parseInt(current));
          swingFeaturesFrame.setText(current + "is set to current");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Checker":
        String checker = swingFeaturesFrame.getText("Please enter a height");
        Scanner checkerScanner = new Scanner(checker);
        String checker2 = swingFeaturesFrame.getText("Please enter a width");
        Scanner checkerScanner2 = new Scanner(checker2);

        try {
          ImageFormat image = new Image(Integer.parseInt(checkerScanner.next()),
              Integer.parseInt(checkerScanner2.next()));
          model.addLayer();
          model.loadImages(image, model.getCurrentLayerIndex());
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Custom CheckerBoard successfully created");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Default":
        ImageFormat image = new Image();
        model.addLayer();
        model.loadImages(image, model.getCurrentLayerIndex());
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("CheckerBoard successfully created");
        break;
      case "Mosaic":
        String mosaic = swingFeaturesFrame
            .getText("Please enter the number of seeds");
        try {
          model.createMosaic(Integer.parseInt(mosaic));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Mosaic Filtering has been successfully performed with " +
              mosaic + " seeds");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Downsize":
        String downsize = swingFeaturesFrame
            .getText("Please enter desired relative ratio of new height as doubles");
        Scanner downsizeScanner = new Scanner(downsize);
        String downsize2 = swingFeaturesFrame
            .getText("Please enter desired relative ratio of new width as doubles");
        Scanner downsizeScanner2 = new Scanner(downsize2);

        try {
          model.downSize(Double.parseDouble(downsizeScanner.next()),
              Double.parseDouble(downsizeScanner2.next()));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Downsizing successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please try again.");
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
    swingFeaturesFrame
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




