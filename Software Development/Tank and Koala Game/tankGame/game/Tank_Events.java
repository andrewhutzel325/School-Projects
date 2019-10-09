package tankGame.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank_Events {

    private int[] player_1, player_2;
    Tank_World tank = Tank_World.getInstance();

    /*
    https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
     */
    public void pressed(KeyEvent e, ArrayList<Tank_Player> players) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            players.get(0).keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            players.get(1).keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            players.get(0).keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            players.get(1).keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            players.get(0).keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            players.get(1).keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            players.get(0).keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            players.get(1).keys[3] = true;
        }
    }

    public void released(KeyEvent e, ArrayList<Tank_Player> players) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            players.get(0).keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            players.get(1).keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            players.get(0).keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            players.get(1).keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            players.get(0).keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            players.get(1).keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            players.get(0).keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            players.get(1).keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            players.get(0).tank_shoot();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            players.get(1).tank_shoot();
        }
    }

    public void movement(ArrayList<Tank_Player> player) {
        player_1 = new int[2];
        player_2 = new int[2];
        player_1[0] = player.get(0).get_x();
        player_1[1] = player.get(0).get_y();
        player_2[0] = player.get(1).get_x();
        player_2[1] = player.get(1).get_y();
        player.get(0).movement_check();
        player.get(1).movement_check();
    }

    public void check_collision(ArrayList<Tank_Player> players, ArrayList<Tank_Sprites> wall_unbreakable, ArrayList<Tank_Walls> wall_breakable) {
        Rectangle hitbox_player1 = new Rectangle(players.get(0).get_x(), players.get(0).get_y(), 54, 54);
        Rectangle hitbox_player2 = new Rectangle(players.get(1).get_x(), players.get(1).get_y(), 54, 54);
        //check colision with sprites
        for (int i = 0; i < wall_unbreakable.size(); i++) {
            Rectangle wall = new Rectangle(wall_unbreakable.get(i).get_x(), wall_unbreakable.get(i).get_y(),
                    wall_unbreakable.get(i).getImg().getWidth() / 2, wall_unbreakable.get(i).getImg().getHeight() / 2);
            if (hitbox_player1.intersects(wall)) {
                players.get(0).set_x(player_1[0]);
                players.get(0).set_y(player_1[1]);
            }
            if (hitbox_player2.intersects(wall)) {
                players.get(1).set_x(player_2[0]);
                players.get(1).set_y(player_2[1]);
            }
        }
        //Player collision
        if (hitbox_player1.intersects(hitbox_player2)) {
            players.get(0).set_x(player_1[0]);
            players.get(0).set_y(player_1[1]);
            players.get(1).set_x(player_2[0]);
            players.get(1).set_y(player_2[1]);
        }
        //check colision with breakable wall
        for (int i = 0; i < wall_breakable.size(); i++) {
            Rectangle wall = new Rectangle(wall_breakable.get(i).getX(), wall_breakable.get(i).getY(),
                    wall_breakable.get(i).getImg().getWidth() / 2, wall_breakable.get(i).getImg().getHeight() / 2);
            if (hitbox_player1.intersects(wall) && tank.tank_Walls.get(i).visible) {
                players.get(0).set_x(player_1[0]);
                players.get(0).set_y(player_1[1]);
            }
            if (hitbox_player2.intersects(wall) && tank.tank_Walls.get(i).visible) {
                players.get(1).set_x(player_2[0]);
                players.get(1).set_y(player_2[1]);
            }
        }
        players.get(0).check_collision(players.get(1));
        if (tank.dead) {
            tank.player1_score++;
            tank.gameOver();
        }
        players.get(1).check_collision(players.get(0));
        if (tank.dead) {
            tank.player2_score++;
            tank.gameOver();
        }

        /*Wrong Idea
        }*/
    }

    public void process(ArrayList<Tank_Player> players, ArrayList<Tank_Sprites> wall_unbreakable, ArrayList<Tank_Walls> wall_breakable) {
        movement(players);
        check_collision(players, wall_unbreakable, wall_breakable);
    }

}
