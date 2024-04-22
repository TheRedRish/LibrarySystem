public class LibrarySystemApp {
    public static void main(String[] args) {
        // TODO Add logic to determine which repository to use and what UI to use.
        // MySQL workbench
        // Spring

        IMediaRepository mediaRepository = new FileMediaRepository(); // Instantiate the appropriate IMediaRepository implementation
        LibrarySystem librarySystem = new LibrarySystem(mediaRepository);
        // ILibrarySystemUI ui = new ConsoleUI(librarySystem); // Instantiate ConsoleUI with IMediaRepository dependency
        JavaFXUI ui = new JavaFXUI(librarySystem);

        ui.run();
    }
}
