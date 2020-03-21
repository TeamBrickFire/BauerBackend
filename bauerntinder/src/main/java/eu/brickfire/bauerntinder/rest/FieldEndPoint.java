package eu.brickfire.bauerntinder.rest;

import eu.brickfire.bauerntinder.BauernTinderApp;
import eu.brickfire.bauerntinder.service.FieldService;
import eu.brickfire.bauerntinder.type.Field;
import eu.brickfire.bauerntinder.type.Helper;
import eu.brickfire.bauerntinder.type.Square;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Path("helpers/{id}")
    public Response getAllHelperByFieldId(@PathParam("id") String id) {
        List<Helper> allHelpers = BauernTinderApp.getInjector().getInstance(FieldService.class).getAllHelperByFieldId(id);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonSquares = new JSONObject();

        for (int i = 0; i < allHelpers.size(); i++) {
            Helper helper = allHelpers.get(i);
            jsonSquares.put(i + 1, helper.getJSON());
        }

        jsonObject.put("helperList", jsonSquares);

        return Response.status(201).entity(jsonObject.toJSONString()).build();
    }

    @GET
    @Path("helperCount/{id}")
    public Response getHelperCountByFieldId(@PathParam("id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("field", BauernTinderApp.getInjector().getInstance(FieldService.class).getFieldById(id).getJSON());
        jsonObject.put("helperCount", BauernTinderApp.getInjector().getInstance(FieldService.class).getHelperCountByFieldId(id));
        return Response.status(201).entity(jsonObject.toJSONString()).build();
    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertField(Field field) {
        JSONObject jsonObject = BauernTinderApp.getInjector().getInstance(FieldService.class).createField(field).getJSON();
        return Response.status(201).entity(jsonObject.toJSONString()).build();
    }
}
