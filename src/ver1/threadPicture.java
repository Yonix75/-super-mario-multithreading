package ver1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Document : Time Created on : May 11, 2022, 12:01:59 PM Author : watch
 */
public class threadPicture implements Runnable
{
    private final int Pause = 3;//arret de 3milsec // temps dattente entre 2 tours

    @Override
    public void run()
    {

        while(true)
        {
            Game.scene.checkPauseStatus();
            //il faut acceder a la classe scene pour redessiner le decore sinon il sort
            Game.scene.repaint(); //on apel de la class game scene pour que elle ne sarrete pas de dessiner le fond et mario
            // System.out.println("ok");//verifier que thread marche
            try
            {
                Thread.sleep(Pause);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(threadPicture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
