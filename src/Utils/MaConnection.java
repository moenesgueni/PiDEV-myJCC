package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnection {

//DB Credentials
    final String USERNAME = "root";
    final String PASSWORD = "";
    final String URL = "jdbc:mysql://127.0.0.1:3306/myjcc";
//var
    Connection cnx;
    //1 privatisation du constructeur
    //2 créer une instance static de MaConnection
    static MaConnection instance;
    
    
    //constructeur
    private MaConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection etablie avec succes !!!");
        } catch (SQLException e) {
            System.err.println(e);
        }

    }
    
    //3 getter static
    public static MaConnection getInstance() {
        if(instance == null){
            instance = new MaConnection();
        }
        return instance;
    }

    public static void setInstance(MaConnection instance) {
        MaConnection.instance = instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }

}