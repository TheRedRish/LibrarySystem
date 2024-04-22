public class Magazine extends Media {
    private int issueNumber;

    public Magazine(String title, int year, int copies, int issueNumber) {
        super(title, year, copies, MediaType.Magazine);
        this.issueNumber = issueNumber;
    }

    public Magazine(int id, String title, int year, int copies, int issueNumber) {
        super(id, title, year, copies, MediaType.Magazine);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public String getSubscriptionTerms() {
        return "One magazine every 14th day for 250 kr / month";
    }

    @Override
    public String getSaveString(String argSeparator) {
        return super.getSaveString(argSeparator) + argSeparator +
                issueNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("%-10s: %d%n", "Issue", issueNumber));
        return sb.toString();
    }
}
