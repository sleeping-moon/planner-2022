package Servlets;

import Dao.UserDataStorage;
import Model.User;
import ejd.UserControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFilter.class);
    private static String checkingValidLoginAndPassword = null;
    private static int checkingUserIsBlocked = 0;
    @Inject
    private UserControl userControl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession(true);
        String url = httpServletRequest.getRequestURI();
        String username = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        servletRequest.setAttribute("message", null);
        User thisuser = UserDataStorage.findeUserByLoginAndPassword(username, password);
        if (httpSession.getAttribute("thisuser") != null) {
            if (((User) httpSession.getAttribute("thisuser")).getUserActive() == 1) {
                filterChain.doFilter(servletRequest, servletResponse);
                LOGGER.info(thisuser.getuserLogin()+" logged into the account");
            } else {
                checkingUserIsBlocked = 1;
                servletRequest.setAttribute("message", checkingUserIsBlocked);
                checkingUserIsBlocked = 0;
                httpSession.removeAttribute("thisuser");
                httpServletRequest.getRequestDispatcher("/authorization.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else if (username != null && password != null && thisuser != null) {
            httpSession.setAttribute("thisuser", thisuser);
            httpServletResponse.sendRedirect("/mainframe");
            userControl.CheckUser();
        } else {
            if (username != null && password != null) {
                checkingValidLoginAndPassword = "Login or password entered incorrectly";
                httpServletRequest.getRequestDispatcher("/authorization.jsp").forward(httpServletRequest, httpServletResponse);
            }
            if ("/authorization".equals(url) || "/registration".equals(url)) {
                servletRequest.setAttribute("message", checkingValidLoginAndPassword);
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (url.startsWith("/ws")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletRequest.getRequestDispatcher("/main.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
