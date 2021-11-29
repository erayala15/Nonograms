package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class Top implements FXComponent {

  private final Controller controller;

  public Top(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane header = new HBox();

    Label title =
        new Label("Nonograms (5 Puzzles): Puzzle #" + (this.controller.getPuzzleIndex() + 1));

    header.getChildren().add(title);

    HBox.setHgrow(header, Priority.ALWAYS);

    return header;
  }
}
