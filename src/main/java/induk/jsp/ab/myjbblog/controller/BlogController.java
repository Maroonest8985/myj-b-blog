package induk.jsp.ab.myjbblog.controller;

import induk.jsp.ab.myjbblog.model.Blog;
import induk.jsp.ab.myjbblog.repository.BlogDAOImpl;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "post", urlPatterns = {"/blogs/post.do", "/blogs/list.do", "/blogs/detail.do"})
public class BlogController extends HttpServlet {
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1);
        String action = command.substring(command.lastIndexOf("/") + 1);
        BlogDAOImpl dao = new BlogDAOImpl();
        if (action.equals("post.do")){
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setPhone(request.getParameter("phone"));
            blog.setMessage(request.getParameter("message"));

            if (dao.create(blog) > 0) {
                request.setAttribute("blog", blog);
                // forward는 request, response 객체 전달
                request.getRequestDispatcher("about.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if (action.equals("list.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>();

            if ((blogList = (ArrayList<Blog>) dao.readList()) != null) {
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("list.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if (action.equals("detail.do")) {
            Blog blog = new Blog();
            blog.setId(request.getParameter("id"));
            Blog retBlog = null;
            if((retBlog = dao.read(blog)) != null) {
                    request.setAttribute("blog", retBlog);
                    request.getRequestDispatcher("detail.jsp").forward(request, response);
                } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doService(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doService(request, response);

         }

    public void destroy() {
    }
}