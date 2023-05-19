package ua.vozniuk.AirlinesProject.services;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.vozniuk.AirlinesProject.models.Plane;
import ua.vozniuk.AirlinesProject.models.Ticket;
import ua.vozniuk.AirlinesProject.repositories.PlanesRepository;
import ua.vozniuk.AirlinesProject.repositories.TicketRepository;

import java.util.List;

@Service
public class TicketsService {
    private final TicketRepository ticketRepository;
    private final PlanesRepository planesRepository;
    private final PersonDetailService personDetailService;

    public TicketsService(TicketRepository ticketRepository, PlanesRepository planesRepository, PersonDetailService personDetailService) {
        this.ticketRepository = ticketRepository;
        this.planesRepository = planesRepository;
        this.personDetailService = personDetailService;
    }

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByPersonId(int personId){
        return personDetailService.findById(personId).getTicketList();
    }

    public List<Ticket> getTicketsByPlaneId(int planeId){
        return findAll().stream().filter(ticket ->
                ticket.getPlaneOfTicket().getId() == planeId).toList();
    }


    @Transactional
    public void saveTicket(Ticket ticket, int personId){
        ticket.setTicketOwner(personDetailService.findById(personId));
        ticket.setPlaneOfTicket(getRandomPlane());
        ticketRepository.save(ticket);
    }

    public Plane getRandomPlane(){
        List<Plane> planes = planesRepository.findAll();
        int randomIndex = (int) (Math.random() * planes.size());
        return planes.get(randomIndex);
    }
}
