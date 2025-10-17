package banquesang.servlet;

import banquesang.model.Donneur;
import banquesang.model.Medical;
import banquesang.enums.Genre;
import banquesang.enums.GroupeSang;
import banquesang.service.DonneurService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

public class EditDonneurServlet extends HttpServlet {

    private DonneurService donneurService;

    @Override
    public void init() throws ServletException {
        donneurService = new DonneurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Donneur d = donneurService.getDonneurById(id);
        request.setAttribute("donneur", d);

        request.setAttribute("genres", Genre.values());
        request.setAttribute("groupesSang", GroupeSang.values());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/donneur/editDonneur.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Donneur d = donneurService.getDonneurById(id);

            d.setNom(request.getParameter("nom"));
            d.setPrenom(request.getParameter("prenom"));
            d.setTelephone(request.getParameter("telephone"));
            d.setCin(request.getParameter("cin"));
            d.setDateNaissance(LocalDate.parse(request.getParameter("datenaissance")));
            d.setPoids(Double.parseDouble(request.getParameter("poids")));
            d.setGenre(Genre.valueOf(request.getParameter("genre")));
            d.setGroupeSang(GroupeSang.valueOf(request.getParameter("groupesang")));

            Medical m = d.getMedical();
            if (m == null) {
                m = new Medical();
            }
            m.setHepatiteB(request.getParameter("hepatiteB") != null);
            m.setHepatiteC(request.getParameter("hepatiteC") != null);
            m.setVih(request.getParameter("vih") != null);
            m.setDiabete(request.getParameter("diabete") != null);
            m.setGrossesse(request.getParameter("grossesse") != null);
            m.setAllaitement(request.getParameter("allaitement") != null);
            d.setMedical(m);
            donneurService.mettreAJourDisponibilite(d);
            donneurService.modifierDonneur(d);

            response.sendRedirect(request.getContextPath() + "/listDonneurs");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la modification du donneur : " + e.getMessage());
            request.getRequestDispatcher("/donneur/editDonneur.jsp").forward(request, response);
        }
    }
}
