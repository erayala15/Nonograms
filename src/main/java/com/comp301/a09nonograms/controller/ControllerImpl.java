package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;
import com.comp301.a09nonograms.model.Model;

import java.util.Random;

public class ControllerImpl implements Controller {
  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public Clues getClues() {
    return model.getClues();
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    int pIndex = model.getPuzzleIndex();
    int pCount = model.getPuzzleCount();
    if (pIndex + 1 < pCount) {
      model.setPuzzleIndex(pIndex + 1);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void prevPuzzle() {
    int pIndex = model.getPuzzleIndex();
    if (pIndex > 0) {
      model.setPuzzleIndex(pIndex - 1);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void randPuzzle() {
    Random random = new Random();
    int rInt = random.nextInt(model.getPuzzleCount());
    model.setPuzzleIndex(rInt);
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
