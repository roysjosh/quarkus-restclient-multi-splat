package com.example.fr;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Multi;

@Path("/hello")
public class SplatResource {

    @Inject
    @RestClient
    GouvFrGeoApiClient api;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Object> hello() {
        return Multi.createFrom().emitter(emitter -> {
            try {
                Set<Commune> communes = api.getCommunes("75007");
                communes.forEach((commune) -> {
                    commune.getCodesPostaux().forEach((postalCode) -> {
                        int level = org.jboss.resteasy.core.ResteasyContext.getContextDataLevelCount();
                        System.out.println("Querying postal code " + postalCode + " with context level " + level);
                        emitter.emit(api.getCommunes(postalCode));
                    });
                });
            } catch (Exception e) {
                e.printStackTrace();
                emitter.fail(e);
            } finally {
                emitter.complete();
            }
        });
    }
}