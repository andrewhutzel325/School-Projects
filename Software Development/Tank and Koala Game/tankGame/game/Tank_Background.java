package tankGame.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tank_Background extends JPanel{
    private BufferedImage image_bg;
    private int width, height;

    public Tank_Background(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void initBackground(BufferedImage init_image){
        image_bg = init_image;
    }

    public void drawBackground(Graphics graphic){
        int height = image_bg.getHeight(this);
        int width = image_bg.getWidth(this);
        int y = height / height;
        int x = width / width;
        for (int i = -1; i <= y; i++) {
            for (int j = 0; j <= x; j++) {
                graphic.drawImage(image_bg, j * width, i * height, width, height, this);
            }
        }
    }
}
