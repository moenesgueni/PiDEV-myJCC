package Services;

import Interfaces.SponsoringInterface;
import Models.ContratSponsoring;
import Utils.EnumTypeContrat;
import Utils.EnumEtatContrat;
import Utils.FileUpload;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//PDF imports
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContratSponsorinService implements SponsoringInterface {

    //Connection a la db
    Connection cnx = MaConnection.getInstance().getCnx();
    //creation service user
    UserService ps = new UserService();

    public String getFormattedDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(formatter);
    }

    //Créer un pdf avec les informations du contrat
    //exemple dest ‪C:\Users\Marwen\Desktop\testContrat.pdf
    public void createPdf(String dest, ContratSponsoring c) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        // Add header
        Paragraph header = new Paragraph("Contrat Sponsoring : " + c.getEtat().toString());
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        // Create table
        PdfPTable table = new PdfPTable(2);
        Paragraph contentSponsor = new Paragraph("Sponsor : " + c.getSponsor().getNom() + " " + c.getSponsor().getPrenom()
                + "\nEmail : " + c.getSponsor().getEmail());
        table.addCell(contentSponsor);
        Paragraph contentPhotographe = new Paragraph("Sponsor : " + c.getPhotoraphe().getNom() + " " + c.getPhotoraphe().getPrenom()
                + "\nEmail : " + c.getPhotoraphe().getEmail());
        table.addCell(contentPhotographe);
        Image imageSponsor = Image.getInstance(c.getSponsor().getPhotoB64());
        imageSponsor.scaleToFit(200, 200);
        table.addCell(imageSponsor);
        Image imagePhotographe = Image.getInstance(c.getPhotoraphe().getPhotoB64());
        imagePhotographe.scaleToFit(200, 200);
        table.addCell(imagePhotographe);
        table.setSpacingBefore(30f);
        table.setSpacingAfter(30f);

        document.add(table);

        //Add termes du contrat
        Paragraph contentTermes = new Paragraph("Date de début du contrat : " + c.getDateDebut()
                + "\nDate de Fin du contrat : " + c.getDateFin()
                + "\nType de contrat : " + c.getType()
                + "\nSalaire en Dt : " + c.getSalaireDt());
        document.add(contentTermes);

        // Add footer
        Paragraph footer = new Paragraph("Le " + getFormattedDate()
                + "\nSignature Sponsor :");
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
        //Signatures***********
                // Create table2
        PdfPTable table2 = new PdfPTable(2);
        Paragraph LeSponsor = new Paragraph("Signature du Sponsor");
        table2.addCell(LeSponsor);
        Paragraph LePhotographe = new Paragraph("Signature du Photographe");
        table2.addCell(LePhotographe);
        
        Image SignatureSponsor = Image.getInstance("C:\\Users\\Marwen\\Desktop\\signature.png");
        imageSponsor.scaleToFit(200, 200);
        table2.addCell(SignatureSponsor);
        Image SignaturePhotographe = Image.getInstance("http://localhost/myjcc/contrats/signatures/Defaultsignature.png");
        imagePhotographe.scaleToFit(200, 200);
        table2.addCell(SignaturePhotographe);
        
        table2.setSpacingBefore(30f);
        table2.setSpacingAfter(30f);

        document.add(table2);
        //**********************
        document.close();
    }
    //---------------------------------------------

    //Méthode création ContratSponsoring c utilisé lors des méthodes afficher
    private ContratSponsoring addContratSponsoring(ResultSet rs) {
        ContratSponsoring c = new ContratSponsoring();
        try {
            c.setID_Contrat(rs.getInt(1));
            c.setDateDebut(rs.getDate(2));
            c.setDateFin(rs.getDate(3));
            c.setType(EnumTypeContrat.valueOf(rs.getString(4)));
            c.setEtat(EnumEtatContrat.valueOf(rs.getString(5)));
            c.setSalaireDt(rs.getFloat(6));
            c.setTermesPDF(rs.getString(7));
            c.setSponsor(ps.afficherUserbyID(rs.getInt(8)));
            c.setSignatureSponsor(rs.getString(9));
            c.setPhotoraphe(ps.afficherUserbyID(rs.getInt(10)));
            c.setSignaturePhotographe(rs.getString(11));
        } catch (SQLException ex) {
            Logger.getLogger(PhotographieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    //Create : creation d'une proposition de contrat
    @Override
    public void ajouterContratSponsorin(ContratSponsoring c) {
        String req = "INSERT INTO `contratsponsoring`(`DateDebut`, `DateFin`, `Type`, `Etat`, `SalaireDt`, `TermesPDF`, `ID_Sponsor`,`SignatureSponsor`, `ID_Photographe`, `SignaturePhotographe`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            //creation contrat pdf
            String contartPdfName = getFormattedDate() + "_Contrat_" + System.currentTimeMillis() + ".pdf";
            createPdf("C:\\Users\\Marwen\\Desktop\\" + contartPdfName, c);
            //ajout contratpdf dans le serveur
            FileUpload.uploadFile("C:\\Users\\Marwen\\Desktop\\" + contartPdfName, "contrats\\" + contartPdfName);
            //ajout signature Sponsor dans le serveur
            FileUpload.uploadFile("C:\\Users\\Marwen\\Desktop\\signature.png", "contrats\\signatures\\" +contartPdfName+"Sponsor.png");
            //ajout du contrat dans la bd
            String contractName = "http://localhost/myjcc/contrats/"+contartPdfName;
            String sponsorSignature = "http://localhost/myjcc/contrats/signatures/"+contartPdfName+"Sponsor.png";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(1, c.getDateDebut());
            ps.setDate(2, c.getDateFin());
            ps.setString(3, c.getType().toString());
            ps.setString(4, c.getEtat().toString());
            ps.setFloat(5, c.getSalaireDt());
            ps.setString(6, contractName);
            ps.setInt(7, c.getSponsor().getID_User());
            ps.setString(8, sponsorSignature);
            ps.setInt(9, c.getPhotoraphe().getID_User());
            ps.setString(10, "");
            //
            ps.executeUpdate();
            System.out.println("Nouveau Contrat Ajoute avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Read : Affichage de tout les contrats
    @Override
    public List<ContratSponsoring> afficherContratsSponsorin() {
        List<ContratSponsoring> contratsSponsoring = new ArrayList<>();
        String request = "SELECT * FROM contratsponsoring";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                ContratSponsoring c = addContratSponsoring(rs);
                //
                contratsSponsoring.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratsSponsoring;
    }

    //GetById : Affichage d'un contrat
    @Override
    public ContratSponsoring afficherContratSponsoring(int id) {
        ContratSponsoring c = new ContratSponsoring();
        String request = "SELECT * FROM contratsponsoring WHERE `ID_Contrat` =" + id + ";";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                c = addContratSponsoring(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    //Filter : Affichage les Contrats d'un sponsor
    @Override
    public List<ContratSponsoring> afficherContratsDeSponsor(int id) {
        List<ContratSponsoring> contratsSponsoring = new ArrayList<>();
        String request = "SELECT * FROM contratsponsoring WHERE ID_Sponsor = " + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                ContratSponsoring c = addContratSponsoring(rs);
                //
                contratsSponsoring.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratsSponsoring;
    }

    //Filter : Affichage les Contrats d'un photographe
    @Override
    public List<ContratSponsoring> afficherContratsDephotographe(int id) {
        List<ContratSponsoring> contratsSponsoring = new ArrayList<>();
        String request = "SELECT * FROM contratsponsoring WHERE ID_Photographe = " + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                ContratSponsoring c = addContratSponsoring(rs);
                //
                contratsSponsoring.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratsSponsoring;
    }

    //Update : Changer les details du contrat
    @Override
    public void modifierContratSponsoring(ContratSponsoring c) {
        String request = "UPDATE contratsponsoring SET DateDebut = ?, DateFin = ?, Type = ?, Etat = ?"
                + ", SalaireDt = ?, TermesPDF = ?, ID_Sponsor = ?, SignatureSponsor = ?"
                + ", ID_Photographe = ?, SignaturePhotographe = ? WHERE ID_Contrat = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setDate(1, c.getDateDebut());
            ps.setDate(2, c.getDateFin());
            ps.setString(3, c.getType().toString());
            ps.setString(4, c.getEtat().toString());
            ps.setFloat(5, c.getSalaireDt());
            ps.setString(6, c.getTermesPDF());
            ps.setInt(7, c.getSponsor().getID_User());
            ps.setString(8, c.getSignatureSponsor());
            ps.setInt(9, c.getPhotoraphe().getID_User());
            ps.setString(10, "");
            ps.setInt(11, c.getID_Contrat());
            //
            ps.executeUpdate();
            System.out.println("Contrat modifié avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Delete : Supprimer un contrat
    @Override
    public void supprimerContratSponsoring(int id) {
        String request = "DELETE FROM contratsponsoring WHERE ID_Contrat = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            //
            ps.executeUpdate();
            System.out.println("Contrat supprimé avec success via prepared Statement!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ContratSponsorinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
