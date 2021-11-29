package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameView implements FXComponent {

  private final Controller controller;

  public GameView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane layout = new VBox();

    FXComponent header = new Top(this.controller);
    FXComponent middle = new Body(this.controller);
    FXComponent footer = new Bottom(this.controller);

    layout.getChildren().add(header.render());
    layout.getChildren().add(middle.render());
    layout.getChildren().add(footer.render());

    return layout;
  }
}
