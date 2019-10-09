package koalaGame.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tankGame.game.*;

public class Koala_Player extends Tank_Sprites {

    protected ArrayList<Tank_Damage> damage;
    private final int DEGREES = 6; 
    boolean[] directionKeys = new boolean[4]; 
    int rotate, initialX,initialY,initialSprite;
    boolean show = true;
    
    public Koala_Player(BufferedImage buffImg, int initialX, int initialY, int sprite, int totalSprites){
        super(buffImg, initialX, initialY, sprite, totalSprites,"player");
        
        for(int i=0; i<=3; i++){
            directionKeys[i]=false;
        }
        
        damage = new ArrayList<>();
        rotate = DEGREES*sprite;
        this.initialY = initialY;
        this.initialX = initialX;
        this.initialSprite = sprite;
    }

    public void moveKoala(){
        if(directionKeys[0]) moveKoalaLeft();
        if(directionKeys[1]) moveKoalaDown();
        if(directionKeys[2]) moveKoalaRight();
        if(directionKeys[3]) moveKoalaUp();
    }

    public void moveKoalaLeft(){
        x-=get_speed();
        sprite++;
        if(sprite>30) sprite=26;
    }
    public void moveKoalaRight(){
        x+=get_speed();
        sprite++;
        if(sprite>15) sprite=8;
    }
    public void moveKoalaUp(){
        y -= get_speed();
        sprite++;
        if(sprite>23) sprite=16;
    }
    public void moveKoalaDown(){
        y += get_speed();
        sprite++;
        if(sprite>7) sprite=0;
    }
}
