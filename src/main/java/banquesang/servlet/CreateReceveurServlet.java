package banquesang.servlet;

import banquesang.model.Receveur;
import banquesang.enums.Urgence;
import banquesang.enums.ReceveurStatus;
import banquesang.enums.Genre;
import banquesang.enums.GroupeSang;
import banquesang.service.ReceveurService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

public class CreateReceveurServlet extends HttpServlet {

    private ReceveurService receveurService;

    @Override
    public void init() throws ServletException {
        receveurService = new ReceveurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("genres", Genre.values());
        request.setAttribute("groupesSang", GroupeSang.values());
        request.setAttribute("urgences", Urgence.values());
        request.setAttribute("statuses", ReceveurStatus.values());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/receveur/createReceveur.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Receveur r = new Receveur();
            r.setNom(request.getParameter("nom"));
            r.setPrenom(request.getParameter("prenom"));
            r.setTelephone(request.getParameter("telephone"));
            r.setCin(request.getParameter("cin"));
            r.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance")));
            r.setGenre(Genre.valueOf(request.getParameter("genre")));
            r.setGroupeSang(GroupeSang.valueOf(request.getParameter("groupeSang")));
            r.setUrgence(Urgence.valueOf(request.getParameter("urgence")));
            r.setReceveurStatus(ReceveurStatus.valueOf(request.getParameter("receveurStatus")));
            r.setDisponible(true);

            receveurService.createReceveur(r);

            response.sendRedirect(request.getContextPath() + "/listReceveurs");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la création du receveur : " + e.getMessage());
            doGet(request, response);
        }
    }
}
