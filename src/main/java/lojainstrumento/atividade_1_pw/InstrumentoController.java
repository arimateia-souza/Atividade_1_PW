package lojainstrumento.atividade_1_pw;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class InstrumentoController {

    @RequestMapping(value = "/doBuscar", method = RequestMethod.GET)
    public void doBuscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var id = Integer.parseInt(request.getParameter("id"));
        var dao = new InstrumentoDao();

        Instrumento t = dao.getInstrumentoById(id);

        var writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        if (t != null){
            writer.println("<hr /> <p>" +t.getNome() + "</p>");
            writer.println("<p>" +t.getQtd() + "</p>");
            writer.println("<p>" + t.getDataCadastro() + "</p>");
        }else{
            writer.println("<p> Não encontrado </p>");
        }
        writer.println("</body>");
        writer.println("</html>");
    }

    @RequestMapping( method = RequestMethod.POST, value = "/doCadastrar")
    public void cadastrarInstrumento(HttpServletRequest request, HttpServletResponse response)throws IOException{
        var inst = new Instrumento();
        var nome = request.getParameter("nome");
        var qtd = Integer.parseInt(request.getParameter("qtd"));

        inst.setNome(nome);
        inst.setQtd(qtd);

        InstrumentoDao dao = new InstrumentoDao();
        dao.cadastrarInstrumento(inst);

        response.setContentType("text/HTML");
        var writer = response.getWriter();

        writer.println("<html>" +
                "<body>"+
                "<h1>" + inst.getNome() + "</h1>"+
                "<p> Prioridade: " + inst.getQtd() + "</p>" +
                "<p> Data" + inst.getDataCadastro() + "</p>"
        );
        writer.println("</body>"+
                "</html>"
        );


    }

}
