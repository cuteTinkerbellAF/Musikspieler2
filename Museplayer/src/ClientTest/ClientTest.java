package ClientTest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    // private static List<Song> songList = new ArrayList<>();

    public static void main(String[] args) {
        //createList();
        guiAnzeige();


    }

    public static void guiAnzeige() {
        JFrame mainWindow = new JFrame("Museplayer");
        mainWindow.setVisible(true);
        mainWindow.setSize(1000, 700);
        mainWindow.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        mainWindow.setResizable(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLayout(new BorderLayout());

        JPanel rootPanel = new JPanel(new GridLayout(1, 2));
        mainWindow.add(rootPanel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel();
        //contentPanel.setBackground(Color.cyan);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));

        JScrollPane leftScrollPanel = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftScrollPanel.setBackground(Color.green);
        rootPanel.add(leftScrollPanel);
        leftScrollPanel.getVerticalScrollBar().setUnitIncrement(15);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.yellow);
        rootPanel.add(rightPanel);


        for (int z = 0; z < 50; z++) { //erstellt die einzelnen Panels für alle Lieder und fügt einen upvotebutton hinzu
            JPanel titelPanel = new JPanel();
            contentPanel.add(titelPanel);
            titelPanel.setLayout(new BorderLayout());
            if (z % 2 == 0) {
                titelPanel.setBackground(Color.lightGray);
            } else {
                titelPanel.setBackground(Color.white);
            }

            JLabel title = new JLabel("Welcome to my House" + z);
            // title.setFont(new Font("", Font.PLAIN, 20));
            titelPanel.add(title, BorderLayout.CENTER);


            JButton voteButton = new JButton("upvote"); //ICON!!!!!
            titelPanel.add(voteButton, BorderLayout.LINE_END);


        }


        JPanel backPanel = new JPanel(new BorderLayout());
        mainWindow.add(backPanel, BorderLayout.PAGE_START);

        JButton backButton = new JButton("back");
        backPanel.add(backButton, BorderLayout.LINE_START);
        //backButton.setIcon(getClass().getResource("/main/icons/back.png"));
    }


    // private static void createList() {


    //    for (int z = 0; z < 100; z++) {
    //         String i = "fabi";
    //        String t = "titel" + z;
//
    //        Song lied = new Song(t, i);
    //        songList.add(lied);
//
    //    }
    // }


    // private static void test() {
    //   for (int z = 0; z < songList.size(); z++) {
    //        JLabel title = new JLabel(songList.get(z).getTitle());
    //   }
    // }

}
