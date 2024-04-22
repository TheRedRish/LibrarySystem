import java.util.ArrayList;

public class LibrarySystem {
    private IMediaRepository mediaRepository;

    public LibrarySystem(IMediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void addMedia(Media mediaToAdd){
        mediaRepository.createMedia(mediaToAdd);
    }
    public void removeMedia(Media mediaToRemove){
        mediaRepository.deleteMedia(mediaToRemove);
    }

    public ArrayList<Media> searchMedia(String title, Integer year) {
        return mediaRepository.searchMedia(title, year);
    }
    public ArrayList<Media> searchMedia(String title) {
        return mediaRepository.searchMedia(title);
    }
    public ArrayList<Media> searchMedia(int year) {
        return mediaRepository.searchMedia(year);
    }

    public ArrayList<Media> getMediaList() {
        return mediaRepository.readMediaList();
    }
}
