package com.kaile.rest.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Tweet {
    @Id
    private ObjectId id;

    private String body;

    private String user;
    
    private Date creationDate;

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
        this.creationDate = new Date();
    }
    
    public Tweet(final ObjectId id, final String body, final String user, final Date date) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.creationDate = date;
        
    }

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
