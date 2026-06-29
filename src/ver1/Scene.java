package ver1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.E;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Document : Scene Created on : May 11, 2022, 1:16:54 AM Author : watch
 */
public class Scene extends JPanel
{

    private final ImageIcon iconFond;
    private final Image imgFond1;
    private final Image imgFond2;
    /* private ImageIcon icoMario;
    private Image imgMario;*/

    boolean clearThreads = false;

    int countThreads;

    public Mario mario;
    public Champs champ;
    public Champs champ1;
    public Champs champ2;
    public Champs champ3;
    public Champs champ4;
    public Champs champ5;
    public Champs champ6;
    public Champs champ7;
    public Champs champ8;
    public Champs champ9;
    public Champs champ10;
    public Champs champ11;
    public Champs champ12;
    public Champs champ13;
    public Champs champ14;
    public Champs champ15;
    public Champs champ16;

    public boolean pause;
    public boolean win = false;
    public static boolean isValid = false;

    public JButton newGame;

    public ArrayList<Champs> champsList;//liste de champignon

    private int hauteurDuPlafon; //hauteur du palfond
    private int ySol;//hauteur courante du sol
    private int xPos;//position absolue dans le jeu
    private int dx;//deplacement du fond ecran

    private int xFond1; //abssice de mon image ou elle se situra

    private int xFond2;

    private Font rules;

//constrator
    public Scene()
    {
        super();

        this.xFond1 = -50;
        this.xFond2 = 650;

        this.ySol = 293;
        this.hauteurDuPlafon = 0;
        this.xPos = -1;
        this.dx = 0;
        iconFond = new ImageIcon(getClass().getResource("/image/vi.png"));
        this.imgFond1 = this.iconFond.getImage();
        this.imgFond2 = this.iconFond.getImage();
        /* icoMario = new ImageIcon(getClass().getResource("/image/mario-remove.png"));
        this.imgMario = icoMario.getImage();*/

        newGame();

        //deploiment demarage dans le constrator des touches
        this.setFocusable(true);//Jpanel a le pouvoir de se concentre
        this.requestFocusInWindow();

        this.addKeyListener(new keyBoardTouch());

        rules = new Font("Arial", Font.BOLD, 18);

        //et dans le constrator de la scene on le demarre
        Thread timeScreen = new Thread(new threadPicture());

        timeScreen.start();

    }

    public int getxPos()
    {
        return xPos;
    }

    public void setxPos(int xPos)
    {
        this.xPos = xPos;
    }

    public int getHauteurDuPlafon()
    {
        return hauteurDuPlafon;
    }

    public void setHauteurDuPlafon(int hauteurDuPlafon)
    {
        this.hauteurDuPlafon = hauteurDuPlafon;
    }

    public int getySol()
    {
        return ySol;
    }

    public void setySol(int ySol)
    {
        this.ySol = ySol;
    }

    public boolean isClearThreads()
    {
        return clearThreads;
    }

    public void setClearThreads(boolean clearThreads)
    {
        this.clearThreads = clearThreads;
    }

    //methode de deplacement
    public void makeMove()
    {

        if(this.xPos >= 0)
        {
            this.xPos = this.xPos + this.dx;
            this.xFond1 = this.xFond1 - this.dx; //la position du fond sera egal a sa position moins quand on bouge pour quel se mette a jour
            this.xFond2 = this.xFond2 - this.dx;
            if(this.xFond1 == -700)//ici je verifie que quand limage arrive sa fin elle se remets a 700 pour quelle apparait toujours dans le decor des deux cotes
            {
                this.xFond1 = 700;
            }
            else if(this.xFond2 == -700)
            {
                this.xFond2 = 700;
            }
            else if(xFond1 == 700)
            {
                this.xFond1 = -700;
            }
            else if(this.xFond2 == 700)
            {
                this.xFond2 = -700;
            }
        }
        for(int i = 0; i < champsList.size(); i++)
        {

            this.champsList.get(i).fixBgMove();
        }

    }

    public int getDx()
    {
        return dx;
    }

    public void setDx(int dx)
    {
        this.dx = dx;
    }

    private boolean gameOver()
    {
        if(mario.isLive() == false)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean winGame()
    {
        if(mario.isLive() && countThreads == 9)
        {

            return true;
        }
        else
        {
            return false;
        }
    }

    //Methode pour avoir une meilleur qualiter graphique
    @Override
    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;

        //a chaque fois que on repaint lecran mais si on on bouge le font se deplace grace a labscisse donc c le paysage qui bouge
        this.makeMove();

        g2.drawImage(this.imgFond1, this.xFond1, 0, null);//dessin de limage du fond
        g2.drawImage(this.imgFond2, this.xFond2, 0, null);

        //detection des contacte avec les champignion(hors mario)
        if(this.mario.isJump())
        {
            g2.drawImage(this.mario.Jump(), this.mario.getX(), this.mario.getY(), null);
        }
        else
        {
            g2.drawImage(this.mario.walk("mario", 25), 100, 240, null);
        }

        synchronized(this)
        {
            //image du champignion
            for(int i = 0; i < champsList.size(); i++)
            {
                if(this.champsList.get(i).isLive())
                {
                    g2.drawImage(this.champsList.get(i).walkOpponent(25), this.champsList.get(i).getX(), 270, null);//X Y abssice du champignon ou il sera exactement*/
                }
            }

            //else
            //{
            //  g2.drawImage(this.champsList.get(i).death(), this.champsList.get(i).getX(), this.champsList.get(i).getY(), null);//X Y abssice du champignon ou il sera exactement*/
            //}
        }

        // check pause status
        if(pause)
        {
            Font pause = new Font("Arial", Font.BOLD, 50);
            g2.setColor(Color.WHITE);
            g2.setFont(pause);
            g.drawString("PAUSE", 210, 180);
        }

        g2.setFont(rules);
        g2.drawString(" move right - arrow right move left - arrow left jump - space", 2, 25);

        g2.drawString("P - Pause  N - NewGame", 4, 55);

        g2.drawString("count of oponnent dead:" + countThreads, 300, 60);
        //end GAme

        if(gameOver())
        {
            Font gameOver = new Font("Arial", Font.BOLD, 50);
            Font replay = new Font("Arial", Font.BOLD, 20);
            g2.setFont(gameOver);
            g2.drawString("GAME OVER", 120, 180);
            g2.setFont(replay);
            g2.drawString("to play again  N - NewGame ", 130, 200);
        }

        if(winGame())
        {
            for(int i = 0; i < champsList.size(); i++)
            {
                champsList.remove(i);

            }

            mario.setWalk(false);
            mario.setJump(false);

            Font winner = new Font("Arial", Font.BOLD, 50);
            Font replay2 = new Font("Arial", Font.BOLD, 20);
            g2.setFont(winner);
            g2.drawString("WINNER", 120, 180);
            g2.setFont(replay2);
            g2.drawString("Bravo!!to play again  N - NewGame ", 130, 200);

        }

    }

    public synchronized void checkColission(Champs champ)
    {

        //detection des contacte avec les champignion(hors mario)
        for(int j = 0; j < champsList.size(); j++)
        {
            if(champ != champsList.get(j))
            {
                if(this.champsList.get(j).intersects(champ))
                {
                    champ.setLive(false);

                    break;
                }
            }

        }

        if(mario.intersects(champ))
        {
            mario.contact(champ);

        }
        if(!champ.live)
        {
            System.out.println("is dead");
            champsList.remove(champ);
            countThreads++;
        }

    }

    public synchronized void checkPauseStatus()
    {
        while(pause || win)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public synchronized void checkWin()
    {
        while(win)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public synchronized void pauseOnOffGame()
    {
        pause = !pause;

        if(pause == true)
        {
            repaint();
        }
        else
        {
            notifyAll();
        }
    }

    public int getxFond1()
    {
        return xFond1;
    }

    public void setxFond1(int xFond1)
    {
        this.xFond1 = xFond1;
    }

    public int getxFond2()
    {
        return xFond2;
    }

    public void setxFond2(int xFond2)
    {
        this.xFond2 = xFond2;
    }

    public void sleep(long mills)
    {
        try
        {
            Thread.sleep(mills);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

    public void newGame()
    {

        clearThreads = false;
        if(champsList != null)
        {

            for(int i = 0; i < champsList.size(); i++)
            {
                champsList.get(i).setLive(false);

            }
            mario.setLive(false);
        }
        countThreads = 0;
        mario = new Mario(100, 50);

        champ = new Champs(350, 263, this);
        champ.setToRight(true);
        champ1 = new Champs(450, 263, this);
        champ2 = new Champs(700, 263, this);
        champ3 = new Champs(800, 263, this);
        champ4 = new Champs(1500, 263, this);
        champ5 = new Champs(1700, 263, this);
        champ6 = new Champs(2000, 263, this);
        champ7 = new Champs(2500, 263, this);
        champ8 = new Champs(3000, 263, this);
        champ9 = new Champs(3200, 263, this);
        champ10 = new Champs(3800, 263, this);
        champ11 = new Champs(4300, 263, this);
        champ12 = new Champs(5000, 263, this);
        champ13 = new Champs(5300, 263, this);
        champ14 = new Champs(5900, 263, this);
        champ15 = new Champs(6300, 263, this);
        champ16 = new Champs(6600, 263, this);

        champsList = new ArrayList<Champs>();
        this.champsList.add(champ);
        this.champsList.add(champ1);
        this.champsList.add(champ2);
        this.champsList.add(champ3);
        this.champsList.add(champ4);
        this.champsList.add(champ5);
        this.champsList.add(champ6);
        this.champsList.add(champ7);
        this.champsList.add(champ8);

    }
}
