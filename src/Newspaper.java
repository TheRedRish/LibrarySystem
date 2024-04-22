public class Newspaper extends Media {
    private String publisher;

    public Newspaper(String title, int year, int copies, String publisher) {
        super(title, year, copies);
        this.publisher = publisher;
    }

    public Newspaper(int id, String title, int year, int copies, String publisher) {
        super(id, title, year, copies);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getSaveString(String className, String argSeparator) {
        return super.getSaveString(className, argSeparator) + argSeparator +
                publisher;
    }

    @Override
    public String getSubscriptionTerms() {
        return "One newspaper every day for 145 kr / month";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%-10s: %s%n", "Publisher", publisher));
        return sb.toString();
    }
}
