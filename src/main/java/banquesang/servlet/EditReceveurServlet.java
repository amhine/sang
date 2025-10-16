package banquesang.servlet;

import banquesang.model.Receveur;
import banquesang.enums.Genre;
import banquesang.enums.GroupeSang;
import banquesang.service.ReceveurService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

public class EditReceveurServlet extends HttpServlet {

    private ReceveurService receveurService;

    @Override
    public void init() throws ServletException {
        receveurService = new ReceveurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Receveur r = receveurService.getReceveurById(id);
        request.setAttribute("receveur", r);

        request.setAttribute("genres", Genre.values());
        request.setAttribute("groupesSang", GroupeSang.values());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/receveur/editReceveur.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Receveur r = receveurService.getReceveurById(id);

            r.setNom(request.getParameter("nom"));
            r.setPrenom(request.getParameter("prenom"));
            r.setTelephone(request.getParameter("telephone"));
            r.setCin(request.getParameter("cin"));
            r.setDateNaissance(LocalDate.parse(request.getParameter("datenaissance")));
            r.setGenre(Genre.valueOf(request.getParameter("genre")));
            r.setGroupeSang(GroupeSang.valueOf(request.getParameter("groupesang")));



            receveurService.updateReceveur(r);

            response.sendRedirect(request.getContextPath() + "/listReceveurs");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la modification du receveur : " + e.getMessage());
            request.getRequestDispatcher("/receveur/editReceveur.jsp").forward(request, response);
        }
    }
}
