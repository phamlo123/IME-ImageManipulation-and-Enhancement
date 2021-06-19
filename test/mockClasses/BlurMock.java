package mockClasses;

import controller.ImageCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Mock class
 */
public class BlurMock implements ImageCommand {

  StringBuilder log;

  public BlurMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void go(MultiLayers model) {
    log.append("blur command has been called");
  }
}
