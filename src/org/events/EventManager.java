package org.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {

    // ATTRIBUTES
    String title;
    List<Event> events;

    // CONSTRUCTORS
    public EventManager(String title) {
        this.title = title;
        this.events = new ArrayList<>();
    }

    // METHODS
    // Add event to list
    public void addEvent(Event event) {
        this.events.add(event);
    }

    // Return all events for a certain date
    public List<Event> getEventsByDate(LocalDate date) {
        return this.events.stream()
                .filter(event -> event.getDate().equals(date))
                .collect(Collectors.toList());
    }

    // Get list size
    public int getNumberOfEvents() {
        return this.events.size();
    }

    // Empty event list
    public void clearEvents() {
        this.events.clear();
    }

    // Return events sorted by date
    public String printEvents() {
        StringBuilder sb = new StringBuilder(this.title + "\n");
        this.events.stream()
                .sorted(Comparator.comparing(Event::getDate))
                .forEach(event -> sb.append(event).append("\n"));
        if (!this.events.isEmpty()){
            return sb.toString();
        }
        return "No events";
    }

    // Test Main
    public static void main(String[] args) {
        EventManager manager = new EventManager("My event list");

        // Create sample events
        Event event1 = new Event("Concert", LocalDate.of(2025, 6, 15), 300);
        Event event2 = new Event("LARP", LocalDate.of(2025, 7, 20), 500);
        Event event3 = new Event("Comic Con", LocalDate.of(2025, 6, 15), 3000);

        // Add events to list
        manager.addEvent(event1);
        manager.addEvent(event2);
        manager.addEvent(event3);

        // Print event list
        System.out.println(manager.printEvents());

        // Get events for specific date
        System.out.println("Events on 2025-06-15:");
        manager.getEventsByDate(LocalDate.of(2025, 6, 15)).forEach(System.out::println);

        // Get number of events
        System.out.println("Total number of events: " + manager.getNumberOfEvents());

        // Clear all events
        manager.clearEvents();
        System.out.println("Events cleared.");

        // Display all events after clearing
        System.out.println(manager.printEvents());
    }
}
