package ver1;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Mario extends Character
{

    private final Image imgMario;
    private final ImageIcon icoMario;

    private boolean jump;//true si il saut
    private int countJump;//il va nous aider a savoir la durer du saut
    private int width, height; //largeur hauteur
    private int x, y;// position du personnage
    private boolean walk;//true quand le perso marche
    private boolean toRight;//true quand le perso est tournee vers la droite
    public int count;//compteur des pas du perso

    public Mario(int x, int y)
    {
        super(x, y, 70, 100);
        this.icoMario = new ImageIcon(getClass().getResource("/image/marioStopRight.png"));
        this.imgMario = this.icoMario.getImage();
        this.toRight = toRight;
        this.walk = walk;
        this.jump = false;
        this.countJump = 0;
        this.live = true;
    }

    public boolean isJump()
    {
        return jump;
    }

    public void setJump(boolean jump)
    {
        this.jump = jump;
    }

    public Image getImgMario()
    {
        return imgMario;
    }

    public Image Jump()
    {

        ImageIcon ico;
        Image img;
        String str;

        this.countJump++;
        //monter du saut

        if(this.countJump <= 35)
        {
            //getY represente la tete de mario ten que il est pas plus grand que le plafond  //getheutplafond corespond a la hauteur du plafond
            if(this.getY() > Game.scene.getHauteurDuPlafon())
            {
                this.setY(this.getY() - 6);//on fait monter mario
            }
            else
            {
                this.countJump = 36;//sinon il reste bloquer a 36
            }
            if(this.isToRight() == true)
            {
                str = "/image/marioJump.png";
            }
            else
            {
                str = "/image/marioJump.png";
            }
        }
        //retomber du sauts
        //getY tete de mario et get width pied de mario
        else if(this.getY() + this.getWidth() < Game.scene.getySol())
        {
            //on fait redescendre mario car en haut c 4 pixel qui saute et la on lui donne 1 pour qui redescends tout doucement
            this.setY(this.getY() + 1);
            if(this.isToRight() == true)
            {
                str = "/image/marioJump.png";
            }
            else
            {
                str = "/image/marioJump.png";
            }
        }
        //saut terminer
        else
        {
            if(this.isToRight() == true)
            {
                str = "/image/marioStopRight.png";
            }
            else
            {
                str = "/image/marioStopLeft.png";
            }
            this.jump = false;
            this.countJump = 0;
        }

        //affichage de limage de mario
        ico = new ImageIcon(getClass().getResource(str));
        img = ico.getImage();
        return img;

    }

    public void contact(Character personnage)
    {
        if(contactSides(personnage))
        {
            System.out.println("you were hit by an enemy");
            this.setWalk(false);//mario meurt donc il arrete de marcher
            this.setLive(false);//et de vivre

        }
        else
        {
            System.out.println("enemy is dead");
            personnage.setWalk(false);//le personnage arrete de marcher
            personnage.setLive(false);//et de vivre

        }
    }

}
