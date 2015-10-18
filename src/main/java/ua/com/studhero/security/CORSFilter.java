package ua.com.studhero.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Eugene on 07.10.2015.
 */

@Component
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.addHeader("Access-Control-Allow-Origin", "http://studhero.org");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.addHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
