import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

class Contact implements Comparable<Contact> {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() { return name; }
    public String getNumber() { return number; }
    public void setName(String name) { this.name = name; }
    public void setNumber(String number) { this.number = number; }

    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name + ": " + number;
    }
}

class ContactManager {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String number) {
        contacts.add(new Contact(name, number));
    }

    public ArrayList<Contact> searchContacts(String query) {
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getNumber().contains(query)) {
                results.add(contact);
            }
        }
        return results;
    }

    public void deleteContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
        }
    }

    public void updateContact(int index, String name, String number) {
        if (index >= 0 && index < contacts.size()) {
            Contact contact = contacts.get(index);
            contact.setName(name);
            contact.setNumber(number);
        }
    }

    public void sortContacts() {
        Collections.sort(contacts);
    }

    public ArrayList<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}

public class PhonebookApp extends JFrame {
    private ContactManager contactManager;
    private JTextField searchField, nameField, numberField;
    private JList<Contact> contactList;
    private DefaultListModel<Contact> listModel;

    public PhonebookApp() {
        contactManager = new ContactManager();
        setTitle("Phonebook Application");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Contacts"));

        // Add Contact Panel
        JPanel addPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        nameField = new JTextField(15);
        numberField = new JTextField(15);
        JButton addButton = new JButton("Add Contact");

        gbc.gridx = 0; gbc.gridy = 0;
        addPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        addPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        addPanel.add(new JLabel("Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        addPanel.add(numberField, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addPanel.add(addButton, gbc);

        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Contact"));

        // Contact List
        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Contact List"));

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");
        JButton sortButton = new JButton("Sort");
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(sortButton);

        // Arrange panels
        JPanel westPanel = new JPanel(new BorderLayout(10, 10));
        westPanel.add(addPanel, BorderLayout.NORTH);
        westPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Event Listeners
        searchButton.addActionListener(e -> searchContacts());
        addButton.addActionListener(e -> addContact());
        deleteButton.addActionListener(e -> deleteContact());
        updateButton.addActionListener(e -> updateContact());
        sortButton.addActionListener(e -> sortContacts());

        refreshContactList();
    }

    private void searchContacts() {
        String query = searchField.getText();
        listModel.clear();
        for (Contact contact : contactManager.searchContacts(query)) {
            listModel.addElement(contact);
        }
    }

    private void addContact() {
        String name = nameField.getText();
        String number = numberField.getText();
        if (!name.isEmpty() && !number.isEmpty()) {
            contactManager.addContact(name, number);
            nameField.setText("");
            numberField.setText("");
            refreshContactList();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both name and number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contact?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                contactManager.deleteContact(selectedIndex);
                refreshContactList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            Contact selectedContact = listModel.getElementAt(selectedIndex);
            JTextField nameField = new JTextField(selectedContact.getName());
            JTextField numberField = new JTextField(selectedContact.getNumber());

            JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Number:"));
            panel.add(numberField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Update Contact", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String number = numberField.getText();
                if (!name.isEmpty() && !number.isEmpty()) {
                    contactManager.updateContact(selectedIndex, name, number);
                    refreshContactList();
                } else {
                    JOptionPane.showMessageDialog(this, "Name and number cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortContacts() {
        contactManager.sortContacts();
        refreshContactList();
    }

    private void refreshContactList() {
        listModel.clear();
        for (Contact contact : contactManager.getAllContacts()) {
            listModel.addElement(contact);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new PhonebookApp().setVisible(true);
        });
    }
}