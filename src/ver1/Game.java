package ver1;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Document : Game Created on : May 11, 2022, 1:09:50 AM Author : watch
 */
public class Game
{

    //elle est static car elle nous permet daccerder a cette classe partout dans notre programme donc elle doit etre accessible de partout
    public static Scene scene;

    public static void main(String[] args)
    {

        //create window
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        scene = new Scene();

        frame.setContentPane(scene);//on associe la scene a la fenetre de lapplication
        frame.setVisible(true);

    }
}
