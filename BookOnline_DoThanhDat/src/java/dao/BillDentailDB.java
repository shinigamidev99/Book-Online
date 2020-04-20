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
import model.BillDentail;

/**
 *
 * @author Administrator
 */
public class BillDentailDB {

    public List<BillDentail> select() throws Exception {
        List<BillDentail> list = new ArrayList<>();
        String sql = "SELECT [bill_id]\n"
                + "      ,[item_id]\n"
                + "      ,[book_id]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "  FROM [BookOnline].[dbo].[tblBillDentail]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new BillDentail(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int insert(BillDentail billDentail) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblBillDentail]\n"
                + "           ([bill_id]\n"
                + "           ,[item_id]\n"
                + "           ,[book_id]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, new BillDB().selectNextBillId() - 1);
        st.setInt(2, billDentail.getItemId());
        st.setString(3, billDentail.getBookId());
        st.setInt(4, billDentail.getQuantity());
        st.setDouble(5, billDentail.getPrice());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int delete(int itemId, int billId) throws Exception {
        int colum = 0;
        String sql = "DELETE FROM [dbo].[tblBillDentail]\n"
                + "      WHERE item_id = ? AND bill_id = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, itemId);
        st.setInt(2, billId);
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public BillDentail selectBillDentailByBillIdAndItemId(BillDentail billDentail) throws Exception {
        BillDentail list = new BillDentail();
        String sql = "SELECT [bill_id]\n"
                + "      ,[item_id]\n"
                + "      ,[book_id]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "  FROM [BookOnline].[dbo].[tblBillDentail]\n"
                + "  WHERE item_id = ? AND bill_id = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, billDentail.getItemId());
        st.setInt(2, billDentail.getBillId());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list = new BillDentail(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<BillDentail> selectBillDentailByBillId(int billId) throws Exception {
        List<BillDentail> list = new ArrayList<>();
        String sql = "SELECT [bill_id]\n"
                + "      ,[item_id]\n"
                + "      ,[book_id]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "  FROM [BookOnline].[dbo].[tblBillDentail]\n"
                + "  WHERE bill_id = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, billId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new BillDentail(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
        }
        rs.close();
        conn.close();
        return list;
    }
}
