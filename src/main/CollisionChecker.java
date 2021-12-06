//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import entity.Entity;
import java.awt.Rectangle;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        int tileNum1;
        int tileNum2;

        switch(entity.direction) {
            case "up":

                    entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                break;

            case "down":

                    entityBottomRow = (entityTopWorldY + entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                break;

            case "left":

                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                break;

            case "right":

                    entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (this.gp.tileM.tile[tileNum1].collision || this.gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
               break;
        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < this.gp.obj.length; ++i) {
            if (this.gp.obj[i] != null) {
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                this.gp.obj[i].solidArea.x += this.gp.obj[i].worldX;
                this.gp.obj[i].solidArea.y += this.gp.obj[i].worldY;
                Rectangle var10000;
                String var5;
                switch((var5 = entity.direction).hashCode()) {
                    case 3739:
                        if (var5.equals("up")) {
                            var10000 = entity.solidArea;
                            var10000.y -= entity.speed;
                            if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
                                if (this.gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }

                                if (player) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case 3089570:
                        if (var5.equals("down")) {
                            var10000 = entity.solidArea;
                            var10000.y += entity.speed;
                            if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
                                if (this.gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }

                                if (player) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case 3317767:
                        if (var5.equals("left")) {
                            var10000 = entity.solidArea;
                            var10000.x -= entity.speed;
                            if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
                                if (this.gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }

                                if (player) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case 108511772:
                        if (var5.equals("right")) {
                            var10000 = entity.solidArea;
                            var10000.x += entity.speed;
                            if (entity.solidArea.intersects(this.gp.obj[i].solidArea)) {
                                if (this.gp.obj[i].collision) {
                                    entity.collisionOn = true;
                                }

                                if (player) {
                                    index = i;
                                }
                            }
                        }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                this.gp.obj[i].solidArea.x = this.gp.obj[i].solidAreaDefaultX;
                this.gp.obj[i].solidArea.y = this.gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}

