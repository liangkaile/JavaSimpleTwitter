package com.kaile.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Tweet {
    @Id
    ObjectId id;

    String body;

    String user;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Tweet() {
    }

    public Tweet(final ObjectId id, final String body, final String user) {
        this.id = id;
        this.body = body;
        this.user = user;
    }

}
