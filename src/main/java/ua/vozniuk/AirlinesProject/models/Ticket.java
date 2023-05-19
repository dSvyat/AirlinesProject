package ua.vozniuk.AirlinesProject.models;

import jakarta.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName ="id")
    private Person ticketOwner;

    @ManyToOne
    @JoinColumn(name = "plane_id", referencedColumnName = "id")
    private Plane planeOfTicket;

    @Column(name="departure")
    private String departure;

    @Column(name="arrival")
    private String arrival;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(Person ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    public Plane getPlaneOfTicket() {
        return planeOfTicket;
    }

    public void setPlaneOfTicket(Plane planeOfTicket) {
        this.planeOfTicket = planeOfTicket;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
