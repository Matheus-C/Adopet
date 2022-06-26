/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpErrors;

import javax.ws.rs.core.Response;

/**
 *
 * @author Mac
 */
public class NotFound extends HttpError{

    private Response.Status status;
    private String message;
    
    public NotFound(String message) {
        this.message = message;
        this.status = Response.Status.NOT_FOUND;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Response.Status getStatus() {
        return this.status;
    }
}
