package com.agenda.resource;

import com.agenda.service.ItemService;
import com.agenda.service.ItemServiceImpl;
import com.agenda.util.JSPUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.agenda.util.JSPUtil.resolvePath;

public class AgendaResource extends HttpServlet {


    private ItemService itemService = new ItemServiceImpl();

    public AgendaResource() {
    }

    public AgendaResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("items", itemService.getLastItems());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(resolvePath("agenda"));
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("item_id"));

        if (request.getParameter("delete") != null) {
            itemService.deleteItem(id);
            response.sendRedirect("agenda");
        }

        else if (request.getParameter("edit") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("item", itemService.getItem(id));
            response.sendRedirect("add-item");
        }

    }

}