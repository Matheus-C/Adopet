package filters;

import DAO.UsuarioDAO;
import Utils.JSONBuilder;
import Utils.ValidationWrapper;
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
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import modelo.Usuario;

@Provider
@UserParam
@Priority(Priorities.USER)
public class UserParamFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        JSONBuilder responseJson = new JSONBuilder();

        try {
            
            String userIdStr = requestContext
                    .getUriInfo().getPathParameters().getFirst("idUsuario");
            
            int userId = ValidationWrapper.parseInt("idUsuario", userIdStr);
            
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuario = usuarioDAO.getById(Long.valueOf(userId));

            if (usuario == null) {
                throw new HttpErrors.NotFound("Usuário não encontrado");
            }

            this.servletRequest.setAttribute("paramUsuario", usuario);

        } catch (HttpErrors.HttpError httpError) {
            requestContext
                    .abortWith(Response.status(httpError.getStatus())
                            .entity(
                                    responseJson
                                            .add("mensagem", httpError.getMessage())
                                            .build()
                            ).build());
        } catch (Exception ex) {

            requestContext
                    .abortWith(Response.status(
                            Response.Status.INTERNAL_SERVER_ERROR
                    ).entity(responseJson.add("mensagem", "Erro interno").build())
                            .build());
            
            throw ex;
        }
    }
}
