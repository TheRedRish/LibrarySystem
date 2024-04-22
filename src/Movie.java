public class Movie extends Media {
    private String producer;
    private int lengthInMin;

    public Movie(String title, int year, int copies, String producer, int lengthInMin) {
        super(title, year, copies, MediaType.Movie);
        this.lengthInMin = lengthInMin;
        this.producer = producer;
    }

    public Movie(int id, String title, int year, int copies, String producer, int lengthInMin) {
        super(id, title, year, copies, MediaType.Movie);
        this.lengthInMin = lengthInMin;
        this.producer = producer;
    }

    @Override
    public String getSubscriptionTerms() {
        return "One movie every day for 200 kr / month";
    }

    public int getLengthInMin() {
        return lengthInMin;
    }

    public void setLengthInMin(int lengthInMin) {
        this.lengthInMin = lengthInMin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String getSaveString(String argSeparator) {
        return super.getSaveString(argSeparator) + argSeparator +
                producer + argSeparator
                + lengthInMin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%-10s: %s%n", "Producer", producer));
        int hours = lengthInMin/60;
        int min = (int)((double) lengthInMin % 60);
        sb.append(String.format("%-10s: %s%n", "Movie length", hours + " H " + min + " Min"));
        return sb.toString();
    }
}
