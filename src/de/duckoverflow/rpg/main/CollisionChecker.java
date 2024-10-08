package de.duckoverflow.rpg.main;

import de.duckoverflow.rpg.entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity e) {

        int entityLeftWorldX = e.worldX + e.solidArea.x;
        int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTopWorldY = e.worldY + e.solidArea.y;
        int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (e.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - e.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision
                        || gp.tileM.tile[tileNum2].collision) e.collisionOn = true;
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + e.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision
                        || gp.tileM.tile[tileNum2].collision) e.collisionOn = true;
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - e.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision
                        || gp.tileM.tile[tileNum2].collision) e.collisionOn = true;
                break;
            case "right":
                entityRightCol = (entityRightWorldX + e.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision
                        || gp.tileM.tile[tileNum2].collision) e.collisionOn = true;
                break;
        }

    }

    public int checkObject(Entity e, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {

                // Get entitys solid area position

                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                // Get objects solid area position

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (e.direction) {
                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                e.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                e.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                e.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                e.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        }

        return index;
    }

    // Check NPC or Monster collision
    public int checkEntity(Entity e, Entity[] target) {

        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                // Get entitys solid area position

                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;

                // Get objects solid area position

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (e.direction) {
                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            e.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            e.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            e.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(target[i].solidArea)) {
                            e.collisionOn = true;
                            index = i;
                        }
                        break;
                }

                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }

        }
        return index;
    }

    public void checkPlayer(Entity e) {

        // Get entitys solid area position

        e.solidArea.x = e.worldX + e.solidArea.x;
        e.solidArea.y = e.worldY + e.solidArea.y;

        // Get objects solid area position

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (e.direction) {
            case "up":
                e.solidArea.y -= e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    e.collisionOn = true;
                }
                break;
            case "down":
                e.solidArea.y += e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    e.collisionOn = true;
                }
                break;
            case "left":
                e.solidArea.x -= e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    e.collisionOn = true;
                }
                break;
            case "right":
                e.solidArea.x += e.speed;
                if (e.solidArea.intersects(gp.player.solidArea)) {
                    e.collisionOn = true;
                }
                break;
        }

        e.solidArea.x = e.solidAreaDefaultX;
        e.solidArea.y = e.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }

}
