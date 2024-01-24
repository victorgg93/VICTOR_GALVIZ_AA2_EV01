package laboratoriopatologos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratorioPatologos {

    public static void main(String[] args) {
        String usuario = "root";
        String password = "P@stelero5041";
        String url = "jdbc:mysql://localhost:3306/patologosusuarios";
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // Paso 1: Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Paso 2: Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();

            // Verificar si el usuario ya existe antes de insertar
            String usuarioAInsertar = "GABRIELA123";
            rs = statement.executeQuery("SELECT * FROM INFORMACIÓNUSUARIOS WHERE USUARIO = '" + usuarioAInsertar + "'");
            if (rs.next()) {
                System.out.println("El usuario '" + usuarioAInsertar + "' ya existe. No se puede insertar.");
            } else {
                // Paso 3: Insertar un nuevo usuario en la tabla INFORMACIÓNUSUARIOS
                statement.executeUpdate("INSERT INTO INFORMACIÓNUSUARIOS (NOMBRE, APELLIDO, USUARIO, CONTRASEÑA, Examen_Realizado) VALUES ('GABRIELA', 'JIMENEZ', '" + usuarioAInsertar + "', '123456789', 'SANGRE')");
                System.out.println("Usuario insertado correctamente.");
            }

            // Paso 4: Seleccionar y mostrar todos los usuarios
            rs = statement.executeQuery("SELECT * FROM INFORMACIÓNUSUARIOS");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " : " + rs.getString("Nombre") + " : " + rs.getString("Apellido") + " : " + rs.getString("Usuario") + " : " + rs.getString("Contraseña") + " : " + rs.getString("Examen_Realizado"));
            }

            // Paso 5: Actualizar la contraseña de un usuario
            statement.executeUpdate("UPDATE INFORMACIÓNUSUARIOS SET Contraseña = 'nueva_contraseña' WHERE USUARIO = 'GABRIELA123'");

            // Paso 6: Eliminar un usuario de la tabla
            statement.executeUpdate("DELETE FROM INFORMACIÓNUSUARIOS WHERE USUARIO = 'GABRIELA123'");

        } catch (ClassNotFoundException | SQLException ex) {
            // Manejo de excepciones
            Logger.getLogger(LaboratorioPatologos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Paso 7: Cerrar recursos (ResultSet, Statement, Connection)
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                // Manejo de excepciones al cerrar recursos
                Logger.getLogger(LaboratorioPatologos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

