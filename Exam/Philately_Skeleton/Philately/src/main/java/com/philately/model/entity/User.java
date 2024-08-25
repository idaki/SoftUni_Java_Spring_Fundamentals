package com.philately.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "owner_purchased_stamps",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id")
    )
    private List<Stamp> purchasedStamps = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_wished_stamps",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id")
    )
    private List<Stamp> wishedStamps = new ArrayList<>();

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Stamp> getPurchasedStamps() {
        return purchasedStamps;
    }

    public void setPurchasedStamps(List<Stamp> purchasedStamps) {
        this.purchasedStamps = purchasedStamps;
    }

    public List<Stamp> getWishedStamps() {
        return wishedStamps;
    }

    public void setWishedStamps(List<Stamp> wishedStamps) {
        this.wishedStamps = wishedStamps;
    }

    public void addWishedStamp(Stamp stamp) {
        wishedStamps.add(stamp);
    }
    public void removeWishedStamp(Stamp stamp) {
        wishedStamps.remove(stamp);
    }

    public void purchaseStamp(Stamp stamp) {
        purchasedStamps.add(stamp);
        wishedStamps.remove(stamp);
    }


}
