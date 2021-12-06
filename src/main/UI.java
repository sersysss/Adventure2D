package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import object.OBJ_Key;

public class UI {
    GamePanel gp;
    Font arial_40;
    Font arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        this.arial_40 = new Font("Arial", 0, 40);
        this.arial_80B = new Font("Arial", 1, 80);
        OBJ_Key key = new OBJ_Key();
        this.keyImage = key.image;
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (this.gameFinished) {
            g2.setFont(this.arial_40);
            g2.setColor(Color.white);
            String text = "You found the treasure!";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            this.gp.getClass();
            int x = 768 / 2 - textLength / 2;
            this.gp.getClass();
            int var10000 = 576 / 2;
            this.gp.getClass();
            int y = var10000 - 48 * 3;
            g2.drawString(text, x, y);
            text = "Your Time is " + this.dFormat.format(this.playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            this.gp.getClass();
            x = 768 / 2 - textLength / 2;
            this.gp.getClass();
            var10000 = 576 / 2;
            this.gp.getClass();
            y = var10000 + 48 * 4;
            g2.drawString(text, x, y);
            g2.setFont(this.arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            this.gp.getClass();
            x = 768 / 2 - textLength / 2;
            this.gp.getClass();
            var10000 = 576 / 2;
            this.gp.getClass();
            y = var10000 + 48 * 2;
            g2.drawString(text, x, y);
            this.gp.gameThread = null;
        } else {
            g2.setFont(this.arial_40);
            g2.setColor(Color.white);
            BufferedImage var10001 = this.keyImage;
            this.gp.getClass();
            int var10002 = 48 / 2;
            this.gp.getClass();
            int var10003 = 48 / 2;
            this.gp.getClass();
            this.gp.getClass();
            g2.drawImage(var10001, var10002, var10003, 48, 48, (ImageObserver)null);
            g2.drawString("x " + this.gp.player.hasKey, 74, 65);
            this.playTime += 0.016666666666666666D;
            String var6 = "Time:" + this.dFormat.format(this.playTime);
            this.gp.getClass();
            g2.drawString(var6, 48 * 11, 65);
            if (this.messageOn) {
                g2.setFont(g2.getFont().deriveFont(30.0F));
                var6 = this.message;
                this.gp.getClass();
                var10002 = 48 / 2;
                this.gp.getClass();
                g2.drawString(var6, var10002, 48 * 5);
                ++this.messageCounter;
                if (this.messageCounter > 120) {
                    this.messageCounter = 0;
                    this.messageOn = false;
                }
            }
        }

    }
}