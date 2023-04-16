package lojainstrumento.atividade_1_pw;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class InstrumentoController {

    @RequestMapping( method = RequestMethod.POST, value = "/doCadastrar")
    public void cadastrarInstrumento(HttpServletRequest request, HttpServletResponse response)throws IOException{
        var inst = new Instrumento();
        var nome = request.getParameter("nome");
        
    }


}
