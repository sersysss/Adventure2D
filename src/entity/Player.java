//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = this.solidArea.x;
        solidAreaDefaultY = this.solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;

            }
            // check tile's collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object's collision
            int objIndex = gp.cChecker.checkObject(this, true);

            // if collision is false, player can move
            if (!collisionOn) {

                switch(direction) {
                    case "up":
                            worldY -= speed;
                        break;
                    case "down":
                            worldY += speed;
                        break;
                    case "left":
                            worldX -= speed;
                        break;
                    case "right":
                            worldX += speed;
                }
            }

            ++spriteCounter;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }

    }

    //public void pickUpObject(int i) {
    //   if (i != 999) {
    //       String objectName = this.gp.obj[i].name;
    //       switch(objectName.hashCode()) {
    //           case 75327:
    //               if (objectName.equals("Key")) {
    //                   this.gp.playSE(1);
    //                   ++this.hasKey;
    //                   this.gp.obj[i] = null;
    //                   this.gp.ui.showMessage("You got a key!");
    //               }
    //               break;
    //           case 2136014:
    //               if (objectName.equals("Door")) {
    //                   if (this.hasKey > 0) {
    //                       this.gp.playSE(3);
    //                       this.gp.obj[i] = null;
    //                       --this.hasKey;
    //                       this.gp.ui.showMessage("You opened the door!");
    //                   } else {
    //                       this.gp.ui.showMessage("You need a key!");
    //                   }
    //               }
    //               break;
    //           case 64369569:
    //               if (objectName.equals("Boots")) {
    //                   this.gp.playSE(2);
    //                   ++this.speed;
    //                   this.gp.obj[i] = null;
    //                   this.gp.ui.showMessage("Speed up!");
    //               }
    //               break;
    //           case 65074913:
    //               if (objectName.equals("Chest")) {
    //                   this.gp.ui.gameFinished = true;
    //                   this.gp.stopMusic();
    //                   this.gp.playSE(4);
    //               }
    //       }
    //   }
    //
    //

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":

                    if (spriteNum == 1) {
                        image = up1;
                    }

                    if (spriteNum == 2) {
                        image = up2;
                    }
                break;
            case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }

                    if (spriteNum == 2) {
                        image = down2;
                    }
                break;
            case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }

                    if (spriteNum == 2) {
                        image = left2;
                    }
                break;
            case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }

                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

