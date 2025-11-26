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
import java.awt.event.ActionEvent;

public class Cell extends JButton {
    private Player status = Player.NEITHER;
    protected int xBox;
    protected int yBox;
    public Cell(int xBox, int yBox) {
        this.xBox = xBox;
        this.yBox = yBox;
        this.status = Player.NEITHER;
    }
    public void playerCellClicked(ActionEvent ae) {
        // If user clicks button during ai turn, don't do anything
        if (Main.game.ai) {
            if (Main.game.getTurn().equals(Player.BLUE)) {
                System.out.println("hacked");
                return;
            }
        }
        if (this.status.equals(Player.NEITHER)) {
            this.setIcon(new ImageIcon(new ImageIcon(Main.game.getTurn().equals(Player.RED)? "RedX.png" : "BlueO.png").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
            if (Main.game.getTurn().equals(Player.RED)) {
                this.status = Player.RED;
            }
            else {
                this.status = Player.BLUE;
            }

            Main.game.gameBox[this.xBox][this.yBox].bingoCheck();
                
            Main.game.gameBox[this.xBox][this.yBox].cellsFilled++;

            if (Main.game.gameBox[this.xBox][this.yBox].cellsFilled == (int) Math.pow(Main.game.gridSize, 2)) {
                Main.game.gameBox[this.xBox][this.yBox].setStatus(Player.FULL);
                Main.game.gameBox[this.xBox][this.yBox].lockBox();
                Main.game.boxesFilled++;
            }

            Main.game.setTurn((Main.game.getTurn().equals(Player.RED))? Player.BLUE : Player.RED);
            if (Main.game.ai) {
                Main.game.aiAction();   
            }
        }   
    }

    public void aiPlace(ActionEvent e) {

    }

    public void setStatus(Player status) {
        this.status = status;
    }

    public Player getStatus() {
        return this.status;
    }
}
