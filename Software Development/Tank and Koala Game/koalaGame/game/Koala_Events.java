package koalaGame.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import tankGame.game.*;

public class Koala_Events {
    int[] koala1,koala2,koala3;
    int rescuedKoala = 0;
    Koala_World koalaGame = Koala_World.getInstanceKoalaWorld();

    public void process(ArrayList<Koala_Player> koalas, ArrayList<Tank_Sprites> sprites){
        move(koalas);
        checkColision(koalas, sprites);
    }

    public void pressed(KeyEvent keyevent, ArrayList<Koala_Player> koalas) {
        if(keyevent.getKeyCode() == KeyEvent.VK_UP){
            for(int i=0; i < koalas.size(); i++) {
                koalas.get(i).directionKeys[3] = true;
                koalas.get(i).sprite = 16;
            }
        } 
        if(keyevent.getKeyCode() == KeyEvent.VK_DOWN) {
            for(int i=0; i < koalas.size(); i++) {
                koalas.get(i).directionKeys[1] = true;
                koalas.get(i).sprite = 0;
            }
        }
        if(keyevent.getKeyCode() == KeyEvent.VK_RIGHT){
            for(int i=0; i < koalas.size(); i++) {
                koalas.get(i).directionKeys[2] = true;
                koalas.get(i).sprite = 8;
            }
        }
        if(keyevent.getKeyCode() == KeyEvent.VK_LEFT){
            for(int i=0; i < koalas.size();i++) {
                koalas.get(i).directionKeys[0] = true;
                koalas.get(i).sprite = 25;
            }
        }
    }

    public void released(KeyEvent keyevent, ArrayList<Koala_Player> koalas){
        
        if(keyevent.getKeyCode() == KeyEvent.VK_UP)
            for(int i=0; i < koalas.size(); i++)
                koalas.get(i).directionKeys[3] = false;
        if(keyevent.getKeyCode() == KeyEvent.VK_DOWN)
            for(int i=0; i < koalas.size(); i++)
                koalas.get(i).directionKeys[1] = false;
        if(keyevent.getKeyCode() == KeyEvent.VK_RIGHT)
            for(int i=0; i < koalas.size(); i++)
                koalas.get(i).directionKeys[2] = false;
        if(keyevent.getKeyCode() == KeyEvent.VK_LEFT)
            for(int i=0; i < koalas.size(); i++)
                koalas.get(i).directionKeys[0] = false; 
    }

    public void clicked(MouseEvent mouseEvent){
        if(mouseEvent.getX() >= 530 && mouseEvent.getX() <= 530+koalaGame.getSpriteHashMap().get("restart").getWidth() && mouseEvent.getY() >=5 && mouseEvent.getY() <= 5+koalaGame.getSpriteHashMap().get("restart").getHeight())
            koalaGame.restart();
    }



    public void checkColision(ArrayList<Koala_Player> koalas, ArrayList<Tank_Sprites> sprites){
        Rectangle koala1Space = new Rectangle(koalas.get(0).get_x(), koalas.get(0).get_y(),35,35);
        Rectangle koala2Space = new Rectangle(koalas.get(1).get_x(), koalas.get(1).get_y(),35,35);
        Rectangle koala3Space = new Rectangle(koalas.get(2).get_x(), koalas.get(2).get_y(),35,35);
        for(int i=0; i < sprites.size(); i++){
            Rectangle spriteSpace = new Rectangle(sprites.get(i).get_x(), sprites.get(i).get_y(),
                sprites.get(i).getImg().getWidth()/2,sprites.get(i).getImg().getHeight()/2);
            
            if(koalas.get(0).show && koala1Space.intersects(spriteSpace)){
                if("detonator".equals(sprites.get(i).get_type())){
                    sprites.get(i).sprite=1;
                    removeTnt(sprites);
                }
                if("tnt".equals(sprites.get(i).get_type())){
                    Tank_World.game_audio.play("resources/Explosion_small.wav");
                    koalas.get(0).show=false;
                }
                if("exit".equals(sprites.get(i).get_type())){
                    rescuedKoala++;
                    Tank_World.game_audio.play("resources/Saved.wav");
                    koalas.get(0).show=false;
                    koalaGame.rescuedKoala.add(new Tank_Sprites(koalaGame.getSpriteHashMap().get("koalaRescued"),150,5,0,1,"koalarescued"));
                }
                koalas.get(0).set_x(koala1[0]);
                koalas.get(0).set_y(koala1[1]);
            }
            if(koalas.get(1).show && koala2Space.intersects(spriteSpace)){
                if("detonator".equals(sprites.get(i).get_type())){
                    sprites.get(i).sprite=1;
                    removeTnt(sprites);
                }
                if("tnt".equals(sprites.get(i).get_type())){
                    Tank_World.game_audio.play("resources/Explosion_small.wav");
                    koalas.get(1).show=false;
                }
                if("exit".equals(sprites.get(i).get_type())){
                    rescuedKoala++;
                    Tank_World.game_audio.play("resources/Saved.wav");
                    koalas.get(1).show=false;
                    koalaGame.rescuedKoala.add(new Tank_Sprites(koalaGame.getSpriteHashMap().get("koalaRescued"),200,5,0,1,"koalarescued"));
                }
                koalas.get(1).set_x(koala2[0]);
                koalas.get(1).set_y(koala2[1]);
            }
            if(koalas.get(2).show && koala3Space.intersects(spriteSpace)){
                if("detonator".equals(sprites.get(i).get_type())){
                    sprites.get(i).sprite=1;
                    removeTnt(sprites);
                }   
                if("tnt".equals(sprites.get(i).get_type())){
                    Tank_World.game_audio.play("resources/Explosion_small.wav");
                    koalas.get(2).show=false;
                }
                if("exit".equals(sprites.get(i).get_type())){
                    rescuedKoala++;
                    Tank_World.game_audio.play("resources/Saved.wav");
                    koalas.get(2).show=false;
                    koalaGame.rescuedKoala.add(new Tank_Sprites(koalaGame.getSpriteHashMap().get("koalaRescued"),250,5,0,1,"koalarescued"));
                }
                koalas.get(2).set_x(koala3[0]);
                koalas.get(2).set_y(koala3[1]);
            }
            if(koala1Space.intersects(koala2Space)){
                koalas.get(0).set_x(koala1[0]);
                koalas.get(0).set_y(koala1[1]);
            }
            if(koala2Space.intersects(koala3Space)){
                koalas.get(1).set_x(koala2[0]);
                koalas.get(1).set_y(koala2[1]);
            }
            if(rescuedKoala == 3) {
                rescuedKoala=0;
                koalaGame.nextLevel();
            }
        }
    }
    public void removeTnt(ArrayList<Tank_Sprites> sprite){
        for(int i=0; i < sprite.size();i++){
            if("tnt".equals(sprite.get(i).get_type())){
                sprite.remove(i);
            }
        }
    }
    
    public void move(ArrayList<Koala_Player> koalas){
        koala1 = new int[2];
        koala2 = new int[2];
        koala3 = new int[2];
        koala1[0] = koalas.get(0).get_x();
        koala1[1] = koalas.get(0).get_y();
        koala2[0] = koalas.get(1).get_x();
        koala2[1] = koalas.get(1).get_y();
        koala3[0] = koalas.get(2).get_x();
        koala3[1] = koalas.get(2).get_y();
        
        for(int i=0; i < koalas.size(); i++){
            koalas.get(i).moveKoala();
        }
    }

}
