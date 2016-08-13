/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhuwan.eventregistration.business.boundary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bhuwan
 */
@Path("event")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class EventRegistrationResource {

    @GET
    @Path("{id}")
    public Response getEventData(@PathParam("id") String id) throws FileNotFoundException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/eventdata/" + id + ".json");
        JsonReader jsonReader = Json.createReader(inputStream);
        return Response.ok(jsonReader.readObject()).header("Access-Control-Allow-Origin", "*").build();
    }
    
}
