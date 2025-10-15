package banquesang.servlet;

import banquesang.model.Donneur;
import banquesang.model.Medical;
import banquesang.enums.Genre;
import banquesang.enums.GroupeSang;
import banquesang.enums.StatusDisponibilite;
import banquesang.service.DonneurService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

public class DonneurServlet extends HttpServlet {

    private DonneurService donneurService;

    @Override
    public void init() throws ServletException {
        donneurService = new DonneurService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Donneur d = new Donneur();
            d.setNom(request.getParameter("nom"));
            d.setPrenom(request.getParameter("prenom"));
            d.setTelephone(request.getParameter("telephone"));
            d.setCin(request.getParameter("cin"));

            String dateStr = request.getParameter("datenaissance");
            if (dateStr != null && !dateStr.isEmpty()) {
                d.setDateNaissance(LocalDate.parse(dateStr));
            }

            String poidsStr = request.getParameter("poids");
            if (poidsStr != null && !poidsStr.isEmpty()) {
                d.setPoids(Double.parseDouble(poidsStr));
            }

            String genreStr = request.getParameter("genre");
            if (genreStr != null && !genreStr.isEmpty()) {
                d.setGenre(Genre.valueOf(genreStr));
            }

            String gs = request.getParameter("groupesang");
            if (gs != null && !gs.isEmpty()) {
                d.setGroupeSang(GroupeSang.valueOf(gs));
            }

            d.setDisponible(true);
            d.setStatusDisponibilite(StatusDisponibilite.Disponible);
            d.setDernierDonation(LocalDate.now().minusMonths(3)); // exemple par défaut

            Medical m = new Medical();
            m.setHepatiteB(Boolean.parseBoolean(request.getParameter("hepatiteB")));
            m.setHepatiteC(Boolean.parseBoolean(request.getParameter("hepatiteC")));
            m.setVih(Boolean.parseBoolean(request.getParameter("vih")));
            m.setDiabete(Boolean.parseBoolean(request.getParameter("diabete")));
            m.setGrossesse(Boolean.parseBoolean(request.getParameter("grossesse")));
            m.setAllaitement(Boolean.parseBoolean(request.getParameter("allaitement")));

            d.setMedical(m);

            donneurService.creerDonneur(d);

            response.sendRedirect(request.getContextPath() + "/listDonneurs");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la création du donneur : " + e.getMessage());
            request.getRequestDispatcher("/createDonneur").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/donneur/createDonneur.jsp");
        dispatcher.forward(request, response);
    }


}
