package tankGame.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Tank_Level {

    Integer position;
    BufferedReader level;
    String file;
    int start;
    int width, height;

    public Tank_Level(String name) {
        String line;
        try {
            file = name;
            level = new BufferedReader(new InputStreamReader(getClass().getResource(file).openStream()));
            line = level.readLine();
            width = line.length();
            height = 0;
            while (line != null) {
                height++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void load() {
        Tank_World tank_game = Tank_World.getInstance();
        String line;
        try {
            level = new BufferedReader(new InputStreamReader(Tank_World.class.getResource(file).openStream()));
            line = level.readLine();
            width = line.length();
            height = 0;
            while (line != null) {
                for (int i = 0, n = line.length(); i < n; i++) {
                    char value = line.charAt(i);
                    switch (value) {
                        case '1':
                            tank_game.add_Sprite(tank_game.getSprites().get("wall1"), i * 32, height * 32, 0, 1, "wall");
                            break;
                        case '2':
                            tank_game.add_Wall(tank_game.getSprites().get("wall2"), i * 32, height * 32); 
                            break;
                        case '3':
                            tank_game.player1_Init(tank_game.getSprites().get("player1"), i * 32, height * 32);
                            break;
                        case '4':
                            tank_game.player2_Init(tank_game.getSprites().get("player2"), i * 32, height * 32); 
                            break;
                        case '5':
                            tank_game.add_Sprite(tank_game.getSprites().get("power"), i * 32, height * 32, 0, 1, "power");
                            break;
                        default:
                            break;
                    }
                }
                height++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
