package banquesang.servlet;

import banquesang.model.Receveur;
import banquesang.service.ReceveurService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ListReceveurServlet extends HttpServlet {

    private ReceveurService receveurService;

    @Override
    public void init() throws ServletException {
        receveurService = new ReceveurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Receveur> receveurs = receveurService.getAllReceveurs();
        request.setAttribute("receveurs", receveurs);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/receveur/listReceveurs.jsp");
        dispatcher.forward(request, response);
    }
}
