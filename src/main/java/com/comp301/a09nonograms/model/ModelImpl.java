package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.Arrays;
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

    boolean solved = true;
    for(int row = 0; row < tempClues.getHeight(); row++) {
      if(!solved) {
        return false;
      }
      int[] rowClues = tempClues.getRowClues(row);
      int[] rowBoard = new int[rowClues.length];

      boolean shaded = false;
      int shadedTiles = 0;
      int consecutiveTiles = 0;


      for(int i=0; i<rowBoard.length; i++) {
        rowBoard[i] = 0;
      }
      // building rowBoard
      for (int i = 0; i < gameBoard[row].length; i++) {
        int val = gameBoard[row][i];
        // if shaded
        if(val == 2) {
          shaded = true;
          consecutiveTiles++;
        }
        else {
          if(consecutiveTiles > 0) {
            rowBoard[i] = consecutiveTiles;
          } else if(!shaded) {
            rowBoard[i] = consecutiveTiles;
          }
          consecutiveTiles = 0;
        }
      }

      // if all are shaded
      if(consecutiveTiles > 0) {
        rowBoard[rowBoard.length - 1] = consecutiveTiles;
      } else if(!shaded) {
        rowBoard[rowBoard.length - 1] = consecutiveTiles;
      }

      solved = Arrays.equals(rowClues, rowBoard);

    }
    return solved;
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
          while (lineNum < clues.length && clues[lineNum] == 0) {
            lineNum++;
          }
          updated = false;
        }
        if (lineNum >= clues.length) {
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
