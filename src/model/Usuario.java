package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    
    private byte[] passwordBytes;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public byte[] getPasswordBytes() {
		return passwordBytes;
	}
    
    public void setPasswordBytes(byte[] passwordBytes) {
		this.passwordBytes = passwordBytes;
	}

    @Override
    public String toString() {
        return "Usuario [username=" + username + ", password=" + password + "]";
    }
}
