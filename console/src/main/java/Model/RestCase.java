package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RestCase {

    private int caseNumber;
    private String caseTitle;
    private String caseDate;
    private String caseDueDate;
    private String caseDescription;
    private int userId;
    private String preparationTime;

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getCaseDueDate() {
        return caseDueDate;
    }

    public void setCaseDueDate(String caseDueDate) {
        this.caseDueDate = caseDueDate;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String AboutCase() {
        return "Дело №" + this.caseNumber + " Название: " + this.caseTitle + " Дата: "
                + this.caseDate + " Срок выполнения:" + this.caseDueDate + " Описание:" + this.caseDescription;
    }
}
