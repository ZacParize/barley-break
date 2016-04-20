package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Class performs all concern stuffs.
 */
public abstract class Gameplay {

    private static Point getCell(Point cell,int cellSize) {
        return new Point((int) (cell.getX()/cellSize + 1),(int) (cell.getY()/cellSize + 1));
    }

    public static boolean isArrayExists(String[][] matrix,String element) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] != null && matrix[i][j].equals(element)) return true;
        return false;
    }

    public static String[] toOneDimensionalArray(String[][] matrix) {
        String[] result = new String[(int)Math.pow(matrix.length,2)];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if (matrix[i][j] != null)
                    result[i + matrix[i].length * j] = matrix[i][j];
                else
                    result[i + matrix[i].length * j] = matrix[i - 1][j];
        return result;
    }

    public static boolean isGameOver(String[][] matrix) {
        if (matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1] == null) {
            String object = matrix[0][0];
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[j][i] == null)
                        return true;
                    else
                    if (Integer.parseInt(matrix[j][i]) < Integer.parseInt(object)) return false;
                    object = matrix[j][i];
                }
        }
        return false;
    }

    public static void move(String[][] matrix,Point from,Point to,int cellSize) {
        Point fromField = Gameplay.getCell(from,cellSize);
        Point toField = Gameplay.getCell(to,cellSize);
        String valueFrom = Gameplay.getValue(matrix,fromField);
        String valueTo = Gameplay.getValue(matrix,toField);
        if ((valueFrom == null || valueTo == null)
                && (Math.abs(fromField.getX() - toField.getX()) + Math.abs(fromField.getY() - toField.getY())) == 1
                    && (fromField.getX() - 1 >= 0 && fromField.getX() <= matrix.length && fromField.getY() <= matrix[(int) fromField.getX() - 1].length)
                        && (toField.getX() - 1 >= 0 && toField.getX() <= matrix.length && toField.getY() <= matrix[(int) toField.getX() - 1].length)) {
            matrix[(int) fromField.getX() - 1][(int) fromField.getY() - 1] = valueTo;
            matrix[(int) toField.getX() - 1][(int) toField.getY() - 1] = valueFrom;
        }
    }

    public static String getValue(String[][] matrix,Point cell) {
        if (matrix.length >= cell.getX() && matrix[(int)cell.getX() - 1].length >= cell.getY())
            return matrix[(int)cell.getX() - 1][(int)cell.getY() - 1];
        else
            return null;
    }

    public static void fillValues(String[][] matrix,int boardSize) {
        for (int iDx = 1; iDx <= Math.pow(boardSize,2); iDx++) {
            if (iDx != 1) {
                String value;
                Random random = new Random();
                do {
                    value = String.valueOf(1 + random.nextInt((int) Math.pow(boardSize, 2) - 1));
                } while (Gameplay.isArrayExists(matrix,value));
                matrix[(iDx - 1) % boardSize][(iDx - 1) / boardSize] = value;
            }
            else
                matrix[(iDx - 1)%boardSize][(iDx - 1)/boardSize] = null;
        }
    }

    public static void clear(Graphics2D g2D,JPanel panel) {
        g2D.clearRect(0,0,panel.getWidth(),panel.getHeight());
    }

    public static void paintBoard(Graphics2D g2D,String[][] matrix,int boardSize,int cellSize) {

        for (int k = 0; k <= Math.pow(boardSize,2) - 1; k+=1) {
            Rectangle2D r = new Rectangle2D.Double(cellSize*(k%boardSize), cellSize*(k/boardSize), cellSize, cellSize);
            g2D.setStroke(new BasicStroke(2));
            g2D.setColor(Color.black);
            g2D.setBackground(Color.blue);
            g2D.draw(r);
            if (matrix[k%boardSize][k/boardSize] != null)
                g2D.drawString(String.valueOf(matrix[k%boardSize][k/boardSize]), cellSize*(k%boardSize) + 20, cellSize*(k/boardSize) + 60);
            else
                g2D.drawString("", cellSize*(k%boardSize) + 20, cellSize*(k/boardSize) + 60);
        }

    }

}
