/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpErrors;

import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Mac
 */
public abstract class HttpError extends Throwable {
    public abstract Status getStatus();
    public abstract String getMessage();
        
}
