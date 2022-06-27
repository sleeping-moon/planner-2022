package webservice.soap;

import Dao.DataStorage;
import Model.Case;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;

@WebService(name="CaseSoap", serviceName="ws/soap/case")
public class CaseSoap {

    @WebMethod(operationName ="getAllCase")
    public List<Case> getAllCase() throws ParseException {
        return  DataStorage.readTask();
    }

    @WebMethod(operationName ="getCaseById")
    public Case getCaseById(@WebParam(name = "id")int id)  {
        return  DataStorage.findeCase(id);
    }

    @WebMethod(operationName ="updateCase")
    public void updateCase(@WebParam(name = "mycase") Case mycase)  {
          DataStorage.updateCase(mycase);
    }
}
