package Model;
import java.util.*;

/**
 *
 */
public class Content {
    public final static String[] imgTypes = {"jpg", "gif", "png"};
    public final static String[] videoTypes = {"mp4", "3gp", "mpeg","webm"};
    public Content() {
        images = new ArrayList<>();
        videos = new ArrayList<>();
    }
    public String text;
    public List<String> images;
    public List<String> videos;

    public boolean isEmptyMedia(){
        return images.isEmpty()&videos.isEmpty();
    }
    @Override
    public String toString() {
        return "Content{" +
                "text='" + text + '\'' +
                ", images=" + images +
                ", videos=" + videos +
                '}';
    }

    public static String[] getImgTypes() {
        return imgTypes;
    }

    public static String[] getVideoTypes() {
        return videoTypes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}