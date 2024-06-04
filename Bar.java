import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bar {
        String server = "jdbc:mysql://140.119.19.73:3315/";
    	String database = "111306037"; // change to your own database
    	String url = server + database + "?useSSL=false";
    	String DBUsername = "111306037"; // change to your own user name
    	String DBPassword = "58g95"; // change to your own password

        private String query;
        private Statement stat;

        private static Bar instance;

        private Bar() {};

        public static Bar getInstance() {
            if (instance == null) {
                instance = new Bar();
            }
            return instance;
        }
        
        //Class FrameLogin
        public void Login(String username, String password) throws WrongDataError {
    		try (Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
    			
    			stat = conn.createStatement();

    			query = "SELECT * FROM User WHERE Name = '" + username + "' AND Password = '" + password + "'";
                
    			ResultSet rs = stat.executeQuery(query);
    			if (!rs.next()) {
    				throw new WrongDataError("Wrong username or password");
    			}else {
    				new FrameBar(conn);
    			}

    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
        
        //Class FrameBar
        public void Logout() throws WrongDataError {
        	try(Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
        		stat = conn.createStatement();
        		new FrameLogin(conn);
        	}catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
        
        //PanelBManage-------------------------
        public void startOfManage(ArrayList<String> infoDetails) throws WrongDataError {
        	try(Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
        		stat = conn.createStatement();
        		String query = "SELECT count(*) FROM information_schema.columns"
        		for(i = 0; i < )
        	}catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
        
        public void ManageUpdateDB(ArrayList<String> infoTitles, ArrayList<String> infoDetails) throws WrongDataError {
        	try(Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
        		stat = conn.createStatement();
        		for(int i = 0; i < infoTitles.size(); i++) {
        			String query = "UPDATE Bar SET " + infoTitles.get(i) + " = '" + infoDetails.get(i) + "' WHERE ";
        			stat.execute(query);
        		}
        	}catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
    }

    class WrongDataError extends Exception {
    	public WrongDataError(String Error) {
    		super(Error);
    	}
    }