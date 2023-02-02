/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        try {
            String userName = "root";
            String password = "";
            int eid = Integer.parseInt(request.getParameter("eid"));
            String name = request.getParameter("Ename");
            int age = Integer.parseInt( request.getParameter("age"));
            int salary = Integer.parseInt( request.getParameter("salary"));
            String dept = request.getParameter("dept");
            String select = request.getParameter("select");
            String url = "jdbc:mysql://localhost/worker";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
            
            request.getRequestDispatcher("link.html").include(request, response);
            
            
           Statement s3 = conn.createStatement();
            
                        
            s3.executeQuery("desc worker;");
            ResultSet rs = s3.getResultSet();
            
            while(rs.next()){
                out.println(rs.getString("Field"));
            }
         
            Statement sx = conn.createStatement();
            String insert = "update worker set name='"+name+"',salary='"+salary+"' where id='"+eid+"';"; 
            sx.executeUpdate(insert);
            
            
            Statement s5 = conn.createStatement();
            s5.execute("select * from worker;");
            ResultSet rs1 = s5.getResultSet();
            
            while(rs1.next()){
                out.println("<br>"+rs1.getString("id"));
                out.println(rs1.getString("name"));
                out.println(rs1.getString("age"));
                out.println(rs1.getString("salary"));
                out.println(rs1.getString("dept"));
            }  
        } catch (Exception e) {
            out.println(e.toString());
        }
        finally {
            out.close();
            if (conn != null) {
                try {
                    conn.close();
                    out.println("Database connection terminated");
                } catch (Exception e) {
                }
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}