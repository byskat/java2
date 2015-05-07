package optimitzacio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Víctor Alarcón Serrano
 */
public class JavaConnection {
    
    private static Connection conn;
    
    public static Connection getConnection(){
        if(conn==null){
            try{
                Class.forName("oracle.jdbc.OracleDriver");
                //Obre la connexió.
                conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:3521:XE","cirv","1234");
            } catch (ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }    
        }
        return conn;
    }
    
    public static void closeConnection() throws SQLException{
        JavaConnection.getConnection().close();
    }
       
    public static void viewTempsNum1(int val) throws SQLException{
        
        String[] params = {Temps.T_NUM1};
        String sql = Temps.createQuery(params);
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, val);
        
        ResultSet rs = JavaConnection.selector(pstmt);
        
        while(rs.next()){
            Temps te = new Temps();
            te.setNum1(rs.getInt(1));
                        
            System.out.println("Num1: "+te.getNum1());   
        }
        rs.close();
    }
    
    public static void viewTempsNum2(int val) throws SQLException{
        
        String[] params = {Temps.T_NUM2};
        String sql = Temps.createQuery(params);
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, val);
        
        ResultSet rs = JavaConnection.selector(pstmt);
        
        while(rs.next()){
            Temps te = new Temps();
            te.setNum2(rs.getInt(1));
                        
            System.out.println("Num2: "+te.getNum2());   
        }
        rs.close();
    }
    
    public static ResultSet selector(PreparedStatement st){
        ResultSet rs = null;
        
        try{
            rs = st.executeQuery();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }   
        
        return rs;
    }

    public static void insertTemps(ArrayList<Temps> arlTemps) throws SQLException{
        PreparedStatement insertTemps = JavaConnection.getConnection().prepareStatement("Insert into temps values (?,?)");
        Iterator<Temps> it = arlTemps.iterator();
        while(it.hasNext()){
            Temps tp = it.next();
            insertTemps.setInt(1, tp.getNum1());
            insertTemps.setInt(2, tp.getNum2());
            insertTemps.executeUpdate();
            insertTemps.clearParameters();
        }
        insertTemps.close();
    }
}
