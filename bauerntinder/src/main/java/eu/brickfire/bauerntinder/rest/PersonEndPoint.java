package eu.brickfire.bauerntinder.rest;

import eu.brickfire.bauerntinder.BauernTinderApp;
import eu.brickfire.bauerntinder.service.PersonService;
import eu.brickfire.bauerntinder.type.Person;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Path("/")
public class PersonEndPoint {

    public static final String PATH = "person/";

    @GET
    @Path("id/{id}")
    public Response getPersonById(@PathParam("id") String id) {
        return Response.status(201).entity(BauernTinderApp.getInjector().getInstance(PersonService.class).getPersonById(id).getJSON().toJSONString()).build();
    }

    @GET
    @Path("helper/id/{id}")
    public Response getHelperById(@PathParam("id") String id) {
        return Response.status(201).entity(BauernTinderApp.getInjector().getInstance(PersonService.class).getHelperById(id).getJSON().toJSONString()).build();
    }

    @GET
    @Path("farmer/id/{id}")
    public Response getFarmerById(@PathParam("id") String id) {
        return Response.status(201).entity(BauernTinderApp.getInjector().getInstance(PersonService.class).getFarmerById(id).getJSON().toJSONString()).build();
    }

    @GET
    @Path("email/{email}")
    public Response isNameFree(@PathParam("email") String email) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("notUsed", BauernTinderApp.getInjector().getInstance(PersonService.class).isEmailFree(email));
        return Response.status(201).entity(jsonObject.toJSONString()).build();
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePerson(Person person) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        person.setPassword(Base64.getEncoder().encodeToString(digest.digest(person.getPassword().getBytes(StandardCharsets.UTF_8))));
        JSONObject jsonObject = BauernTinderApp.getInjector().getInstance(PersonService.class).savePerson(person).getJSON();
        jsonObject.put("token", BauernTinderApp.getInjector().getInstance(PersonService.class).createToken(person));
        return Response.status(201).entity(
                jsonObject.toJSONString()
        ).build();
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginPerson(Person personA) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        personA.setPassword(Base64.getEncoder().encodeToString(digest.digest(personA.getPassword().getBytes(StandardCharsets.UTF_8))));
        Person personB = BauernTinderApp.getInjector().getInstance(PersonService.class).getPersonByEmail(personA.getEmail());
        if(MessageDigest.isEqual(personA.getPassword().getBytes(), personB.getPassword().getBytes())) {
            JSONObject jsonObject = personB.getJSON();
            if(personB.getToken() == null) {
                jsonObject.put("token", BauernTinderApp.getInjector().getInstance(PersonService.class).createToken(personB));
            }else{
                jsonObject.put("token", personB.getToken());
            }
            return Response.status(201).entity(jsonObject.toJSONString()).build();
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", false);
            return Response.status(201).entity(jsonObject.toJSONString()).build();
        }
    }

    @POST
    @Path("token-login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tokenLoginPerson(Person personA) {
        if(BauernTinderApp.getInjector().getInstance(PersonService.class).checkToken(personA.getEmail(), personA.getToken())) {
            JSONObject jsonObject = BauernTinderApp.getInjector().getInstance(PersonService.class).getPersonByEmail(personA.getEmail()).getJSON();
            return Response.status(201).entity(jsonObject.toJSONString()).build();
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login", false);
            return Response.status(201).entity(jsonObject.toJSONString()).build();
        }
    }


}
