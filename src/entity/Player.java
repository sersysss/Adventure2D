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
import main.UtilityTool;
import tile.Tile;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

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

        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");

    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";


            }
            // check tile's collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object's collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check npc's collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // if collision is false, player can move
            if (!collisionOn) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            ++spriteCounter;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i) {

        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                        gp.playSE(1);
                        ++hasKey;
                        gp.obj[i] = null;
                        gp.ui.showMessage("You got a key!");
                        break;
                case "Door":
                        if (hasKey > 0) {
                            gp.playSE(3);
                            gp.obj[i] = null;
                            --hasKey;
                            gp.ui.showMessage("You opened the door!");
                        } else {
                            gp.ui.showMessage("You need a key!");
                        }
                        break;
                case "Boots":
                        gp.playSE(2);
                        ++speed;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Speed up!");
                        break;
                case "Chest":
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSE(4);
                        break;
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {

            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            gp.keyH.enterPressed = false;
        }
    }

    public void draw(Graphics2D g2) {

            BufferedImage image = null;

            switch (direction) {
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
            int x = screenX;
            int y = screenY;

            if (screenX > worldX) {
                x = worldX;
            }
            if (screenY > worldY) {
                y = worldY;
            }
            int rightOffset = gp.screenWidth - screenX;
            if (rightOffset > gp.worldWidth - worldX) {
                x = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - screenY;
            if (bottomOffset > gp.worldHeight - worldY) {
                y = gp.screenHeight - (gp.worldHeight - worldY);
            }

            g2.drawImage(image, x, y, null);
        }
    }
