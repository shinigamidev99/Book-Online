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
import model.Category;

/**
 *
 * @author Administrator
 */
public class CategoryDB {

    public List<Category> select() throws Exception {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT [category_id]\n"
                + "      ,[category_name]\n"
                + "  FROM [BookOnline].[dbo].[tblCategory]";
        Connection conn = new DBContext().getConnection(); 
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new Category(rs.getString(1), rs.getString(2)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Category> selectByCategoryId(String categoryId) throws Exception {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT [category_id]\n"
                + "      ,[category_name]\n"
                + "  FROM [BookOnline].[dbo].[tblCategory]\n"
                + "  WHERE category_id = ?";
        Connection conn = new DBContext().getConnection(); // open db connection
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, categoryId);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            list.add(new Category(rs.getString(1), rs.getString(2)));
        }
        rs.close();
        conn.close();
        return list;
    }
}
