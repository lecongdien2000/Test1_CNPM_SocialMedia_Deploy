package Model;

public class PostCreated {
    String fname;
    String text;
    String mediapath;
    String datecreated;

    public PostCreated(String fname, String text, String mediapath, String datecreated) {
        this.fname = fname;
        this.text = text;
        this.mediapath = mediapath;
        this.datecreated = datecreated;
    }

    public String getFname() {
        return fname;
    }

    public String getText() {
        return text;
    }

    public String getMediapath() {
        return mediapath;
    }

    public String getDatecreated() {
        return datecreated;
    }


}
