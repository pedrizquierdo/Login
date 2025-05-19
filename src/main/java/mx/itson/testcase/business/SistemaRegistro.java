/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.testcase.business;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mx.itson.testcase.entities.Paciente;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedrizquierdo
 */
@Service
public class SistemaRegistro {
    
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    private CorreoService correoService;

    public SistemaRegistro() {
        this.pacienteRepositorio = new PacienteRepositorio();
        this.correoService = new CorreoService();
    }
    
    public boolean existePaciente(String correo) {
        return pacienteRepositorio.existePaciente(correo);
    }
    
    public String registrarPaciente(String nombre, String correo, String contrasena, String confirmacionContrasena, String telefono, int edad, boolean aceptaTerminos) {
        if (nombre == null || correo == null || contrasena == null || telefono == null) {
            return "Error: Todos los campos obligatorios deben ser completados.";
        }

        if (!aceptaTerminos) {
            return "Error: Debes aceptar los términos y condiciones.";
        }

        if (!telefono.matches("\\d+")) {
            return "Error: El número de teléfono debe contener solo dígitos.";
        }

        if (!contrasena.equals(confirmacionContrasena)) {
            return "Error: Las contraseñas no coinciden.";
        }

        if (contrasena.length() < 8 || !contrasena.matches(".*[A-Z].*") || !contrasena.matches(".*\\d.*")) {
            return "Error: La contraseña debe tener al menos 8 caracteres, incluir una mayúscula y un número.";
        }

        if (edad < 18) {
            return "Error: Debes ser mayor de 18 años.";
        }

        // Email Validation Regex
        if (!correo.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            return "Error: El correo electrónico no es válido.";
        }

        if (pacienteRepositorio.existePaciente(correo)) {
            return "Error: El correo ya está registrado.";
        }
        
        

        // Hash password before storing it
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        
        Paciente nuevoPaciente = new Paciente(nombre, correo, hashedPassword, telefono, edad);
        pacienteRepositorio.guardarPaciente(nuevoPaciente);
        correoService.enviarCorreoConfirmacion(correo);

        return "Registro exitoso, ahora puedes iniciar sesión.";
    }
    @Component
public class DialogManager {

    public static void showSuccessDialog(String email) {
        JFrame parent = new JFrame();
        JOptionPane.showMessageDialog(parent,
            "Registro exitoso para: " + email,
            "Registro Completado",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
}


