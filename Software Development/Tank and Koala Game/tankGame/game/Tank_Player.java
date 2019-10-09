package tankGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tank_Player extends Tank_Sprites {

    private final int DEGREES = 6; //degree 360/60 is 6.
    private int move_angle, player_x, player_y, player_sprite;
    private int life = 3, points = 0;
    boolean[] keys = new boolean[4]; //all keys
    boolean show = true, powerup = false;
    public ArrayList<Tank_Explode> explosions;
    protected ArrayList<Tank_Damage> bullet;

    public Tank_Player(BufferedImage image, int x, int y, int sprite, int total_Sprite) {
        super(image, x, y, sprite, total_Sprite, "player");
        for (int i = 0; i < 4; i++) {
            keys[i] = false;
        }
        bullet = new ArrayList<>();
        explosions = new ArrayList<>();
        move_angle = DEGREES * sprite;
        this.player_y = y;
        this.player_x = x;
        this.player_sprite = sprite;
    }

    public void movement_check() {
        if (keys[0]) {
            move_left();
        }
        if (keys[1]) {
            move_down();
        }
        if (keys[2]) {
            move_right();
        }
        if (keys[3]) {
            move_up();
        }
    }

    public void move_left() {
        sprite++;
        if (sprite > 59) {
            sprite = 0;
        }
        move_angle = DEGREES * sprite;
    }

    public void move_right() {
        sprite--;
        if (sprite < 0) {
            sprite = 59;
        }
        move_angle = DEGREES * sprite;
    }

    public void move_up() {
        x += speed * Math.cos(Math.toRadians(move_angle));
        y -= speed * Math.sin(Math.toRadians(move_angle));
    }

    public void move_down() {
        x -= speed * Math.cos(Math.toRadians(move_angle));
        y += speed * Math.sin(Math.toRadians(move_angle));
    }

    public void tank_shoot() {
        bullet.add(new Tank_Damage(game.getSprites().get("bullet"), x + getImg().getWidth() / 2, y + getImg().getHeight() / 2, move_angle));
        if (powerup) {
            bullet.add(new Tank_Damage(game.getSprites().get("bullet"), x + getImg().getWidth() / 2 + 10, y + getImg().getHeight() / 2 + 10, move_angle));
        }
    }

    public Rectangle returnBounds() {
        return new Rectangle(x, y, 54, 54);
    }

    public void check_collision(Tank_Player player) {
        Rectangle tank = returnBounds();
        Rectangle player_Bound = player.returnBounds();
        //Powerup?
        for (int i = 0; i < game.sprite.size(); i++) {
            if (game.sprite.get(i).type.equals("power")) {
                Rectangle powers = game.sprite.get(i).get_Bounds();
                if (tank.intersects(powers)) {
                    powerup = true;
                    game.sprite.remove(i);
                }
            }
        }

        for (int i = 0; i < bullet.size(); i++) {
            boolean remove = false;
            Rectangle r1 = new Rectangle(bullet.get(i).get_x(), bullet.get(i).get_y(), bullet.get(i).get_image().getWidth(), bullet.get(i).get_image().getHeight());
            //bullets and player
            if (r1.intersects(player_Bound)) {
                explosions.add(new Tank_Explode(game.getSprites().get("smallExplosion"), (int) r1.getX() - 16, (int) r1.getY() - 16, 0, 6));
                game.game_audio.play("resources/Explosion_small.wav");
                remove = true;
                player.life -= 1;
                if (player.life == 0) {
                    game.game_audio.play("resources/Explosion_large.wav");
                    game.dead = true;
                }
            }
            //check for wall break
            for (int j = 0; j < game.sprite.size(); j++) {
                if (game.sprite.get(i).type.equals("wall")) {
                    Rectangle w2 = new Rectangle(game.sprite.get(j).get_x(), game.sprite.get(j).get_y(), game.sprite.get(j).getImg().getWidth(), game.sprite.get(j).getImg().getHeight());
                    if (r1.intersects(w2)) {
                        remove = true;
                    }
                }
            }
            //check for perm wall collision
            for (int j = 0; j < game.tank_Walls.size(); j++) {
                Rectangle w2 = new Rectangle(game.tank_Walls.get(j).getX(), game.tank_Walls.get(j).getY(), game.tank_Walls.get(j).getImg().getWidth(), game.tank_Walls.get(j).getImg().getHeight());
                if (r1.intersects(w2) && game.tank_Walls.get(j).visible) {
                    explosions.add(new Tank_Explode(game.getSprites().get("smallExplosion"), game.tank_Walls.get(j).getX(), game.tank_Walls.get(j).getY(), 0, 6));
                    game.game_audio.play("resources/Explosion_small.wav");
                    game.tank_Walls.get(j).visible = false;
                    remove = true;
                }
            }
            if (remove) {
                bullet.remove(i);
            }
        }
    }
    public int get_life(){
        return this.life;
    }

    public ArrayList get_Bullet() {
        return bullet;
    }

}
