public class Book extends Media {
    private String author;
    private int ISBN;

    public Book(String title, int year, int copies, String author, int ISBN) {
        super(title, year, copies, MediaType.Book);
        this.author = author;
        this.ISBN = ISBN;
    }

    public Book(int id, String title, int year, int copies, String author, int ISBN) {
        super(id, title, year, copies, MediaType.Book);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String getSubscriptionTerms() {
        return "One book a month for 200 kr / quarter";
    }

    @Override
    public String getSaveString(String argSeparator) {
        return super.getSaveString(argSeparator) + argSeparator +
                author + argSeparator
                + ISBN;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%-10s: %s%n", "Author", author));
        sb.append(String.format("%-10s: %d%n", "ISBN", ISBN));
        return sb.toString();
    }
}
