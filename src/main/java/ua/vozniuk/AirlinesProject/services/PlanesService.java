package ua.vozniuk.AirlinesProject.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.vozniuk.AirlinesProject.models.Plane;
import ua.vozniuk.AirlinesProject.repositories.PlanesRepository;

import java.util.List;

@Service
public class PlanesService {
    private final PlanesRepository planesRepository;
    private final TicketsService ticketsService;

    public PlanesService(PlanesRepository planesRepository, TicketsService ticketsService) {
        this.planesRepository = planesRepository;
        this.ticketsService = ticketsService;
    }

    public Plane findById(int id){
        return planesRepository.findById(id).orElse(null);
    }
    public List<Plane> findAll(){
        return planesRepository.findAll();
    }

    @Transactional
    public void save(Plane plane){
        planesRepository.save(plane);
    }

    @Transactional
    public void edit(int id, Plane updatedPlane){
        updatedPlane.setId(id);
        planesRepository.save(updatedPlane);
    }

    @Transactional
    public void delete(int id){
        ticketsService.getTicketsByPlaneId(id).forEach(ticket ->
                ticket.setPlaneOfTicket(ticketsService.getRandomPlane()));
        planesRepository.deleteById(id);
    }
}
