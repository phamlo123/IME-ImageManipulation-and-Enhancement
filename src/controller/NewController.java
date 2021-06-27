package controller;

import static java.lang.String.valueOf;

import gui.ImageView;
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

  private final ImageView view;
  private final MultiLayers model;

  /**
   * Creates a new NewController object used for processing images based on User input.
   *
   * @param model              the model used for processing the images
   * @param view the view used to take user input and display images
   * @throws IllegalArgumentException if the parameters are null
   */
  public NewController(MultiLayers model, ImageView view)
      throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Cannot pass null parameters");
    }
    this.view = view;
    this.model = model;
    view.setListener(this);
    view.setImage(model.getTopmost());
    setCurrentDisplay();
  }


  /**
   * Method that delegates to the model the desired functionality based on the press of a button
   * from the view.
   *
   * @param arg0 the action from the view (button pressed)
   */
  public void actionPerformed(ActionEvent arg0) {
    this.actionHelper(arg0.getActionCommand());
  }

  /**
   * Helper for actionPerformed that takes the String version of the ActionEvent given to
   * actionPerformed. This method is public for easier testing.
   *
   * @param command the command to be executed.
   */
  public void actionHelper(String command) {
    switch (command) {
      case "Blur":
        model.blurringImage();
        view.setImage(model.getTopmost());
        view.setText("Blur Filtering has been successfully performed");
        break;
      case "Sharpen":
        model.sharpeningImage();
        view.setImage(model.getTopmost());
        view.setText("Sharpen Filtering has been successfully performed");
        break;
      case "Gray":
        model.createMonochrome();
        view.setImage(model.getTopmost());
        view.setText("GrayScaling has been successfully performed");
        break;
      case "Sepia":
        model.createSepia();
        view.setImage(model.getTopmost());
        view.setText("Sepia Filtering has been successfully performed");
        break;
      case "Import":
        String load = view.getText("Please enter image file name");
        if (load.equals("-1")) {
          return;
        }
        Scanner loadScanner = new Scanner(load);
        String load2 = view.getText("Please enter layer index");
        if (load2.equals("-1")) {
          return;
        }
        Scanner loadScanner2 = new Scanner(load2);

        try {
          model.loadImages(new Image(loadScanner.next()), Integer.parseInt(loadScanner2.next()));
          view.setImage(model.getTopmost());
          view.setText("Import successful");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Import All":
        String importAll = view.getText("Please enter text file name");
        if (importAll.equals("-1")) {
          return;
        }
        try {
          model.importAll(importAll);
          view.setImage(model.getTopmost());
          view.setText("Import All successful");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Export":
        String export = view
            .getText("Please enter name for this image");
        if (export.equals("-1")) {
          return;
        }
        Scanner exportScanner = new Scanner(export);
        String export2 = view.getText("Please enter file format");
        if (export2.equals("-1")) {
          return;
        }
        Scanner scannerExport2 = new Scanner(export2);
        try {
          model.saveImages(exportScanner.next(), this.toFormat(scannerExport2.next()));
          view.setText("Export successfully");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Export All":
        String exportAll = view
            .getText("Please enter base name for the images");
        if (exportAll.equals("-1")) {
          return;
        }
        Scanner exportAllScanner = new Scanner(exportAll);

        String exportAll2 = view.getText("Please enter file format");
        if (exportAll2.equals("-1")) {
          return;
        }
        Scanner scannerExportAll2 = new Scanner(exportAll2);

        try {
          model.exportAll(exportAllScanner.next(), this.toFormat(scannerExportAll2.next()));
          view.setText("Export All successfully");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Add Layer":
        model.addLayer();
        view.setText("New layer added successfully");
        break;
      case "Remove Layer":
        String remove = view.getText("Please enter a layer index");
        if (remove.equals("-1")) {
          return;
        }
        try {
          model.removeLayer(Integer.parseInt(remove));
          view.setText("Remove layer" + remove + " successfully");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Visible":
        String visible = view.getText("Please enter a layer index");
        if (visible.equals("-1")) {
          return;
        }
        try {
          model.setInvisibility(Integer.parseInt(visible), true);
          view.setImage(model.getTopmost());
          view.setText(visible + "is set to visible");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + ". Please try again.");
          return;
        }
        break;
      case "Invisible":
        String invisible = view.getText("Please enter a layer index");
        if (invisible.equals("-1")) {
          return;
        }
        try {
          model.setInvisibility(Integer.parseInt(invisible), false);
          view.setImage(model.getTopmost());
          view.setText(invisible + "is set to invisible");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Set Current":
        String current = view.getText("Please enter a layer index");
        if (current.equals("-1")) {
          return;
        }
        try {
          model.setCurrent(Integer.parseInt(current));
          view.setText(current + "is set to current");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Checker":
        String checker = view.getText("Please enter a height");
        if (checker.equals("-1")) {
          return;
        }
        Scanner checkerScanner = new Scanner(checker);
        String checker2 = view.getText("Please enter a width");
        if (checker2.equals("-1")) {
          return;
        }
        Scanner checkerScanner2 = new Scanner(checker2);

        try {
          ImageFormat image = new Image(Integer.parseInt(checkerScanner.next()),
              Integer.parseInt(checkerScanner2.next()));
          model.addLayer();
          int layer = model.getNumLayers() - 1;
          model.loadImages(image, layer);
          view.setImage(model.getTopmost());
          view.setText("Custom CheckerBoard successfully created");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Default":
        ImageFormat image = new Image();
        model.addLayer();
        int layer = model.getNumLayers() - 1;
        model.loadImages(image, layer);
        view.setImage(model.getTopmost());
        view.setText("CheckerBoard successfully created");
        break;
      case "Mosaic":
        String mosaic = view
            .getText("Please enter the number of seeds");
        if (mosaic.equals("-1")) {
          return;
        }
        try {
          model.createMosaic(Integer.parseInt(mosaic));
          view.setImage(model.getTopmost());
          view.setText("Mosaic Filtering has been successfully performed with " +
              mosaic + " seeds");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
          return;
        }
        break;
      case "Downsize":
        String downsize = view
            .getText("Please enter desired relative ratio of new height as doubles");
        if (downsize.equals("-1")) {
          return;
        }
        Scanner downsizeScanner = new Scanner(downsize);
        String downsize2 = view
            .getText("Please enter desired relative ratio of new width as doubles");
        if (downsize2.equals("-1")) {
          return;
        }
        Scanner downsizeScanner2 = new Scanner(downsize2);

        try {
          model.downSize(Double.parseDouble(downsizeScanner.next()),
              Double.parseDouble(downsizeScanner2.next()));
          view.setImage(model.getTopmost());
          view.setText("Downsizing successfully");
        } catch (IllegalArgumentException e) {
          view.setText(e.getMessage() + " Please try again.");
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
    view
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




