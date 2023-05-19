package ua.vozniuk.AirlinesProject.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vozniuk.AirlinesProject.models.Person;

@Service
public class RegistrationService {
    private final PersonDetailService personDetailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonDetailService personDetailService, PasswordEncoder passwordEncoder) {
        this.personDetailService = personDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personDetailService.save(person);
    }
}
