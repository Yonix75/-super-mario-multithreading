package ver1;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.xml.stream.events.Characters;
import ver1.Game;

/**
 * Document : characters Created on : May 14, 2022, 4:04:09 PM Author : watch
 */
public class Character
{
    private int width, height; //largeur hauteur
    private int x, y;// position du personnage
    private boolean walk;//true quand le perso marche
    boolean toRight;//true quand le perso est tournee vers la droite
    public int count;//compteur des pas du perso
    protected boolean live;//mario vivvant

    public Character(int x, int y, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public Character(int width, int height, int x, int y, boolean walk, boolean toRight, int count)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.walk = walk;
        this.toRight = toRight;
        this.count = count;
        this.live = true;

    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public boolean isLive()
    {
        return live;
    }

    public void setLive(boolean live)
    {
        this.live = live;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean isWalk()
    {
        return walk;
    }

    public void setWalk(boolean walk)
    {
        this.walk = walk;
    }

    public boolean isToRight()
    {
        return toRight;
    }

    public void setToRight(boolean toRight)
    {
        this.toRight = toRight;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public Image walk(String name, int frequence)
    {
        String str;

        ImageIcon ico;
        Image img;

        if(this.walk == false)//personnage arreter ou completements a gauche de lecran
        {
            if(this.toRight == true)
            {
                str = "/image/" + name + "StopRight.png";

            }
            else
            {
                str = "/image/" + name + "StopLeft.png";

            }

        }
        else
        {
            this.count++;
            if(this.count / frequence == 0)
            {
                if(this.toRight == true)
                {
                    str = "/image/" + name + "StopRight.png";
                }
                else
                {
                    str = "/image/" + name + "StopLeft.png";
                }
            }
            else
            {
                if(this.toRight == true)
                {
                    str = "/image/" + name + "RunRight.png";
                }
                else
                {
                    str = "/image/" + name + "RunLeft.png";
                }

            }
            if(this.count == 2 * frequence)
            {
                this.count = 0;
            }
        }
        //affichage du personnage
        ico = new ImageIcon(getClass().getResource(str));
        img = ico.getImage();
        return img;

    }

    //methodes////////////
    public Image walkOpponent(int frequence2)
    {
        String str2;

        ImageIcon ico2;
        Image img2;

        if(this.walk == false)//personnage arreter ou completements a gauche de lecran
        {
            if(this.toRight == true)
            {
                str2 = "/image/champStopRight.png";

            }
            else
            {
                str2 = "/image/champStopLeft.png";

            }

        }
        else
        {
            this.count++;
            if(this.count / frequence2 == 0)
            {
                if(this.toRight == true)
                {
                    str2 = "/image/champStopRight.png";
                }
                else
                {

                    str2 = "/image/champStopLeft.png";
                }
            }
            else
            {
                if(this.toRight == true)
                {
                    str2 = "/image/champStopRight.png";
                    //str2 = "/image/champRunRight.png";
                }
                else
                {
                    str2 = "/image/champStopLeft.png";
                    // str2 = "/image/champRunLeft.png";
                }

            }
            if(this.count == 2 * frequence2)
            {
                this.count = 0;
            }
        }
        //affichage du personnage
        ico2 = new ImageIcon(getClass().getResource(str2));
        img2 = ico2.getImage();
        return img2;

    }

    public void fixBgMove()//ici nous fixons les mechant
    {
        if(Game.scene.getxPos() >= 0)
        {
            this.x = this.x - Game.scene.getDx();
        }
    }

    public boolean intersects(Character character)//proche quand mario est proche de lenemie
    {
        return isLive() && getBounds().intersects(character.getBounds());//ici  ous verifion si le rectangle ou je suis coupe le deuxieme rectangle

    }

    public boolean contactSides(Character character)
    {
        int dec = 2;
        Rectangle rect = new Rectangle(x, y - dec, width, height - dec);
        return rect.intersects(character.getBounds());
    }

    public boolean contactDown(Character character)
    {
        Rectangle bottom = new Rectangle(x, height, width, 10);
        return bottom.intersects(character.getBounds());

    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }

}
