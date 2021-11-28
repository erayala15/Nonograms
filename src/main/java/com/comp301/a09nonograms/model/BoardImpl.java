package com.comp301.a09nonograms.model;

import java.util.HashMap;
import java.util.Objects;

public class BoardImpl implements Board {
  private final HashMap<int[], String> board;
  private final int size;

  public BoardImpl(int size) {
    this.size = size;
    board = new HashMap<>();
    int i = 1;
    while (i <= size) {
      for (int j = 1; j <= size; j++) {
        board.put(new int[] {i, j}, "Space");
      }
      i++;
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (!board.containsKey(new int[] {row, col})) {
      throw new IllegalArgumentException();
    } else return Objects.equals(board.get(new int[] {row, col}), "Shaded");
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (!board.containsKey(new int[] {row, col})) {
      throw new IllegalArgumentException();
    } else return Objects.equals(board.get(new int[]{row, col}), "Eliminated");
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (!board.containsKey(new int[] {row, col})) {
      throw new IllegalArgumentException();
    } else return Objects.equals(board.get(new int[]{row, col}), "Space");
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (!board.containsKey(new int[] {row, col})) {
      throw new IllegalArgumentException();
    }
    if (Objects.equals(board.get(new int[] {row, col}), "Shaded")) {
      return;
    }
    board.replace(new int[] {row, col}, "Shaded");
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (!board.containsKey(new int[] {row, col})) {
      throw new IllegalArgumentException();
    }
    if (Objects.equals(board.get(new int[] {row, col}), "Eliminated")) {
      return;
    }
    board.replace(new int[] {row, col}, "Eliminated");
  }

  @Override
  public void clear() {
    board.clear();
    int i = 1;
    while (i <= this.size) {
      for (int j = 1; j <= this.size; j++) {
        board.put(new int[] {i, j}, "Space");
      }
      i++;
    }
  }
}
