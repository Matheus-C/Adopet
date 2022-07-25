package filters;

import DAO.UsuarioDAO;
import HttpErrors.Unauthorized;
import Utils.JSONBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
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
    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        JSONBuilder responseJson = new JSONBuilder();

        String authorizationHeader = requestContext
                .getHeaderString(HttpHeaders.AUTHORIZATION);
        try {
            
            if(authorizationHeader == null) throw new HttpErrors.Unauthorized("Token de autenticação ausente");
            
            String token = authorizationHeader.substring("Bearer".length()).trim();

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token);

            String login = claims.getBody().getSubject();

            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuario = usuarioDAO.findByLogin(login);

            if (usuario == null) {
                throw new HttpErrors.Unauthorized("Token sem correspondencia válida");
            }

            this.servletRequest.setAttribute("usuario", usuario);

        } catch (ExpiredJwtException | MalformedJwtException ex) {

            HttpErrors.HttpError httpError = new HttpErrors.Unauthorized("Token inválido");

            requestContext
                    .abortWith(Response.status(httpError.getStatus())
                            .entity(
                                    responseJson
                                            .add("message", httpError.getMessage())
                                            .build()
                            ).build());
        } catch (HttpErrors.HttpError httpError) {
            requestContext
                    .abortWith(Response.status(httpError.getStatus())
                            .entity(
                                    responseJson
                                            .add("message", httpError.getMessage())
                                            .build()
                            ).build());
        } catch (Exception ex) {
            requestContext
                    .abortWith(Response.status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    ).entity(responseJson.add("message", "Erro interno").build())
                            .build());
            throw ex;
        }
    }
}
