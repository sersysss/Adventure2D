//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = 48;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public Player player;
    public SuperObject[] obj;

    public GamePanel() {
        this.player = new Player(this, this.keyH);
        this.obj = new SuperObject[10];
        this.setPreferredSize(new Dimension(768, 576));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        this.aSetter.setObject();
        this.playMusic(0);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void run() {
        double drawInterval = (double)(1000000000 / this.FPS);
        double delta = 0.0D;
        long lastTime = System.nanoTime();
        long timer = 0L;
        int drawCount = 0;

        while(this.gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (double)(currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1.0D) {
                this.update();
                this.repaint();
                --delta;
                ++drawCount;
            }

            if (timer >= 1000000000L) {
                drawCount = 0;
                timer = 0L;
            }
        }

    }

    public void update() {
        this.player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        this.tileM.draw(g2);

        for(int i = 0; i < this.obj.length; ++i) {
            if (this.obj[i] != null) {
                this.obj[i].draw(g2, this);
            }
        }

        this.player.draw(g2);
        this.ui.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        this.music.setFile(i);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playSE(int i) {
        this.se.setFile(i);
        this.se.play();
    }
}
