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
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

@Path("/")
public class FieldEndPoint {
    public static final String PATH = "field/";

    @GET
    @Path("id/{id}")
    public Response getFieldById(@PathParam("id") String id) {
        return Response.status(201).entity(BauernTinderApp.getInjector().getInstance(FieldService.class).getFieldById(id).getJSON().toJSONString()).build();
    }

    @GET
    @Path("square/id/{id}")
    public Response getSquareById(@PathParam("id") String id) {
        return Response.status(201).entity(BauernTinderApp.getInjector().getInstance(FieldService.class).getSquareById(id).getJSON().toJSONString()).build();
    }

    @GET
    @Path("squares/{id}")
    public Response selectAllSquaresByFieldId(@PathParam("id") String id) {
        List<Square> allSquares = BauernTinderApp.getInjector().getInstance(FieldService.class).getAllSquaresByFieldId(id);
        JSONObject fields = new JSONObject();

        for (Square square: allSquares) {
            JSONObject x, y = new JSONObject();

            if(!fields.containsKey(square.getX())){
                x = new JSONObject();
                fields.put(square.getX(), x);
            } else {
                x = (JSONObject) fields.get(square.getX());
            }

            x.put(square.getY(), y);
            y.put("blocked", square.isBlocked());
            y.put("id", square.getId());
        }

        return Response.status(201).entity(fields.toJSONString()).build();
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

    @POST
    @Path("setSquares/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertField(@PathParam("id") String fieldId, JSONObject squares) {
        JSONObject allUpdateSquares = new JSONObject();

        for (int x = 0; x < squares.size(); x ++){
            LinkedHashMap row = (LinkedHashMap) squares.get(x + "");
            for (int y = 0; y < row.size(); y ++){
                LinkedHashMap square = (LinkedHashMap) row.get(y + "");
                Object id = square.get("id");
                String idString = "";

                if(id != null){
                    idString = id.toString();
                }

                System.out.println(idString + ", " +  x+ ", " + y + ", " + (boolean) square.get("blocked"));

                Square sq = new Square(idString, fieldId, x, y, (boolean) square.get("blocked"));

                BauernTinderApp.getInjector().getInstance(FieldService.class).setSquare(new Square(idString, fieldId, x, y, (boolean) square.get("blocked")));
            }
        }



        //System.out.println(squares.toJSONString());
        //JSONObject jsonObject = BauernTinderApp.getInjector().getInstance(FieldService.class).createField(field).getJSON();
        //return Response.status(201).entity(jsonObject.toJSONString()).build();
        return null;
    }
}
