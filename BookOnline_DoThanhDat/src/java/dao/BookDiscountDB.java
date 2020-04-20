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
import model.BookDiscount;

/**
 *
 * @author Administrator
 */
public class BookDiscountDB {

    public List<BookDiscount> select() throws Exception {
        List<BookDiscount> list = new ArrayList<>();
        String sql = "SELECT [book_id]\n"
                + "      ,[discount]\n"
                + "      ,[start date]\n"
                + "      ,[end date]\n"
                + "  FROM [BookOnline].[dbo].[tblBookDiscount]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new BookDiscount(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<BookDiscount> selectBookDiscount() throws Exception {
        List<BookDiscount> list = new ArrayList<>();
        String sql = "SELECT [book_id]\n"
                + "      ,[discount]\n"
                + "      ,[start date]\n"
                + "      ,[end date]\n"
                + "  FROM [BookOnline].[dbo].[tblBookDiscount]\n"
                + "  WHERE GETDATE() BETWEEN [start date] AND [end date]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new BookDiscount(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public BookDiscount selectDiscountByBookId(String bookId) throws Exception {
        BookDiscount discount = null;
        String sql = "SELECT [book_id]\n"
                + "      ,[discount]\n"
                + "      ,[start date]\n"
                + "      ,[end date]\n"
                + "  FROM [BookOnline].[dbo].[tblBookDiscount]\n"
                + "  WHERE book_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, bookId);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            discount = new BookDiscount(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4));
        }
        rs.close();
        conn.close();
        return discount;
    }

    public int update(BookDiscount discount) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblBookDiscount]\n"
                + "   SET [discount] = ?\n"
                + "      ,[start date] = ?\n"
                + "      ,[end date] = ?\n"
                + " WHERE [book_id] = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setDouble(1, discount.getDiscount());
        st.setString(2, discount.getStartDate());
        st.setString(3, discount.getEndDate());
        st.setString(4, discount.getBookId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int insert(BookDiscount discount) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblBookDiscount]\n"
                + "           ([book_id]\n"
                + "           ,[discount]\n"
                + "           ,[start date]\n"
                + "           ,[end date])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, discount.getBookId());
        st.setDouble(2, discount.getDiscount());
        st.setString(3, discount.getStartDate());
        st.setString(4, discount.getEndDate());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int delete(String bookId) throws Exception {
        int colum = 0;
        String sql = "DELETE FROM [dbo].[tblBookDiscount]\n"
                + "      WHERE book_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, bookId);
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
