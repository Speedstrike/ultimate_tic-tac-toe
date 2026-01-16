/*
 * Copyright (c) 2024 Aaryan Karlapalem
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
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game {
    protected final Box[][] gameBox;
    protected final JFrame gameFrame;
    protected final JPanel gamePanel;

    private Player turn;
    private Player gameWinner;
    protected int boxesFilled;
    protected final int gridSize = 3;
    protected final boolean ai;

    protected static final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    protected static final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    protected static Random rand = new Random();

    public Game(boolean ai) {
        this.gameWinner = Player.NEITHER;
        this.ai = ai;

        this.gameBox = new Box[this.gridSize][this.gridSize];

        this.gameFrame = new JFrame("Tic-tac-toe Game");
        this.gameFrame.setSize(Game.screenWidth, Game.screenHeight);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gamePanel = new JPanel();
        this.gamePanel.setSize(Game.screenWidth, Game.screenHeight);
        this.gameFrame.add(this.gamePanel);

        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                this.gameBox[i][j] = new Box(i, j, this.gridSize, this.ai);
                this.gamePanel.add(this.gameBox[i][j]);
            }
        }

        this.gamePanel.setLayout(new GridLayout(this.gridSize, this.gridSize));
        this.gameFrame.add(this.gamePanel);
        this.gameFrame.setVisible(true);

        this.turn = Player.RED;
    }

    public boolean isVertical() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize - 1; j++) {
                if (this.gameBox[j][i].getStatus().equals(this.gameBox[j + 1][i].getStatus())) {
                    if (this.gameBox[j][i].getStatus().equals(Player.NEITHER)) {
                        break;
                    }
                    if (j == this.gridSize - 2) {
                        Main.game.setGameWinner(this.gameBox[j][i].getStatus());
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
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize - 1; j++) {
                if (this.gameBox[i][j].getStatus().equals(this.gameBox[i][j + 1].getStatus())) {
                    if (this.gameBox[i][j].getStatus().equals(Player.NEITHER)) {
                        break;
                    }
                    if (j == this.gridSize - 2) {
                        Main.game.setGameWinner(this.gameBox[i][j].getStatus());
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
        for (int i = 0; i < this.gridSize - 1; i++) {
            if (this.gameBox[i][i].getStatus().equals(this.gameBox[i + 1][i + 1].getStatus())) {
                if (i == this.gridSize - 2) {
                    if (this.gameBox[2][2].getStatus().equals(Player.NEITHER)) {
                        break;
                    }
                    Main.game.setGameWinner(this.gameBox[2][2].getStatus());
                    return true;
                }
                continue;
            }
            else {
                break;
            }
        }

        for (int i = 0; i < this.gridSize - 1; i++) {
            if (this.gameBox[i][this.gridSize - 1 - i].getStatus().equals(this.gameBox[i + 1][this.gridSize - 2 - i].getStatus())) {
                if (i == this.gridSize - 2) {
                    if (this.gameBox[2][0].getStatus().equals(Player.NEITHER)) {
                        break;
                    }
                    this.setGameWinner(this.gameBox[i][i].getStatus());
                    return true;
                }
                continue;
            }
            else {
                break;
            }
        }
        return false;
    }

    public void aiAction() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean set = false;
        while (!set) {
            int xBox = rand.nextInt(this.gridSize);
            int yBox = rand.nextInt(this.gridSize);
            int xCell = rand.nextInt(this.gridSize);
            int yCell = rand.nextInt(this.gridSize);
            if (Main.game.gameBox[xBox][yBox].miniBox[xCell][yCell].getStatus().equals(Player.NEITHER)) {
                Main.game.gameBox[xBox][yBox].miniBox[xCell][yCell].setIcon(new ImageIcon(new ImageIcon("assets/BlueO.png").getImage().getScaledInstance(Main.game.gameBox[xBox][yBox].miniBox[xCell][yCell].getWidth(), Main.game.gameBox[xBox][yBox].miniBox[xCell][yCell].getHeight(), Image.SCALE_SMOOTH)));
                Main.game.gameBox[xBox][yBox].miniBox[xCell][yCell].setStatus(Player.BLUE);
                this.gameBox[xBox][yBox].bingoCheck();
                set = true;
                Main.game.setTurn(Player.RED);
            }
        }
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }
    public Player getTurn() {
        return this.turn;
    }
    public void setGameWinner(Player gameWinner) {
        this.gameWinner = gameWinner;
    }
    public Player getGameWinner() {
        return this.gameWinner;
    }
}

enum Player {
    RED, BLUE, NEITHER, FULL
}
