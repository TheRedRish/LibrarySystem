import java.util.ArrayList;

public interface IMediaRepository {
    void deleteMedia(Media mediaToRemove);
    void updateMediaList(ArrayList<Media> mediaListToSave);
    void updateMedia(Media media);
    void createMedia(Media mediaToSave);
    ArrayList<Media> readMediaList();
    ArrayList<Media> searchMedia(String title, Integer year);
    ArrayList<Media> searchMedia(String title);
    ArrayList<Media> searchMedia(int year);
}
