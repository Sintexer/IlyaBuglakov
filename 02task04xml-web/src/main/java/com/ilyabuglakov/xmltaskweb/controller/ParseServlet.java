package com.ilyabuglakov.xmltaskweb.controller;

import com.ilyabuglakov.xmltaskweb.controller.command.Command;
import com.ilyabuglakov.xmltaskweb.controller.command.DOMParseCommand;
import com.ilyabuglakov.xmltaskweb.exception.InvalidInpitXMLException;
import com.ilyabuglakov.xmltaskweb.exception.ParserException;
import com.ilyabuglakov.xmltaskweb.exception.SchemaException;
import com.ilyabuglakov.xmltaskweb.model.ParserType;
import com.ilyabuglakov.xmltaskweb.model.gem.Gem;
import com.ilyabuglakov.xmltaskweb.service.ParserTypeService;
import com.ilyabuglakov.xmltaskweb.service.PartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "parseServlet", urlPatterns = "/parse")
public class ParseServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger(ParseServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspPage = "parse.jsp";
        jspPage = "/view/" + jspPage;
        request.setAttribute("types", ParserTypeService.getParserTypeStringList());
        getServletContext().getRequestDispatcher(jspPage).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xml = PartService.toString(request.getPart("xml"));
        ParserType parserType = ParserType.valueOf(request.getParameter("parserType").toUpperCase());

        Command parseCommand = CommandController.getInstance().getCommand(parserType);
        try {
            Set<Gem> gems = parseCommand.execute(xml);
            request.setAttribute("gems", gems);
            String jspPage = "/view/" + "result.jsp";
            getServletContext().getRequestDispatcher(jspPage).forward(request, response);

        } catch (InvalidInpitXMLException e) {
            request.setAttribute("error", "Your XML file has an error, fix it or try another");
        } catch (ParserException e) {
            request.setAttribute("error", "XSD schema error");
        }
        request.setAttribute("types", ParserTypeService.getParserTypeStringList());
        String jspPage = "parse.jsp";
        jspPage = "/view/" + jspPage;
        getServletContext().getRequestDispatcher(jspPage).forward(request, response);
    }
}
