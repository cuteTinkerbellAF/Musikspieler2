package ClientTest;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    private static final List<Songtest> songList = new ArrayList<>();
    private static final List<JButton> buttonList = new ArrayList<>();

    public static void main(String[] args) {
        //createSongList();
        createSongListTest();
        createButtonList();
        createButtonAction();
        guiAnzeige();
        //test();

    }

    private static void guiAnzeige() {
        //erstellt MainWindow
        JFrame mainWindow = new JFrame("Museplayer");
        mainWindow.setVisible(true);
        mainWindow.setSize(1000, 700);
        mainWindow.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        mainWindow.setResizable(true);
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
//erstellt ein rechtesPanel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.yellow);
        rootPanel.add(rightPanel);


        for (int z = 0; z < songList.size(); z++) { //erstellt die einzelnen Panels für alle Lieder, fügt einen upvotebutton hinzu und fügt diese Panels dem contentPanel hinzu

            JPanel titelPanel = new JPanel();
            contentPanel.add(titelPanel);
            titelPanel.setLayout(new BorderLayout());

            if (z % 2 == 0) {
                titelPanel.setBackground(Color.lightGray);
            } else {
                titelPanel.setBackground(Color.white);
            }

            JLabel title = new JLabel(songList.get(z).getTitle());
            // title.setFont(new Font("", Font.PLAIN, 20));
            titelPanel.add(title, BorderLayout.CENTER);


            titelPanel.add(buttonList.get(z), BorderLayout.LINE_END);

            //JButton voteButton = new JButton("upvote"); //ICON!!!!!
            //titelPanel.add(voteButton, BorderLayout.LINE_END);


        }

//erstellt das backpanel und fügt einen zurück knopf hinzu
        JPanel backPanel = new JPanel(new BorderLayout());
        mainWindow.add(backPanel, BorderLayout.PAGE_START);

        JButton backButton = new JButton("back");
        backPanel.add(backButton, BorderLayout.LINE_START);
        //backButton.setIcon(getClass().getResource("/main/icons/back.png"));
    }


    private static void createSongList() {
        File file = new File("songs/");
        if (!file.exists()) throw new RuntimeException("songfolder does not exist");

        File[] songs = file.listFiles();
        assert songs != null;
                    for (File musicFile : songs) {
                        try (InputStream input = new FileInputStream(musicFile)) {
                            byte[] b = new byte[(int) musicFile.length()];
                            int i = 0;
                            byte[] buffer = new byte[1028 * 8];
                            while (true) {
                                int x = input.read(buffer);
                                if (x == -1) break;

                                System.arraycopy(buffer, 0, b, i, x);

                    i += x;
                }



            } catch (Throwable t) {

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
                        System.out.println(songList.get(i).getTitle());
                        int v = songList.get(i).getVotes();
                        System.out.println(v);
                        v = v + 1;
                        songList.get(i).setVotes(v);
                        System.out.print(songList.get(i).getVotes());
                    }
            );
        }
    }

    private static void createSongListTest(){
        for (int i = 0; i<50;i++){
            songList.add(new Songtest("titel "+i, "fabi"));
        }
    }

    private static void test() {

    }
}
