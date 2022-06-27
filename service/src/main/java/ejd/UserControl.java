package ejd;


import Model.User;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;
import java.io.Serializable;


@Stateful
public class UserControl implements Serializable  {

    @Inject
    private HttpSession httpSession;

    public void CheckUser() {
        User thisuser = (User)httpSession.getAttribute("thisuser");
        System.out.println(thisuser.getuserName()+ " start session ");
    }


}
