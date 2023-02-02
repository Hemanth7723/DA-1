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

public class InsertServlet extends HttpServlet {
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
//            String select = request.getParameter("select");
            String url = "jdbc:mysql://localhost/worker";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
            
            request.getRequestDispatcher("link.html").include(request, response);
            
            
            Statement s1 = conn.createStatement();
            s1.execute("drop table if exists worker;");
            
            Statement s2 = conn.createStatement();
            s2.execute("create table worker(id int primary key,name varchar(50),age int,salary int,dept varchar(50));");
            
            Statement s3 = conn.createStatement();
            
            Statement sx = conn.createStatement();
            String insert = "insert into worker values('"+eid+"','"+name+"','"+age+"','"+salary+"','"+dept+"');"; 
            sx.execute(insert);
            
            s3.executeQuery("desc worker;");
            ResultSet rs = s3.getResultSet();
            
            while(rs.next()){
                out.println(rs.getString("Field"));
            }
            
            Statement s4 = conn.createStatement();
            s4.execute("insert into worker values(1,'DHARSHINI',18,30000,'SWEEPERS');");
            s4.execute("insert into worker values(2,'DEEPAK',20,35000,'CLEANERS');");
            s4.execute("insert into worker values(3,'SAI',21,20000,'FACULTY');");
            s4.execute("insert into worker values(4,'RAMESH',22,33000,'TECHINICIAN');");
            s4.execute("insert into worker values(5,'SURESH',25,30000,'SECURITY');");
            s4.execute("insert into worker values(6,'HARI',23,30000,'FACULTY');");
            s4.execute("insert into worker values(7,'VIKRAM',19,30000,'STAFF');");
            s4.execute("insert into worker values(8,'NATARAJ',18,30000,'SWEEPERS');");
            s4.execute("insert into worker values(9,'SIVA',22,30000,'TECHINICIAN');");
            s4.execute("insert into worker values(10,'JOHN0',25,30000,'SECURITY');");
            
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