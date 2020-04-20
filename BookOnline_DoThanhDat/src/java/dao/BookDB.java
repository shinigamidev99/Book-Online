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
import model.Book;

/**
 *
 * @author Administrator
 */
public class BookDB {

    public List<Book> select() throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT [book_id]\n"
                + "      ,[category_id]\n"
                + "      ,[publication_id]\n"
                + "      ,[title]\n"
                + "      ,[publication date]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[notes]\n"
                + "      ,[image]\n"
                + "      ,[post of date]\n"
                + "      ,[author_id]\n"
                + "  FROM [BookOnline].[dbo].[tblBooks]\n"
                + "  ORDER BY [post of date] desc, [numerical order import] desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Book> selectTop(int top) throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT TOP (?) [book_id]\n"
                + "      ,[category_id]\n"
                + "      ,[publication_id]\n"
                + "      ,[title]\n"
                + "      ,[publication date]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[notes]\n"
                + "      ,[image]\n"
                + "      ,[post of date]\n"
                + "      ,[author_id]\n"
                + "  FROM [BookOnline].[dbo].[tblBooks]\n"
                + "  ORDER BY [post of date] desc, [numerical order import] desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, top);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Book> selectTopByCategoryId(int top, String categoryId) throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT TOP (?) [book_id]\n"
                + "      ,[category_id]\n"
                + "      ,[publication_id]\n"
                + "      ,[title]\n"
                + "      ,[publication date]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[notes]\n"
                + "      ,[image]\n"
                + "      ,[post of date]\n"
                + "      ,[author_id]\n"
                + "  FROM [BookOnline].[dbo].[tblBooks]\n"
                + "  WHERE category_id = ?"
                + "  ORDER BY [post of date] desc, [numerical order import] desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, top);
        st.setString(2, categoryId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int delete(String bookId) throws Exception {
        int colum = 0;
        String sql = "DELETE FROM [dbo].[tblBooks]\n"
                + "      WHERE book_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, bookId);
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int update(Book book) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblBooks]\n"
                + "   SET [category_id] = ?\n"
                + "      ,[publication_id] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[publication date] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[notes] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[post of date] = GETDATE()\n"
                + "      ,[author_id] = ?\n"
                + "      ,[numerical order import] = (SELECT top 1 [numerical order import] + 1\n"
                + "FROM [BookOnline].[dbo].[tblBooks]\n"
                + "WHERE  [post of date] = CONVERT(varchar, GETDATE(), 23)\n"
                + "ORDER BY [numerical order import] desc)\n"
                + " WHERE book_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, book.getCategoryId());
        st.setString(2, book.getPublicationId());
        st.setString(3, book.getTitle());
        st.setString(4, book.getPublicationDate());
        st.setString(5, book.getPrice());
        st.setString(6, book.getQuantity());
        st.setString(7, book.getNotes());
        st.setString(8, book.getImage());
        st.setString(9, book.getAuthorId());
        st.setString(10, book.getBookId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public Book selectBookByBookId(String bookId) throws Exception {
        Book list = new Book();
        String sql = "SELECT [book_id]\n"
                + "      ,[category_id]\n"
                + "      ,[publication_id]\n"
                + "      ,[title]\n"
                + "      ,[publication date]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[notes]\n"
                + "      ,[image]\n"
                + "      ,[post of date]\n"
                + "      ,[author_id]\n"
                + "      ,[numerical order import]\n"
                + "  FROM [BookOnline].[dbo].[tblBooks]\n"
                + "  WHERE book_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, bookId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int insert(Book book) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblBooks]\n"
                + "           ([book_id]\n"
                + "           ,[category_id]\n"
                + "           ,[publication_id]\n"
                + "           ,[title]\n"
                + "           ,[publication date]\n"
                + "           ,[price]\n"
                + "           ,[quantity]\n"
                + "           ,[notes]\n"
                + "           ,[image]\n"
                + "           ,[post of date]\n"
                + "           ,[author_id]\n"
                + "           ,[numerical order import])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,?\n"
                + "           ,(SELECT top 1 [numerical order import] + 1\n"
                + "FROM [BookOnline].[dbo].[tblBooks]\n"
                + "WHERE  [post of date] = CONVERT(varchar, GETDATE(), 23)\n"
                + "ORDER BY [numerical order import] desc))";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, book.getBookId());
        st.setString(2, book.getCategoryId());
        st.setString(3, book.getPublicationId());
        st.setString(4, book.getTitle());
        st.setString(5, book.getPublicationDate());
        st.setString(6, book.getPrice());
        st.setString(7, book.getQuantity());
        st.setString(8, book.getNotes());
        st.setString(9, book.getImage());
        st.setString(10, book.getAuthorId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int updateQuantity(Book book) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblBooks]\n"
                + "   SET [quantity] = ?\n"
                + "FROM [BookOnline].[dbo].[tblBooks]\n"
                + " WHERE book_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, book.getQuantity());
        st.setString(2, book.getBookId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
