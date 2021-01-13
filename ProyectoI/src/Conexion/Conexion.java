package Conexion;
import formularios.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Conexion {
    static Properties props = new Properties();
    String hostname = null;
    String port = null;
    String database = null;
    String username = null;
    String password = null;
    String jndi  = null;
    
    
    public Conexion (){
        InputStream in = null;
        try {
            // Looks for properties file in the root of the src directory in Netbeans Project
              in = Files.newInputStream(FileSystems.getDefault().getPath("c:\\facturacion\\db_props.properties"));
            //in = Files.newInputStream(FileSystems.getDefault().getPath(System.getProperty("user.dir")+File.separator+"db_props.properties"));
            props.load(in);
            in.close();
            }   catch (IOException ex) {
                        ex.printStackTrace();
            }   finally{
                    try{
                            in.close();
                        }catch (IOException ex ) {
                                ex.printStackTrace();
                                }
                }
        // Metodo que se manda a llamar para cargar las propiedades del archivo de propiedades
        loadProperties();  
    }
    
    public void loadProperties(){
                hostname = props.getProperty("hostname");
                port  = props.getProperty("port");
                database = props.getProperty("database");
                username =   props.getProperty("username");
                password =   props.getProperty("password");
                jndi = props.getProperty("jndi");
        }
    
    
    /*
    Demostraqtes obtaining a conection via driver manager
    
    */
    
    public Connection getConnection() throws SQLException{
    Connection conn = null;
    String jdbcUrl = "jdbc:oracle:thin:@"+this.hostname+":"+
            this.port + ":" + this.database;
    
    conn = DriverManager.getConnection(jdbcUrl,username,password);
    System.out.println("Conexion establecida");
    
    return conn;
    }
    
    public Connection getDSConnection(){
    Connection conn  = null;
    try{    Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup(this.jndi);
            conn = ds.getConnection();
            
        }catch(NamingException | SQLException ex){
            ex.printStackTrace();
            }
    return conn;
    }
}
