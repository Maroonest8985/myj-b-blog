package induk.jsp.ab.myjbblog.controller;

import induk.jsp.ab.myjbblog.model.Blog;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/blogs/post.do")
public class BlogController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        message = request.getParameter("name") + ":" + request.getParameter("phone");
        Blog blog = new Blog();
        blog.setName(request.getParameter("name"));
        blog.setEmail(request.getParameter("email"));
        blog.setPhone(request.getParameter("phone"));
        blog.setMessage(request.getParameter("message"));

        request.setAttribute("name", blog.getName());
        request.setAttribute("email", blog.getEmail());
        request.setAttribute("phone", blog.getPhone());
        request.setAttribute("message", blog.getMessage());
        // forward는 request, response 객체 전달
        request.getRequestDispatcher("about.jsp").forward(request, response);
    }

    public void destroy() {
    }
}