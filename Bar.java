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
        private String barName;
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
    			setBarName(username);
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
        public void setBarName(String Username) {
        	barName = Username;
        }
        
        public String getBarName() {
        	return barName;
        }
        
        public void startOfManage(ArrayList<String> infoTitles, ArrayList<String> infoDetails) throws WrongDataError {
        	try(Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
        		stat = conn.createStatement();
        		
        		for(int i = 0; i < infoTitles.size(); i++) {
        			String query = "SELECT " + infoTitles.get(i) + " FROM Bar WHERE BarName = '" + getBarName() + "'";
        			ResultSet rs = stat.executeQuery(query);
        			rs.next();
        			infoDetails.add(rs.getString(infoTitles.get(i)));
        		}
        	}catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
        
        public void ManageUpdateDB(ArrayList<String> infoTitles, ArrayList<String> infoDetails) throws WrongDataError {
        	try(Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
        		stat = conn.createStatement();
        		for(int i = 0; i < infoTitles.size(); i++) {
        			String query = "UPDATE Bar SET " + infoTitles.get(i) + " = '" + infoDetails.get(i) + "' WHERE BarName = '" + getBarName() + "'";
        			stat.execute(query);
        		}
        	}catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
        
        //PanelBAnalysis----------------------
        public String showResultSet(String sort) throws WrongDataError{
        	try(Connection conn = DriverManager.getConnection(url, DBUsername, DBPassword)) {
        		stat = conn.createStatement();
        		switch(sort) {
        			case "Sales figures":
        				String query = "SELECT Order.Item, sum(Order.total_Price) AS 'Sales figures', sum(Order.Amount) AS 'Sales volume', "
        						+ "FROM Order JOIN Menu "
        						+ "ON Order.BarName = Menu.BarName "
        						//+ "AND Order.Item = Menu.Cocktail";
        						+ "WHERE ";
        				stat.executeQuery(query);
        				//getString
        			case "Sort2":
        				
        			case "Sort3":
        				
        			
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