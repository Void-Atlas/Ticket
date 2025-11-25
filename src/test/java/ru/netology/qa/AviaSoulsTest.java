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

        Ticket t1 = new Ticket("MOW", "SPB", 2000, 1000, 1130);
        Ticket t2 = new Ticket("MOW", "SPB", 5000, 1200, 1330);
        Ticket t3 = new Ticket("MOW", "SPB", 8000, 1400, 1530);

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        Ticket[] result = manager.search("MOW", "SPB");
        Ticket[] expected = {t1, t2, t3}; // 2000, 5000, 8000

        assertArrayEquals(expected, result);
    }

    @Test
    public void testTicketTimeComparator() {
        Ticket t1 = new Ticket("A", "B", 5000, 1000, 1200); // 120 мин
        Ticket t2 = new Ticket("A", "B", 10000, 1000, 1400);  // 240 мин

        TicketTimeComparator comp = new TicketTimeComparator();
        assertTrue(comp.compare(t1, t2) < 0);
        assertTrue(comp.compare(t2, t1) > 0);
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();

        Ticket t1 = new Ticket("MOW", "SPB", 5000, 1000, 1060); // 60 минут
        Ticket t2 = new Ticket("MOW", "SPB", 7000, 1200, 1290); // 90 минут
        Ticket t3 = new Ticket("MOW", "SPB", 10000, 1400, 1640); // 240 минут

        manager.add(t1);
        manager.add(t2);
        manager.add(t3);

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] result = manager.searchAndSortBy("MOW", "SPB", comparator);

        Ticket[] expected = {t1, t2, t3};

        assertArrayEquals(expected, result);
    }
}