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

            d.setDernierDonation(LocalDate.now().minusMonths(3));

            Medical m = new Medical();
            m.setHepatiteB(Boolean.parseBoolean(request.getParameter("hepatiteB")));
            m.setHepatiteC(Boolean.parseBoolean(request.getParameter("hepatiteC")));
            m.setVih(Boolean.parseBoolean(request.getParameter("vih")));
            m.setDiabete(Boolean.parseBoolean(request.getParameter("diabete")));
            m.setGrossesse(Boolean.parseBoolean(request.getParameter("grossesse")));
            m.setAllaitement(Boolean.parseBoolean(request.getParameter("allaitement")));

            d.setMedical(m);

            // Vérifier l'éligibilité du donneur
            if (!d.isEligible()) {
                request.setAttribute("error", "Donneur non éligible - Vérifiez l'âge, le poids et les conditions médicales");
                request.setAttribute("statusDisponibilite", StatusDisponibilite.Non_eligible);
                request.getRequestDispatcher("/donneur/createDonneur.jsp").forward(request, response);
                return;
            }

            // Vérifier si le donneur a déjà une donation (association)
            if (d.getDonation() != null) {
                d.setStatusDisponibilite(StatusDisponibilite.Non_disponible);
                request.setAttribute("warning", "Donneur associé à une donation - Statut: Indisponible");
            } else {
                // Tout est correct et pas encore associé
                d.setStatusDisponibilite(StatusDisponibilite.Disponible);
                request.setAttribute("success", "Donneur créé avec le statut: Disponible");
            }

            donneurService.creerDonneur(d);
            response.sendRedirect(request.getContextPath() + "/listDonneurs");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la création du donneur : " + e.getMessage());
            request.getRequestDispatcher("/donneur/createDonneur.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/donneur/createDonneur.jsp");
        dispatcher.forward(request, response);
    }
}
