package com.comp301.a09nonograms.model;

import java.util.HashMap;
import java.util.Objects;

public class BoardImpl implements Board {
  int[][] board;

  public BoardImpl(int height, int width) {
    board = new int[height][width];
    for(int i=0; i<board.length; i++) {
      for(int j=0; j<board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    return board[row][col] == 1;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return board[row][col] == 2;
  }

  @Override
  public boolean isSpace(int row, int col) {
    return board[row][col] == 0;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if(isShaded(row, col)) {
      board[row][col] = 0;
    } else {
      board[row][col] = 1;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if(isEliminated(row, col)) {
      board[row][col] = 0;
    } else {
      board[row][col] = 2;
    }
  }

  @Override
  public void clear() {
    for(int i=0; i<board.length; i++) {
      for(int j=0; j<board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }
}
