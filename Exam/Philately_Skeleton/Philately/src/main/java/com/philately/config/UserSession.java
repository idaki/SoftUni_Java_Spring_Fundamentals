package com.philately.config;


import com.philately.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    private long id;
    private String username;

    public void login(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
    }
    public boolean isUserLoggedIn() {
        return this.id != 0;
    }

    public void logout() {
        id = 0;
        username = "";
    }
    public String username(){
        return username;
    }


    public long getId() {
        return id;
    }

    public UserSession setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserSession setUsername(String username) {
        this.username = username;
        return this;
    }
}
