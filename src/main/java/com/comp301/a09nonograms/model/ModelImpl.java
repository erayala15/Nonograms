package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelImpl implements Model {
  List<Clues> cluesList;
  List<ModelObserver> observers;
  List<Board> boards;
  int index;

  public ModelImpl(List<Clues> clues) {
    cluesList = new ArrayList<>();
    cluesList.addAll(clues);
    index = 0;
    observers = new ArrayList<>();
    for (Clues value : cluesList) {
      boards.add(new BoardImpl(value.getHeight(), value.getWidth()));
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    return boards.get(index).isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return boards.get(index).isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return boards.get(index).isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    boards.get(index).toggleCellShaded(row, col);
    update();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    boards.get(getPuzzleIndex()).toggleCellEliminated(row, col);
    update();
  }

  @Override
  public void clear() {
    boards.get(index).clear();
    update();
  }

  @Override
  public int getWidth() {
    return cluesList.get(this.index).getWidth();
  }

  @Override
  public int getHeight() {
    return cluesList.get(this.index).getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return cluesList.get(this.index).getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
     return cluesList.get(this.index).getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return cluesList.get(this.index).getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return cluesList.get(this.index).getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return cluesList.size();
  }

  @Override
  public int getPuzzleIndex() {
    return index;
  }

  @Override
  public void setPuzzleIndex(int index) {
    this.index = index;
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
    boolean rMatching = false;
    boolean cMatching = false;
    for (int i = 0; i < cluesList.get(index).getHeight(); i++) {
      int numRowClues = cluesList.get(index).getRowCluesLength();
      int actual = 0;
      int clicked = 0;
      for (int j = 0; j < numRowClues; j++) {
        actual = actual + cluesList.get(index).getRowClues(i)[j];
      }
      for (int k = 0; k < cluesList.get(index).getWidth(); k++) {
        if (boards.get(index).isShaded(i, k)) {
          clicked++;
        }
      }
      rMatching = clicked == actual;
    }
    for (int i = 0; i < cluesList.get(index).getWidth(); i++) {
      int numColClues = cluesList.get(index).getColCluesLength();
      int actual = 0;
      int clicked = 0;
      for (int j = 0; j < numColClues; j++) {
        actual = actual + cluesList.get(index).getColClues(i)[j];
      }
      for (int k = 0; k < cluesList.get(index).getHeight(); k++) {
        if (boards.get(index).isShaded(k, i)) {
          clicked++;
        }
      }
      cMatching = clicked == actual;
    }
    return cMatching && rMatching;
  }


  private void update() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public Clues getClues() {
    return cluesList.get(index);
  }
}
