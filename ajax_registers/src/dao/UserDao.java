package dao;

import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection con;
    private PreparedStatement pst;

    public UserDao (Connection con){
        this.con=con;
    }

    public User get(String userName){
        User user=null;
        String sql="select * from t_user where userName=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,userName);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                user=new User();
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setChrName(rs.getString("chrName"));
                user.setEmail(rs.getString("email"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByEmail(String email){
        User user=null;
        String sql="select * from t_user where email=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,email);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                user = new User();
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setChrName(rs.getString("chrName"));
                user.setEmail(rs.getString("email"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insert(User user){
        String sql = "insert into t_users(userName,password,chrName,email,province,city) values(?,?,?,?,?,?) ";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getChrName());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getProvince());
            pst.setString(6,user.getCity());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
