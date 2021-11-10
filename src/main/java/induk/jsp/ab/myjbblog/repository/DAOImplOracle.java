package induk.jsp.ab.myjbblog.repository;
import java.sql.*;


public class DAOImplOracle implements DAO {
    Connection conn = null;
    @Override
    public Connection getConnection(){
        String jdbcUrl = "jdbc:oracle:thin:@db_medium?TNS_ADMIN=/Users/yongjun/Downloads/Wallet_db/";
        String dbUser = "ADMIN";
        String dbPw = "Vkfkrhs8985!";
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPw);

        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return conn;
    }
    @Override
    public void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs){

    }
}
