import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class FileMediaRepository implements IMediaRepository {
    private static final String FILE_PATH = "src/mediaList.txt";
    private static final String argSeparator = "//##//";

    @Override
    public ArrayList<Media> readMediaList() {
        ArrayList<Media> mediaList = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] parts = line.split(argSeparator);
                Media media = getMedia(parts);

                if (media != null) {
                    mediaList.add(media);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaList;
    }

    @Override
    public ArrayList<Media> searchMedia(String title, Integer year) {
        ArrayList<Media> mediaList = readMediaList();
        ArrayList<Media> exactSearchResults = MediaSearch.searchByExactYearAndTitle(mediaList, title, year);
        ArrayList<Media> looselySearchResults = MediaSearch.searchLooselyByTitle(mediaList, title);

        // Combine searches to eliminate duplicates and show most exact results in the top of the list.
        HashSet<Media> combinedSearchResults = new HashSet<>();
        combinedSearchResults.addAll(exactSearchResults);
        combinedSearchResults.addAll(looselySearchResults);

        return new ArrayList<>(combinedSearchResults);
    }

    @Override
    public ArrayList<Media> searchMedia(String title) {
        // TODO
        return null;
    }

    @Override
    public ArrayList<Media> searchMedia(int year) {
        // TODO
        return null;
    }

    private Media getMedia(String[] parts) {
        String mediaType = parts[0];
        Media media = null;

        // Look at format on each class of getSaveString() to know format of how it is saved.
        if (Book.class.getName().equals(mediaType)) {
            media = new Book(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[6]));
        } else if (Magazine.class.getName().equals(mediaType)) {
            media = new Magazine(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
        } else if (Newspaper.class.getName().equals(mediaType)) {
            media = new Newspaper(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]);
        } else if (Movie.class.getName().equals(mediaType)) {
            media = new Movie(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[6]));
        }
        return media;
    }

    @Override
    public void createMedia(Media mediaToSave) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            // Handles id here, since the text file cannot handle a unique id because it's dumb
            if (mediaToSave.getId() == null) {
                mediaToSave.setId(getHighestId() + 1);
            }
            writer.write(mediaToSave.getSaveString(mediaToSave.getClass().getName(), argSeparator) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMediaList(ArrayList<Media> mediaListToSave) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            int highestId = 0;
            for (Media mediaToSave : mediaListToSave) {
                // Handles id here, since the text file cannot set a unique id because it's dumb
                if (mediaToSave.getId() == null && highestId == 0) {
                    highestId = getHighestId();
                    mediaToSave.setId(highestId + 1);
                    highestId++;
                } else if (mediaToSave.getId() == null) {
                    mediaToSave.setId(highestId + 1);
                    highestId++;
                }
                writer.write(mediaToSave.getSaveString(mediaToSave.getClass().getName(), argSeparator) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedia(Media media) {
        // TODO
    }

    @Override
    public void deleteMedia(Media mediaToRemove) {
        ArrayList<Media> mediaList = readMediaList();
        for (Media media : mediaList) {
            if (Objects.equals(media.getId(), mediaToRemove.getId())) {
                mediaList.remove(media);
                break;
            }
        }
        updateMediaList(mediaList);
    }

    private int getHighestId() {
        int highestId = 0;
        ArrayList<Media> mediaList = readMediaList();
        for (Media media : mediaList) {
            if (media.getId() > highestId) {
                highestId = media.getId();
            }
        }
        return highestId;
    }
}
