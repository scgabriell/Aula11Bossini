package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
    public byte[] validar(Usuario usuario) {
        String sqlSelect = "SELECT * FROM usuario WHERE username = ?";

        try (Connection conn = ConnectionFactory.obtemConexao(); 
                PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
                    stm.setString(1, usuario.getUsername());
                    try (ResultSet rs = stm.executeQuery();) {
                        if (rs.next()) {
                        	return rs.getBytes("password");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void cadastrar(Usuario usuario) {
    	String sqlInsert = "INSERT INTO usuario VALUES(?,?)";
    	
    	try (Connection conn = ConnectionFactory.obtemConexao();
    			PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
    		stm.setString(1, usuario.getUsername());
    		stm.setBytes(2, usuario.getPasswordBytes());
    		
    		stm.execute();	
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
   
}
