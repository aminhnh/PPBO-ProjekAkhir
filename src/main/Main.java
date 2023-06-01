package main;

import javax.swing.*;
import java.util.IllegalFormatWidthException;

public class Main {
    public static void main(String args[]){
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dinos");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // mengatur ukuran window menjadi

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
