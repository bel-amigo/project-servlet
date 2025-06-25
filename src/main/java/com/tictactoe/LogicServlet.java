package com.tictactoe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Field field = extractField(session);
        int selectedIndex = getSelectedIndex(req);
        field.getField().put(selectedIndex, Sign.CROSS);
        session.setAttribute("field", field);
        session.setAttribute("data", field.getFieldData());
        resp.sendRedirect("/index.jsp");
    }


    private int getSelectedIndex(HttpServletRequest request) {
        String parameter = request.getParameter("click");
        return Integer.parseInt(parameter);
    }

    public Field extractField(HttpSession currentSession){
        Object a = currentSession.getAttribute("field");

        if(a instanceof Field){
            return (Field) a;
        }
        else{
            throw new RuntimeException("Произошла ошибка при приведении нажатия к типу Field");
        }


    }
}
