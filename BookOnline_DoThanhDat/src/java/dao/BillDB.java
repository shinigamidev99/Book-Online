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
import model.Bills;

/**
 *
 * @author Administrator
 */
public class BillDB {

    public List<Bills> select() throws Exception {
        List<Bills> list = new ArrayList<>();
        String sql = "SELECT [bill_id]\n"
                + "      ,[customer_id]\n"
                + "      ,[date created]\n"
                + "      ,[address]\n"
                + "      ,[total]\n"
                + "      ,[status]\n"
                + "  FROM [BookOnline].[dbo].[tblBills]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new Bills(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int insert(Bills bill) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblBills]\n"
                + "           ([bill_id]\n"
                + "           ,[customer_id]\n"
                + "           ,[date created]\n"
                + "           ,[address]\n"
                + "           ,[total]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, selectNextBillId());
        st.setString(2, bill.getCustomerId());
        st.setString(3, bill.getAddress());
        st.setDouble(4, bill.getTotal());
        st.setInt(5, bill.getStatus());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int selectNextBillId() throws Exception {
        int billId = 1;
        String sql = "SELECT TOP (1) [bill_id] + 1\n"
                + "  FROM [BookOnline].[dbo].[tblBills]\n"
                + "ORDER BY [bill_id] desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            billId = rs.getInt(1);
        }
        conn.close();
        return billId;
    }

    public int updateTotal(Bills bill) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblBills]\n"
                + "   SET [total] = ?\n"
                + " WHERE bill_id = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setDouble(1, bill.getTotal());
        st.setInt(2, bill.getBillId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public Bills selectBillByBillId(int billId) throws Exception {
        Bills list = new Bills();
        String sql = "SELECT [bill_id]\n"
                + "      ,[customer_id]\n"
                + "      ,[date created]\n"
                + "      ,[address]\n"
                + "      ,[total]\n"
                + "      ,[status]\n"
                + "  FROM [BookOnline].[dbo].[tblBills]\n"
                + "  WHERE bill_id = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setDouble(1, billId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list = new Bills(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int updateStatus(Bills bill) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblBills]\n"
                + "   SET [status] = ?\n"
                + " WHERE bill_id = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setDouble(1, bill.getStatus());
        st.setInt(2, bill.getBillId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
