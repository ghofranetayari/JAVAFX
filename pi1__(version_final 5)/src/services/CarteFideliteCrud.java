/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.CarteFidelite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.CarteFidelite.NiveauCarte;  
import Utils.MyConnection;
import entities.CarteFidelite.EtatCarte;
/**
 *
 * @author ghofr
 */

public class CarteFideliteCrud {  
 public static ArrayList<String> mails = new ArrayList(); 

  private static CarteFideliteCrud instance;


    private Connection cnx2;

    public CarteFideliteCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

   public static CarteFideliteCrud getInstance() {
 
        if (instance == null) {
            instance = new CarteFideliteCrud();
        }
        return instance;
    }


    
    
public int ajouterPts(float price, int IdU) {
    CarteFidelite carte = getCarteFideliteByIdU(IdU);

    if (carte != null && carte.getEtatCarte() == EtatCarte.active) {
        int pts = Math.round(price / 100.00f);

        // Increment the 'PtsFidelite' value by 'pts'
        int newPtsFidelite = carte.getPtsFidelite() + pts;

        // Update 'PtsFidelite' in the database
        String updateQuery = "UPDATE CarteFidelite SET PtsFidelite = ? WHERE id = ?";
        try {
            PreparedStatement updateStatement = cnx2.prepareStatement(updateQuery);
            updateStatement.setInt(1, newPtsFidelite);
            updateStatement.setInt(2, IdU); // Use 'IdU' for id

            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("PtsFidelite updated to " + newPtsFidelite + " for id: " + IdU);
            } else {
                System.out.println("No rows were updated for id: " + IdU);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }

        incrementDateFin(carte);

        return pts;
    } else {
        System.out.println("Carte inactive or not found");
        return 0;
    }
}

public CarteFidelite getCarteFideliteById(int IdCarte) {
    try {
        String sql = "SELECT * FROM CarteFidelite WHERE IdCarte = ?";
        Connection connection = cnx2;
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, IdCarte);  // Set the IdCarte parameter
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            System.out.println("fffff");
           CarteFidelite f = new CarteFidelite();
            f.setIdCarte(IdCarte);
           f.setPtsFidelite(rs.getInt(2));
          f.setDateDebut(rs.getDate(3));
          f.setDateFin(rs.getDate(4));
          f.setid(rs.getInt(5));
           String etatStringValue = rs.getString(6);
            System.out.println(etatStringValue);
        CarteFidelite.EtatCarte etatValue =EtatCarte.valueOf(etatStringValue);
           f.setEtatCarte(etatValue);
            String NiveauCarteString = rs.getString(7);
            System.out.println(NiveauCarteString);
            CarteFidelite.NiveauCarte niveau = NiveauCarte.valueOf(NiveauCarteString);
            System.out.println(niveau);
            f.setNiveauCarte(niveau);
            return f;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}

public static CarteFidelite getCarteFideliteByIdU(int IdCarte) {
    try {
        String sql = "SELECT * FROM CarteFidelite WHERE id = ?"; 
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, IdCarte);  // Set the IdCarte parameter
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            CarteFidelite f = new CarteFidelite();
            f.setIdCarte(IdCarte);
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            ///CarteFidelite.EtatCarte etatValue = CarteFidelite.EtatCarte.valueOf(etatStringValue);
           // f.setEtatCarte(etatValue);
            String NiveauCarteString = rs.getString(7);
            CarteFidelite.NiveauCarte niveau = NiveauCarte.valueOf(NiveauCarteString);
            f.setNiveauCarte(niveau);
            return f;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}


public boolean incrementDateFin(CarteFidelite carte) {
    String updateQuery = "UPDATE CarteFidelite SET DateFin = DATE_ADD(DateFin, INTERVAL 1 YEAR) WHERE IdCarte = ?";

    try (Connection connection = cnx2;
        PreparedStatement st = cnx2.prepareStatement(updateQuery)) {
        st.setInt(1, carte.getIdCarte()); // Use the IdCarte from the CarteFidelite object
        int rowsUpdated = st.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

/*public boolean ajouterCarteFidelite2() 
{ 
    String requete = "INSERT INTO CarteFidelite (PtsFidelite, DateDebut, DateFin, id, NiveauCarte)"+ "VALUES (0,11/10/2023,11/10/2024, 1,bronze )";
        try { 
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete); 
            return true; 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
            return false; 
        }
    
}*/
     
public int parseClientID(int IdCarte) {
    try (Connection connection = cnx2;
         PreparedStatement preparedStatement = cnx2.prepareStatement("SELECT id FROM CarteFidelite WHERE IdCarte = ?")) {
        preparedStatement.setInt(1, IdCarte);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt(1); 
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return 0;
}
    
    
public boolean ajouterCarteFidelite(int id) {
    try {
        String checkQuery = "SELECT IdCarte FROM CarteFidelite WHERE id = ? LIMIT 1";
        PreparedStatement checkStmt = cnx2.prepareStatement(checkQuery);
        checkStmt.setInt(1, id);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            System.out.println("Client already has a loyalty card");
            return false;
        } else {
            String insertQuery = "INSERT INTO CarteFidelite (PtsFidelite, DateDebut, DateFin, id, NiveauCarte, EtatCarte) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = cnx2.prepareStatement(insertQuery);

            insertStmt.setInt(1, 0);

            Calendar calendar = Calendar.getInstance();
            Date DateDebut = calendar.getTime();
            calendar.add(Calendar.YEAR, 1);
            Date DateFin = calendar.getTime();

            insertStmt.setDate(2, new java.sql.Date(DateDebut.getTime()));
            insertStmt.setDate(3, new java.sql.Date(DateFin.getTime()));
            insertStmt.setInt(4, id);
            insertStmt.setString(5, "bronze");
            insertStmt.setString(6, "active");

            int rowsAffected = insertStmt.executeUpdate();

            return rowsAffected > 0; 
            
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

public ArrayList<CarteFidelite> afficherCarte(){  
    ArrayList<CarteFidelite> myList = new ArrayList<>(); 
    Statement st;      
    String requete3 = "SELECT * FROM cartefidelite"; 

        try {
            st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);  
            while (rs.next()) { 
                CarteFidelite f = new CarteFidelite(); 
                f.setIdCarte(rs.getInt(1));  
                f.setPtsFidelite(rs.getInt(2));  
                f.setDateDebut(rs.getDate(3)); 
                f.setDateFin(rs.getDate(4));   
                f.setid(rs.getInt(5));
                String EtatCarteString = rs.getString(6); // Assuming this string represents an enum value
                CarteFidelite.EtatCarte EtatCarteValue = EtatCarte.valueOf(EtatCarteString); 
                f.setEtatCarte(EtatCarteValue);
                String etatStringValue= rs.getString(7); 
                CarteFidelite.NiveauCarte etatValue=NiveauCarte.valueOf(etatStringValue); 
                f.setNiveauCarte(etatValue); 

                myList.add(f); 
            }

        } 
        catch (SQLException ex) {
               System.err.println(ex.getMessage()); 
               
            }
        return myList ;  }  


protected void afficherCarte(ArrayList<CarteFidelite> list ) 
{  
    for (CarteFidelite carte:list)  
    {  
        System.out.println(carte.toString()); 
    }
    
}
public CarteFidelite chercherCarte(int IdCarte) {  
    List<CarteFidelite> myList = new ArrayList<>();     
    String requete3 = "SELECT * FROM CarteFidelite WHERE IdCarte= " +IdCarte;  
    Connection connection = cnx2; 
        try {  
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            CarteFidelite f = new CarteFidelite(); 
            if (rs.next()) 
            { 
            f.setIdCarte(IdCarte);
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            CarteFidelite.NiveauCarte etatValue = NiveauCarte.valueOf(etatStringValue);
            f.setNiveauCarte(etatValue);
            String EtatCarteString = rs.getString(7);
            CarteFidelite.EtatCarte EtatCarteValue = EtatCarte.valueOf(EtatCarteString);
            f.setEtatCarte(EtatCarteValue);
            return f;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    
        return null ;  }    

public boolean modifierCarte(CarteFidelite carte, int IdCarte) {
    try {
        Connection connection = cnx2;
        String requete = "UPDATE user SET PtsFidelite = ?, DateDebut = ?, DateFin = ?, id = ?, NiveauCarte = ?, EtatCarte = ? WHERE id = ?";
        
        PreparedStatement pst = connection.prepareStatement(requete);
        
        pst.setInt(1, carte.getPtsFidelite());
        pst.setDate(2, new java.sql.Date(carte.getDateDebut().getTime()));  // Assuming carte.getDateDebut() returns a java.util.Date
        pst.setDate(3, new java.sql.Date(carte.getDateFin().getTime()));    // Assuming carte.getDateFin() returns a java.util.Date
        pst.setInt(4, carte.getid());
        CarteFidelite.NiveauCarte niveauString = carte.getNiveauCarte();
        String Value1 = niveauString.toString();
        pst.setString(5, Value1);
        CarteFidelite.EtatCarte etatString = carte.getEtatCarte();
        String Value2 = etatString.toString();
        pst.setString(6, Value2);
        pst.setInt(7, IdCarte);  // Set the ID parameter 
        
        
        int rowsUpdated = pst.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();  // Print the exception for debugging purposes
        return false;
    }
}

public int SuspendCarte(int idCarte) {
    CarteFidelite f = new CarteFidelite();
    f = getCarteFideliteById(idCarte);

if (f != null && f.getEtatCarte() != EtatCarte.valueOf("suspended")) { 
    String updateQuery = "UPDATE CarteFidelite SET EtatCarte = ? WHERE IdCarte = ?";

        try (Connection connection = cnx2;
             PreparedStatement st = connection.prepareStatement(updateQuery)) {
            st.setString(1, "suspended");
            st.setInt(2, idCarte);
            int rowsUpdated = st.executeUpdate(); 
            
        if (rowsUpdated > 0) {
            System.out.println("successfully updated"); 
            return 1;  
        } 
        else 
        { 
            return -3; 
        }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -2;
        }
    } else if (f== null) {
        System.out.println("Card not found");
        return 0;
    } 
    else {
    System.out.println("Card already Suspended");
    return -1;
}}


public boolean SupprimerCarte(int id) { 
    CarteFidelite f = getCarteFideliteById(id); 
    if (f!=null){
    try (Connection connection = cnx2;
         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CarteFidelite WHERE IdCarte = ?")) {
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Carte Successfully deleted");
            return true; 
        } else {
            System.out.println("Carte not found or failed to delete");
            return false; 
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage()); 
        return false; 
    }} 
    else 
    { 
        System.out.println("Card not found "); 
        return false;  }} 
    
    public static ObservableList<CarteFidelite> getCarteFidelites()
    { Connection cnx2=MyConnection.getInstance().getCnx();  
    ObservableList<CarteFidelite> list = FXCollections.observableArrayList(); 
        try {
            PreparedStatement st = cnx2.prepareStatement("select * from CarteFidelite"); 
            ResultSet rs = st.executeQuery(); 
            while (rs.next()){ 
            CarteFidelite f = new CarteFidelite();
            f.setIdCarte(rs.getInt("IdCarte"));
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            CarteFidelite.EtatCarte etatValue = EtatCarte.valueOf(etatStringValue);
            f.setEtatCarte(etatValue);
            String NiveauCarteString = rs.getString(7);
            CarteFidelite.NiveauCarte NiveauCarteValue = NiveauCarte.valueOf(NiveauCarteString);
            f.setNiveauCarte(NiveauCarteValue);
            list.add(f);  
            System.out.println(f);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return list; }
    
    public static ObservableList<CarteFidelite> listeDesEntites1() {
                 Connection cnx3 = MyConnection.getInstance().getCnx();

        ObservableList<CarteFidelite> myList1 = FXCollections.observableArrayList();  
     
       try {
        
         
         
         String requete = "SELECT * FROM CarteFidelite";
         System.out.println("connexion etablie");
         
         Statement st = cnx3.createStatement();
         System.out.println("connexion");
         
         ResultSet rs = st.executeQuery(requete);
         System.out.println("y-");
         while (rs.next()) {
             CarteFidelite f = new CarteFidelite();
             f.setIdCarte(rs.getInt("IdCarte"));
             f.setPtsFidelite(rs.getInt(2));
             f.setDateDebut(rs.getDate(3));
             f.setDateFin(rs.getDate(4));
             f.setid(rs.getInt(5));
             String etatStringValue = rs.getString(6);
             CarteFidelite.EtatCarte etatValue = EtatCarte.valueOf(etatStringValue);
             f.setEtatCarte(etatValue);
             String NiveauCarteString = rs.getString(7);
             CarteFidelite.NiveauCarte NiveauCarteValue = NiveauCarte.valueOf(NiveauCarteString);
             f.setNiveauCarte(NiveauCarteValue);
             myList1.add(f);
         }
          
         
     } catch (SQLException ex) {
         Logger.getLogger(CarteFideliteCrud.class.getName()).log(Level.SEVERE, null, ex);
     }
     return myList1;
    }

    public int ActivateCarte(int idCarte) {
    CarteFidelite f = new CarteFidelite();
    f = getCarteFideliteById(idCarte);

if (f != null && f.getEtatCarte() !=EtatCarte.valueOf("active")) { 
    String updateQuery = "UPDATE CarteFidelite SET EtatCarte = ? WHERE IdCarte = ?";

        try (Connection connection = cnx2;
             PreparedStatement st = connection.prepareStatement(updateQuery)) {
            st.setString(1, "active");
            st.setInt(2, idCarte);
            int rowsUpdated = st.executeUpdate(); 
            
        if (rowsUpdated > 0) {
            System.out.println("successfully updated"); 
            return 1;  
        } 
        else 
        { 
            return -3; 
        }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -2;
        }
    } else if (f== null) {
        System.out.println("Card not found");
        return 0;
    } 
    else {
    System.out.println("Card already activated");
    return -1;
}}  
    
    

  public float  calculate(int PtsFidelite,float price )  
  { 
      
       return PtsFidelite/100.2f ;  
  }  
public float UtiliserPts(int idU, float price) {
    CarteFidelite f = getCarteFideliteByIdU(idU);

    int ptsToDeduct = f.getPtsFidelite();
    float priceAfterDeduction = price - (ptsToDeduct / 100);

    String updateQuery = "UPDATE CarteFidelite SET PtsFidelite = 0 WHERE id = ?";
    try {
        PreparedStatement updateStatement = cnx2.prepareStatement(updateQuery);
        updateStatement.setInt(1, idU); // Use 'idU' for id

        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("All PtsFidelite points used for id: " + idU);
        } else {
            System.out.println("No rows were updated for id: " + idU);
        }
    } catch (SQLException ex) {
        System.out.println("SQL Error: " + ex.getMessage());
    }

    return priceAfterDeduction;
}

  
          
         
  
  
  
public int UpgradeCarte1(int IdCarte) { 
    System.out.println("methode executé");
    String requete = "SELECT ptsFidelite, niveauCarte FROM CarteFidelite WHERE id = ?";
    try {
        PreparedStatement st = cnx2.prepareStatement(requete);
        st.setInt(1, IdCarte);
        ResultSet rs = st.executeQuery();
        System.out.println("2nd check");

        if (rs.next()) { 
            System.out.println("rs loop");

            int ptsFidelite = rs.getInt("PtsFidelite");
            System.out.println(ptsFidelite);
            NiveauCarte niveauCarte = NiveauCarte.valueOf(rs.getString("NiveauCarte")); 
            System.out.println(niveauCarte);

            if (ptsFidelite >=1000 && niveauCarte != NiveauCarte.gold) {
                if (niveauCarte == NiveauCarte.bronze) { 
                    System.out.println("if condtion");
                    niveauCarte = NiveauCarte.silver;
                } else if (niveauCarte == NiveauCarte.silver) {
                    niveauCarte = NiveauCarte.gold;
                } 
                ptsFidelite -= 1000;  
                System.out.println(ptsFidelite); 
                
                
                String updateQuery = "UPDATE CarteFidelite SET NiveauCarte = ?, PtsFidelite = ? WHERE id = ?"; 
                PreparedStatement updateStatement = cnx2.prepareStatement(updateQuery); 

                updateStatement.setString(1, niveauCarte.toString());
                updateStatement.setInt(2, ptsFidelite);
                updateStatement.setInt(3, IdCarte);

                int rowsUpdated = updateStatement.executeUpdate();   
                System.out.println(ptsFidelite);
                System.out.println(IdCarte);
                System.out.println(niveauCarte);
                
                System.out.println(rowsUpdated);
                if (rowsUpdated>0) 
                        { 
                            System.out.println("carte Upgraded"); 
                        } 
                else { 
                    System.out.println("failed");
                }

            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());    }
    return 0;
}  

public static int getNbrOffreSpecial(NiveauCarte niveau) {
    Connection cnx2 = MyConnection.getInstance().getCnx();
    int rowCount = -1;
    try {
        String query = "SELECT COUNT(*) FROM offrespecialevenment WHERE niveau = ?";
        PreparedStatement st = cnx2.prepareStatement(query);
        st.setString(1, niveau.toString()); // Assuming niveau is a String value

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rowCount = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CarteFideliteCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rowCount;
}

} 
 

