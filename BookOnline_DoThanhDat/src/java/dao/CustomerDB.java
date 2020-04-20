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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author Administrator
 */
public class CustomerDB {

    public List<Customer> select() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT [customer_id]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[zip_code]\n"
                + "      ,[gender]\n"
                + "  FROM [BookOnline].[dbo].[tblCustomers]";
        Connection conn = new DBContext().getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public Customer selectCustomerByUsername(String username) throws Exception {
        Customer list = null;
        String sql = "SELECT        tblCustomers.*\n"
                + "FROM            tblAccount INNER JOIN\n"
                + "                         tblUsers ON tblAccount.username = tblUsers.username INNER JOIN\n"
                + "                         tblCustomers ON tblUsers.customer_id = tblCustomers.customer_id\n"
                + "WHERE tblAccount.username like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, username);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            list = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
        }
        rs.close();
        conn.close();
        return list;
    }

    public List<Customer> selectByEmail(String email) throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT [customer_id]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[zip_code]\n"
                + "  FROM [BookOnline].[dbo].[tblCustomers]\n"
                + "  WHERE email like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
        }
        rs.close();
        conn.close();
        return list;
    }

    public String getCustomerIdFormat(String customerId) throws Exception {
        String result = customerId;
        List<Customer> list = select();
        for (int i = list.size() - 1; i >= 0; i--) {
            String[] cusSplit = list.get(i).getCustomerId().split("_");
            if (customerId.equalsIgnoreCase(cusSplit[0])) {
                result += "_" + (new DecimalFormat("000").format(Double.valueOf(cusSplit[1]) + 1));
                break;
            }
        }
        return result.toUpperCase();
    }

    public int insert(Customer customer) throws Exception {
        int colum = 0;
        String sql = "INSERT INTO [dbo].[tblCustomers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[gender])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, getCustomerIdFormat(customer.getCustomerId()));
        st.setString(2, customer.getFirstName());
        st.setString(3, customer.getLastName());
        st.setString(4, customer.getPhone());
        st.setString(5, customer.getEmail());
        st.setString(6, customer.getStreet());
        st.setString(7, customer.getCity());
        st.setBoolean(8, customer.isGender());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }

    public Customer selectNLastItem(int top, String customerId) throws Exception {
        Customer customer = null;
        String sql = "SELECT TOP (?)[customer_id]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[zip_code]\n"
                + "      ,[gender]\n"
                + "  FROM [BookOnline].[dbo].[tblCustomers]\n"
                + "WHERE customer_id like '%' + ? + '%'\n"
                + "ORDER BY [customer_id] desc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, top);
        pr.setString(2, customerId);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
        }
        rs.close();
        conn.close();
        return customer;
    }

    public Customer selectCustomerById(String customerId) throws Exception {
        Customer list = null;
        String sql = "SELECT [customer_id]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[zip_code]\n"
                + "      ,[gender]\n"
                + "  FROM [BookOnline].[dbo].[tblCustomers]\n"
                + "  WHERE customer_id like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, customerId);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            list = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
        }
        rs.close();
        conn.close();
        return list;
    }

    public int update(Customer customer) throws Exception {
        int colum = 0;
        String sql = "UPDATE [dbo].[tblCustomers]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[street] = ?\n"
                + "      ,[city] = ?\n"
                + "      ,[zip_code] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE [customer_id] like ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, customer.getFirstName());
        st.setString(2, customer.getLastName());
        st.setString(3, customer.getPhone());
        st.setString(4, customer.getEmail());
        st.setString(5, customer.getStreet());
        st.setString(6, customer.getCity());
        st.setString(7, customer.getZip_code());
        st.setBoolean(8, customer.isGender());
        st.setString(9, customer.getCustomerId());
        colum = st.executeUpdate();
        conn.close();
        return colum;
    }
}
