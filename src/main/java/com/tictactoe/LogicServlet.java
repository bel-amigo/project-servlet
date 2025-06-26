package com.tictactoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Field field = extractField(session);
        int selectedIndex = getSelectedIndex(req);
        if (!field.getFieldData().get(selectedIndex).equals(Sign.EMPTY)) {
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
            return;

        }
        field.getField().put(selectedIndex, Sign.CROSS);
        if(checkWin(resp, session, field)){
            return;
        }
        int AIMoveIndex = field.getEmptyFieldIndex();
        if (AIMoveIndex >= 0) {
            field.getField().put(AIMoveIndex, Sign.NOUGHT);
            if (checkWin(resp, session, field)) {
                return;
            }
        }
        session.setAttribute("field", field);
        session.setAttribute("data", field.getFieldData());
        resp.sendRedirect("/index.jsp");

    }


    private int getSelectedIndex(HttpServletRequest request) {
        String parameter = request.getParameter("click");
        return Integer.parseInt(parameter);
    }

    public Field extractField(HttpSession currentSession) {
        Object a = currentSession.getAttribute("field");

        if (a instanceof Field) {
            return (Field) a;
        } else {
            throw new RuntimeException("Произошла ошибка при приведении нажатия к типу Field");
        }
    }

    private boolean checkWin(HttpServletResponse response, HttpSession currentSession, Field field) throws IOException {
        Sign winner = field.checkWin();
        if(Sign.CROSS.equals(winner) || Sign.NOUGHT.equals(winner)){
            currentSession.setAttribute("winner", winner);
            List<Sign> data = field.getFieldData();
            currentSession.setAttribute("data", data);
            response.sendRedirect("/index.jsp");
            return true;
        }
        return false;
    }
}
