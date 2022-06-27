package Model;

import dto.CaseTramsformer;
import soap.CaseSoap;
import soap.Ws_002fSoap_002fCase;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import static Controller.ShowCaseController.*;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        while (true) {
            Scanner in = new Scanner(System.in);
            int caseNumber;
            System.out.println("Чем бы хотели заняться\n(1.можно выйти,\n 2.добавить новое дело,\n" +
                    " 3.посмотреть описание задания по номеру\n 4.изменить данные дела,\n 5. удалить задание,\n" +
                    " 6. посмотреть информацию о всех делах,\n " +
                    "7.посмотреть описание задания по имени ?\n "+
                    "8.Creators\n " +
                    "9.Soup\n "+
                    "10.Rest)?\n ");
            if (in.hasNext("1")) {
                System.exit(0);
            } else if (in.hasNext("2")) {
                getInstance().CreateCase();
            } else if (in.hasNext("3")) {
                System.out.print("Введите номер дела: ");
                Scanner number = new Scanner(System.in);
                if (number.hasNextInt()) getInstance().ShowCase(number.nextInt());
            } else if (in.hasNext("4")) {
                System.out.print("Введите номер дела: ");
                Scanner number = new Scanner(System.in);
                if (number.hasNextInt()) getInstance().EditCase(number.nextInt());
            } else if (in.hasNext("5")) {
                System.out.print("Введите номер дела: ");
                Scanner number = new Scanner(System.in);
                if (number.hasNextInt()) getInstance().DeleteCase(number.nextInt());
            } else if (in.hasNext("6")) {
                getInstance().ShowAllCase();
            } else if (in.hasNext("7")) {
                System.out.print("Введите номер дела: ");
                Scanner number = new Scanner(System.in);
                if (number.hasNextLine()) getInstance().ShowCasebyTitle(number.nextLine());
            }  else if (in.hasNext("8")) {
                getInstance().printCreators();
            }  else if (in.hasNext("9")) {
                SoapTestClient();
            }  else if (in.hasNext("10")) {
                RestTestClient();
            } else System.out.println("Введены неверные данные, попробуйте еще");
        }

    }

    public static void SoapTestClient(){
        Ws_002fSoap_002fCase caseWsFactory = new Ws_002fSoap_002fCase();
        CaseSoap caseSoapWs = caseWsFactory.getCaseSoapPort();

        System.out.println((new CaseTramsformer().transform(caseSoapWs.getCaseById(1))).AboutCase());
    }

    public static void RestTestClient(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/ws/rest/cases");

        Response response = target.request().get();
        GenericType<List<RestCase>> type = new GenericType<List<RestCase>>(){};

        List<RestCase> thiscase = response.readEntity(type);
        for(int i =0; i< thiscase.size();i++)
        System.out.println(thiscase.get(i).AboutCase()+ "\n");
    }
}

