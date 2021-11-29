package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;

public class Body implements FXComponent {
  private final Controller controller;

  public Body(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    if (this.controller.isSolved()) {

      Pane win_display = new HBox();
      Parent board = this.makeBoard();
      Parent win_notification = this.win();

      win_display.getChildren().add(board);
      win_display.getChildren().add(win_notification);

      HBox.setHgrow(win_display, Priority.ALWAYS);

      return win_display;
    } else {
      return this.makeBoard();
    }
  }

  private Parent win() {
    Pane text_display = new HBox();
    Label text = new Label("YOU WIN!");
    text_display.getChildren().add(text);
    HBox.setHgrow(text_display, Priority.ALWAYS);
    return text_display;
  }

  private Parent makeBoard() {
    GridPane board = new GridPane();
    board.setHgap(10);
    board.setVgap(10);

    for (int i = 0; i < this.controller.getClues().getWidth(); i++) {
      VBox clues_col = new VBox();
      boolean all_zero = true;
      for (int j = 0; j < this.controller.getClues().getColCluesLength(); j++) {
        int val = this.controller.getClues().getColClues(i)[j];
        Label l;
        if (val == 0) {
          l = new Label(" ");
        } else {
          l = new Label("" + val);
          all_zero = false;
        }
        if (all_zero && j == this.controller.getClues().getColCluesLength() - 1) {
          l = new Label("" + 0);
        }
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        clues_col.getChildren().add(l);
      }
      board.add(clues_col, i + 1, 0);
    }

    for (int i = 0; i < this.controller.getClues().getHeight(); i++) {
      HBox clues_row = new HBox();
      boolean all_zero = true;
      for (int j = 0; j < this.controller.getClues().getRowCluesLength(); j++) {
        int val = this.controller.getClues().getRowClues(i)[j];
        Label l;
        if (val == 0) {
          l = new Label(" ");
        } else {
          l = new Label("" + val);
          all_zero = false;
        }
        if (all_zero && j == this.controller.getClues().getRowCluesLength() - 1) {
          l = new Label("" + 0);
        }
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        clues_row.getChildren().add(l);
      }
      board.add(clues_row, 0, i + 1);
    }

    for (int i = 1; i <= this.controller.getClues().getHeight(); i++) {
      for (int j = 1; j <= this.controller.getClues().getWidth(); j++) {
        board.add(makeButton(i - 1, j - 1), j, i);
      }
    }

    return board;
  }

  private Button makeButton(int i, int j) {

    Button button = new Button();
    button.setOnMouseClicked(
        mouseEvent -> {
          if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (controller.isEliminated(i, j)) {
              controller.toggleEliminated(i, j);
            }
            controller.toggleShaded(i, j);
          } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            if (controller.isShaded(i, j)) {
              controller.toggleShaded(i, j);
            }
            controller.toggleEliminated(i, j);
          }
        });
    if (this.controller.isShaded(i, j)) {
      button.setStyle("-fx-background-color: #3CDFFF;");
    } else if (this.controller.isEliminated(i, j)) {
      button.setText("X");
      button.setStyle("-fx-background-color: #FF0000;");
    } else {
      button.setText(" ");
    }

    button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    return button;
  }
}
