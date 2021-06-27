
package controller;

import static java.lang.String.valueOf;

import gui.SwingFeaturesFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import model.enums.FileFormat;
import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

public class NewController implements ActionListener {

  private final SwingFeaturesFrame swingFeaturesFrame;
  private final MultiLayers model;

  public NewController(MultiLayers model, SwingFeaturesFrame swingFeaturesFrame)
      throws IllegalArgumentException {
    if (model == null || swingFeaturesFrame == null) {
      throw new IllegalArgumentException();
    }
    this.swingFeaturesFrame = swingFeaturesFrame;
    this.model = model;
    swingFeaturesFrame.setListener(this);
    swingFeaturesFrame.setImage(model.getTopmost());
    setCurrentDisplay();
  }


  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
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
        String load = swingFeaturesFrame.getText("Please enter image file name and layer index");
        Scanner loadScanner = new Scanner(load);
        try {
          model.loadImages(new Image(loadScanner.next()), Integer.parseInt(loadScanner.next()));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Import successful");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + ". Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Import All":
        String importAll = swingFeaturesFrame.getText("Please enter text file name");
        try {
          model.importAll(importAll);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Import All successful");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Export":
        swingFeaturesFrame.saveFile();
        String export = swingFeaturesFrame
            .getText("Please enter name for this image and file format");
        Scanner exportScanner = new Scanner(export);
        try {
          model.saveImages(exportScanner.next(), this.toFormat(exportScanner.next()));
          swingFeaturesFrame.setText("Export successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Export All":
        String exportAll = swingFeaturesFrame
            .getText("Please enter name for this image and file format");
        Scanner exportAllScanner = new Scanner(exportAll);
        try {
          model.exportAll(exportAllScanner.next(), this.toFormat(exportAllScanner.next()));
          swingFeaturesFrame.setText("Export All successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Add Layer":
        model.addLayer();
        swingFeaturesFrame.setText("Add layer was selected");
        break;
      case "Remove Layer":
        String remove = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.removeLayer(Integer.parseInt(remove));
          swingFeaturesFrame.setText("Remove layer" + remove + " successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + ". Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Visible":
        String visible = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(visible), true);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText(visible + "is set to visible");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + ". Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Invisible":
        String invisible = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(invisible), false);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText(invisible + "is set to invisible");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Set Current":
        String current = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setCurrent(Integer.parseInt(current));
          swingFeaturesFrame.setText(current + "is set to current");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Checker":
        String checker = swingFeaturesFrame.getText("Please enter a height and a width");
        Scanner checkerScanner = new Scanner(checker);
        try {
          ImageFormat image = new Image(Integer.parseInt(checkerScanner.next()),
              Integer.parseInt(checkerScanner.next()));
          model.addLayer();
          model.loadImages(image, model.getCurrentLayerIndex());
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Custom CheckerBoard successfully created");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
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
              mosaic +" seeds");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Downsize":
        String downsize = swingFeaturesFrame
            .getText("Please enter desired width and height as doubles");
        Scanner downsizeScanner = new Scanner(downsize);
        try {
          model.downSize(Double.parseDouble(downsizeScanner.next()),
              Double.parseDouble(downsizeScanner.next()));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Downsizing successfully");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText(e.getMessage() + " Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
    }
    setCurrentDisplay();
  }

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




