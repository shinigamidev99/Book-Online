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
import model.Publisher;

/**
 *
 * @author Administrator
 */
public class PublisherDB {

    public List<Publisher> select() throws Exception {
        List<Publisher> list = new ArrayList<>();
        String sql = "SELECT [publication_id]\n"
                + "      ,[publication_name]\n"
                + "      ,[publication_address]\n"
                + "  FROM [BookOnline].[dbo].[tblPublishers]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new Publisher(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Publisher> selectBublisherById(String publicationId) throws Exception {
        List<Publisher> list = new ArrayList<>();
        String sql = "SELECT [publication_id]\n"
                + "      ,[publication_name]\n"
                + "      ,[publication_address]\n"
                + "  FROM [BookOnline].[dbo].[tblPublishers]\n"
                + "  WHERE publication_id = ?";
        Connection conn = new DBContext().getConnection(); // open db connection
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, publicationId);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            list.add(new Publisher(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int insert(Publisher publisher) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblPublishers]\n"
                + "           ([publication_id]\n"
                + "           ,[publication_name]\n"
                + "           ,[publication_address])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, publisher.getPublicationId());
        st.setString(2, publisher.getPublicationName());
        st.setString(3, publisher.getPublicationAddress());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int delete(String publicationId) throws Exception {
        int colum = 0;
        String sql = "DELETE FROM [dbo].[tblPublishers]\n"
                + "      WHERE publication_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, publicationId);
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public int update(Publisher publisher) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblPublishers]\n"
                + "   SET [publication_name] = ?\n"
                + "      ,[publication_address] = ?\n"
                + " WHERE publication_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, publisher.getPublicationName());
        st.setString(2, publisher.getPublicationAddress());
        st.setString(3, publisher.getPublicationId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
