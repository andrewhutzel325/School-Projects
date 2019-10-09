package koalaGame.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tankGame.game.*;

public class Koala_World extends JPanel implements Runnable{
    private static final Koala_World koalaWorld = new Koala_World();
    Graphics2D graphics;
    protected int width,height; 
    int level;
    private Tank_Background background;
    private Koala_Events koalaEvents;
    KeyControl keyControl;
    MouseControl mouseControl;
    String[] maps = new String[2]; 
    public static HashMap<String, BufferedImage> sprites;
    private static Koala_Level koalaLevel;
    Point sizeOfMap;
    public static final Tank_Audio tankAudio = new Tank_Audio();
    protected ArrayList<Koala_Player> koalaPlayers;
    ArrayList<Tank_Sprites> sprite;
    ArrayList<Tank_Sprites> rescuedKoala;
    static JFrame frame;
    private BufferedImage buffer_img;
    private Thread thread;

    private void initGameWorld(int lev){
        this.level = lev;
        maps[0] = "resources/level0.txt";
        maps[1] = "resources/level1.txt";
        
        if(lev+1 > maps.length){
            frame.dispose();
            Koala_EmptyLvls.init();
        }
        setFocusable(true); 
        
        sprites = new HashMap<String, BufferedImage>();
        loadSprites();
        
        koalaPlayers = new ArrayList<>();
        sprite = new ArrayList<>();
        rescuedKoala = new ArrayList<>();
        
        koalaLevel = new Koala_Level(maps[lev]);
        koalaLevel.load();
        sizeOfMap = new Point(koalaLevel.width * 40, koalaLevel.height * 40);
        background = new Tank_Background(sizeOfMap.x,sizeOfMap.y);
        background.initBackground(sprites.get("background"));
        
        koalaEvents = new Koala_Events();
        keyControl = new KeyControl();
        mouseControl = new MouseControl();
        
        addKeyListener(keyControl);
        addMouseListener(mouseControl);
    }
    
    @Override
    public void paint(Graphics g){
        if(buffer_img == null) {
            Dimension sizeOfWindow = getSize();
            buffer_img = (BufferedImage) createImage(sizeOfWindow.width,
                    sizeOfWindow.height);
            graphics = buffer_img.createGraphics();
        }
        draw();
        g.drawImage(buffer_img, 0, 0, this);
    }

    private void draw(){
        background.drawBackground(graphics);
        koalaEvents.process(koalaPlayers, sprite);

        for(int i=0; i < sprite.size(); i++)
            if(sprite.get(i).get_visible())
                sprite.get(i).draw(graphics,this);

        for(int i=0; i < koalaPlayers.size(); i++) {
            if(koalaPlayers.get(i).show)
                koalaPlayers.get(i).draw(graphics, this);
        }
        graphics.drawImage(koalaWorld.getSpriteHashMap().get("rescued"),10,5,this);
        graphics.drawImage(koalaWorld.getSpriteHashMap().get("restart"), 530, 5, this);
        for(int i=0; i<rescuedKoala.size();i++){
            if(rescuedKoala.get(i).get_visible())
                rescuedKoala.get(i).draw(graphics,this);}
        
    }

    @Override
    public void run() {
        Thread current_thread = Thread.currentThread();
        while (thread == current_thread) {
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    public class KeyControl extends KeyAdapter {
        
        public void keyPressed(KeyEvent event) {
            koalaEvents.pressed(event,koalaPlayers);
        }
        
        public void keyReleased(KeyEvent event) {
            koalaEvents.released(event, koalaPlayers);
        }
    }
   
    public class MouseControl extends MouseAdapter{
        public void mouseClicked(MouseEvent event){
            koalaEvents.clicked(event);
        }
    }

    public void loadSprites(){
        sprites.put("background", getSpriteBuffImg("resources/Background.png"));
        sprites.put("player", getSpriteBuffImg("resources/player.png"));
        sprites.put("wall1", getSpriteBuffImg("resources/Wall.png"));
        sprites.put("tnt", getSpriteBuffImg("resources/TNT.png"));
        sprites.put("exit", getSpriteBuffImg("resources/Exit1.png"));
        sprites.put("rescued", getSpriteBuffImg("resources/Rescued.png"));
        sprites.put("restart", getSpriteBuffImg("resources/Restart.png"));
        sprites.put("koalaRescued", getSpriteBuffImg("resources/koalaRescued.png"));
        sprites.put("detonator", getSpriteBuffImg("resources/detonator.png"));
    }
    public void addSprite(BufferedImage buffImg, int x, int y, int sprite, int totalSprites, String type) {
        this.sprite.add(new Tank_Sprites(buffImg, x,y,sprite,totalSprites,type));
    }
    
    public void addPlayer(BufferedImage buffImg, int x, int y){
        koalaPlayers.add(new Koala_Player(buffImg, x, y, 0, 32));
    }

    public BufferedImage getSpriteBuffImg(String spriteName){
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(getClass().getResource(spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffImg;
    }
    
    public HashMap<String, BufferedImage> getSpriteHashMap() {
        return sprites;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Koala_World getInstanceKoalaWorld() {
        return koalaWorld;
    }


    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void restart(){
        removeKeyListener(keyControl);
        removeMouseListener(mouseControl);
        koalaWorld.initGameWorld(level);
    }

    public void nextLevel(){
        removeKeyListener(keyControl);
        removeMouseListener(mouseControl);
        frame.dispose();
        init(level+1);
    }

    public static void init(int level){
        final Koala_World koalaGame = Koala_World.getInstanceKoalaWorld();
        frame = new JFrame("Koala Game");
        frame.getContentPane().add("Center", koalaGame);
        frame.pack();
        frame.setSize(new Dimension(640, 520));
        frame.setLocationRelativeTo(null); 
        koalaGame.setDimensions(640, 520);
        koalaGame.initGameWorld(level);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        koalaGame.start();
    }
}