package org.events;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Open Scanner
        Scanner scanner = new Scanner(System.in);

        String eventTitle;
        LocalDate eventDate;
        int eventTotalSeats;
        boolean exit = false;
        Event event = null;

        // Event creation
        while (event == null) {
            try {
                System.out.println("Welcome to the event planner!");
                System.out.print("Please enter the title of the event: ");
                eventTitle = scanner.nextLine();

                System.out.print("Please enter the date of the event (YYYY-MM-DD): ");
                eventDate = LocalDate.parse(scanner.nextLine());

                System.out.print("How many total seats are there in the venue? ");
                eventTotalSeats = Integer.parseInt(scanner.nextLine());

                event = new Event(eventTitle, eventDate, eventTotalSeats);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date: " + e.getMessage());
            }

        }

        // Booking management
        while (!exit) {
            String menuChoice;
            System.out.println("There are " + event.getBookedSeats() + " booked seats out of " + event.getTotalSeats() + ". What would you like to do? ");
            System.out.println("1-Make a booking 2-Cancel a booking 3-Exit");
            menuChoice = scanner.nextLine();

            try {
                switch (menuChoice) {
                    case "1":
                        System.out.println("How many seats would you like to book? ");
                        int seatsToBook = Integer.parseInt(scanner.nextLine());
                        event.bookSeats(seatsToBook);
                        break;

                    case "2":
                        System.out.println("How many bookings would you like to cancel? ");
                        int seatsToCancel = Integer.parseInt(scanner.nextLine());
                        event.cancelBooking(seatsToCancel);
                        break;

                    case "3":
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("There are now " + event.getAvailableSeats() + " available seats and " + event.getBookedSeats() + " booked seats.");

        // Close Scanner
        scanner.close();
    }
}
