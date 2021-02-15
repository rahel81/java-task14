package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket item) {
        repository.save(item);
    }

    public Ticket[] searchBy(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0];
        for (Ticket item : repository.findAll()) {
            if (item.getDepartureAirport().equals(from) || item.getArrivalAirport().equals(to)) {

                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }

    public Ticket[] getAll() {
        return repository.findAll();
    }

    public Ticket findById(int id) {
        return repository.findById(id);
    }
}



