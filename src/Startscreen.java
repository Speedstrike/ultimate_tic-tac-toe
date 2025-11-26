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
import java.awt.*;

import javax.swing.*;

public class Startscreen {
    JFrame startFrame;
    JPanel startPanel;
    JLabel startLabel;
    JButton startA;
    JButton startB;

    private static final int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public Startscreen() {
        this.startFrame = new JFrame("Startscreen");
        this.startFrame.setSize(width, height);
        this.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.startPanel = new JPanel();
        this.startPanel.setSize(width, height);
        this.startFrame.add(this.startPanel);

        new Color(56, 56, 56);

        this.startA = new JButton();
        this.startA.setBounds(width / 6, height * 9 / 16, width / 6, height/ 8);
        this.startA.setFont(new Font("Calibri", Font. PLAIN, 25));
        this.startA.setBackground(Color.decode("#2c2c2c"));
        this.startA.setForeground(Color.decode("#ffffff"));
        this.startA.setText("Play with a friend");
        this.startFrame.add(this.startA, BorderLayout.WEST);

        this.startB = new JButton();
        this.startB.setBounds(width * 4 / 6, height * 9 / 16, width / 6, height / 8);
        this.startB.setFont(new Font("Calibri", Font. PLAIN, 25));
        this.startB.setBackground(Color.decode("#2c2c2c"));
        this.startB.setForeground(Color.decode("#ffffff"));
        this.startB.setText("Play with AI");
        this.startFrame.add(this.startB, BorderLayout.EAST);

        this.startLabel = new JLabel();
        this.startLabel.setFont(new Font("Calibri", Font.BOLD, 60));
        this.startLabel.setForeground(Color.decode("#FFB027")); 
        this.startLabel.setText("Welcome to ultimate tic-tac-toe! Please select your game option!");
        this.startPanel.add(this.startLabel);

        this.startFrame.setLayout(new BorderLayout());
        this.startFrame.add(this.startPanel);
        this.startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.startFrame.setUndecorated(true);
        this.startFrame.setVisible(true);

        this.startA.addActionListener(e -> {Main.startGame(false);});
        this.startB.addActionListener(e -> {Main.setDifficulty(true);});
    }
}