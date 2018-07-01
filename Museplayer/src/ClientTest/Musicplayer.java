package ClientTest;
//package musicmain;
import javax.swing.*;

//import sun.audio.*;

import java.awt.*;

public class Musicplayer {

   // AudioStream as;
    JButton play = new JButton("play");
    JButton stop = new JButton("stop");
    JButton pause = new JButton("pause");



    public static void main(String [] why)
    {
        new Musicplayer().createGui();

    }
    public void createGui(){

        JFrame frame =new JFrame("Musikplayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,3));
        frame.add(play);
        frame.add(stop);
        frame.add(pause);
        frame.pack();
        frame.setVisible(true);




    }


}
