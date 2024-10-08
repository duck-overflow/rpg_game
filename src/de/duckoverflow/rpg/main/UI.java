package de.duckoverflow.rpg.main;


import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    //public boolean messageOn = false;
    //public String message = "";
    //int messageCounter = 0;
    //public boolean gameFinished = false;

    //double playTime;
    //DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    /*
    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }
    */
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
/*
        if (gp.gameState == gp.playState) {
            // playstate stuff
        }
*/
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED!";
        int x = getXForCentered(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public int getXForCentered(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2 - length / 2);
    }

}
