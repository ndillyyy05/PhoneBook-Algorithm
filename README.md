Problem Description:

This project is aimed to implement as well as design an efficient phonebook for Namibian Telecommunications Company with the use of simple linear data structures. The phonebook is to support the operstions that follow:

Insert- To add anew contact( name and phone number to the phonebook)
Search- To retrieve a contact's phone number by searching their name.
Delete- To remove a contact from the phonebook by name.
Update- To modify an existing contact's phone number.

Data structures and Algorithms explored:

Arrays- Simple to implement and efficient for small datasets 
- Supports insert, search, delete, and update operations
Linked lists- Dynamic memory allocation, which may be useful when the size of the phonebook is not known beforehand.

Explanation of the code:
1. Contact Class
This class represents a contact with a name and phone number. It implements the Comparable interface, which allows contacts to be sorted by their names.

Attributes:

name: The name of the contact.
number: The contact's phone number.
Constructor:

Initializes name and number.

Methods:

Getter and setter methods for name and number.
compareTo(Contact other): Compares this contact with another based on the name (ignoring case).
toString(): Returns a string representation of the contact (format: "name: number").

2. ContactManager Class
This class manages a list of contacts and provides functionality to add, search, delete, update, and sort contacts.

Attributes:

contacts: An ArrayList to store Contact objects.
Methods:

addContact(String name, String number): Adds a new contact to the list.
searchContacts(String query): Searches for contacts by name or number, returning matches.
deleteContact(int index): Deletes a contact at a specified index.
updateContact(int index, String name, String number): Updates the contact's information at the specified index.
sortContacts(): Sorts the contacts alphabetically by name.
getAllContacts(): Returns all contacts as an ArrayList.
3. PhonebookApp Class
This class extends JFrame and serves as the main GUI for the application.

Attributes:

contactManager: An instance of ContactManager.
Various GUI components: text fields for input, a JList to display contacts, and buttons for actions.
Constructor:

Sets up the GUI components, layout, and event listeners for buttons.

Methods:

searchContacts(): Searches for contacts and updates the list.
addContact(): Adds a new contact from user input.
deleteContact(): Deletes a selected contact after confirmation.
updateContact(): Updates a selected contact's details after user confirmation.
sortContacts(): Sorts contacts in alphabetical order and refreshes the display.
refreshContactList(): Clears and repopulates the contact list displayed in the GUI.

4. Main Method
The main method runs the application in the Event Dispatch Thread (EDT) for thread safety with Swing components. It sets the look and feel of the application and creates an instance of PhonebookApp.

Key Features of the Program:
User Interface: The GUI is structured into panels for searching, adding, and displaying contacts.
Event Handling: Each button is associated with an action listener that defines what happens when the button is clicked.
Contact Management: Users can add, search, delete, update, and sort their contacts.
Example Usage:
Add a Contact: Fill in the name and number, then click "Add Contact."
Search for Contacts: Type in the search field and press "Search."
Update a Contact: Select a contact, click "Update," edit the details in the dialog, and confirm.
Delete a Contact: Select a contact and click "Delete" to remove it after confirming the action.
Sort Contacts: Click "Sort" to reorder the contacts alphabetically.
Visual Structure:
The GUI will have sections for:

Searching Contacts: Input field and button for searching.
Adding Contacts: Input fields and a button for adding new contacts.
Displaying Contacts: A scrollable list of contacts.
Action Buttons: For deleting, updating, and sorting contacts.

Future enhancements:

Explore other data structures- If the current implementation's performance is insufficient, explore more advanced data structures such as; balanced trees, skip lists, tries and hash tables.
Improne search efficiency- For huge datasets, search operations may become bottlenecks. Optimizations like binary Search( for sorted arrays)or making use of tries for prefix matching can be explored.
Add persistence- Extend the phonebook to allow to save contacts to disk for persistent storage.

Conclusion:

This phonebook algorithm provides a simple yet efficient solution to basic operations such as insert, search, delete and update while it allows further exploration for more advanced structures when necessary.

Group Members:

Selma NT Nghinamanu  224067818

Jo-Lynn F Naftalie   224066064

Juliette Kalabo      224100742

Zenne-Lee Louw       224034987

Barth Kitatshi       223133469

Section A: PhonebookApp, Pseudocode and Flowchart
Section B: Program
