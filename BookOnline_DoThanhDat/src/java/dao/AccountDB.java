/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author Administrator
 */
public class AccountDB {

    public List<Account> select() throws Exception {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[role]\n"
                + "  FROM [BookOnline].[dbo].[tblAccount]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Account> selectByEmail(String email) throws Exception {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT        tblAccount.*\n"
                + "FROM            tblAccount INNER JOIN\n"
                + "                         tblUsers ON tblAccount.username = tblUsers.username INNER JOIN\n"
                + "                         tblCustomers ON tblUsers.customer_id = tblCustomers.customer_id\n"
                + "WHERE tblCustomers.email like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int insert(Account account) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblAccount]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[role])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, account.getUsername());
        st.setString(2, account.getPassword());
        st.setString(3, account.getRole());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int updatePassword(Account account) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblAccount]\n"
                + "   SET [password] = ?\n"
                + " WHERE [username] like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, account.getPassword());
        st.setString(2, account.getUsername());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
