import java.util.Comparator;

public class MediaTitelAndYearComparator implements Comparator<Media> {
    @Override
    public int compare(Media media1, Media media2) {
        int yearCompare = Integer.compare(media1.getYear(), media2.getYear());

        if (yearCompare == 0){
            return media1.getTitle().compareTo(media2.getTitle());
        }

        return yearCompare;
    }
}
