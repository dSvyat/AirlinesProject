package ua.vozniuk.AirlinesProject.services;

import
.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.vozniuk.AirlinesProject.models.Person;
import ua.vozniuk.AirlinesProject.repositories.PeopleRepository;
import ua.vozniuk.AirlinesProject.security.PersonDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);
        if(person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(person.get());
    }


    public Person findById(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    public List<Person> getUsers(){
        return peopleRepository.findAll().stream().filter(person ->
                !person.getRole().equals("ROLE_ADMIN")).toList();
    }

    public List<Person> getAdmins(){
        return peopleRepository.findAll().stream().filter(person ->
                person.getRole().equals("ROLE_ADMIN")).toList();
    }

    @Transactional
    public void save(Person person){
        enrichPerson(person);
        peopleRepository.save(person);
    }

    @Transactional
    public void edit(int id, Person updatedPerson){
        findById(id).setName(updatedPerson.getName());
        findById(id).setUsername(updatedPerson.getUsername());
        findById(id).setPassword(updatedPerson.getPassword());
        findById(id).setEmail(updatedPerson.getEmail());
        findById(id).setBirthYear(updatedPerson.getBirthYear());
    }

    public List<Person> getAllPeople(){
        return peopleRepository.findAll();
    }

    private void enrichPerson(Person person){
        person.setRegistrationDate(LocalDateTime.now());
    }
}
