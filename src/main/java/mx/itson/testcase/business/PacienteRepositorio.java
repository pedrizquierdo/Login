/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.testcase.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.itson.testcase.entities.Paciente;
import mx.itson.testcase.persistence.Conexion;

/**
 *
 * @author pedrizquierdo
 */
public class PacienteRepositorio {

    public boolean existePaciente(String correo) {
        try (Connection conn = Conexion.getConnection()) {
            String query = "SELECT COUNT(*) FROM pacientes WHERE correo = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void guardarPaciente(Paciente paciente) {
        try (Connection conn = Conexion.getConnection()) {
            String query = "INSERT INTO pacientes (nombre, correo, contrasena, telefono, edad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getCorreo());
            stmt.setString(3, paciente.getContrasena());
            stmt.setString(4, paciente.getTelefono());
            stmt.setInt(5, paciente.getEdad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


