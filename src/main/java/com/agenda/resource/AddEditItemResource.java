package com.agenda.resource;

import com.agenda.service.ItemService;
import com.agenda.service.ItemServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.agenda.util.JSPUtil.resolvePath;

public class AddEditItemResource extends HttpServlet {

    private ItemService itemService = new ItemServiceImpl();

    public AddEditItemResource() {
    }

    public AddEditItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(resolvePath("add_edit_item"));
        dispatcher.forward(request, response);

        clearSessionItemAttribute(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("save") != null) {
            itemService.saveOrUpdateItem(itemService.getItemFromRequest(request));
        }

        response.sendRedirect("agenda");
    }

    private void clearSessionItemAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("item");
    }
}
