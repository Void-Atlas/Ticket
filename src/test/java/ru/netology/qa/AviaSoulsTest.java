package ru.netology.qa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void testCompareTo() {
        Ticket t1 = new Ticket("MOW", "SPB", 5000, 1000, 1130);
        Ticket t2 = new Ticket("MOW", "SPB", 3000, 1400, 1530);

        assertTrue(t2.compareTo(t1) < 0); // 3000 < 5000
        assertTrue(t1.compareTo(t2) > 0);
        assertEquals(0, t1.compareTo(new Ticket("A", "B", 5000, 0, 0)));
    }

    @Test
    public void testSearchSortByPrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("MOW", "SPB", 5000, 1000, 1130));
        manager.add(new Ticket("MOW", "SPB", 2000, 1200, 1330));
        manager.add(new Ticket("MOW", "SPB", 8000, 1500, 1630));
        manager.add(new Ticket("LED", "KZN", 4000, 900, 1200));

        Ticket[] found = manager.search("MOW", "SPB");

        assertEquals(3, found.length);
        assertEquals(2000, found[0].getPrice());
        assertEquals(5000, found[1].getPrice());
        assertEquals(8000, found[2].getPrice());
    }

    @Test
    public void testTicketTimeComparator() {
        Ticket t1 = new Ticket("A", "B", 10000, 1000, 1200); // 120 мин
        Ticket t2 = new Ticket("A", "B", 5000, 1000, 1400);  // 240 мин

        TicketTimeComparator comp = new TicketTimeComparator();
        assertTrue(comp.compare(t1, t2) < 0);
        assertTrue(comp.compare(t2, t1) > 0);
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "SPB", 7000, 1000, 1090)); // 90 минут
        manager.add(new Ticket("MOW", "SPB", 10000, 1200, 1440)); // 240 минут
        manager.add(new Ticket("MOW", "SPB", 5000, 1700, 1760)); // 60 минут

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] result = manager.searchAndSortBy("MOW", "SPB", comparator);

        assertEquals(60, result[0].getFlightTime());
        assertEquals(90, result[1].getFlightTime());
        assertEquals(240, result[2].getFlightTime());
    }
}