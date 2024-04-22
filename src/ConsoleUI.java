import console.UserInputUtil;

import java.util.*;

public class ConsoleUI implements ILibrarySystemUI {
    private LibrarySystem librarySystem;

    public ConsoleUI(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void run() {
        displayMainMenu();
    }

    @Override
    public void displayMainMenu() {
        int selection;
        do {
            System.out.println("1. Add media");
            System.out.println("2. Remove media");
            System.out.println("3. Show media list");
            System.out.println("4. Search media list");
            System.out.println("5. Show subscription terms");

            selection = UserInputUtil.getIntInput("Selection (0 to exit): ", "Please select one of the above options", new int[]{0, 1, 2, 3, 4, 5});

            switch (selection) {
                case 0:
                    break;
                case 1:
                    addMedia();
                    break;
                case 2:
                    removeMedia();
                    break;
                case 3:
                    displayMediaList();
                    break;
                case 4:
                    searchMediaList();
                    break;
                case 5:
                    showAvailableSubscriptionTerms();
                    break;
                default:
                    System.out.println("Selection not available");
                    break;
            }
        } while (selection != 0);
    }

    @Override
    public void addMedia() {
        System.out.println("1. Add book");
        System.out.println("2. Add Magazine");
        System.out.println("3. Add Newspaper");
        System.out.println("4. Add Movie");
        int selection = UserInputUtil.getIntInput("Select media to add (0 to exit): ", "Please select one of the above options", new int[]{0, 1, 2, 3, 4});
        Media media = null;
        switch (selection) {
            case 0:
                break;
            case 1:
                media = addBook();
                break;
            case 2:
                media = addMagazine();
                break;
            case 3:
                media = addNewspaper();
                break;
            case 4:
                media = addMovie();
                break;
            default:
                System.out.println("Selection not available");
                break;
        }

        if (media != null) {
            librarySystem.addMedia(media);
            System.out.println("Your " + media.getClass().getName().toLowerCase() + " has been added to the library.");
        }
    }

    @Override
    public void removeMedia() {
        System.out.println("Search for media you want to remove:");
        ArrayList<Media> result = getMediaListFromSearch();


        int[] selectionOptions = new int[result.size() + 1];
        selectionOptions[0] = 0;

        for (int i = 0; i < result.size(); i++) {
            selectionOptions[i] = i + 1;
            System.out.println((i + 1) + ". " + result.get(i).toString());
        }

        int selection = UserInputUtil.getIntInput("Selection (0 to exit): ", "Please select one of the above options", selectionOptions);

        if (selection != 0) {
            Media mediaToRemove = result.get(selection - 1);
            librarySystem.removeMedia(mediaToRemove);
            System.out.println("Media has been removed");
        }
    }

    @Override
    public void displayMediaList() {
        ArrayList<Media> mediaList = librarySystem.getMediaList();
        if (mediaList.isEmpty()) {
            System.out.println("No media has been added to the list yet.");
            return;
        }
        System.out.println("1. Show sorted list");
        System.out.println("2. Show unsorted list");
        int selection = UserInputUtil.getIntInput("Selection (0 to exit): ", "Please select one of the above options", new int[]{1, 2});

        switch (selection) {
            case 0:
                break;
            case 1:
                ArrayList<Media> sortedMediaList = new ArrayList<>(mediaList);
                sortedMediaList.sort(new MediaTitelAndYearComparator());
                for (Media media : sortedMediaList) {
                    System.out.println(media);
                }
                break;
            case 2:
                for (Media media : mediaList) {
                    System.out.println(media);
                }
                break;
            default:
                System.out.println("Selection not available");
                break;
        }
    }

    @Override
    public void searchMediaList() {
        System.out.println("Search for a media:");
        ArrayList<Media> result = getMediaListFromSearch();

        for (Media media : result) {
            System.out.println(media);
        }
    }

    @Override
    public void showAvailableSubscriptionTerms() {
        ArrayList<Media> mediaList = librarySystem.getMediaList();
        if (mediaList.isEmpty()) {
            System.out.println("No media has been added to the list yet and we can therefore not offer any subscriptions");
            return;
        }

        LinkedHashMap<Class<?>, Integer> classesCount = new LinkedHashMap<>();
        List<String> subscriptionTerms = new ArrayList<>();
        for (Media media : mediaList) {
            classesCount.merge(media.getClass(), 1, Integer::sum);
            if (!subscriptionTerms.contains(media.getSubscriptionTerms())) {
                subscriptionTerms.add(media.getSubscriptionTerms());
            }
        }

        System.out.println("We have these subscriptions available:");

        int counter = 0;
        for (Map.Entry<Class<?>, Integer> entry : classesCount.entrySet()) {
            System.out.println("This library has " + entry.getValue() + " " + entry.getKey().getName().toLowerCase() + (entry.getValue() > 1 ? "s" : ""));
            System.out.println(" -" + subscriptionTerms.get(counter));
            counter++;
        }
    }

    private ArrayList<Media> getMediaListFromSearch() {
        String title = UserInputUtil.getStringInput("Title of media ('null' if no title): ");
        int yearInput = UserInputUtil.getIntInput("Year of media (0 if no year): ");
        Integer year = yearInput == 0 ? null : yearInput;
        title = title.equalsIgnoreCase("null") ? null : title;

        return librarySystem.searchMedia(title, year);
    }

    private static Book addBook() {
        String title = UserInputUtil.getStringInput("Title of the book: ");
        int year = UserInputUtil.getIntInput("Year the book was published: ");
        int copies = UserInputUtil.getIntInput("Amount of copies: ");
        String author = UserInputUtil.getStringInput("Author of the book: ");
        int ISBN = UserInputUtil.getIntInput("ISBN of the book: ");

        return new Book(title, year, copies, author, ISBN);
    }

    private static Magazine addMagazine() {
        String title = UserInputUtil.getStringInput("Title of the magazine: ");
        int year = UserInputUtil.getIntInput("Year the magazine was published: ");
        int copies = UserInputUtil.getIntInput("Amount of copies: ");
        int issueNumber = UserInputUtil.getIntInput("Issue number of the magazine: ");

        return new Magazine(title, year, copies, issueNumber);
    }

    private static Newspaper addNewspaper() {
        String title = UserInputUtil.getStringInput("Title of the newspaper: ");
        int year = UserInputUtil.getIntInput("Year the newspaper was published: ");
        int copies = UserInputUtil.getIntInput("Amount of copies: ");
        String publisher = UserInputUtil.getStringInput("Publisher of the newspaper: ");

        return new Newspaper(title, year, copies, publisher);
    }

    private static Movie addMovie(){
        String title = UserInputUtil.getStringInput("Title of the movie: ");
        int year = UserInputUtil.getIntInput("Year the movie was published: ");
        int copies = UserInputUtil.getIntInput("Amount of copies: ");
        String producer = UserInputUtil.getStringInput("Producer of the movie: ");
        int lengthInMin = UserInputUtil.getIntInput("Length of the movie in minutes: ");

        return new Movie(title, year, copies, producer, lengthInMin);
    }
}
