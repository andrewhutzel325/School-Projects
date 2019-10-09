package tankGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Tank_Background_Object {
    boolean passby;
    int x, y;
    BufferedImage image;
    boolean visible;

    public Tank_Background_Object(BufferedImage image, int x, int y){
        this.x = x;
        this.y = y;
        this.image = image;
        visible = true;
    }

    public void draw(Graphics graphic, ImageObserver observer){

            graphic.drawImage(image,x,y,observer);
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public BufferedImage getImg(){return image;}
}
