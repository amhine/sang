package banquesang.servlet;

import banquesang.model.Donneur;
import banquesang.model.Receveur;
import banquesang.service.DonationService;
import banquesang.service.DonneurService;
import banquesang.service.ReceveurService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DonationServlet extends HttpServlet {

    private DonationService donationService;
    private DonneurService donneurService;
    private ReceveurService receveurService;

    @Override
    public void init() throws ServletException {
        donationService = new DonationService();
        donneurService = new DonneurService();
        receveurService = new ReceveurService();
    }



    @Override


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String receveurId = request.getParameter("receveurId");

        if (receveurId != null) {
            Receveur receveur = receveurService.getReceveurById(Long.parseLong(receveurId));

            if (receveur == null) {
                request.setAttribute("error", "Receveur introuvable !");
                return;
            }

            List<Donneur> donneursCompatibles = donationService.getDonneursCompatibles(receveur);
            request.setAttribute("donneursCompatibles", donneursCompatibles);
            request.setAttribute("receveur", receveur);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/receveur/donneursCompatibles.jsp");
            dispatcher.forward(request, response);
            return;
        }

        List<Receveur> receveurs = receveurService.getAllReceveurs();
        request.setAttribute("receveurs", receveurs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/receveur/listReceveur.jsp");
        dispatcher.forward(request, response);    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🩸 [DonationServlet] doPost() appelé");

        try {
            Long donneurId = Long.parseLong(request.getParameter("donneurId"));
            Long receveurId = Long.parseLong(request.getParameter("receveurId"));
            System.out.println("➡️ donneurId=" + donneurId + ", receveurId=" + receveurId);

            Donneur donneur = donneurService.getDonneurById(donneurId);
            Receveur receveur = receveurService.getReceveurById(receveurId);

            boolean success = donationService.associerDonneurReceveur(donneur, receveur);
            System.out.println("✅ Résultat association : " + success);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/DonationServlet?receveurId=" + receveurId);
            } else {
                request.setAttribute("error", "Association impossible !");
                doGet(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}