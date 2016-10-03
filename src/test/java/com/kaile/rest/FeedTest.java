package com.kaile.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FeedTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Feed.class);
    }

    @Test
    public void test() {
        Response response = target("feed").request().get();
        assertEquals(response.getStatusInfo(), Response.Status.OK);
    }
}
