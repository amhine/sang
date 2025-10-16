package banquesang.servlet;

import banquesang.service.DonneurService;
import banquesang.model.Donneur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ListeDonneurServlet extends HttpServlet {

    private DonneurService donneurService;

    @Override
    public void init() {
        donneurService = new DonneurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Donneur> donneurs = donneurService.getAllDonneurs();

            System.out.println("Nombre de donneurs récupérés : " + (donneurs != null ? donneurs.size() : 0));
            if (donneurs != null) {
                for (Donneur d : donneurs) {
                    System.out.println("Donneur: " + d.getNom() + " - Medical: " + (d.getMedical() != null));
                }
            }

            request.setAttribute("donneurs", donneurs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/donneur/listDonneurs.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur : " + e.getMessage(), e);
        }
    }
}
