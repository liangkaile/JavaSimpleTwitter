package com.kaile.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;
import com.kaile.MongoDBHelper;
import com.kaile.model.User;
import com.kaile.rest.exception.AppException;
import com.kaile.rest.exception.AppError;
import com.kaile.model.Tweet;

@Path("feed")
public class Feed {
    private static Datastore ds = MongoDBHelper.getMongoDataStore();
    private static Morphia morphia = MongoDBHelper.getMorphia();
    private final static int LIMIT = 2;

    static {
        morphia.map(Tweet.class);
    }
    
    /**
     * 
     * get tweets which is created by user
     * @param name
     * @return
     * @throws AppException
     */
    @GET
    @Path("/mine")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tweet> getMyOwnTweets(@HeaderParam("user") String name) throws AppException {
        User user = Feed.ds.createQuery(User.class)
                .filter("name", name).get();

        if (user != null && user.getName() != null) {
            Query<Tweet> query = Feed.ds.createQuery(Tweet.class)
                    .field("user").equal(user.getName()).limit(Feed.LIMIT);

            return new ArrayList<>(query.asList());
        } else {	
        	throw new AppException(400, AppError.USER_NOT_FOUND);
        }
    }
    
    /**
     * get following feeds
     * 
     * @param name
     * @return
     * @throws AppException
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tweet> getFeeds(@HeaderParam("user") String name) throws AppException {
        User user = Feed.ds.createQuery(User.class)
                .filter("name", name).get();

        if (user == null) {
        	throw new AppException(400, AppError.USER_NOT_FOUND);
        }else if(user.getFollowNames() != null) {
            Query<Tweet> query = Feed.ds.createQuery(Tweet.class)
                    .field("user").in(user.getFollowNames()).limit(Feed.LIMIT);

            return new ArrayList<>(query.asList());
        }

        return new ArrayList<>();
    }
    
    
    
    /**
     * create feed
     * 
     * @param tweet
     * @return
     * @throws AppException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTweet(Tweet tweet) throws AppException {
        // verify user
        User user = Feed.ds.createQuery(User.class)
                .filter("name", tweet.getUser()).get();
        if (user == null) {
        	throw new AppException(400, AppError.USER_NOT_FOUND);
        } else {
        	 ds.save(tweet);
             return Response.status(201).entity(tweet).build();
        }
       
    }
}
