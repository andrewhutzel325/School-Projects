package koalaGame.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Koala_EmptyLvls extends JComponent implements Runnable{
    private Thread thread;
    static JFrame jFrameWindow;
    BufferedImage buffer_img1 = null, congratulation = null;

    public static void init(){
        jFrameWindow = new JFrame();
        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameWindow.setSize(640, 480);
        jFrameWindow.setLocationRelativeTo(null);
        jFrameWindow.getContentPane().add(new Koala_EmptyLvls());
        jFrameWindow.setResizable(false);
        jFrameWindow.setVisible(true);
    }

    public void paint(Graphics g) {
        try {
            buffer_img1 = ImageIO.read(getClass().getResource("resources/Background.png"));
            congratulation = ImageIO.read(getClass().getResource("resources/Congratulation.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(buffer_img1,0,0,this);
        g.drawImage(congratulation,80,0,this);
    }

    @Override
    public void run() {
        Thread current_thread = Thread.currentThread();
        while (thread == current_thread) {
            repaint();
            try {
                thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
