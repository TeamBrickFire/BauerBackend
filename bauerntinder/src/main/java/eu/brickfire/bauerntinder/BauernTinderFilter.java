package eu.brickfire.bauerntinder;

import eu.brickfire.bauerntinder.rest.PersonEndPoint;
import eu.brickfire.bauerntinder.service.PersonService;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BauernTinderFilter implements Filter {

    PersonService personService;

    public void init(FilterConfig fConfig) {
        personService = BauernTinderApp.getInjector().getInstance(PersonService.class);
    }

    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        String id = request.getParameter("loginId");
        String token = request.getParameter("loginToken");
        if ((id != null) && (token != null) && !id.equals("") && !token.equals("") && personService.checkToken(id, token)) {
            chain.doFilter(request, response);
        } else if(((HttpServletRequest)request).getRequestURI().equals("/rest/json/" + PersonEndPoint.PATH + "login") || ((HttpServletRequest)request).getRequestURI().equals("/rest/json/" + PersonEndPoint.PATH + "register")){
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Key not valid");
        }
    }

    @Override
    public void destroy() {

    }
}
