package banquesang.servlet;

import banquesang.service.DonneurService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeleteDonneurServlet extends HttpServlet {

    private DonneurService donneurService;

    @Override
    public void init() throws ServletException {
        donneurService = new DonneurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        donneurService.supprimerDonneur(id);
        response.sendRedirect(request.getContextPath() + "/listDonneurs");
    }
}
