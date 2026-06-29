package ver1;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Document : Champs Created on : May 14, 2022, 10:17:55 PM Author : watch
 */
public class Champs extends Character implements Runnable
{
    private final Image imgChamp;

    private final ImageIcon icoChamp;

    private final int PAUSE = 15;//temps dattente en miliseconde entre 2 tours de boucle
    private int dxChamp;//les pas de deplacement du champignon
    public Scene scene;

    public Champs(int x, int y, Scene scene)
    {
        super(x, y, 70, 129);
        super.setToRight(false);
        super.setWalk(true);

        this.dxChamp = 1;
        this.icoChamp = new ImageIcon(getClass().getResource("/image/champRunLeft.png"));
        this.imgChamp = this.icoChamp.getImage();
        this.live = true;
        this.scene = scene;

        Thread chronoChamp = new Thread(this);
        chronoChamp.start();
    }

    public Image getImgChamp()
    {
        return imgChamp;
    }

    //move to opponnent
    public void Move()
    {

        if(super.isToRight() == true)
        {
            this.dxChamp = 1;//il va vers la droite
        }
        else
        {
            this.dxChamp = -1;//il va vers la gauche
        }
        super.setX(super.getX() + this.dxChamp);//mets a jour labsisse du champignion a chaque fois

    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " thread Started...");
        try
        {
            Thread.sleep(20);//on endors le thread 20 mili secondes pour les object ait le temps de ce creer

        }
        catch(InterruptedException e)
        {
        }

        while(true)//boucle inifini
        {
            if(!this.live || scene.isClearThreads())
            {
                Thread.interrupted();
                System.out.println(Thread.currentThread().getName() + " thread Finished...");
                break;
            }

            else
            {
                scene.checkPauseStatus();
                this.Move();
                scene.checkColission(this);

                try
                {
                    Thread.sleep(PAUSE);//lendormi a 15 mili secondes
                    /*System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");*/
                }
                catch(InterruptedException e)
                {

                }
            }

        }

    }

    public Image death()
    {
        String str;
        ImageIcon ico;
        Image img;

        if(this.isToRight() == true)
        {
            str = "/image/death.png";
        }
        else
        {
            str = "/image/death.png";
        }
        ico = new ImageIcon(getClass().getResource(str));
        img = ico.getImage();
        return img;
    }

    public void contact(Character personnage)
    {
        if(contactSides(personnage) && this.isLive() == true)
        {
            setToRight(!toRight);
//            personnage.setToRight(!personnage.toRight);
            setX(getX() + 10);
            personnage.setX(personnage.getX() - 10);

//            this.dxChamp = -1;
        }
        /*else if(super.contactDown(personnage) == true && this.isToRight() == false)
        {
            super.setToRight(true);
            this.dxChamp = 1;
        }*/
    }

}
