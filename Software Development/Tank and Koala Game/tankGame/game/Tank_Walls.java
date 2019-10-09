package tankGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Tank_Walls extends Tank_Background_Object {

    int timer = 1000;

    public Tank_Walls(BufferedImage image, int x, int y) {
        super(image, x, y);
        visible = true;
    }

    public void draw(Graphics graphic, ImageObserver observer) {
        if (!visible) {
            this.timer--;
            if (this.timer < 0) {
                this.timer = 1000;
                this.visible = true;
            }
        } else {
            super.draw(graphic, observer);
        }
    }
}
