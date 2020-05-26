package service;

import java.io.File;

import dao.UsuarioDAO;
import model.Usuario;
import utils.CryptoAES;

public class UsuarioService {

	private UsuarioDAO dao = new UsuarioDAO();
	
    public boolean validar(Usuario usuario) throws Exception {
    	byte[] senha = this.dao.validar(usuario);
    	if (senha == null) {
    		return false;
    	}
        String senhaBanco = descriptografaSenha(senha);
        return senhaBanco.equals(usuario.getPassword());
    }
    
    public void cadastrar(Usuario usuario) throws Exception {
    	usuario.setPasswordBytes(criptografaSenha(usuario.getPassword()));
    	this.dao.cadastrar(usuario);
    }
    
    private byte[] criptografaSenha(String senha) throws Exception {
    	CryptoAES aes = new CryptoAES();
    	byte[] arrayBytes = senha.getBytes();
    	aes.geraCifra(arrayBytes, new File("/home/dusan/eclipse-workspace/Aula11/src/chave.simetrica"));
    	return aes.getTextoCifrado();
    }
    
    private String descriptografaSenha(byte[] senha) throws Exception {
    	CryptoAES aes = new CryptoAES();
    	aes.geraDecifra(senha, new File("/home/dusan/eclipse-workspace/Aula11/src/chave.simetrica"));
    	return new String(aes.getTextoDecifrado());
    }
}
