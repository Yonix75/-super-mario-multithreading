package ver1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Document : keyBoardTouch Created on : May 11, 2022, 12:30:02 PM Author :
 * watch
 */
public class keyBoardTouch implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent ke)
    {

    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(Game.scene.mario.isLive() == true)
        {
            if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                if(Game.scene.getxPos() == -1)//si ma position est a -1 je restre a 0
                {
                    Game.scene.setxPos(0);//ici pour bloquer la deuxieme partie de la photo car il il n y a rien
                    Game.scene.setxFond1(-50);//bien centrer la photo qui complete lautre sinon il y aura des decalage le deuxieme font
                    Game.scene.setxFond2(650);//ici mest les mem mesure que le premier font pour que la photo fond1 complete la deuxieme a chaque fois

                }
                Game.scene.setDx(1);//on avance donc le paysage bouge avec nous vers la droite
                Game.scene.mario.setToRight(true);
                Game.scene.mario.setWalk(true);

            }
            if(ke.getKeyCode() == KeyEvent.VK_LEFT)
            {
                Game.scene.setDx(-1);//on avance donc le paysage bouge avec nous vers la gauche
                Game.scene.mario.setToRight(false);
                Game.scene.mario.setWalk(true);
            }
            //mario saute
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
            {
                Game.scene.mario.setJump(true);
            }
        }
        if(ke.getKeyCode() == KeyEvent.VK_P)
        {
            System.out.println(" P");
            Game.scene.pauseOnOffGame();
        }
        if(ke.getKeyCode() == KeyEvent.VK_N)
        {
            Game.scene.setClearThreads(true);
            System.out.println("newGame");
            Game.scene.newGame();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        Game.scene.setDx(0);//quand on appuie sur rien dx et a zero et rien navance
        Game.scene.mario.setWalk(false);
    }

}
