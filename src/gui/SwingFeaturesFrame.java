package gui;

import controller.NewController;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class opens the main window, that has different elements illustrated in it. It also doubles
 * up as all the listeners for simplicity. Such a design is not recommended in general.
 */

public class SwingFeaturesFrame extends JFrame implements ItemListener, ListSelectionListener {

  private final JLabel imageLabel;

  private JPanel imagePanel;


  private JLabel checkboxDisplay;

  private final JButton downSizePanelButton;
  private final JButton mosaicPanelButton;
  private final JButton sharpenButton;
  private final JButton createGrayScaleButton;
  private final JButton createSepiaButton;
  private final JButton saveAllImagesButton;
  private final JButton saveAnImageButton;
  private final JButton blurButton;
  private final JButton loadImagePanelButton;
  private final JButton loadAllImagesButton;
  private final JScrollPane imageScroll;

  private final JButton createCheckerBoardButton;
  private final JButton createDefaultCheckerBoardButton;
  private final JButton makeVisibleButton;
  private final JButton makeInvisibleButton;
  private final JButton addLayerButton;
  private final JButton removeLayerButton;
  private final JButton setCurrenButton;

  private JTextArea textAreaWidth;
  private JTextArea textAreaHeight;


  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;


  public SwingFeaturesFrame() {
    super();
    setTitle("Image Processor");
    setSize(1000, 1000);

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imagePanel = new JPanel();

    TitledBorder title = BorderFactory.createTitledBorder("Image");
    title.setTitleJustification(TitledBorder.CENTER);
    imagePanel.setBorder(title);
    imagePanel.add(new JScrollPane());
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setSize(new Dimension(500, 500));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    imageScroll = new JScrollPane(imageLabel);
    imagePanel.add(imageScroll);

    //Filtering Boxes
    JPanel filteringBoxesPanel = new JPanel();

    TitledBorder title2 = BorderFactory.createTitledBorder("Filtering Options");
    title2.setTitleJustification(TitledBorder.CENTER);
    filteringBoxesPanel.setBorder(title2);
    filteringBoxesPanel.setPreferredSize(new Dimension(150, 120));
    filteringBoxesPanel.setLayout(new GridLayout(18, 1, 0, 0));
    mainPanel.add(filteringBoxesPanel, BorderLayout.WEST);

    blurButton = new JButton("Blur");
    blurButton.setSize(new Dimension(150, 20));
    blurButton.setActionCommand("Blur");
    blurButton.setMaximumSize(new Dimension(150, 20));

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setSize(new Dimension(150, 20));
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.setMaximumSize(new Dimension(150, 20));

    createGrayScaleButton = new JButton("Create Grayscale");
    createGrayScaleButton.setSize(new Dimension(150, 20));
    createGrayScaleButton.setActionCommand("Gray");
    createGrayScaleButton.setMaximumSize(new Dimension(150, 20));

    createSepiaButton = new JButton("Create Sepia");
    createSepiaButton.setSize(new Dimension(150, 20));
    createSepiaButton.setActionCommand("Sepia");
    createSepiaButton.setMaximumSize(new Dimension(150, 20));

    downSizePanelButton = new JButton("Downsize");
    downSizePanelButton.setSize(new Dimension(150, 20));
    downSizePanelButton.setActionCommand("Downsize");

    downSizePanelButton.setMaximumSize(new Dimension(150, 20));

    mosaicPanelButton = new JButton("Create Mosaic");
    mosaicPanelButton.setSize(new Dimension(150, 20));
    mosaicPanelButton.setActionCommand("Mosaic");
    mosaicPanelButton.setMaximumSize(new Dimension(150, 20));

    filteringBoxesPanel.add(blurButton);
    filteringBoxesPanel.add(sharpenButton);
    filteringBoxesPanel.add(createGrayScaleButton);

    filteringBoxesPanel.add(createSepiaButton);
    filteringBoxesPanel.add(downSizePanelButton);
    filteringBoxesPanel.add(mosaicPanelButton);

    JPanel otherOptions = new JPanel();

    otherOptions.setLayout(new BorderLayout());

    otherOptions.setPreferredSize(new Dimension(250, 100));
    mainPanel.add(otherOptions, BorderLayout.EAST);

    JPanel checkerBoard = new JPanel();
    TitledBorder checkerBoardTitle = BorderFactory.createTitledBorder("Checker Board");
    checkerBoardTitle.setTitleJustification(TitledBorder.CENTER);
    checkerBoard.setBorder(checkerBoardTitle);
    checkerBoard.setLayout(new GridLayout(4, 2, 0, 0));
    checkerBoard.setPreferredSize(new Dimension(200, 200));

    createCheckerBoardButton = new JButton("Create New Checker Board");
    checkerBoard.add(createCheckerBoardButton);
    createDefaultCheckerBoardButton = new JButton("Create New Default Checker Board");
    checkerBoard.add(createDefaultCheckerBoardButton);

    //text area
    textAreaWidth = new JTextArea(1, 1);
    textAreaWidth.setBorder(BorderFactory.createTitledBorder("CheckerBoard Width"));
    checkerBoard.add(textAreaWidth);

    textAreaHeight = new JTextArea(1, 1);
    textAreaHeight.setBorder(BorderFactory.createTitledBorder("CheckerBoard Height"));
    checkerBoard.add(textAreaHeight);

    JPanel layers = new JPanel();
    TitledBorder layersTitle = BorderFactory.createTitledBorder("Layers");
    layersTitle.setTitleJustification(TitledBorder.CENTER);
    layers.setBorder(layersTitle);
    layers.setLayout(new GridLayout(10, 1, 0, 0));
    makeInvisibleButton = new JButton("Make Invisible");
    makeVisibleButton = new JButton("Make Visible");
    makeInvisibleButton.setActionCommand("Invisible");
    makeVisibleButton.setActionCommand("Visible");
    layers.add(makeInvisibleButton);
    layers.add(makeVisibleButton);
    addLayerButton = new JButton("Add a Layer");
    addLayerButton.setActionCommand("Add Layer");
    removeLayerButton = new JButton("Remove a Layer");
    removeLayerButton.setActionCommand("Remove Layer");
    layers.add(addLayerButton);
    layers.add(removeLayerButton);

    setCurrenButton = new JButton("Set Current");
    setCurrenButton.setActionCommand("Set Current");

    layers.add(setCurrenButton);


    otherOptions.add(checkerBoard, BorderLayout.NORTH);
    otherOptions.add(layers, BorderLayout.CENTER);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    TitledBorder title3 = BorderFactory.createTitledBorder("File");
    title3.setTitleJustification(TitledBorder.CENTER);
    dialogBoxesPanel.setBorder(title3);
    dialogBoxesPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    dialogBoxesPanel.setLayout(new FlowLayout());

    mainPanel.add(dialogBoxesPanel, BorderLayout.PAGE_END);

    //load an Image
    JPanel loadImagePanel = new JPanel();
    loadImagePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadImagePanel);
    loadImagePanelButton = new JButton("Load an Image");
    loadImagePanelButton.setActionCommand("Import");
    loadImagePanel.add(loadImagePanelButton);

    //Load All Images
    JPanel loadAllImages = new JPanel();
    loadAllImages.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadAllImages);
    loadAllImagesButton = new JButton("Load All Images");
    loadAllImagesButton.setActionCommand("Import All");
    loadAllImages.add(loadAllImagesButton);

    //Save an Image
    JPanel saveAnImage = new JPanel();
    saveAnImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAnImage);
    saveAnImageButton = new JButton("Save");
    saveAnImageButton.setActionCommand("Export");
    saveAnImage.add(saveAnImageButton);

    //Save all Images
    JPanel saveAllImage = new JPanel();
    saveAllImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAllImage);
    saveAllImagesButton = new JButton("Save All");
    saveAllImagesButton.setActionCommand("Export All");
    saveAllImage.add(saveAllImagesButton);


  }


  @Override
  public void itemStateChanged(ItemEvent arg0) {
    // TODO Auto-generated method stub
    String who = ((JCheckBox) arg0.getItemSelectable()).getActionCommand();

    switch (who) {
      case "CB1":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 1 was selected");
        } else {
          checkboxDisplay.setText("Check box 1 was deselected");
        }
        break;
      case "CB2":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 2 was selected");
        } else {
          checkboxDisplay.setText("Check box 2 was deselected");
        }
        break;
      case "CB3":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 3 was selected");
        } else {
          checkboxDisplay.setText("Check box 3 was deselected");
        }
        break;
      case "CB4":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 4 was selected");
        } else {
          checkboxDisplay.setText("Check box 4 was deselected");
        }
        break;

      case "CB5":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 5 was selected");
        } else {
          checkboxDisplay.setText("Check box 5 was deselected");
        }
        break;

    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // We don't know which list called this callback, because we're using it
    // for two lists.  In practice, you should use separate listeners
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The source object is " + e.getSource(), "Source", JOptionPane.PLAIN_MESSAGE);
    // Regardless, the event information tells us which index was selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The changing index is " + e.getFirstIndex(), "Index", JOptionPane.PLAIN_MESSAGE);
    // This gets us the string value that's currently selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The current string item is " + this.listOfStrings.getSelectedValue(), "Selected string",
        JOptionPane.PLAIN_MESSAGE);
    // This gets us the integer value that's currently selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The current number item is " + this.listOfIntegers.getSelectedValue(), "Selected integer",
        JOptionPane.PLAIN_MESSAGE);
  }


  public void setListener(NewController controller) {

    downSizePanelButton.addActionListener(controller);
    mosaicPanelButton.addActionListener(controller);
    sharpenButton.addActionListener(controller);
    createGrayScaleButton.addActionListener(controller);
    createSepiaButton.addActionListener(controller);
    saveAllImagesButton.addActionListener(controller);
    saveAnImageButton.addActionListener(controller);
    blurButton.addActionListener(controller);
    loadImagePanelButton.addActionListener(controller);
    loadAllImagesButton.addActionListener(controller);
    createCheckerBoardButton.addActionListener(controller);
    createDefaultCheckerBoardButton.addActionListener(controller);
    makeVisibleButton.addActionListener(controller);
    makeInvisibleButton.addActionListener(controller);
    addLayerButton.addActionListener(controller);
    removeLayerButton.addActionListener(controller);
    setCurrenButton.addActionListener(controller);
  }

  public String getText(String string) {
    return JOptionPane.showInputDialog(string);
  }

  public void setText(String string) {
    JOptionPane.showMessageDialog(this, string);
  }

  public void setImage(BufferedImage image) {
    imageLabel.setIcon(new ImageIcon(image));
    imageScroll.setViewportView(imageLabel);
  }

}
