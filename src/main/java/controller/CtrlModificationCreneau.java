package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Commande;
import model.CreneauRetrait;
import model.Magasin;
import bd.ConnectionMySql;

/**
 * Servlet implementation class ModifierCommande
 */
@WebServlet("/ModifierCommande")
public class CtrlModificationCreneau extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCommande = Integer.parseInt(request.getParameter("id"));
        
        Commande commande = null;
        ArrayList<CreneauRetrait> creneaux = new ArrayList<>();
        ArrayList<Magasin> magasins = new ArrayList<>();

        try {
            commande = ConnectionMySql.getCommandeById(idCommande);
            creneaux = ConnectionMySql.getHoursOpenedByMagasin(commande.getNomMagasin());
            magasins = ConnectionMySql.getAllMagasins();
            String nomMagasinCommande = commande.getNomMagasin();
            Time DebutCreneauCommande = commande.getDebutCreneau();
            Time FinCreneauCommande = commande.getFinCreneau();

            request.setAttribute("commande", commande);
            request.setAttribute("creneaux", creneaux);
            request.setAttribute("magasins", magasins);
            request.setAttribute("nomMagasinCommande", nomMagasinCommande);
            request.setAttribute("DebutCreneauCommande", DebutCreneauCommande);
            request.setAttribute("FinCreneauCommande", FinCreneauCommande);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("ModificationCreneau").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCommande = Integer.parseInt(request.getParameter("idCommande"));
        int idCreneau = Integer.parseInt(request.getParameter("idCreneau"));
        int idMagasin = Integer.parseInt(request.getParameter("idMagasin"));

        try {
            ConnectionMySql.updateCommande(idCommande, idCreneau, idMagasin);
            response.sendRedirect("DetailsCommande?id=" + idCommande);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
