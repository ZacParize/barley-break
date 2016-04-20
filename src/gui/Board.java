package gui;

import interfaces.IBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Board implementation
 */
public class Board extends JPanel implements MouseListener,IBoard {

    private java.util.List<Point> coordinates = new ArrayList();
    private int cellSize;
    private int boardSize;
    private String[][] matrix;

    public Board() {
        this.setBoardSize(5);
        this.setMatrix(new String[this.getBoardSize()][this.getBoardSize()]);
        this.setCellSize(100);
        Gameplay.fillValues(this.matrix,this.getBoardSize());
        this.addMouseListener(this);
    }

    /**
     *
     * @return
     */
    public int getCellSize() {
        return this.cellSize;
    }

    /**
     *
     * @param cellSize
     */
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    /**
     *
     * @return
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     *
     * @param boardSize
     */
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * get game matrix
     * @return two-dimensional array
     */
    public String[][] getMatrix() {
        return matrix;
    }

    /**
     * set game matrix
     * @param matrix two-dimensional array
     */
    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    /**
     *
     * @return
     */
    public java.util.List<Point> getCoordinates() {
        return coordinates;
    }

    @Override
    public void move(Point from,Point to) {
        Gameplay.move(this.matrix, from, to, this.getCellSize());
    }

    @Override
    public boolean isGameOver() {
        return Gameplay.isGameOver(this.getMatrix());
    }

    @Override
    public void paintComponent(Graphics g) {
        this.draw((Graphics2D)g);
    }

    private void draw(Graphics2D g2d) {
        super.paintComponent(g2d);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setFont(new Font("Arial", Font.PLAIN, 60));
        Gameplay.paintBoard(g2d, this.matrix, this.getBoardSize(), this.getCellSize());
        /*Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        Rectangle2D r = new Rectangle2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.gray);*/

        /*for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
            g2d.draw(at.createTransformedShape(r));
        }*/

        /*for (double deg = 0; deg < 5; deg += 5) {
            AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(r));
        }*/

    }

    /*private void showEventDemo(){
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("OK");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("OK");
        submitButton.setActionCommand("Submit");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }*/


    @Override
    public void mousePressed(MouseEvent e) {
        this.getCoordinates().clear();
        this.getCoordinates().add(new Point(e.getX(), e.getY()));
        //System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.getCoordinates().add(new Point(e.getX(), e.getY()));
        this.move(this.getCoordinates().get(0), this.getCoordinates().get(1));
        this.repaint();
        if (this.isGameOver()) System.exit(0);
        //System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /*for (int i = 0; i < this.getMatrix().length; i++)
            for (int j = 0; j < this.getMatrix()[i].length; j++) {
                if (i == this.getMatrix().length - 1 && j == this.getMatrix()[i].length - 1)
                    this.getMatrix()[i][j] = null;
                else
                    this.getMatrix()[i][j] = String.valueOf(i + j*this.getBoardSize()  +1);
            }
        this.repaint();*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouse clicked");
    }

}
