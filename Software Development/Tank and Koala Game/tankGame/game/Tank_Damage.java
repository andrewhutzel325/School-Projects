package tankGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Tank_Damage{
    private int movement,x,y;
    BufferedImage b;
    boolean visible;
    public Tank_Damage(BufferedImage image, int x, int y, int movement) {
        this.x = x;
        b=image;
        this.y=y;
        this.movement = movement;
        visible = true;
    }

    public void draw(Graphics graphic, ImageObserver observer){
        graphic.drawImage(b, x, y, observer);
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public BufferedImage get_image(){
        return b;
    }
    public void update(){
        x+=Math.cos(Math.toRadians(movement))*5;
        y-=Math.sin(Math.toRadians(movement))*5;
    }
}
