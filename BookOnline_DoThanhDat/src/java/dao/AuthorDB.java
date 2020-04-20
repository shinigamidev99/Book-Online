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
import model.Author;

/**
 *
 * @author Administrator
 */
public class AuthorDB {

    public List<Author> select() throws Exception {
        List<Author> list = new ArrayList<>();
        String sql = "SELECT [author_id]\n"
                + "      ,[author_name]\n"
                + "      ,[author_address]\n"
                + "  FROM [BookOnline].[dbo].[tblAuthor]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new Author(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Author> selectAuthorNameById(String authorId) throws Exception {
        List<Author> list = new ArrayList<>();
        String sql = "SELECT [author_id]\n"
                + "      ,[author_name]\n"
                + "      ,[author_address]\n"
                + "  FROM [BookOnline].[dbo].[tblAuthor]\n"
                + "  WHERE author_id = ?";
        Connection conn = new DBContext().getConnection(); // open db connection
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, authorId);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            list.add(new Author(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int insert(Author author) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblAuthor]\n"
                + "           ([author_id]\n"
                + "           ,[author_name]\n"
                + "           ,[author_address])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, author.getAuthorId());
        st.setString(2, author.getAuthorName());
        st.setString(3, author.getAuthorAddress());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int delete(String authorId) throws Exception {
        int colum = 0;
        String sql = "DELETE FROM [dbo].[tblAuthor]\n"
                + "      WHERE author_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, authorId);
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int update(Author author) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblAuthor]\n"
                + "   SET [author_name] = ?\n"
                + "      ,[author_address] = ?\n"
                + " WHERE [author_id] like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, author.getAuthorName());
        st.setString(2, author.getAuthorAddress());
        st.setString(3, author.getAuthorId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
