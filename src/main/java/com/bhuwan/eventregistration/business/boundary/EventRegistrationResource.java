/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileName, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhuwan.eventregistration.business.boundary;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
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
import org.apache.commons.io.FileUtils;

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

    @GET
    public Response getAllEventData() throws FileNotFoundException, IOException {
        String path = getClass().getClassLoader().getResource("/eventdata/").getPath();
        File eventDataDirectory = new File(path);
        String[] list = eventDataDirectory.list();
        JsonArray array = new JsonArray();
        for (String fileName : list) {
            array.add(new JsonParser().parse(FileUtils.readFileToString(Paths.get(path + fileName).toFile())));
        }
        return Response.ok(array.toString()).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("{id}")
    public void saveEventData(@PathParam("id") String id, JsonObject eventData) throws IOException, URISyntaxException {
        String path = getClass().getClassLoader().getResource("/eventdata/").getPath();
        System.out.println(" filepath: " + path + id + ".json");
        System.out.println("Data: " + eventData.toString());
        Files.write(Paths.get(path + id + ".json"), eventData.toString().getBytes(), StandardOpenOption.CREATE);
    }

}
