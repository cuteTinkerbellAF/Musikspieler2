package ClientTest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {
    private static String username = "";
    private static String newusername;
    private static final List<Songtest> songList = new ArrayList<>();
    private static final List<JButton> buttonList = new ArrayList<>();
    private static final List<Songtest> voteList = new ArrayList<>();
    private static JFrame mainWindow = new JFrame("Museplayer");

    public static void main(String[] args) {

        createSongListTest();
        createVoteList();

        createButtonList();
        createButtonAction();
        bubblesort();
        guiAnzeige();
        createLogInGUI();
        //test();
    }

    private static void guiAnzeige() {

//erstellt MainWindow
        mainWindow.setSize(1000, 700);
        mainWindow.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLayout(new BorderLayout());
//erstellt rootPanel als "erstes" Panel
        JPanel rootPanel = new JPanel(new GridLayout(1, 2));
        mainWindow.add(rootPanel, BorderLayout.CENTER);
//erstellt contentPanel in dem später die titelpanel hinzugefügt werden
        JPanel contentPanel = new JPanel();
        //contentPanel.setBackground(Color.cyan);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
//erstellt ein scrollpanel in der linken hälfte
        JScrollPane leftScrollPanel = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftScrollPanel.setBackground(Color.green);
        rootPanel.add(leftScrollPanel);
        leftScrollPanel.getVerticalScrollBar().setUnitIncrement(15);

 //erstellt Player gui HIER MUSS NOCH GEARBEITET WERDEN!!!!!!!!!
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setPreferredSize(new Dimension(mainWindow.getWidth(),40));
        mainWindow.add(playerPanel, BorderLayout.PAGE_END);

        JLabel placeholder = new JLabel("Gerade spielt: "+getPlaying());
        playerPanel.add(placeholder, BorderLayout.CENTER);

 //erstellt das topPanel und fügt einen benutzerwechselen knopf hinzu
        JPanel topPanel = new JPanel(new BorderLayout());
        mainWindow.add(topPanel, BorderLayout.PAGE_START);

        JButton logoutButton = new JButton("Benutzer wechseln");
        topPanel.add(logoutButton, BorderLayout.LINE_START);
        //backButton.setIcon(getClass().getResource("/main/icons/back.png"));
        logoutButton.addActionListener(e -> {
            createLogInGUI2();
        });

        JLabel loggedin = new JLabel("Angemeldet als "+username+"  ");
        topPanel.add(loggedin,BorderLayout.LINE_END);


        for (int z = 0; z < songList.size(); z++) { //erstellt die einzelnen Panels für alle Lieder, fügt einen upvotebutton hinzu und fügt diese Panels dem contentPanel hinzu

            JPanel titelPanel = new JPanel();
            contentPanel.add(titelPanel);
            titelPanel.setLayout(new BorderLayout());

            if (z % 2 == 0) {
                titelPanel.setBackground(Color.lightGray);
            } else {
                titelPanel.setBackground(Color.white);
            }

            JLabel title = new JLabel("  "+songList.get(z).getTitle());
            titelPanel.add(title, BorderLayout.CENTER);


            titelPanel.add(buttonList.get(z), BorderLayout.LINE_END);



        }


        JPanel contentPanels2 = new JPanel();
        contentPanels2.setLayout(new BoxLayout(contentPanels2, BoxLayout.PAGE_AXIS));

        JScrollPane rightScrollPanel = new JScrollPane(contentPanels2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightScrollPanel.setBackground(Color.green);
        rootPanel.add(rightScrollPanel);
        rightScrollPanel.getVerticalScrollBar().setUnitIncrement(15);



        mainWindow.setVisible(true);

        for (int z = 0; z < songList.size(); z++) {
            JPanel titlePanelRight = new JPanel(new BorderLayout());
            contentPanels2.add(titlePanelRight);
            titlePanelRight.setPreferredSize(new Dimension(contentPanel.getComponent(0).getWidth(),contentPanel.getComponent(0).getHeight()));

            JLabel titleVotes = new JLabel(voteList.get(z).getTitle(),SwingConstants.CENTER);
            titlePanelRight.add(titleVotes, BorderLayout.CENTER);

            JLabel voteVotes = new JLabel("Stimmen: " + voteList.get(z).getVotes()+"   ");
            titlePanelRight.add(voteVotes,BorderLayout.LINE_END);

            if (z % 2 == 0) {
                titlePanelRight.setBackground(Color.lightGray);
            } else {
                titlePanelRight.setBackground(Color.white);
            }
        }
    }


    private static void createButtonList() {
        for (int z = 0; z < songList.size(); z++) {
            buttonList.add(new JButton("upvote"));
        }

    }
    private static void createButtonAction() {
        for (final JButton button : buttonList) {
            button.addActionListener(e -> {
                        int i = buttonList.indexOf(button);
                        //button.setIcon("");
                        buttonList.get(i).setEnabled(false);
                        int v = songList.get(i).getVotes();
                        v = v + 1;
                        songList.get(i).setVotes(v);
                        bubblesort();
                        mainWindow.repaint();
                    }
            );
        }
    }
    private static void createSongListTest() {
        for (int i = 0; i < 50; i++) {
            songList.add(new Songtest("titel " + i, "fabi"));
        }
    }
    private static void createVoteList() {
        for (int i = 0; i < songList.size(); i++) {
            voteList.add(songList.get(i));
        }
    }
    private static void createLogInGUI(){
        String input = JOptionPane.showInputDialog( "Username" );
        if (input == null){
            System.exit(0);
        }
        else{
            newusername = input;
            activateButtons();
            mainWindow.getComponent(0).repaint(); //geht nicht
        }
    }      //erstellt den Dialog der auftaucht sobald man das Programm zum ersten mal startet, bei abbruch oder X wird programm beendet
    private static void createLogInGUI2 (){     //erstellt den Dialog der auftaucht wenn man den benutzer wechseln will, andere action bei abbruch
        String input2 = JOptionPane.showInputDialog("Username");
        if (input2 != null){
            newusername = input2;
            activateButtons();
            mainWindow.repaint(); //geht nicht
        }

    }

    private static void bubblesort(){
        Songtest temp;
        for(int i=1; i<voteList.size(); i++) {
            for(int j=0; j<voteList.size()-i; j++) {
                if(voteList.get(j).getVotes()<voteList.get(j+1).getVotes()) {

                    temp=voteList.get(j);
                    voteList.set(j, voteList.get(j+1));
                    voteList.set(j+1,temp);

                }

            }
        }
    } //sortiert die lieder nach den Votes

    private static void activateButtons(){
        if(!newusername.equals(username)){
            for (int i = 0; i<buttonList.size();i++){
                buttonList.get(i).setEnabled(true);
                test();
                username = newusername;
            }
        }
    }

    private static String getPlaying(){
        for(int i = 0; i<songList.size();i++){
            if (songList.get(i).getPlaying()){
                return songList.get(i).getTitle();
            }
        }
        return "leider gar nichts :(";
    }

    private static void test(){

    }
}
