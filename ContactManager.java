import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;
    private String databaseFile;

    public ContactManager(String databaseFile) {
        this.contacts = new ArrayList<>();
        this.databaseFile = databaseFile;
        loadContacts();
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(databaseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String phoneNumber = parts[1];
                    String email = parts[2];
                    contacts.add(new Contact(name, phoneNumber, email));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Contact> searchContacts(String query) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().contains(query) || contact.getPhoneNumber().contains(query) || contact.getEmail().contains(query)) {
                results.add(contact);
            }
        }
        return results;
    }

    private void saveContacts() {
        try (FileWriter writer = new FileWriter(databaseFile)) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
