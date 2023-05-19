package ua.vozniuk.AirlinesProject.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.vozniuk.AirlinesProject.models.Person;
import ua.vozniuk.AirlinesProject.services.PersonDetailService;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailService personDetailService;

    @Autowired
    public PersonValidator(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;
        try {
            personDetailService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username", "", "Username already taken");
    }
}
