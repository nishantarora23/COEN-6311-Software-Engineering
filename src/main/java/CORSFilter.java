import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The CORSFilter class implements a servlet filter for handling Cross-Origin Resource Sharing (CORS) headers.
 * It allows specified origins to make requests to the servlet, helping to secure and control cross-origin requests.
 * This filter is configured to allow requests from "http://localhost:3000" and supports common HTTP methods.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class CORSFilter implements Filter {
	
	/**
     * Initializes the CORS filter. This method is called when the filter is created.
     *
     * @param filterConfig The filter configuration object.
     * @throws ServletException If an exception occurs during initialization.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }
    
    /**
     * Filters the request and response to handle CORS headers. This method is called for every request
     * that matches the filter mapping. It sets necessary CORS headers to allow specified origins.
     *
     * @param request  The servlet request.
     * @param response The servlet response.
     * @param chain    The filter chain to proceed with the request.
     * @throws IOException      If an I/O error occurs during the filter execution.
     * @throws ServletException If an exception occurs during the filter execution.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Set CORS headers
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Allow the request to proceed
        chain.doFilter(request, response);
    }
    
    /**
     * Destroys the CORS filter. This method is called when the filter is taken out of service.
     * It provides an opportunity to release resources or perform cleanup operations.
     */
    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}