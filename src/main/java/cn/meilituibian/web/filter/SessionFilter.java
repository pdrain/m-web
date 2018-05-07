package cn.meilituibian.web.filter;

import cn.meilituibian.web.common.Constants;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String strUri = request.getRequestURI() ;

        if( strUri.indexOf("/admin/")!=-1 && request.getSession().getAttribute(Constants.USER)==null ){
          response.sendRedirect(request.getServletContext().getContextPath()) ;
          return;
        }
        filterChain.doFilter(request, response);
    }
}
