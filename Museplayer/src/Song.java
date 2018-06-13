public class Song {
    private final String interpret;
    private final String title;
    private int votes = 0;

    public Song(String t, String i) {
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

    public Song getMe() {
        return this;
    }
}