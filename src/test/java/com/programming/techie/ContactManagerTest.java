package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public void setupAll() {
        System.out.println("Should print before all tests");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Run before each test");
        contactManager = new ContactManager();
    }

    @Test
    @Disabled
    public void shouldCreateContact() {

        contactManager.addContact("Suyog", "Patil", "8329763258");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("Suyog") && contact.getLastName().equals("Patil") && contact.getPhoneNumber().equals("8329763258")));
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Patil", "1234567890");
        });
    }

    @Test
    @DisplayName("Should not create contact when last name is null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Suyog", null, "1234567890");
        });
    }

    @Test
    @DisplayName("Should not create contact when phone number is null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Suyog", "Patil", null);
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should execute after each test");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be executed at the end of the test");
    }

    @Test
    @DisplayName("Should not create contact on Windows OS")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
    public void shouldNotCreateContactOnlyOnWindows() {

        contactManager.addContact("Suyog", "Patil", "8329763258");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("Suyog") && contact.getLastName().equals("Patil") && contact.getPhoneNumber().equals("8329763258")));
    }

    @Test
    @DisplayName("Should create contact on MAC OS")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Disabled on MAC OS")
    public void shouldCreateContactOnlyOnMAC() {

        contactManager.addContact("Suyog", "Patil", "8329763258");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("Suyog") && contact.getLastName().equals("Patil") && contact.getPhoneNumber().equals("8329763258")));
    }


    @DisplayName("Repeat contact creation for 5 times using method source")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestContactCreationUsingMethodSource(String phoneNumber) {
        contactManager.addContact("Suyog", "Patil", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());

    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("1023456789", "8329763258", "8664651414");
    }

    @Nested
    class RepeatedNestedTest {

        @DisplayName("Repeat contact creation for 5 times")
        @RepeatedTest(value = 5, name = "Repeating contact creation test {currentRepetition} of {totalRepetitions}")
        public void shouldTestContactCreationRepeatedly() {
            contactManager.addContact("Suyog", "Patil", "8329763258");
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
            Assertions.assertTrue(contactManager.getAllContacts().stream().anyMatch(contact -> contact.getFirstName().equals("Suyog") && contact.getLastName().equals("Patil") && contact.getPhoneNumber().equals("8329763258")));
        }
    }

    @Nested
    class ParameterizedNestedTest {

        @DisplayName("Repeat contact creation for 5 times using parameterized test")
        @ParameterizedTest
        @ValueSource(strings = {"1023456789", "8329763258", "8664651414"})
        public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
            contactManager.addContact("Suyog", "Patil", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());

        }


        // CSV :- Comma Separated Values
        @DisplayName("CSV source case: Phone number should match the required format")
        @ParameterizedTest
        @CsvSource({"1023456789", "8329763258", "8664651414"})
        public void shouldTestContactCreationUsingCSVSource(String phoneNumber) {
            contactManager.addContact("Suyog", "Patil", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV source case: Phone number should match the required format")
        @ParameterizedTest
        @CsvFileSource(resources = "/contact.csv")
        public void shouldTestContactCreationUsingCSVFileSource(String phoneNumber) {
            contactManager.addContact("Suyog", "Patil", phoneNumber);
            Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
            Assertions.assertEquals(1, contactManager.getAllContacts().size());

        }
    }
}
