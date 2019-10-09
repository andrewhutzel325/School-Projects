package tankGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Tank_Sprites {

    Tank_World game;
    public int x, y, sprite = 0, total_Sprites;
    BufferedImage image;
    BufferedImage[] split_image = new BufferedImage[60];
    int speed = 3;
    String type;
    boolean visible = true;

    public Tank_Sprites(BufferedImage image, int x, int y, int sprite, int total_Sprites, String type) {
        this.total_Sprites = total_Sprites;
        this.sprite = sprite;
        this.type = type;
        init(image, x, y);
    }

    //initiate sprite
    private void init(BufferedImage image, int x, int y) {
        game = Tank_World.getInstance();
        this.x = x;
        this.y = y;
        this.image = image;
        int width = image.getWidth() / total_Sprites;
        int height = image.getHeight();
        for (int i = 0; i <= total_Sprites - 1; i++) {
            split_image[i] = image.getSubimage(i * width, 0, width, height);
        }
    }

    public void draw(Graphics graphic, ImageObserver observer) {
        graphic.drawImage(split_image[sprite], x, y, observer);
    }

    public BufferedImage getImg() {
        return split_image[sprite];
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    public Rectangle get_Bounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public String get_type() {
        return type;
    }

    public int get_speed() {
        return speed;
    }

    public boolean get_visible() {
        return visible;
    }

        public void set_x(int x) {
        this.x = x;
    }

    public void set_y(int y) {
        this.y = y;
    }
    
    public void set_visible(boolean is_Visibile) {
        visible = is_Visibile;
    }
}
