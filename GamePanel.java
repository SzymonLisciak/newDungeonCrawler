package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // USTAWIENIA EKRANU
    final int originalTileSize = 16; // 16x16 rozmiar gracza npc oraz kafelków na mapie;
    final int scale = 3; // skalowanie dla monitorów
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // liczba w kafelków kolumnie kafelków na ekranie
    final  int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
    //stworzenie zegaru gry
    public void  startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            // System.out.println("Test");

            //aktualizacja innformacji o zdarzeniach
            update();

            //rysownaie ekranu
            repaint();
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
    }
}
