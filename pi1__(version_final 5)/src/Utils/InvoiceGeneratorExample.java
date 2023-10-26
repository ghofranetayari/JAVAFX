/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import Utils.MyConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.io.IOUtils;

public class InvoiceGeneratorExample {

    public InvoiceGeneratorExample() {
    }
    public  void main2() {
        // Récupérez les données de la table commande depuis votre base de données
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String selectCommandeQuery = "SELECT * FROM commande ORDER BY id DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(selectCommandeQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //int  s  = resultSet.getInt("")
                int userId = resultSet.getInt("userId");
                int productId = resultSet.getInt("productId");
                
                
          String selectCommandeQuery2 = "SELECT * FROM produit WHERE id = ?";
PreparedStatement preparedStatement2 = connection.prepareStatement(selectCommandeQuery2);
preparedStatement2.setInt(1, productId);
ResultSet resultSet2 = preparedStatement2.executeQuery();
int prix=00;
if (resultSet2.next()) {
    prix = resultSet2.getInt(3);
    System.out.println(prix);
    }

 String selectCommandeQuery3 = "SELECT * FROM user WHERE id = ?";
PreparedStatement preparedStatement3 = connection.prepareStatement(selectCommandeQuery3);
preparedStatement3.setInt(1, userId);
ResultSet resultSet3 = preparedStatement3.executeQuery();
String nom="st";
String prenom="ts";
if (resultSet3.next()) {
    nom = resultSet3.getString(2);
    System.out.println(nom);
    prenom = resultSet3.getString(3);
    }



                // Créez un objet HttpClient
                HttpClient httpClient = HttpClients.createDefault();

                // Créez un objet HttpPost avec l'URL de l'API
                String apiUrl = "https://invoice-generator.com";
                HttpPost httpPost = new HttpPost(apiUrl);

                // Définissez les données de facturation au format JSON sans le champ "number"
                String invoiceData = "{"
                        + "\"from\":\" Epics Journeys'Store\","
                        + "\"to\":\"Nom et prenom du Client: "+nom+"  "+prenom+"\"," 
                        + "\"logo\":\"https://example.com/img/logo-invoice.png\","
                        + "\"items\":["
                        + "{\"name\":\"Starter plan\",\"quantity\":1,\"unit_cost\": "+ prix +" }"
                        + "],"
                        + "\"notes\":\"Merci pour votre achat !\""
                        + "}";

                // Configurez l'entité de la requête avec les données de facturation au format JSON
                StringEntity requestEntity = new StringEntity(invoiceData);
                httpPost.setEntity(requestEntity);
                httpPost.setHeader("Content-Type", "application/json");

                // Exécutez la requête HTTP POST
                HttpResponse response = httpClient.execute(httpPost);

                // Vérifiez le code de réponse HTTP
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // La requête a réussi (code 200). Vous pouvez traiter la réponse ici.

                    // Récupérez le contenu de la réponse
                    InputStream pdfInputStream = response.getEntity().getContent();

                    // Enregistrez le contenu dans un fichier PDF (vous pouvez personnaliser le nom du fichier)
                    String pdfFileName = "invoice_" + userId + "_" + productId + ".pdf";
                    FileOutputStream fileOutputStream = new FileOutputStream(pdfFileName);
                    IOUtils.copy(pdfInputStream, fileOutputStream);
                    fileOutputStream.close();

                    System.out.println("Facture générée et enregistrée sous le nom '" + pdfFileName + "'");
                } else {
                    // La requête a échoué. Gérez les erreurs en conséquence.
                    System.err.println("La requête a échoué avec le code de réponse : " + statusCode);
                }
            }

        } catch (Exception e) {
            // Gérez les erreurs d'E/S
            e.printStackTrace();
        }
    }
}

