package com.giserhub.servlet.idea;

import com.giserhub.dao.impl.IdeaImpl;
import com.giserhub.dao.inf.Ideadao;
import com.giserhub.entity.Idea;
import com.giserhub.tool.idea.JsonToIdea;
import com.giserhub.tool.system.CookieMade;
import com.giserhub.tool.system.Head;
import com.giserhub.tool.system.successorfail;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class insertPublicIdea extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Head.setheader(req,resp);
        try
        {
            boolean flag = false;
            PrintWriter out = resp.getWriter();
            String date = req.getParameter("date");
            JSONObject json = JSONObject.fromObject(date);
            int U_id = Integer.parseInt(CookieMade.readcookie(req,"id"));
            Idea idea ;
            idea = JsonToIdea.jsontoidea(json,U_id);
            Ideadao id = new IdeaImpl();
            flag = id.insertPublicIdea(idea);
            if (flag)
            {
                successorfail.success(out);
            }
            else
            {
                successorfail.fail(out);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}