package com.example.liberty;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;



import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @Inject
    @ConfigProperty(name="conference") String conf;
    @GET
    public String sayHello() {
        return "Hello World @" + conf;
    }
}
