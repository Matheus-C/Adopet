package filters;

import DAO.UsuarioDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import modelo.Usuario;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {

    private final SecretKey CHAVE
            = Keys.hmacShaKeyFor("7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));
    @Context private HttpServletRequest servletRequest;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext
                .getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            String token = authorizationHeader.substring("Bearer".length()).trim();

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token);

            String login = claims.getBody().getSubject();

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuario = usuarioDAO.findByLogin(login);

            if(usuario == null) throw new Exception("Token sem correspondencia válida");
            
            this.servletRequest.setAttribute("usuario", usuario);
            
        } catch (Exception e) {
            requestContext
                .abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .build());
        }
    }
}
