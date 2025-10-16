
package banquesang.servlet;

import banquesang.service.DonneurService;
import banquesang.service.ReceveurService;
import jakarta.servlet.*;
        import jakarta.servlet.http.*;
        import java.io.IOException;

public class DeleteReceveurServlet extends HttpServlet {

    private ReceveurService receveurService;

    @Override
    public void init() throws ServletException {
        receveurService = new ReceveurService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        receveurService.deleteReceveur(id);
        response.sendRedirect(request.getContextPath() + "/listReceveurs");
    }
}
