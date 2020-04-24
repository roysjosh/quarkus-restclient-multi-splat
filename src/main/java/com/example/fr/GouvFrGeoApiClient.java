package com.example.fr;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface GouvFrGeoApiClient {

    @GET
    @Path("/communes")
    public Set<Commune> getCommunes(
        @QueryParam("codePostal") String postalCode
    );
}