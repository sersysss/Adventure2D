//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import main.GamePanel;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public SuperObject() {
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        int var10000 = worldX;

        if (var10000 + 48 > gp.player.worldX - gp.player.screenX) {
            var10000 = worldX;

            if (var10000 - 48 < gp.player.worldX + gp.player.screenX) {
                var10000 = worldY;

                if (var10000 + 48 > gp.player.worldY - gp.player.screenY) {
                    var10000 = worldY;

                    if (var10000 - 48 < gp.player.worldY + gp.player.screenY) {
                        BufferedImage var10001 = image;
                        g2.drawImage(var10001, screenX, screenY, 48, 48, (ImageObserver)null);
                    }
                }
            }
        }

    }
}

