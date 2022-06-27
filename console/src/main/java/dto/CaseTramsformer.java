package dto;

import Model.Case;

import java.util.Date;

public class CaseTramsformer {

      public Case transform(soap.Case thisCase){
          Case newcase = new Case();
          newcase.setcaseNumber(thisCase.getCaseNumber());
          newcase.setcaseTitle(thisCase.getCaseTitle());
          Date date = new Date(thisCase.getCaseDate().getMillisecond());
          newcase.setcaseDate(date);
          Date dueDate = new Date(thisCase.getCaseDueDate().getMillisecond());
          newcase.setcaseDueDate(dueDate);
          newcase.setcaseDescription(thisCase.getCaseDescription());
          return  newcase;
      }
}
