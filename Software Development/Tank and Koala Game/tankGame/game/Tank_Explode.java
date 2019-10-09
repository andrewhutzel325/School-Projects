package tankGame.game;

import java.awt.image.BufferedImage;

public class Tank_Explode extends Tank_Sprites {
    int invulnerable, vulnerable;

    public Tank_Explode(BufferedImage image, int x, int y, int sprite, int total_sprite) {
        super(image, x, y, sprite, total_sprite, "explosion");
        vulnerable = 0;
        invulnerable = 3;
    }
    public void draw_Explosion(){
            draw(game.graphic_2d, game);
            vulnerable++;
            if(vulnerable>invulnerable){
                vulnerable=0;
                sprite++;
            }
    }
    public boolean end(){
        return sprite >= total_Sprites;
    }
}
