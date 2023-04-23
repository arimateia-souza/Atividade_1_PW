package lojainstrumento.atividade_1_pw;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Date;

@Controller
public class InstrumentoController {

    @RequestMapping(method = RequestMethod.POST, value = "/doAtualizar")
    public void doAtualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = Integer.parseInt(request.getParameter("id"));
        var nome = request.getParameter("nome");
        var quantidade = Integer.parseInt(request.getParameter("qtd"));
        var dataCadastro = Long.parseLong(request.getParameter("dataCadastro"));

        var Instrumento = new Instrumento(id, nome, quantidade, new Date(dataCadastro));

        var dao = new InstrumentoDao();

        dao.updateInstrumento(Instrumento);
        response.sendRedirect("/doListar");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doEditarPage")
    public void doEditarPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = Integer.parseInt(request.getParameter("id"));
        var dao = new InstrumentoDao();

        Instrumento i = dao.getInstrumentoById(id);

        var writer = response.getWriter();

        writer.println("<html>" +
                "<body>" +  //manda para doAtualizar do metodo POST
                "<form action='doAtualizar' method='post'>");
        writer.println("<input type='hidden' name='id' value='"+i.getId()+"'>");
        writer.println("<input type='text' name='nome' value='"+i.getNome()+"'>");
        writer.println("<input type='number' name='qtd' value='"+i.getQtd()+"'>");
        writer.println("<input type='hidden' name='dataCadastro' value='"+i.getDataCadastro().getTime()+"'>");
        writer.println("<button type='submit'>Editar</button");


        writer.println("</form>" +
                "</body>" +
                "<html>");
    }
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
                "<p> Quantidade: " + inst.getQtd() + "</p>" +
                "<p> Data de cadastro: " + inst.getDataCadastro() + "</p>"
        );
        writer.println("</body>"+
                "</html>"
        );
    }
    @RequestMapping(method = RequestMethod.GET, value = "/doListar")
    public void listarTarefas(HttpServletRequest request, HttpServletResponse response) throws IOException {

            /*Tarefa t = (Tarefa) request.getAttribute("tarefa");
            System.out.println(t.toString());*/

        var dao = new InstrumentoDao();
        var writer = response.getWriter();



        var listarInstrumentos = dao.listarTodos();
        response.setContentType("text/HTML");

        writer.println("<html>" +
                "<body>");

        for (var i : listarInstrumentos){
            writer.println("<p> Nome: " +i.getNome() + "</p>");
            writer.println("<p> Quantidade: " +i.getQtd() + "</p>");
            writer.println("<p> Data de cadastro: " + i.getDataCadastro() + "</p> ");
            writer.println("<a href='doEditarPage?id="+i.getId()+"'>Editar</a>");
            writer.println("<a href='doExcluir?id="+i.getId()+"'>Excluir</a>");
        }
        writer.println("</body>" +
                "</html>");
    }
    @RequestMapping(value = "/doExcluir", method = RequestMethod.GET)
    public void apagarTarefa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var id = Integer.parseInt(request.getParameter("id"));
        var dao = new InstrumentoDao();

        // verifica se o instrumento existe antes de apagar
        Instrumento i = dao.getInstrumentoById(id); //funçao de busca pra encontrar instrumento
        if (i != null) {
            dao.apagarInstrumento(id);
            var writer = response.getWriter();
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<p> Instrumento apagado com sucesso! </p>");
            writer.println("</body>");
            writer.println("</html>");
            response.sendRedirect("/doListar");
        } else {
            var writer = response.getWriter();
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<p> Instrumento não encontrada! </p>");
            writer.println("</body>");
            writer.println("</html>");
            response.sendRedirect("/doListar");
        }
    }


}



