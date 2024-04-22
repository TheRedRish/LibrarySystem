import java.util.ArrayList;

public class MediaSearch {

    public static ArrayList<Media> searchByExactYearAndTitle(ArrayList<Media> mediaList, String title, Integer year) {

        ArrayList<Media> sortedMediaList = new ArrayList<>(mediaList);
        sortedMediaList.sort(new MediaTitelAndYearComparator());
        ArrayList<Media> results = new ArrayList<>();

        if (year == null && title == null){
            return results;
        }

        int index = binarySearchByYearAndTitle(sortedMediaList, year, title);

        if (index < 0) {
            // No exact match found, but index of insertion point is returned
            index = -(index + 1);
        }

        // Search backward to find all matching elements with the same title and year
        for (int i = index - 1; i >= 0; i--) {
            Media media = sortedMediaList.get(i);
            if ((year != null && media.getYear() != year) || (title != null &&  !media.getTitle().equalsIgnoreCase(title))) {
                break;
            }
            results.add(media);
        }

        // Search forward to find all matching elements with the same title and year
        for (int i = index; i < sortedMediaList.size(); i++) {
            Media media = sortedMediaList.get(i);
            if ((year != null && media.getYear() != year) || (title != null && !media.getTitle().equalsIgnoreCase(title))) {
                break;
            }
            results.add(media);
        }

        return results;
    }

    public static ArrayList<Media> searchLooselyByTitle(ArrayList<Media> mediaList, String title){
        ArrayList<Media> results = new ArrayList<>();
        if (title == null){
            return results;
        }
        for (Media media : mediaList){
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())){
                results.add(media);
            }
        }
        return results;
    }

    private static int binarySearchByYearAndTitle(ArrayList<Media> mediaList, Integer year, String title) {
        int low = 0;
        int high = mediaList.size() - 1;

        while (low <= high) {
            int mid = (high - low) / 2 + low;
            Media media = mediaList.get(mid);
            int compare = compareByYearAndTitle(media, year, title);

            if (compare == 0) {
                // Found the media
                return mid;
            } else if (compare < 0) {
                // Media is before the current position
                high = mid - 1;
            } else {
                // Media is after the current position
                low = mid + 1;
            }
        }

        // Media not found, return index of insertion point
        return -(low + 1);
    }

    private static int compareByYearAndTitle(Media media, Integer year, String title) {
        if (year != null && media.getYear() != year) {
            return year - media.getYear();
        } else if (title != null){
            return media.getTitle().compareTo(title);
        } else {
            return 0;
        }
    }


}
