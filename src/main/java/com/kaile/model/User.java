package com.kaile.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

@Entity
public class User {
    @Id
    String name;

    String email;
    boolean activated;
    List<String> followNames;

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public List<String> getFollowNames() {
        return followNames;
    }

    public void setFollowNames(List<String> followNames) {
        this.followNames = followNames;
    }

    public User(final String name, final String email, final boolean activated, final List<String> followNames) {
        this.name = name;
        this.email = email;
        this.activated = activated;
        this.followNames = followNames;
    }

    public User() {
    }
}
