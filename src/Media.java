import java.util.Comparator;

public abstract class Media {
    private String title;
    private int year;
    private int copies;
    private Integer id;

    public Media(String title, int year, int copies) {
        this.id = null;
        this.title = title;
        this.year = year;
        this.copies = copies;
    }
    public Media(int id, String title, int year, int copies) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.copies = copies;
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

    public abstract String getSubscriptionTerms();

    public String getSaveString(String className, String argSeparator) {
        return className + argSeparator +
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
