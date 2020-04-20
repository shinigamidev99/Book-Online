/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import context.DBContext;
import java.io.Serializable;
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
public class PagingBean implements Serializable {

    private int page, size;
    private String categoryId;
    private String publicationId;
    private String authorId;
    private String title;

    public PagingBean() {
        size = 12;
        page = 1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // return the total pages
    public int getPages() throws Exception {
        String sql = "SELECT COUNT(*) FROM tblbooks\n"
                + "WHERE category_id LIKE ? AND publication_id LIKE ? AND author_id LIKE ? AND title like '%' + ? + '%'";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, (getCategoryId() == null || getCategoryId().equals("-1")) ? "%%" : getCategoryId());
        ps.setString(2, getPublicationId() == null ? "%%" : getPublicationId());
        ps.setString(3, getAuthorId() == null ? "%%" : getAuthorId());
        ps.setString(4, getTitle() == null ? "%%" : getTitle());
        ResultSet rs = ps.executeQuery();
        int rows = 0;
        if (rs.next()) {
            rows = rs.getInt(1);
        }
        rs.close();
        conn.close();
        return (int) Math.ceil(rows * 1.0 / size);
    }

    public List<Book> getBooks() throws Exception {
        int from = (page - 1) * size + 1;
        int to = page * size;
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
                + "  FROM ( \n"
                + "	SELECT *, ROW_NUMBER() OVER (ORDER BY [post of date] desc, [numerical order import] desc) AS row\n"
                + "	FROM [BookOnline].[dbo].[tblBooks] a"
                + "     WHERE category_id LIKE ? AND publication_id LIKE ? AND author_id LIKE ? AND title like '%' + ? + '%') a\n"
                + "WHERE a.row >= ? AND a.row <= ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, (getCategoryId() == null || getCategoryId().equals("-1")) ? "%%" : getCategoryId());
        st.setString(2, getPublicationId() == null ? "%%" : getPublicationId());
        st.setString(3, getAuthorId() == null ? "%%" : getAuthorId());
        st.setString(4, getTitle() == null ? "%%" : getTitle());
        st.setInt(5, from);
        st.setInt(6, to);

        ResultSet rs = st.executeQuery();
        List<Book> p = new ArrayList<>();
        while (rs.next()) {
            p.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
        }

        rs.close();
        conn.close();
        return p;
    }

    public List<Book> getSales() throws Exception {
        int from = (page - 1) * size + 1;
        int to = page * size;
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
                + "  FROM ( \n"
                + "	SELECT *, ROW_NUMBER() OVER (ORDER BY [post of date] desc, [numerical order import] desc) AS row\n"
                + "	FROM (	SELECT tblBooks.*\n"
                + "			FROM tblBookDiscount INNER JOIN\n"
                + "                 tblBooks ON tblBookDiscount.book_id = tblBooks.book_id"
                + "             WHERE GETDATE() BETWEEN [start date] AND [end date]) a) a\n"
                + "WHERE a.row >= ? and a.row <= ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, from);
        st.setInt(2, to);

        ResultSet rs = st.executeQuery();
        List<Book> p = new ArrayList<>();
        while (rs.next()) {
            p.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
        }

        rs.close();
        conn.close();
        return p;
    }

    public int getPagesSale() throws Exception {
        String sql = "SELECT COUNT(*) FROM tblbookdiscount"
                + "   WHERE GETDATE() BETWEEN [start date] AND [end date]";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int rows = 0;
        if (rs.next()) {
            rows = rs.getInt(1);
        }
        rs.close();
        conn.close();
        return (int) Math.ceil(rows * 1.0 / size);
    }
}
