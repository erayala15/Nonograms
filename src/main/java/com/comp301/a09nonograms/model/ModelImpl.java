package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  List<Clues> clues;
  List<ModelObserver> observers;
  Clues tempClues;
  int[][] gameBoard;
  int index;

  public ModelImpl(List<Clues> clues) {
    this.clues = clues;
    this.observers = new ArrayList<ModelObserver>();
    setGameBoard(0);
  }

  @Override
  public boolean isShaded(int row, int col) {
    return gameBoard[row][col] == 2;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return gameBoard[row][col] == 1;
  }

  @Override
  public boolean isSpace(int row, int col) {
    return gameBoard[row][col] == 0;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    gameBoard[row][col] = (gameBoard[row][col] != 2) ? 2 : 0;
    update();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    gameBoard[row][col] = (gameBoard[row][col] != 1) ? 1 : 0;
    update();
  }

  @Override
  public void clear() {
    setGameBoard(index);
    update();
  }

  @Override
  public int getWidth() {
    return tempClues.getWidth();
  }

  @Override
  public int getHeight() {
    return tempClues.getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return tempClues.getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return tempClues.getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return tempClues.getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return tempClues.getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return clues.size();
  }

  @Override
  public int getPuzzleIndex() {
    return index;
  }

  @Override
  public void setPuzzleIndex(int index) {
    setGameBoard(index);
    update();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    for (int row = 0; row < tempClues.getHeight(); row++) {
      int[] rowClues = tempClues.getRowClues(row);
      if (!lineSolved(rowClues, gameBoard[row])) {
        return false;
      }
    }

    for (int col = 0; col < tempClues.getWidth(); col++) {
      int[] colClues = tempClues.getColClues(col);
      int[] line = new int[tempClues.getHeight()];
      for (int r = 0; r < line.length; r++) {
        line[r] = gameBoard[r][col];
      }
      if (!lineSolved(colClues, line)) {
        return false;
      }
    }

    return true;
  }

  private void setGameBoard(int index) {
    this.index = index;
    gameBoard = new int[clues.get(index).getHeight()][clues.get(index).getWidth()];
    tempClues = clues.get(index);
  }

  private void update() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  private boolean lineSolved(int[] clues, int[] line) {
    int lineNum = -1;
    boolean updated = true;
    for (int i : line) {
      if (i == 2) {
        if (updated) {
          lineNum++;
          while(lineNum < clues.length && clues[lineNum] == 0) {
            lineNum++;
          }
          updated = false;
        }
        if(lineNum >= clues.length) {
          return false;
        }
        clues[lineNum] -= 1;
      } else if (i == 1) {
        updated = true;
      }
    }
    for (int i : clues) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }
}