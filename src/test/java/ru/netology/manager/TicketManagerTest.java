package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketComparator;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);
    private TicketComparator comparator = new TicketComparator();

    private Ticket first = new Ticket(1, 2000, "LED", "BOD", 120);
    private Ticket second = new Ticket(2, 2500, "LED", "UIP", 70);
    private Ticket third = new Ticket(3, 1000, "BOD", "LED", 100);
    private Ticket fourth = new Ticket(4, 1500, "LED", "BOD", 150);
    private Ticket fifth = new Ticket(5, 3000, "LED", "MAD", 200);
    private Ticket sixth = new Ticket(6, 5500, "UIP", "BOD", 95);

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    public void shouldFindByFrom() {

        Ticket[] actual = manager.searchBy("LED", "", comparator);
        Ticket[] expected = new Ticket[]{second, first, fourth, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByTo() {

        Ticket[] actual = manager.searchBy("", "BOD", comparator);
        Ticket[] expected = new Ticket[]{sixth, first, fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByFromIfOneAirport() {

        Ticket[] actual = manager.searchBy("UIP", "", comparator);
        Ticket[] expected = new Ticket[]{sixth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByToIfOneAirport() {

        Ticket[] actual = manager.searchBy("", "MAD", comparator);
        Ticket[] expected = new Ticket[]{fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByNotAirport() {

        Ticket[] actual = manager.searchBy("", "", comparator);
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
    }
}
