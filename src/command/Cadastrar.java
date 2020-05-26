package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.UsuarioService;

public class Cadastrar implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String nome = request.getParameter("username");
        String senha = request.getParameter("passwd");
        
        Usuario usuario = new Usuario();
        UsuarioService service = new UsuarioService();

        usuario.setUsername(nome);
        usuario.setPassword(senha);

        if (!service.validar(usuario)) {
        	service.cadastrar(usuario);
            HttpSession session = request.getSession();
            session.setAttribute("logado", usuario);
            System.out.println("Logou " + usuario);
        } else {
            System.out.println("Não Logou " + usuario);
            throw new ServletException("Usuário/Senhas já cadastrados");
        }

        response.sendRedirect("index.jsp");

	}

}
