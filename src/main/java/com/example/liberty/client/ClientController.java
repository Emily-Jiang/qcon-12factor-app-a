package com.example.liberty.client;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/client")
@ApplicationScoped
public class ClientController {

    @Inject
    @RestClient
    private Service service;

    @GET
    @Path("/test/{parameter}")
    @Retry
    @Timeout(100)
    @Asynchronous
    @Fallback(fallbackMethod="fallback")
    public CompletionStage<String> onClientSide(@PathParam("parameter") String parameter) {
        return CompletableFuture.completedFuture(service.doSomething(parameter));
    }

    private CompletionStage<String> fallback(@PathParam("parameter") String parameter) {
        return CompletableFuture.completedFuture("This is fallback!");
    }

}
