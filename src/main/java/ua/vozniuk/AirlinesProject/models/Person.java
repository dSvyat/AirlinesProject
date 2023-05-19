package ua.vozniuk.AirlinesProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Person")
public class Person {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    @Size(min=2, max = 40, message = "Username is too short or too long")
    private String username;

    @Column(name="year_of_birth")
    @Min(value = 1900, message = "Birth year is too low")
    @Max(value = 2023, message = "Birth year is too big")
    private int birthYear;

    @Column(name="password")
    @Size(min = 2, message = "Inappropriate password")
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="registration_date")
    private LocalDateTime registrationDate;

    @Column(name="name")
    @NotEmpty(message = "Username should not be empty")
    @Size(min=2, max = 40, message = "Username is too short or too long")
    private String name;

    @Column(name="email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Not a valid email")
    private String email;

    @OneToMany(mappedBy = "ticketOwner")
    private List<Ticket> ticketList;

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", yearOfBirth=" + birthYear +
                ", password='" + password + '\'' +
                '}';
    }
}
