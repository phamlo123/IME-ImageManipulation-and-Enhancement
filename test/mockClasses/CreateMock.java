package mockClasses;

import controller.ImageCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;

public class CreateMock implements ImageCommand {

  StringBuilder log;

  public CreateMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void go(MultiLayers model) {
    log.append("create command has been called");
  }
}
