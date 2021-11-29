package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Bottom implements FXComponent {

  private final Controller controller;

  public Bottom(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane footer = new HBox();

    Button previous = new Button();
    previous.setText("Previous Puzzle");
    previous.setOnAction(actionEvent -> controller.prevPuzzle());

    Button next = new Button();
    next.setOnAction(actionEvent -> controller.nextPuzzle());
    next.setText("Next Puzzle");

    Button random = new Button();
    random.setText("Random Puzzle");
    random.setOnAction(actionEvent -> controller.randPuzzle());

    Button reset = new Button();
    reset.setText("Reset Puzzle");
    reset.setOnAction(actionEvent -> controller.clearBoard());

    footer.getChildren().add(previous);
    footer.getChildren().add(next);
    footer.getChildren().add(random);
    footer.getChildren().add(reset);

    return footer;
  }
}
