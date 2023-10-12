package br.com.marllonmendez.todolist.filter;


import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marllonmendez.todolist.repository.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        if (servletPath.equals("/tasks/")) {

            // Autenticação pegando usuário e senha descriptografada
            var auth = request.getHeader("Authorization");
            var authEncoded = auth.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String user = credentials[0];
            String password = credentials[1];

            var userValidate = userRepository.findByUsername(user);
            if (userValidate == null) {
                response.sendError(401);
            } else {
                var passwordValidate = BCrypt.verifyer().verify(password.toCharArray(), userValidate.getPassword());
                if (passwordValidate.verified) {
                    request.setAttribute("idUser", userValidate.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
