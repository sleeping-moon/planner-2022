package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Case {

    private int caseNumber;
    private String caseTitle;
    private Date caseDate;
    private Date caseDueDate;
    private String caseDescription;
    private int userId;
    private Date preparationTime;
    private int caseActive;

    public Case() {
        this.caseNumber = 0;
        this.caseTitle = null;
        this.caseDate = null;
        this.caseDueDate = null;
        this.caseDescription = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getcaseNumber() {
        return caseNumber;
    }

    public void setcaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getcaseTitle() {
        return caseTitle;
    }

    public void setcaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public Date getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Date preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Date getcaseDate() {
        return this.caseDate;
    }

    public void setcaseDate(Date caseDate) {
        this.caseDate = caseDate;
    }

    public Date getcaseDueDate() {
        return this.caseDueDate;
    }

    public void setcaseDueDate(Date caseDueDate) {
        this.caseDueDate = caseDueDate;
    }

    public String getcaseDescription() {
        return caseDescription;
    }

    public void setcaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public int getCaseActive() {
        return caseActive;
    }

    public void setCaseActive(int caseActive) {
        this.caseActive = caseActive;
    }

    public String AboutCase() {
        return "Дело №" + this.caseNumber + " Название: " + this.caseTitle + " Дата: "
                + new SimpleDateFormat("dd.MM.yyy").format(this.caseDate) + " Срок выполнения:" + this.ConvertDateToWords() + " Описание:" + this.caseDescription;
    }

    public Date ConvertWordsToDate(String word_due_date) {
        String[] parts = word_due_date.split(" ");
        int quantity = Integer.parseInt(parts[0]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.caseDate);
        cal.add(Calendar.DATE, quantity);
        Date new_due_date = cal.getTime();
        return new_due_date;
    }

    public String ConvertDateToWords() {
        int diffInDays = (int) ((this.caseDueDate.getTime() - this.caseDate.getTime()) / (1000 * 60 * 60 * 24));
        String word_due_date;
        if (diffInDays % 10 == 1 && diffInDays != 11) {
            word_due_date = diffInDays + " день";
        } else if ((diffInDays % 10 == 2 || diffInDays % 10 == 3 || diffInDays % 10 == 3)
                || (diffInDays < 20 && diffInDays > 10)) {
            word_due_date = diffInDays + " дня";
        } else {
            word_due_date = diffInDays + " дней";
        }
        return word_due_date;
    }
}



