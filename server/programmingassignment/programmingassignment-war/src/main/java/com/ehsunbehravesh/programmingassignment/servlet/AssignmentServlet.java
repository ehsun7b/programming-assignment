package com.ehsunbehravesh.programmingassignment.servlet;

import com.ehsunbehravesh.programmingassignment.dao.AssignmentDAO;
import com.ehsunbehravesh.programmingassignment.entity.Assignment;
import com.ehsunbehravesh.programmingassignment.response.AssignmentResponse;
import com.ehsunbehravesh.programmingassignment.response.InsertResponse;
import com.ehsunbehravesh.programmingassignment.response.ServiceResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ehsun Behravesh
 */
public class AssignmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        InsertResponse response = new InsertResponse();

        try {
            String description = req.getParameter("description");
            String deadline = req.getParameter("deadline");
            String useragent = req.getHeader("User-Agent");
            String ip = req.getRemoteAddr();
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String country = req.getParameter("country");
            String city = req.getParameter("city");
            String password = req.getParameter("password");

            Assignment assignment = new Assignment();

            assignment.setCategory(category);
            assignment.setCity(city);
            assignment.setCountry(country);
            assignment.setDeadline(deadline);
            assignment.setDescription(description);
            assignment.setIP(ip);
            assignment.setPassword(password);
            assignment.setStatus(status);
            assignment.setTime(new Date().toString());
            assignment.setTimestamp(new Date().getTime());
            assignment.setUserAgent(useragent);

            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

            AssignmentDAO dao = new AssignmentDAO(ds);

            Entity entity = dao.insert(assignment);
            long id = entity.getKey().getId();

            response.setId(id);
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setErrorMessage(ex.getMessage());
        }

        String json = new Gson().toJson(response);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ServiceResponse response = new ServiceResponse();

        try {
            String strId = req.getParameter("id");            
            String description = req.getParameter("description");
            String deadline = req.getParameter("deadline");
            String useragent = req.getHeader("User-Agent");
            String ip = req.getRemoteAddr();
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String country = req.getParameter("country");
            String city = req.getParameter("city");
            String password = req.getParameter("password");

            Assignment assignment = new Assignment();

            assignment.setId(Long.parseLong(strId));
            assignment.setCategory(category);
            assignment.setCity(city);
            assignment.setCountry(country);
            assignment.setDeadline(deadline);
            assignment.setDescription(description);
            assignment.setIP(ip);
            assignment.setPassword(password);
            assignment.setStatus(status);            
            assignment.setUserAgent(useragent);

            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

            AssignmentDAO dao = new AssignmentDAO(ds);

            dao.update(assignment);

            response.setSuccess(true);
        } catch (EntityNotFoundException ex) {
            response.setSuccess(false);
            response.setErrorMessage(ex.getMessage());
        }

        String json = new Gson().toJson(response);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ServiceResponse response = new ServiceResponse();

        try {
            String strId = req.getParameter("id");
            Long id = Long.parseLong(strId);

            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

            AssignmentDAO dao = new AssignmentDAO(ds);

            dao.delete(id);

            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setErrorMessage(ex.getMessage());
        }

        String json = new Gson().toJson(response);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        AssignmentResponse response = new AssignmentResponse();

        try {
            String strId = req.getParameter("id");
            String strTop = req.getParameter("top");

            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
            AssignmentDAO dao = new AssignmentDAO(ds);

            if (strId != null) {
                Long id = Long.parseLong(strId);
                Assignment assignment = dao.read(id);
                response.setAssignment(assignment);
            } else {
                if (strTop == null) {
                    strTop = "1000";
                }
                
                int top = Integer.parseInt(strTop);
                List<Assignment> assignments = dao.readtop(top);
                response.setAssignments(assignments);
            }

            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setErrorMessage(ex.getMessage());
        }

        String json = new Gson().toJson(response);
        PrintWriter writer = resp.getWriter();
        writer.write(json);
        writer.flush();
    }

}
