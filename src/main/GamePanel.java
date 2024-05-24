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

    final  int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    //domyślna pozycja gracza
    int PlayerX = 100;
    int PlayerY = 100;
    int playerSpeed = 4;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    //stworzenie zegaru gry
    public void  startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {


            //aktualizacja innformacji o zdarzeniach
            update();

            //rysownaie ekranu
            repaint();



            try {
                double remainingTime = (nextDrawTime - System.nanoTime());
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void update() {
        if(keyH.upPressed) {
            PlayerY -= playerSpeed;
        }
        if(keyH.downPressed) {
            PlayerY += playerSpeed;
        }
        if(keyH.leftPressed) {
            PlayerX -= playerSpeed;
        }
        if(keyH.rightPressed) {
            PlayerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(PlayerX, PlayerY, tileSize, tileSize);
        g2.dispose();
    }
}
