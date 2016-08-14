/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhuwan.eventregistration.business.boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    
    @POST
    @Path("{id}")
    public void saveEventData(@PathParam("id") String id, JsonObject eventData) throws IOException, URISyntaxException {
        System.out.println("Writing to file..................");
        String path = getClass().getClassLoader().getResource("/eventdata/").getPath();
        System.out.println(" filepath: "+ path+id+ ".json");
        System.out.println("Data: "+eventData.toString());
        Files.write(Paths.get(path + id + ".json"), eventData.toString().getBytes(), StandardOpenOption.CREATE);
    }
    
}
