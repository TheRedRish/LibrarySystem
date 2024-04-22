import java.util.Comparator;

public abstract class Media {
    private String title;
    private int year;
    private int copies;
    private Integer id;
    private MediaType mediaType;


    public Media(String title, int year, int copies, MediaType mediaType) {
        this.id = null;
        this.title = title;
        this.year = year;
        this.copies = copies;
        this.mediaType = mediaType;
    }

    public Media(int id, String title, int year, int copies, MediaType mediaType) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.copies = copies;
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public abstract String getSubscriptionTerms();

    public String getSaveString(String argSeparator) {
        return mediaType.toString() + argSeparator +
                id + argSeparator +
                title + argSeparator +
                year + argSeparator +
                copies;
    }

    @Override
    public String toString() {
        String sb = String.format("Title: %s%n", title) +
                String.format("%-10s: %d%n", "Year", year) +
                String.format("%-10s: %d%n", "Copies", copies);
        return sb;
    }

}
