import java.util.List;
import java.util.Scanner;

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager("contacts.txt");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a contact");
            System.out.println("2. View all contacts");
            System.out.println("3. Search for a contact");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, email);
                    contactManager.addContact(newContact);
                    System.out.println("Contact added successfully.");
                    break;
                case 2:
                    List<Contact> allContacts = contactManager.getContacts();
                    for (Contact contact : allContacts) {
                        System.out.println(contact.getName() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail());
                    }
                    break;
                case 3:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    List<Contact> searchResults = contactManager.searchContacts(query);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching contacts found.");
                    } else {
                        for (Contact contact : searchResults) {
                            System.out.println(contact.getName() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting the Contact App. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
