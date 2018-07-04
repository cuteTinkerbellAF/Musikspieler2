package ClientTest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {
//erstellt verschiedene Listen und Variablen die aus der ganzen Klasse aufrufbar sein sollen
    private static String username = "Benutzer";
    private static String newusername;
    private static final List<Songtest> songList = new ArrayList<>();
    private static final List<JButton> buttonList = new ArrayList<>();
    private static final List<Songtest> voteList = new ArrayList<>();
    private static JFrame mainWindow = new JFrame("Museplayer");

    public static void main(String[] args) {
//mainMethode; Aufruf der benötigten Methoden
        createSongListTest();
        createVoteList();

        createButtonList();
        createButtonAction();
        bubblesort();
        guiAnzeige();
        createLogInGUI();
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
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
//erstellt ein scrollpanel in der linken hälfte und verändert scrollgeschindigkeit
        JScrollPane leftScrollPanel = new JScrollPane(contentPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftScrollPanel.setBackground(Color.green);
        rootPanel.add(leftScrollPanel);
        leftScrollPanel.getVerticalScrollBar().setUnitIncrement(15);

 //erstellt die Fußzeile in der das aktuell gespielte lied angezeigt wird
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setPreferredSize(new Dimension(mainWindow.getWidth(),40));
        mainWindow.add(playerPanel, BorderLayout.PAGE_END);

        JLabel activeSong = new JLabel("Gerade spielt: "+getPlaying());
        playerPanel.add(activeSong, BorderLayout.CENTER);

 //erstellt das topPanel(Kopfzeile) und fügt einen benutzerwechseln Knopf hinzu
        JPanel topPanel = new JPanel(new BorderLayout());
        mainWindow.add(topPanel, BorderLayout.PAGE_START);

        JButton logoutButton = new JButton("Benutzer wechseln");
        topPanel.add(logoutButton, BorderLayout.LINE_START);
        //backButton.setIcon(getClass().getResource("/main/icons/back.png"));
        logoutButton.addActionListener(e -> {
            createLogInGUI2();
        });
//Zeigt den gerade angemeldeten Benutzer im Toppanel an
        JLabel loggedIn = new JLabel("Angemeldet als "+username+"  ");
        topPanel.add(loggedIn,BorderLayout.LINE_END);

//erstellt einzelne Panels für alle Lieder, fügt einen upvotebutton hinzu und fügt diese Panels dem contentPanel hinzu
        for (int z = 0; z < songList.size(); z++) {

            JPanel titlePanel = new JPanel();
            contentPanel.add(titlePanel);
            titlePanel.setLayout(new BorderLayout());
//schaut ob ein Panel eine gerade oder ungerade Zahl hat und verändert die Panelfarbe für eine benutzerfreundlichere GUI
            if (z % 2 == 0) {
                titlePanel.setBackground(Color.lightGray);
            } else {
                titlePanel.setBackground(Color.white);
            }

            JLabel title = new JLabel("  "+songList.get(z).getTitle());
            titlePanel.add(title, BorderLayout.CENTER);


            titlePanel.add(buttonList.get(z), BorderLayout.LINE_END);



        }

//erstellt ein zweites contentPanel für die rechte Hälfte und fügt dieses ins Rootpanel ein
        JPanel contentPanel2 = new JPanel();
        contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.PAGE_AXIS));
//erstellt ein Scrollpanel für das rechte Contentpanel und verändert dessen Scrollgeschwindigkeit
        JScrollPane rightScrollPanel = new JScrollPane(contentPanel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootPanel.add(rightScrollPanel);
        rightScrollPanel.getVerticalScrollBar().setUnitIncrement(15);


//mainwindow muss aktiv werden um die Höhe der einzelnen LiederPanels zu bekommen
        mainWindow.setVisible(true);
//sortiert die Lieder nach der Anzahl der Stimmen absteigend
        bubblesort();
//erstellt die Rankingliste für die rechte Seite mit Platz, Titel und Stimmen
        for (int z = 0; z < songList.size(); z++) {
            JPanel titlePanelRight = new JPanel(new BorderLayout());
            contentPanel2.add(titlePanelRight);
            titlePanelRight.setPreferredSize(new Dimension(contentPanel.getComponent(0).getWidth(),contentPanel.getComponent(0).getHeight()));

            JLabel titleVotes = new JLabel((z+1)+". "+voteList.get(z).getTitle(),SwingConstants.CENTER);
            titlePanelRight.add(titleVotes, BorderLayout.CENTER);

            JLabel voteVotes = new JLabel("Stimmen: " + voteList.get(z).getVotes()+"   ");
            titlePanelRight.add(voteVotes,BorderLayout.LINE_END);
//schaut ob ein Panel eine gerade oder ungerade Zahl hat und verändert die Panelfarbe für eine benutzerfreundlichere GUI
            if (z % 2 == 0) {
                titlePanelRight.setBackground(Color.lightGray);
            } else {
                titlePanelRight.setBackground(Color.white);
            }
        }
    }


    private static void createButtonList() {
//erstellt genauso viele Buttons wie es Lieder gibt und fügt diese einer Liste zu um sie gezielt abzugreifen
        for (int z = 0; z < songList.size(); z++) {
            buttonList.add(new JButton("upvote"));
        }

    }
    private static void createButtonAction() {
//fügt den einzelnen Buttons eine Funktion hinzu, wie die Stimmfunktion und dass jeder Knopf nur einmal gedrückt werden kann
        for (final JButton button : buttonList) {
            button.addActionListener(e -> {
                        int i = buttonList.indexOf(button);
                        //button.setIcon("");
                        buttonList.get(i).setEnabled(false);
                        int v = songList.get(i).getVotes();
                        v = v + 1;
                        songList.get(i).setVotes(v);
//nachdem für ein Lied gestimmt worden ist, soll sich die Voteliste aktualisieren und sich auf die GUI übertragen
                        bubblesort();
                        mainWindow.repaint(); //geht nicht!!
                    }
            );
        }
    }
    private static void createSongListTest() {
//erstellt fiktive Lieder um die Arbeit und den Umgang mit der GUI zu verdeutlichen (nur solange aktiv bis lieder durch Programm hinzugefügt werden)
        for (int i = 0; i < 50; i++) {
            songList.add(new Songtest("titel " + i, "fabi"));
        }
    }
    private static void createVoteList() {
//überträgt die Lieder der SongList in die VoteList, um sie später unabhängig behandeln zu können
        for (int i = 0; i < songList.size(); i++) {
            voteList.add(songList.get(i));
        }
    }
    private static void createLogInGUI(){
//erstellt den Dialog der auftaucht sobald man das Programm zum ersten mal startet, bei abbruch oder X wird programm beendet, sonst soll neuer Benutzer angenommen und angezeigt werden,
//für neuen Benutzer soll die Votefunktion wiederhergestellt werden
        String input = JOptionPane.showInputDialog( "Username" );
        if (input == null){
            System.exit(0);
        }
        else{
            newusername = input;
            activateButtons();
            mainWindow.getComponent(0).repaint(); //geht auch nicht, genauso wie mainWindow.repaint()
        }
    }
    private static void createLogInGUI2 (){
//erstellt den Dialog der auftaucht wenn man den benutzer wechseln will, Dialog verschwindet bei Abbruch oder X und beendet nicht mehr das Programm
        String input2 = JOptionPane.showInputDialog("Username");
        if (input2 != null){
            newusername = input2;
            activateButtons();
            mainWindow.repaint(); //geht nicht
        }

    }
    private static void bubblesort(){
//sortiert die lieder nach den Votes, nicht der schnellste, jedoch ausreiched
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
    }
    private static void activateButtons(){
//reaktiviert alle Buttons, wenn sich ein neuer Benutzer anmeldet und ändert die username variable
        if(!newusername.equals(username)){
            for (int i = 0; i<buttonList.size();i++){
                buttonList.get(i).setEnabled(true);
                username = newusername;
            }
        }
    }
    private static String getPlaying(){
//sucht das aktuell gespielte Lied um es in der Fußleiste anzeigen zulassen und gibt den Titel des Liedes zurück
        for(int i = 0; i<songList.size();i++){
            if (songList.get(i).getPlaying()){
                return songList.get(i).getTitle();
            }
        }
        return "leider gar nichts :(";
    }
}
