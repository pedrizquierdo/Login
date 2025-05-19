/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.testcase.business;


import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import mx.itson.testcase.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pedrizquierdo
 */
@Controller
public class AuthController {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @GetMapping("/oauth2/callback/google")
    public void handleGoogleCallback(@AuthenticationPrincipal OAuth2User principal, 
                                   HttpServletResponse response) throws IOException {
        // Obtener datos del usuario
        String nombre = principal.getAttribute("name");
        String correo = principal.getAttribute("email");
        String telefono = principal.getAttribute("phone_number"); 
        
        // Crear paciente con valores por defecto
        if(!pacienteRepositorio.existePaciente(correo)) {
            Paciente paciente = new Paciente(
                nombre != null ? nombre : "Usuario Google",
                correo,
                "Oauth2_password", // Contraseña dummy
                telefono != null ? telefono : "N/A", // Teléfono
                18 // Edad por defecto
            );   
            pacienteRepositorio.guardarPaciente(paciente);
        }
       
        response.sendRedirect("https://www.google.com");
    }
    
    @Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    
    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/"; // O la página que prefieras
    }
}
}