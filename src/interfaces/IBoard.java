package interfaces;

import java.awt.*;

/**
 * field itself
 */
public interface IBoard {

    int getBoardSize();
    void setBoardSize(int boardSize);
    //IField get(int x,int y);
    void move(Point from,Point to);
    boolean isGameOver();

}
