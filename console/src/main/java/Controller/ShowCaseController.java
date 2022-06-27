package Controller;

import Dao.DataStorage;
import Model.Case;
import ejd.CreatorsRemoteServiceInterface;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static Dao.DataStorage.ValidateDate;
import static Dao.DataStorage.ValidateDueDate;


public class ShowCaseController {

    private static volatile ShowCaseController instance;
    private CreatorsRemoteServiceInterface creatorsRemoteService;

    public ShowCaseController() {
    Properties p = new Properties();

        p.setProperty("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
        p.setProperty("java.naming.provider.url", "remote+http://localhost:8080");
        p.setProperty("java.naming.security.principal", "123456");
        p.setProperty("java.naming.security.credentials", "123456");
        try {
            InitialContext ctx = new InitialContext(p);
            System.out.println("checking on remote");
            this.creatorsRemoteService = (CreatorsRemoteServiceInterface)ctx.lookup("Web-1.0-SNAPSHOT/CreatorsRemoteService!ejd.CreatorsRemoteServiceInterface");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public static ShowCaseController getInstance() {
        if (instance == null) {
            synchronized (ShowCaseController.class) {
                if (instance == null) {
                    instance = new ShowCaseController();
                }
            }
        }
        return instance;
    }

    public  Case CreateCase() throws IOException, ParseException {
        Case new_task = new Case();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название:");
        new_task.setcaseTitle(in.nextLine());
        while (true) {
            System.out.print("Введите дату(формат дд.мм.гггг):");
            String date = in.nextLine();
            if (ValidateDate(date)) {
                new_task.setcaseDate(new SimpleDateFormat("dd.MM.yyy").parse(date));
                break;
            } else System.out.println("Вы ввели дату неправильно, попробуйте еще раз");
        }
        while (true) {
            System.out.print("Введите срок выполнения:");
            String due_date = in.nextLine();
            if (ValidateDueDate(due_date)) {
                new_task.setcaseDueDate(new_task.ConvertWordsToDate(due_date));
                break;
            } else System.out.println("Вы ввели неверный срок выполнения, попробуйте еще раз");
        }
        System.out.print("Введите описание:");
        new_task.setcaseDescription(in.nextLine());
        if (DataStorage.addCase(new_task) != null) {
            System.out.println("Дело №" + new_task.getcaseNumber() + " успешно добавлено");
        } else {
            System.out.println("Что-то пошло не так... не удалось создать задачу");
        }
        return new_task;
    }

    public  void DeleteCase(int number) throws IOException, ParseException {
        Case result = DataStorage.findeCase(number);
        if (result != null) {
            System.out.println(result.AboutCase());
            System.out.println("Введите да, если вы действительно хотите удалить дело");
            Scanner in = new Scanner(System.in);
            if (in.hasNext("Да") || in.hasNext("да")) {
                DataStorage.deleteCase(number);
                System.out.println("Данное дело удалено");
            }
        } else {
            System.out.println("Не удалось найти задание с таким номером");
        }
    }

    public  void EditCase(int number) throws IOException, ParseException {
        Case result = DataStorage.findeCase(number);
        if (result != null) {
            System.out.println("Укажите характеристику, которую необходимо изменить\n1. Название\n" +
                    "2. Дата\n3. Срок выполнения\n4. Описание");
            while (true) {
                Scanner in = new Scanner(System.in);
                if (in.hasNextInt()) {
                    if (in.hasNext("1")) {
                        System.out.println("Введите новое название:");
                        Scanner title = new Scanner(System.in);
                        result.setcaseTitle(title.nextLine());
                        DataStorage.updateCase(result);
                        System.out.println("Название дела успешно изменино");
                        break;
                    } else if (in.hasNext("2")) {
                        while (true) {
                            System.out.print("Введите новую дату(формат дд.мм.гггг):");
                            Scanner date = new Scanner(System.in);
                            if (ValidateDate(date.nextLine())) {
                                String newdate = date.nextLine();
                                result.setcaseDate(new SimpleDateFormat("dd.MM.yyy").parse(newdate));
                                break;
                            } else System.out.println("Вы ввели дату неправильно, попробуйте еще раз");
                        }
                        DataStorage.updateCase(result);
                        System.out.println("Дата успешно изменина");
                        break;
                    } else if (in.hasNext("3")) {
                        while (true) {
                            System.out.println("Введите новый срок выполнения:");
                            Scanner date = new Scanner(System.in);
                            String due_date = date.nextLine();
                            if (ValidateDueDate(due_date)) {
                                result.setcaseDueDate(result.ConvertWordsToDate(due_date));
                                break;
                            } else System.out.println("Вы ввели неверный срок выполнения, попробуйте еще раз");
                        }
                        DataStorage.updateCase(result);
                        System.out.println("Срок выполнения дела успешно изменен");
                        break;
                    } else if (in.hasNext("4")) {
                        System.out.println("Введите новое описание:");
                        Scanner description = new Scanner(System.in);
                        result.setcaseDescription(description.nextLine());
                        DataStorage.updateCase(result);
                        System.out.println("Описание с успешно изменен");
                        break;
                    } else System.out.println("У нас только 4 характеристики, попробуйте еще раз");
                }
            }
        } else System.out.println("Введены неверные данные, попробуйте еще");
    }

    public  void ShowCase(int number) throws IOException, ParseException {
        if (DataStorage.findeCase(number) != null) {
            System.out.println(DataStorage.findeCase(number).AboutCase());
        } else {
            System.out.println("Не удалось найти задание с таким номером");
        }
    }

    public void ShowCasebyTitle(String number) throws IOException, ParseException {
        if (DataStorage.findeCasebytitle(number) != null) {
            System.out.println(DataStorage.findeCasebytitle(number).AboutCase());
        } else {
            System.out.println("Не удалось найти задание с таким номером");
        }
    }

    public  void ShowAllCase() throws IOException, ParseException {
        List<Case> result = DataStorage.readTask();
        for (int i = 0; i < result.size(); i++) System.out.println(result.get(i).AboutCase());
    }

    public  void printCreators(){
        if(creatorsRemoteService != null){
        creatorsRemoteService.createdBy();
        System.out.println("someone tries to read creators of this application");}
    }
}
