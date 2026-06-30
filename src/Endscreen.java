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

public class Endscreen {
    protected final JFrame endFrame;
    protected final JPanel endPanel;
    protected final JLabel endLabel;
    protected final JButton endButton;
    public Endscreen() {
        this.endFrame = new JFrame("Endscreen");
        this.endFrame.setSize(Game.screenWidth, Game.screenHeight);
        this.endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.endFrame.setVisible(true);

        this.endPanel = new JPanel();
        this.endPanel.setSize(Game.screenWidth, Game.screenHeight);
        this.endFrame.add(this.endPanel);

        this.endLabel = new JLabel();
        this.endLabel.setFont(new Font("Times new Roman", Font.PLAIN, 32));
        this.endPanel.add(this.endLabel);

        this.endButton = new JButton();
        this.endButton.setText("Play again");
        this.endButton.setBounds(500, 500, Game.screenWidth / 2, Game.screenHeight / 2);
        this.endButton.addActionListener(e -> {Main.runNewGame();});
        this.endPanel.add(this.endButton);
    }
}
