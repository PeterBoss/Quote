/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boss.restful1;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Peter
 */
@Path("quote")
public class MyResource {

    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyResource
     */
    public MyResource() {
    }

    /**
     *
     * @param id
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String get(@PathParam("id") int id) {

        JsonObject quote = new JsonObject();
        quote.addProperty("quote", quotes.get(id));
        return new Gson().toJson(quote);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("random")
    public String getRandom() {
        JsonObject quote = new JsonObject();
        quote.addProperty("quote", quotes.get(1 + new Random().nextInt(quotes.size())));
        return new Gson().toJson(quote);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void postQuote(String content) {
        JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
        String quote = jo.get("quote").getAsString();
        quotes.put(quotes.size() + 1, quote);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void putQuote(@PathParam("id") int id, String content) {
        JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
        String quote = jo.get("quote").getAsString();
        quotes.replace(id, quote);
    }

    @DELETE
    @Path("{id}")
    public void deleteQuote(@PathParam("id") int id) {
        quotes.remove(id);
    }
}
