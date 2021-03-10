package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class FilterAutorizacije implements Filter {

    public FilterAutorizacije() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("/index.xhtml") >= 0 || reqURI.indexOf("/javno/") >= 0 || reqURI.indexOf("javax.faces.resource") >= 0) {
                chain.doFilter(request, response);
            } else if ((ses != null && ses.getAttribute("recenzent") != null && (reqURI.indexOf("/korisnik") > 0 || reqURI.indexOf("/uploads/") > 0))
                    || (ses != null && ses.getAttribute("admin") != null && (reqURI.indexOf("/admin") > 0|| reqURI.indexOf("/uploads/") > 0))
                    ) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
