/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.testcase.business;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author pedrizquierdo
 */
@Controller
public class AuthController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login"; // redirige al login automático con Google
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User principal, Model model) {
        String nombre = principal.getAttribute("name");
        String correo = principal.getAttribute("email");
        model.addAttribute("nombre", nombre);
        model.addAttribute("correo", correo);
        return "bienvenida"; // una vista de bienvenida, si usas Thymeleaf
    }
    
}
