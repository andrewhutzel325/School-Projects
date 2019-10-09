package koalaGame.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Koala_Level {
    BufferedReader map;
    String file;
    int width, height;

    public Koala_Level(String name){
        String line;
        try {
            file = name;
            map = new BufferedReader(new InputStreamReader(getClass().getResource(file).openStream()));
            line = map.readLine();
            width = line.length();
            height=0;
            while(line!=null){
                height++;
                line = map.readLine();
            }
            map.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void load(){
        Koala_World gameKoala = Koala_World.getInstanceKoalaWorld();
        String line;
        try {
            map = new BufferedReader(new InputStreamReader(Koala_World.class.getResource(file).openStream()));
            line = map.readLine();
            width = line.length();
            height=0;
            
            while(line!=null){
                for(int i = 0, n = line.length() ; i < n ; i++) {
                    
                    char c = line.charAt(i);
                    
                    switch(c) {
                        case '0': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,0,16,"wall");
                            break;
                        case '1': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,1,16,"wall");
                            break;
                        case '2': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,2,16,"wall");
                            break;
                        case '3': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,4,16,"wall");
                            break;
                        case '4': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,4,16,"wall");
                            break;
                        case '5': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,5,16,"wall");
                            break;
                        case '6': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,6,16,"wall");
                            break;
                        case '7': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,7,16,"wall");
                            break;
                        case '8': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,8,16,"wall");
                            break;
                        case '9': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,9,16,"wall");
                            break;
                        case 'a': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,10,16,"wall");
                            break;      
                        case 'b': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,11,16,"wall");
                            break;
                        case 'c': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,12,16,"wall");
                            break;
                        case 'd': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,13,16,"wall");
                            break;
                        case 'e': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,14,16,"wall");
                            break;
                        case 'f': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("wall1"),i*40,height*40,15,16,"wall");
                            break;
                        case 't': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("tnt"), i * 40, height * 40,0,1, "tnt");
                            break;
                        case 'x': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("exit"), i * 40, height * 40, 0, 1, "exit");
                            break;
                        case 'w': gameKoala.addSprite(gameKoala.getSpriteHashMap().get("detonator"), i * 40, height * 40, 0, 2, "detonator");
                            break;
                        case 'p': gameKoala.addPlayer(gameKoala.getSpriteHashMap().get("player"),i*40,height*40);
                            break;
                    }
                }
                height++;
                line = map.readLine();
            }
            map.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
