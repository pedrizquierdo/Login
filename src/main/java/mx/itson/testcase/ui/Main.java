/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.testcase.ui;

import java.util.Scanner;
import mx.itson.testcase.business.SistemaRegistro;

/**
 *
 * @author pedrizquierdo
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaRegistro sistema = new SistemaRegistro();

        System.out.println("=== Registro de Paciente ===");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.print("Confirmar Contraseña: ");
        String confirmacion = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // consumir salto de línea

        System.out.print("¿Aceptas los términos y condiciones? (true/false): ");
        boolean aceptaTerminos = scanner.nextBoolean();

        String resultado = sistema.registrarPaciente(nombre, correo, contrasena, confirmacion, telefono, edad, aceptaTerminos);
        System.out.println(resultado);
    }
}


