/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Fiction;
import config.*;

import java.util.*;

@WebServlet(name = "AddNewFictionServlet", urlPatterns = {"/AddNewFictionServlet"})
public class AddNewFictionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // จัดการ request ที่ส่งเข้ามาด้วย GET method
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // จัดการ request ที่ส่งเข้ามาด้วย POST method
        response.setContentType("text/html;charset=UTF-8");

        // รับค่าจากฟอร์ม
        String fictionName = request.getParameter("fictionName");
        String fictionLanguage = request.getParameter("fictionLanguage");
        String[] fictionType = request.getParameterValues("fictionType");
        String story = request.getParameter("story");
        double fictionPrice = Double.parseDouble(request.getParameter("fictionPrice"));
        
        // สร้างอ็อบเจกต์ Fiction จากข้อมูลที่รับมา
        Fiction newFiction = new Fiction();
        newFiction.setFictionName(fictionName);
        newFiction.setFictionLanguage(fictionLanguage);
        newFiction.setFictionType(fictionType);
        newFiction.setStory(story);
        newFiction.setFictionPrice(fictionPrice);
        
        // เก็บ Fiction ไว้ใน Database หรือ Session ตามที่ต้องการ
        FictionController product = new FictionController();
        if (!product.insertFiction(newFiction)) {
            System.out.println(">>> AddNewFictionMySQL ERROR");
        }

        // forward ไปยัง addNewFictionSuccess.jsp
        HttpSession session = request.getSession();
        session.setAttribute("fiction", newFiction);
        RequestDispatcher rd = request.getRequestDispatcher("/addNewFictionSuccess.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

