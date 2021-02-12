package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);
    private Ticket first = new Ticket(1, 2000, "LED", "BOD", 120);
    private Ticket second = new Ticket(2, 2500, "LED", "UIP", 70);
    private Ticket third = new Ticket(3, 1000, "BOD", "LED", 100);
    private Ticket fourth = new Ticket(4, 1500, "LED", "BOD", 150);


    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
    }

    @Test
    public void shouldFindByFrom() {

        Ticket[] actual = manager.searchBy("LED", "");
        Ticket[] expected = new Ticket[]{fourth, first,second};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByTo() {

        Ticket[] actual = manager.searchBy("", "BOD");
        Ticket[] expected = new Ticket[]{fourth, first};
        assertArrayEquals(expected, actual);
    }

}
