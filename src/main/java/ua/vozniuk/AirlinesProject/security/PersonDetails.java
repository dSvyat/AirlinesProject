package ua.vozniuk.AirlinesProject.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.vozniuk.AirlinesProject.models.Person;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    public void setName(String name){
        this.person.setName(name);
    }
    public void setUsername(String username){
        this.person.setUsername(username);
    }
    public void setBirthYear(int birthYear){
        this.person.setBirthYear(birthYear);
    }
    public void setEmail(String email){
        this.person.setEmail(email);
    }
    public void setPassword(String password){
        this.person.setPassword(password);
    }

    public int getId(){
        return this.person.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //need to get data of auth user
    public Person getPerson(){
        return this.person;
    }
}
