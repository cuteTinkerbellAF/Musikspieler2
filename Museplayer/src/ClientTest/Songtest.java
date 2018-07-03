package ClientTest;
public class Songtest {
    private final String interpret;
    private final String title;
    private int votes = 0;
    private boolean playing = false;

    public Songtest(String t, String i) {
        title = t;
        interpret = i;
    }

    public String getInterpret() {
        return interpret;
    }

    public String getTitle() {
        return title;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int v) {
        votes = v;
    }

    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying(boolean b){
        playing = b;
    }

    public Songtest getMe() {
        return this;
    }
}