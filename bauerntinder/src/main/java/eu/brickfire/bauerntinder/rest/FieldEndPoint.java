package eu.brickfire.bauerntinder.rest;

import eu.brickfire.bauerntinder.BauernTinderApp;
import eu.brickfire.bauerntinder.service.FieldService;
import eu.brickfire.bauerntinder.type.Square;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class FieldEndPoint {
    public static final String PATH = "field/";

    @GET
    @Path("id/{id}")
    public Response getFieldById(@PathParam("id") String id) {
        return Response.status(201).entity(BauernTinderApp.getInjector().getInstance(FieldService.class).getFieldById(id).getJSON().toJSONString()).build();
    }

    @GET
    @Path("squares/{id}")
    public Response selectAllSquaresByFieldId(@PathParam("id") String id) {
        List<Square> allSquares = BauernTinderApp.getInjector().getInstance(FieldService.class).getAllSquaresByFieldId(id);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonSquares = new JSONObject();

        for (int i = 0; i < allSquares.size(); i++) {
            Square square = allSquares.get(i);
            jsonSquares.put(i + 1, square.getJSON());
        }

        jsonObject.put("squareList", jsonSquares);

        return Response.status(201).entity(jsonObject.toJSONString()).build();
    }
}
