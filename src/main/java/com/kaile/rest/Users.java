package com.kaile.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.Morphia;

import java.util.List;
import java.util.ArrayList;

import com.kaile.model.User;
import com.kaile.rest.exception.AppError;
import com.kaile.rest.exception.AppException;
import com.kaile.MongoDBHelper;

@Path("users")
public class Users {
    private static Datastore ds = MongoDBHelper.getMongoDataStore();
    private static Morphia morphia = MongoDBHelper.getMorphia();

    static {
        morphia.map(User.class);
    }

    /**
     * get all users
     * 
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsers() {
        Query<User> query = Users.ds.createQuery(User.class);

        return new ArrayList<>(query.asList());
    }

    /**
     * create user
     * 
     * @param user
     * @return
     * @throws AppException 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) throws AppException {
    	//validate uniqueness of name and email
    	User userFromDBByName = Users.ds.createQuery(User.class)
                .filter("name", user.getName()).get();
    	if (userFromDBByName != null) {
    		throw new AppException(400, AppError.USERNAME_ALREADY_EXIST);
    	}
    	
    	User userFromDBByEmail = Users.ds.createQuery(User.class)
                .filter("email", user.getEmail()).get();
    	if (userFromDBByEmail != null) {
    		throw new AppException(400, AppError.EMAIL_ALREADY_EXIST);
    	}
    	
    	//save user
        ds.save(user);

        return Response.status(201).entity(user).build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) throws AppException {
    	//username is the unique identifier, make sure user exist
    	User userFromDBByName = Users.ds.createQuery(User.class)
                .filter("name", user.getName()).get();
    	if (userFromDBByName == null) {
    		throw new AppException(400, AppError.USER_NOT_FOUND);
    	}
    	
    	//validate uniqueness of email
    	User userFromDBByEmail = Users.ds.createQuery(User.class)
                .filter("email", user.getEmail()).get();
    	if (userFromDBByEmail != null && !user.getName().equals(userFromDBByEmail.getName())) {
    		throw new AppException(400, AppError.EMAIL_ALREADY_EXIST);
    	}
    	
        ds.save(user);
        return Response.status(201).entity(user).build();
    }
    
}
