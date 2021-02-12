package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.TicketManager;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);
    private Ticket first = new Ticket(1, 2000, "LED", "BOD", 120);
    private Ticket second = new Ticket(2, 2500, "LED", "UIP", 70);
    private Ticket third = new Ticket(3, 1000, "BOD", "LED", 100);
    private Ticket fourth = new Ticket(4, 1500, "LED", "BOD", 150);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    public void shouldFindById() {
        Ticket actual = repository.findById(2);
        assertEquals(second, actual);
    }

    @Test
    public void shouldRemoveIfExists() {
        repository.removeById(3);

        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{first, second, fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveIfNotExists() {
        assertThrows(NotFoundException.class, () -> repository.removeById(7));
    }


}