import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    private List<Patron> patrons = new ArrayList<>();

    // Add patrons from file
    public void addPatronsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("-");
                if (data.length == 4) {
                    String id = data[0];
                    String name = data[1];
                    String address = data[2];
                    double fine = Double.parseDouble(data[3]);
                    if (fine >= 0 && fine <= 250) {
                        patrons.add(new Patron(id, name, address, fine));
                    } else {
                        System.out.println("Invalid fine for patron: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Add patron manually
    public void addPatronManually(String id, String name, String address, double fine) {
        if (fine >= 0 && fine <= 250) {
            patrons.add(new Patron(id, name, address, fine));
        } else {
            System.out.println("Invalid fine amount. Must be between $0 and $250.");
        }
    }

    // Remove patron by ID
    public void removePatron(String id) {
        patrons.removeIf(patron -> patron.getId().equals(id));
    }

    // Display all patrons
    public void displayPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("No patrons found.");
        } else {
            patrons.forEach(System.out::println);
        }
    }

    // Menu
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add patrons from file");
            System.out.println("2. Add a patron manually");
            System.out.println("3. Remove a patron by ID");
            System.out.println("4. Display all patrons");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file path: ");
                    String filePath = scanner.nextLine();
                    addPatronsFromFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter fine: ");
                    double fine = scanner.nextDouble();
                    addPatronManually(id, name, address, fine);
                    break;
                case 3:
                    System.out.print("Enter ID to remove: ");
                    String removeId = scanner.nextLine();
                    removePatron(removeId);
                    break;
                case 4:
                    displayPatrons();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        lms.menu();
    }
}
