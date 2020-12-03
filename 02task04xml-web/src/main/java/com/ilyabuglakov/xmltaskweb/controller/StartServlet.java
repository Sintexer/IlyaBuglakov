package com.ilyabuglakov.xmltaskweb.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartServlet", urlPatterns = "/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String jspPage="start.jsp";
        jspPage = "/view/" + jspPage;//Что это такое:? localhost/start/view?
        getServletContext().getRequestDispatcher(jspPage).forward(request, response);
    }

}