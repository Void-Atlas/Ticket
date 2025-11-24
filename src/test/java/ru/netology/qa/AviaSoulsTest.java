package ru.netology.qa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();

        manager.add(new Ticket("MOW", "SPB", 7000, 1000, 1090)); //90
        manager.add(new Ticket("MOW", "SPB", 10000, 1200, 1440)); //240
        manager.add(new Ticket("MOW", "SPB", 5000, 1700, 1760)); //60

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] result = manager.searchAndSortBy("MOW", "SPB", comparator);

        assertEquals(60, result[0].getFlightTime());
        assertEquals(90, result[1].getFlightTime());
        assertEquals(240, result[2].getFlightTime());
    }
}