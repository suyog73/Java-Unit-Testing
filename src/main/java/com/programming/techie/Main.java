package com.programming.techie;

public class Main {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();

        contactManager.addContact("Suyog", "Patil", "8329763258");
        contactManager.addContact("A", "Patil", "9465464946");
        contactManager.addContact("B", "Patil", "7654837197");
        contactManager.addContact("C", "Patil", "8979763254");
        contactManager.addContact("D", "Patil", "7632654456");

        contactManager.printAllContacts();

    }
}
