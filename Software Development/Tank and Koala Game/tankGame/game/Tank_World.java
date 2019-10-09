package tankGame.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Tank_World extends JPanel implements Runnable {

    private static final Tank_World game = new Tank_World();
    public static final Tank_Audio game_audio = new Tank_Audio();
    public static HashMap<String, BufferedImage> sprites;
    private BufferedImage background_image, player1_view, player2_view;
    private Thread thread;
    private Tank_Background background;
    private Tank_Events tank_events;
    private static Tank_Level level;
    protected int width, height, player1_score = 0, player2_score = 0;
    protected ArrayList<Tank_Player> players;
    protected ArrayList<Tank_Sprites> sprite;
    boolean dead = false;
    Graphics2D graphic_2d;
    Key_Controller key;
    ArrayList<Tank_Walls> tank_Walls;
    BufferedImage minimap;
    Point map;

    public void load_Sprites() {
        sprites.put("background", getSprite("resources/Background.png"));
        sprites.put("player1", getSprite("resources/tank1_strips.png"));
        sprites.put("player2", getSprite("resources/tank2_strips.png"));
        sprites.put("wall1", getSprite("resources/Wall1.png"));
        sprites.put("wall2", getSprite("resources/wall2.png"));
        sprites.put("bullet", getSprite("resources/bullet.png"));
        sprites.put("smallExplosion", getSprite("resources/small_explosion6.png"));
        sprites.put("power", getSprite("resources/power.png"));
    }

    public BufferedImage getSprite(String name) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public class Key_Controller extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            tank_events.pressed(e, players);
        }

        public void keyReleased(KeyEvent e) {
            tank_events.released(e, players);
        }

    }

    public void add_Sprite(BufferedImage image, int x, int y, int sprite, int total_sprite, String type) {
        this.sprite.add(new Tank_Sprites(image, x, y, sprite, total_sprite, type));
    }

    public void add_Wall(BufferedImage image, int x, int y) {
        tank_Walls.add(new Tank_Walls(image, x, y));
    }

    public void player1_Init(BufferedImage image, int x, int y) {
        players.add(new Tank_Player(image, x, y, 0, 60));
    }

    public void player2_Init(BufferedImage image, int x, int y) {
        players.add(new Tank_Player(image, x, y, 30, 60));
    }

    @Override
    public void paint(Graphics graphic) {
        if (background_image == null) {
            background_image = (BufferedImage) createImage(map.x, map.y);
            graphic_2d = background_image.createGraphics();
            minimap = background_image;
        }
        draw();
        Image map_mini = minimap.getScaledInstance(this.getHeight() / 5, minimap.getWidth() * this.getHeight() / (5 * minimap.getHeight()), BufferedImage.SCALE_FAST);
        Dimension windowSize = getSize();
        int player1_x = players.get(0).get_x() - windowSize.width / 4 > 0 ? players.get(0).get_x() - windowSize.width / 4 : 0;
        int player1_y = players.get(0).get_y() - windowSize.height / 2 > 0 ? players.get(0).get_y() - windowSize.height / 2 : 0;

        if (player1_x > map.x - windowSize.width / 2) {
            player1_x = map.x - windowSize.width / 2;
        }
        if (player1_y > map.y - windowSize.height) {
            player1_y = map.y - windowSize.height;
        }

        int player2_x = players.get(1).get_x() - windowSize.width / 4 > 0 ? players.get(1).get_x() - windowSize.width / 4 : 0;
        int player2_y = players.get(1).get_y() - windowSize.height / 2 > 0 ? players.get(1).get_y() - windowSize.height / 2 : 0;

        if (player2_x > map.x - windowSize.width / 2) {
            player2_x = map.x - windowSize.width / 2;
        }
        if (player2_y > map.y - windowSize.height) {
            player2_y = map.y - windowSize.height;
        }

        player1_view = background_image.getSubimage(player1_x, player1_y, windowSize.width / 2, windowSize.height);
        player2_view = background_image.getSubimage(player2_x, player2_y, windowSize.width / 2, windowSize.height);
        graphic.drawImage(player1_view, 0, 0, this);
        graphic.drawImage(player2_view, windowSize.width / 2, 0, this);
        graphic.drawRect(windowSize.width / 2 - 1, 0, 1, windowSize.height);
        graphic.drawImage(map_mini, windowSize.width / 2 - 75, 450, this);
        Font font = new Font("Dialog", Font.PLAIN, 30);
        graphic.setFont(font);
        graphic.drawString("P1 HP: " + players.get(0).get_life(), 32, getHeight() - 520);
        graphic.drawString("P2 HP: " + players.get(1).get_life(), getWidth() - 150, getHeight() - 60);
        graphic.drawString("Kills: " + player1_score, 32, getHeight() - 480);
        graphic.drawString("Kills: " + player2_score, getWidth() - 150, getHeight() - 100);
    }

    private void draw() {
        background.drawBackground(graphic_2d);
        tank_events.process(players, sprite, tank_Walls);
        ArrayList<Tank_Damage> tank_dmg;

        for (int i = 0; i < sprite.size(); i++) {
            sprite.get(i).draw(graphic_2d, this);
        }
        for (int i = 0; i < tank_Walls.size(); i++) {
            tank_Walls.get(i).draw(graphic_2d, this);
        }

        for (int i = 0; i < players.size(); i++) {
            tank_dmg = players.get(i).get_Bullet();
            for (Tank_Damage bullet : tank_dmg) {
                if (bullet.visible) {
                    bullet.draw(graphic_2d, this);
                    bullet.update();
                }
            }
            if (players.get(i).show) {
                players.get(i).draw(graphic_2d, this);
            }
        }

        for (int i = 0; i < players.size(); i++) {

            for (int j = 0; j < players.get(i).explosions.size(); j++) {
                Tank_Explode exp = players.get(i).explosions.get(j);
                if (!exp.end()) {
                    exp.draw_Explosion();
                } else {
                    players.get(i).explosions.remove(exp);
                }
            }
        }
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Tank_World getInstance() {
        return game;
    }

    public HashMap<String, BufferedImage> getSprites() {
        return sprites;
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void gameOver() {
        dead = false;
        removeKeyListener(key);
        game.init();
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    void init() {
        setFocusable(true);
        sprites = new HashMap<>();
        load_Sprites();
        tank_Walls = new ArrayList<>();
        players = new ArrayList<>();
        sprite = new ArrayList<>();
        level = new Tank_Level("resources/level.txt");
        level.load();
        map = new Point(level.width * 32, level.height * 32);
        //background is applied here...
        background = new Tank_Background(map.x, map.y);
        background.initBackground(sprites.get("background"));
        tank_events = new Tank_Events();
        key = new Key_Controller();
        addKeyListener(key);
    }

    public static void main(String argv[]) {
        final Tank_World tankgame = Tank_World.getInstance();
        JFrame tankframe = new JFrame("Tank Gamev3");
        tankframe.getContentPane().add("Center", tankgame);
        tankframe.pack();
        tankframe.setSize(new Dimension(900, 600));
        tankframe.setLocationRelativeTo(null);
        tankgame.setDimensions(900, 600);
        tankgame.init();
        tankframe.setVisible(true);
        tankframe.setResizable(false);
        tankframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tank_World.game_audio.playLoop("resources/Music.wav");
        tankgame.start();
    }
}
