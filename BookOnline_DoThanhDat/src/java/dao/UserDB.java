/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;

/**
 *
 * @author Administrator
 */
public class UserDB {
    public int insert(User user) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblUsers]\n"
                + "           ([username]\n"
                + "           ,[customer_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, user.getUsername());
        st.setString(2, new CustomerDB().selectNLastItem(1, user.getCustomerId()).getCustomerId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
