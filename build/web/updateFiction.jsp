<%-- 
    Document   : updateFood
    Created on : Jul 18, 2024, 12:04:00 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Fiction" %>
<!DOCTYPE html>
<html>
    <body>
        <% Fiction fiction = (Fiction) session.getAttribute("fiction");%>
        <p>แก้ไขข้อมูลนิยาย</p>
        <form action="editFiction" method="post">
            <input type="hidden" name="id" value="${fiction.id}"><br/>
            ชื่อนิยาย: <input type="text" name="fictionName" value="${fiction.fictionName}"><br/>
            ภาษา :
            <select id="fictionLanguage" name="fictionLanguage">
                <% String[] fictionLanguage = {"จีน", "ญี่ปุ่น", "ไทย", "เกาหลี"};%>

                <% for (int i = 0; fictionLanguage.length > i; i++) {%>
                <option value="<%=fictionLanguage[i]%>" <% if (fiction != null && fiction.getFictionLanguage() != null && fiction.getFictionLanguage().equals(fictionLanguage[i])) { %>selected<% }%>>
                    <%= fictionLanguage[i]%>
                </option>
                <%   } %>

            </select>
            </br>
            แนวนิยาย: <br/>
            <%
                String[] fictionType = {"แอคชัน", "แฟนตาซี", "ตลก", "ดราม่า", "โรแมนติก", "วิทยาศาสตร์", "วาย", "ระทึกขวัญ"};
                String[] story = {"จบแล้ว", "ยังไม่จบ"};

                String[] selectedFictionType = fiction.getFictionType(); 

                for (int i = 0; i < fictionType.length; i++) {
                    boolean isChecked = false;
                    // ตรวจสอบว่า fictionType[i] อยู่ใน selectedFictionType หรือไม่
                    for (String selected : selectedFictionType) {
                        if (fictionType[i].equals(selected)) {
                            isChecked = true;
                            break;
                        }
                    }
            %>
            <input type="checkbox" name="fictionType" value="<%= fictionType[i]%>" <%= isChecked ? "checked" : ""%>/> <%= fictionType[i]%><br/>
            <%
                }
            %>


            
            สตอรี่: <br/> 
            <% for (int i = 0; story.length > i; i++) {%>
            <input type="radio" name="story" value="<%= story[i]%>"  <%= fiction.getStory().equals(story[i]) ? "checked" : ""%>  /> <%= story[i]%>
            <%   }%>
            <br/>
            ราคา: <br/>
            <input type="text" name="fictionPrice" value="<%= fiction.getFictionPrice()%>"/>

            <br/><br/>
            <button type="submit" value="ตกลง">ตกลง<button/>
        </form>
    </body>
</html>
