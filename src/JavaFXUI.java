import com.sun.javafx.scene.control.IntegerField;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JavaFXUI extends Application implements ILibrarySystemUI {
    private double sceneWidth;
    private double sceneHeight;
    private Stage primaryStage;
    private BorderPane mainPane;
    private LibrarySystem librarySystem;

    public JavaFXUI(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    // JavaFX requires a default constructor to run
    public JavaFXUI() {

    }

    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        sceneWidth = screenWidth * 0.8;
        sceneHeight = screenHeight * 0.8;
        mainPane = new BorderPane();
        displayMainMenu();
    }

    @Override
    public void addMedia() {
        // Create the main layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Create labels and text fields for input
        Label mediaTypeLabel = new Label("Media type:");
        ComboBox<MediaType> mediaTypeComboBox = new ComboBox<>();
        for (MediaType mediaType : MediaType.values()) {
            mediaTypeComboBox.getItems().add(mediaType);
        }

        Button addButton = new Button("Add Media");
        List<Node> nodes = new ArrayList<>();
        mediaTypeComboBox.setOnAction(event -> {
            // Clear existing fields
            grid.getChildren().removeAll(nodes);

            // Add fields based on selected media type
            MediaType selectedType = mediaTypeComboBox.getValue();
            switch (selectedType) {
                case Book:
                    addBookFields(nodes, grid, addButton);
                    break;
                case Newspaper:
                    addNewspaperFields(nodes, grid, addButton);
                    break;
                case Magazine:
                    addMagazineFields(nodes, grid, addButton);
                    break;
                case Movie:
                    addMovieFields(nodes, grid, addButton);
                    break;
                default:
                    break;
            }
        });
        grid.add(mediaTypeLabel, 0, 0);
        grid.add(mediaTypeComboBox, 1, 0);

        grid.add(addButton, 0, 1, 2, 1);
        addButton.setVisible(false);

        // Create the scene
        mainPane.setCenter(grid);

        // Set the stage properties
        primaryStage.setTitle("Add Media");
    }

    private void addBookFields(List<Node> nodes, GridPane grid, Button addButton) {
        nodes.clear();

        Label titleLabel = new Label("Title:");
        nodes.add(titleLabel);
        TextField titleField = new TextField();
        nodes.add(titleField);
        Label titleErrorLabel = new Label("This field is required");
        titleErrorLabel.setVisible(false);
        titleErrorLabel.setTextFill(Color.RED);
        nodes.add(titleErrorLabel);

        Label yearLabel = new Label("Year:");
        nodes.add(yearLabel);
        IntegerField yearField = new IntegerField();
        nodes.add(yearField);
        Label yearErrorLabel = new Label("This field is required");
        yearErrorLabel.setVisible(false);
        yearErrorLabel.setTextFill(Color.RED);
        nodes.add(yearErrorLabel);

        Label copiesLabel = new Label("Copies:");
        nodes.add(copiesLabel);
        IntegerField copiesField = new IntegerField();
        nodes.add(copiesField);
        Label copiesErrorLabel = new Label("This field is required");
        copiesErrorLabel.setVisible(false);
        copiesErrorLabel.setTextFill(Color.RED);
        nodes.add(copiesErrorLabel);

        Label authorLabel = new Label("Author:");
        nodes.add(authorLabel);
        TextField authorField = new TextField();
        nodes.add(authorField);
        Label authorErrorLabel = new Label("This field is required");
        authorErrorLabel.setVisible(false);
        authorErrorLabel.setTextFill(Color.RED);
        nodes.add(authorErrorLabel);

        Label ISBNLabel = new Label("ISBN:");
        nodes.add(ISBNLabel);
        IntegerField ISBNField = new IntegerField();
        nodes.add(ISBNField);
        Label ISBNErrorLabel = new Label("This field is required");
        ISBNErrorLabel.setVisible(false);
        ISBNErrorLabel.setTextFill(Color.RED);
        nodes.add(ISBNErrorLabel);

        grid.add(titleLabel, 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(titleErrorLabel, 2, 1);
        grid.add(yearLabel, 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(yearErrorLabel, 2, 2);
        grid.add(copiesLabel, 0, 3);
        grid.add(copiesField, 1, 3);
        grid.add(copiesErrorLabel, 2, 3);
        grid.add(authorLabel, 0, 4);
        grid.add(authorField, 1, 4);
        grid.add(authorErrorLabel, 2, 4);
        grid.add(ISBNLabel, 0, 5);
        grid.add(ISBNField, 1, 5);
        grid.add(ISBNErrorLabel, 2, 5);

        GridPane.setRowIndex(addButton, 6);
        GridPane.setHalignment(addButton, HPos.CENTER);
        addButton.setVisible(true);
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            int year = yearField.getValue();
            int copies = copiesField.getValue();
            String author = authorField.getText();
            int ISBN = ISBNField.getValue();

            // Display error messages
            titleErrorLabel.setVisible(Objects.equals(title, ""));
            yearErrorLabel.setVisible(year == 0);
            copiesErrorLabel.setVisible(copies == 0);
            authorErrorLabel.setVisible(Objects.equals(author, ""));
            ISBNErrorLabel.setVisible(ISBN == 0);

            if (!Objects.equals(title, "") && year != 0 && copies != 0 && !Objects.equals(author, "") && ISBN != 0) {
                Book book = new Book(title, year, copies, author, ISBN);
                System.out.println("Book added!");
                System.out.println(book);
                //librarySystem.addMedia(book);
            }
        });
    }

    private void addNewspaperFields(List<Node> nodes, GridPane grid, Button addButton) {
        nodes.clear();

        Label titleLabel = new Label("Title:");
        nodes.add(titleLabel);
        TextField titleField = new TextField();
        nodes.add(titleField);
        Label titleErrorLabel = new Label("This field is required");
        nodes.add(titleErrorLabel);

        Label yearLabel = new Label("Year:");
        nodes.add(yearLabel);
        IntegerField yearField = new IntegerField();
        nodes.add(yearField);
        Label yearErrorLabel = new Label("This field is required");
        nodes.add(yearErrorLabel);

        Label copiesLabel = new Label("Copies:");
        nodes.add(copiesLabel);
        IntegerField copiesField = new IntegerField();
        nodes.add(copiesField);
        Label copiesErrorLabel = new Label("This field is required");
        nodes.add(copiesErrorLabel);

        Label publisherLabel = new Label("Publisher:");
        nodes.add(publisherLabel);
        TextField publisherField = new TextField();
        nodes.add(publisherField);

        grid.add(titleLabel, 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(yearLabel, 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(copiesLabel, 0, 3);
        grid.add(copiesField, 1, 3);
        grid.add(publisherLabel, 0, 4);
        grid.add(publisherField, 1, 4);

        GridPane.setRowIndex(addButton, 5);
        GridPane.setHalignment(addButton, HPos.CENTER);
        addButton.setVisible(true);
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            int year = yearField.getValue();
            int copies = copiesField.getValue();
            String publisher = publisherField.getText();
            if (!Objects.equals(title, "") && year != 0 && copies != 0 && !Objects.equals(publisher, "")) {
                Newspaper newspaper = new Newspaper(title, year, copies, publisher);
                System.out.println("Newspaper added!");
                System.out.println(newspaper);
                //librarySystem.addMedia(book);
            }
        });
    }

    private void addMagazineFields(List<Node> nodes, GridPane grid, Button addButton) {
        nodes.clear();

        Label titleLabel = new Label("Title:");
        nodes.add(titleLabel);
        TextField titleField = new TextField();
        nodes.add(titleField);
        Label titleErrorLabel = new Label("This field is required");
        nodes.add(titleErrorLabel);

        Label yearLabel = new Label("Year:");
        nodes.add(yearLabel);
        IntegerField yearField = new IntegerField();
        nodes.add(yearField);
        Label yearErrorLabel = new Label("This field is required");
        nodes.add(yearErrorLabel);

        Label copiesLabel = new Label("Copies:");
        nodes.add(copiesLabel);
        IntegerField copiesField = new IntegerField();
        nodes.add(copiesField);
        Label copiesErrorLabel = new Label("This field is required");
        nodes.add(copiesErrorLabel);

        Label issueLabel = new Label("Issue:");
        nodes.add(issueLabel);
        IntegerField issueField = new IntegerField();
        nodes.add(issueField);

        grid.add(titleLabel, 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(yearLabel, 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(copiesLabel, 0, 3);
        grid.add(copiesField, 1, 3);
        grid.add(issueLabel, 0, 4);
        grid.add(issueField, 1, 4);

        GridPane.setRowIndex(addButton, 5);
        GridPane.setHalignment(addButton, HPos.CENTER);
        addButton.setVisible(true);
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            int year = yearField.getValue();
            int copies = copiesField.getValue();
            int issue = issueField.getValue();
            if (!Objects.equals(title, "") && year != 0 && copies != 0 && issue != 0) {
                Magazine magazine = new Magazine(title, year, copies, issue);
                System.out.println("Magazine added!");
                System.out.println(magazine);
                //librarySystem.addMedia(book);
            }
        });
    }

    private void addMovieFields(List<Node> nodes, GridPane grid, Button addButton) {
        nodes.clear();

        Label titleLabel = new Label("Title:");
        nodes.add(titleLabel);
        TextField titleField = new TextField();
        nodes.add(titleField);
        Label titleErrorLabel = new Label("This field is required");
        nodes.add(titleErrorLabel);

        Label yearLabel = new Label("Year:");
        nodes.add(yearLabel);
        IntegerField yearField = new IntegerField();
        nodes.add(yearField);
        Label yearErrorLabel = new Label("This field is required");
        nodes.add(yearErrorLabel);

        Label copiesLabel = new Label("Copies:");
        nodes.add(copiesLabel);
        IntegerField copiesField = new IntegerField();
        nodes.add(copiesField);
        Label copiesErrorLabel = new Label("This field is required");
        nodes.add(copiesErrorLabel);

        Label producerLabel = new Label("Producer:");
        nodes.add(producerLabel);
        TextField producerField = new TextField();
        nodes.add(producerField);

        Label lengthLabel = new Label("Length in min:");
        nodes.add(lengthLabel);
        IntegerField lengthField = new IntegerField();
        nodes.add(lengthField);

        grid.add(titleLabel, 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(yearLabel, 0, 2);
        grid.add(yearField, 1, 2);
        grid.add(copiesLabel, 0, 3);
        grid.add(copiesField, 1, 3);
        grid.add(producerLabel, 0, 4);
        grid.add(producerField, 1, 4);
        grid.add(lengthLabel, 0, 5);
        grid.add(lengthField, 1, 5);

        GridPane.setRowIndex(addButton, 6);
        GridPane.setHalignment(addButton, HPos.CENTER);
        addButton.setVisible(true);
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            int year = yearField.getValue();
            int copies = copiesField.getValue();
            String producer = producerField.getText();
            int length = lengthField.getValue();
            if (!Objects.equals(title, "") && year != 0 && copies != 0 && !Objects.equals(producer, "") && length != 0) {
                Movie movie = new Movie(title, year, copies, producer, length);
                System.out.println("Movie added!");
                System.out.println(movie);
                //librarySystem.addMedia(book);
            }
        });
    }

    @Override
    public void removeMedia() {
        // UI implementation for removing media
    }

    @Override
    public void displayMediaList() {
        // UI implementation for displaying media list
    }

    @Override
    public void searchMediaList() {
        // UI implementation for searching media list
    }

    @Override
    public void showAvailableSubscriptionTerms() {
        // UI implementation for showing subscription terms
    }

    public void displayMainMenu() {
        // Create the menu items
        MenuItem menuItem1 = new MenuItem("Add Media");
        menuItem1.setOnAction(e -> addMedia());
        MenuItem menuItem2 = new MenuItem("Remove Media");
        menuItem2.setOnAction(e -> removeMedia());
        MenuItem menuItem3 = new MenuItem("Show Media List");
        menuItem3.setOnAction(e -> displayMediaList());
        MenuItem menuItem4 = new MenuItem("Search Media List");
        menuItem4.setOnAction(e -> searchMediaList());
        MenuItem menuItem5 = new MenuItem("Show Subscription Terms");
        menuItem5.setOnAction(e -> showAvailableSubscriptionTerms());

        // Create the menu
        Menu menu = new Menu("Menu");
        menu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5);

        // Create the menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        // Create the main content area
        mainPane.setTop(menuBar);

        // Create the scene
        Scene scene = new Scene(mainPane, sceneWidth, sceneHeight);

        // Set the stage properties
        primaryStage.setTitle("Library System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
