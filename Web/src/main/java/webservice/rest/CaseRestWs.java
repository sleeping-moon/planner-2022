package webservice.rest;


import Dao.DataStorage;
import Model.Case;


import javax.ws.rs.*;
import java.text.ParseException;
import java.util.List;

@Path("cases")
@Consumes({"application/json"})
@Produces({"application/json"})
public class CaseRestWs {

    @GET
    public List<Case> getAllCase() throws ParseException {
        return  DataStorage.readTask();
    }

    @GET
    @Path("/{id}")
    public Case getCaseById(@PathParam("id") int id)  {
        return  DataStorage.findeCase(id);
    }

    @POST
    public void updateCase( Case mycase)  {
        DataStorage.updateCase(mycase);
    }

}
