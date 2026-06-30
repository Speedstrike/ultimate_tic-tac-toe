/*
 * Copyright (c) 2026 Aaryan Karlapalem
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import module java.base;

public class Box extends JButton {
    private Player status = Player.NEITHER;
    protected Cell[][] miniBox;
    protected JButton boxImage;
    protected int cellsFilled;
    
    protected Box(int xBox, int yBox, int size, boolean ai) {
        this.setSize(Game.screenWidth, Game.screenHeight);

        this.miniBox = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int iX = i;
                final int jX = j;
                this.miniBox[i][j] = new Cell(xBox, yBox);
                this.add(this.miniBox[i][j]);
                this.miniBox[i][j].addActionListener(e -> {this.miniBox[iX][jX].playerCellClicked(e);});
            }
        }

        this.boxImage = new JButton();
        this.boxImage.setText("DRAW");

        this.setLayout(new GridLayout(size, size));
    }

    //Methods to check if there is a bingo in the CELLS to set the box to an image
    public boolean isVertical() {
        for (int i = 0; i < Main.game.gridSize; i++) {
            for (int j = 0; j < Main.game.gridSize - 1; j++) {
                if (this.miniBox[j][i].getStatus().equals(this.miniBox[j + 1][i].getStatus())) {
                    if (j == Main.game.gridSize - 2) {
                        if (this.miniBox[j][i].getStatus().equals(Player.NEITHER)) {
                            break;
                        }
                        this.setStatus(this.miniBox[j][i].getStatus());
                        System.out.println("V");
                        return true;
                    }
                }
                else {
                    break;
                }
            }
        } 
        return false;
    }

    public boolean isHorizontal() {
        for (int i = 0; i < Main.game.gridSize; i++) {
            for (int j = 0; j < Main.game.gridSize - 1; j++) {
                if (this.miniBox[i][j].getStatus().equals(this.miniBox[i][j + 1].getStatus())) {
                    if (j == Main.game.gridSize - 2) {
                        if (this.miniBox[i][j].getStatus().equals(Player.NEITHER)) {
                            break;
                        }
                        this.setStatus(this.miniBox[i][j].getStatus());
                        System.out.println("H");
                        return true;
                    }
                }
                else {
                    break;
                }
            }
        } 
        return false;
    }

    public boolean isDiagonal() {
        for (int i = 0; i < Main.game.gridSize - 1; i++) {
            if (this.miniBox[i][i].getStatus().equals(this.miniBox[i + 1][i + 1].getStatus())) {
                if (i + 2 == Main.game.gridSize) {
                    if (this.miniBox[i][i].getStatus().equals(Player.NEITHER)) {
                        break;
                    }
                    this.setStatus(this.miniBox[2][2].getStatus());
                    System.out.println("D");
                    return true;
                }
            }
            else {
                break;
            }
        }

        for (int i = 0; i < Main.game.gridSize - 1; i++) {
            if (this.miniBox[i][Main.game.gridSize - i - 1].getStatus().equals(this.miniBox[i + 1][Main.game.gridSize - i - 2].getStatus())) {
                if (i + 2 == Main.game.gridSize) {
                    if (this.miniBox[i][Main.game.gridSize - i - 1].getStatus().equals(Player.NEITHER)) {
                        break;
                    }
                    this.setStatus(this.miniBox[i][Main.game.gridSize - i - 1].getStatus());
                    return true;
                }
            }
            else {
                break;
            }
        }
        return false;
    }

    public void bingoCheck() {
        if (this.isVertical() || this.isHorizontal() || this.isDiagonal()) {
            this.lockBox();
            switch (this.getStatus()) {
                case RED: {
                    this.boxImage.setIcon(new ImageIcon(new ImageIcon("assets/RedX.png").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
                    break;
                }
                case BLUE: {
                    this.boxImage.setIcon(new ImageIcon(new ImageIcon("assets/BlueO.png").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
                    break;
                }
                default: {
                    break;
                }
            }

            if (Main.game.boxesFilled == Main.game.gridSize * Main.game.gridSize) {
                Main.game.setGameWinner(Player.NEITHER);
            }

            if ((Main.game.isHorizontal() || Main.game.isVertical() || Main.game.isDiagonal()) || (Main.game.boxesFilled == Main.game.gridSize * Main.game.gridSize)) {
                Main.game.gameFrame.setVisible(false);
                System.out.println("here");
                Main.endscreen = new Endscreen();
                switch (Main.game.getGameWinner()) {
                    case RED: {
                        Main.endscreen.endLabel.setText("The RED player won the game!");
                        break;
                    }
                    case BLUE: {
                        Main.endscreen.endLabel.setText("The BLUE player won the game!");
                        break;
                    }
                    case NEITHER: {
                        Main.endscreen.endLabel.setText("The game was a draw!");
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
    }
    public void lockBox() {
        this.setIcon(new ImageIcon(new ImageIcon((this.getStatus().equals(Player.RED))?"assets/RedX.png" : ((this.getStatus().equals(Player.BLUE))?"assets/BlueO.png" : "assets/Draw.png")).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
        for (int i = 0; i < Main.game.gridSize; i++) {
            for (int j = 0; j < Main.game.gridSize; j++) {
                this.miniBox[i][j].setVisible(false);
                Main.game.gamePanel.remove(this.miniBox[i][j]);
            }
        }
    }

    public void setStatus(Player status) {
        this.status = status;
    }
    public Player getStatus() {
        return this.status;
    }
}
