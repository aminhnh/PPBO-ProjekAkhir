package main;

import javax.swing.*;
import java.util.IllegalFormatWidthException;

public class Main {
    public static void main(String args[]){

        // Mengatur Window (window terbuat dari JFrame)
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dinos");

        // Membuat class GamePanel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Mengatur ukuran, lokasi, dan visibility window
        window.pack(); // mengatur ukuran window menjadi
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
