package controller;

import config.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import model.Fiction;

@WebServlet(name = "editFiction", urlPatterns = {"/editFiction"})
public class EditFiction extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // รับค่า input จาก request parameters
            String fictionName = request.getParameter("fictionName");
            String fictionLanguage = request.getParameter("fictionLanguage");
            String[] fictionType = request.getParameterValues("fictionType");
            String story = request.getParameter("story");
            double fictionPrice = Double.parseDouble(request.getParameter("fictionPrice"));
            String id = request.getParameter("id");

            // รวมค่าของ fictionType เป็น string เดียว
            StringBuilder fictionTypeBuilder = new StringBuilder();
            for (int i = 0; i < fictionType.length; i++) {
                fictionTypeBuilder.append(fictionType[i]);
                if (i < fictionType.length - 1) {
                    fictionTypeBuilder.append(";");
                }
            }
            String fictionTypeString = fictionTypeBuilder.toString();

            // สร้าง Fiction object ใหม่และตั้งค่าด้วยข้อมูลที่ได้รับมา
            Fiction newFiction = new Fiction();
            newFiction.setId(id);
            newFiction.setFictionName(fictionName);
            newFiction.setFictionLanguage(fictionLanguage);
            newFiction.setFictionType(fictionType);
            newFiction.setStory(story);
            newFiction.setFictionPrice(fictionPrice);

            // เรียกใช้ฟังก์ชัน updateFiction จาก Product class
            FictionController product = new FictionController();
            boolean updateSuccessful = product.updateFiction(newFiction);

            // ตรวจสอบผลการอัปเดตและส่ง response กลับไป
            if (updateSuccessful) {
                System.out.println("Success to update Fiction");
            } else {
                response.getWriter().println("Failed to update Fiction.");
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("fiction", newFiction);
            RequestDispatcher rd = request.getRequestDispatcher("/updateFictionSuccess.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
