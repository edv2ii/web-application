<%-- 
    Document   : updateFictionSuccess
    Created on : Aug 21, 2024, 11:59:57 PM
    Author     : armwo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h2>แก้ไขนิยายเรียบร้อย</h2>
    
    <c:if test="${not empty Fiction}">
        
        <p><strong>ชื่อนิยาย:</strong> ${fiction.fictionName}</p>
        <p><strong>ภาษา:</strong> ${fiction.fictionLanguage}</p>
       
<% 
    String[] fictionType = request.getParameterValues("fictionType");
    if (fictionType != null) {
    out.print("<strong>แนวนิยาย: </strong>");
        for (int i = 0; i < fictionType.length; i++) {
            out.print(fictionType[i]);
            if (i < fictionType.length - 1) {
                out.print(", ");
            }
        }
    } else {
        out.print("No fictionType specified.");
    }
%>


        <p><strong>เนื้อเรื่อง:</strong> ${fiction.story}</p>
        <p><strong>ราคา:</strong> ${fiction.fictionPrice}</p>
    </c:if>

    <a type="submit" href="addNewFiction.html" >เพิ่มนิยาย</a> 
    </body>
</html>
