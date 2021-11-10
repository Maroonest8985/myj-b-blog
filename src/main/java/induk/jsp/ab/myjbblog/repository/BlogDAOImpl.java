package induk.jsp.ab.myjbblog.repository;
import induk.jsp.ab.myjbblog.model.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends DAOImplOracle implements BlogDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public BlogDAOImpl(){
        conn=getConnection();
    }

    @Override
    public int create(Blog blog) {
        String query = "insert into blogb values(seq_blogb.nextval, ?, ?, ?, ?)";
        int rows = 0;
    try{
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, blog.getName());
        pstmt.setString(2, blog.getEmail());
        pstmt.setString(3, blog.getPhone());
        pstmt.setString(4, blog.getMessage());

        rows = pstmt.executeUpdate();
    }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return rows;
    }

    @Override
    public Blog read(Blog blog) {
        Blog retBlog = null;
        String sql = "select * from blogb where id=?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getId());
            rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println("ok");
                retBlog = new Blog();
                retBlog.setId(rs.getString("id"));
                retBlog.setName(rs.getString("name"));
                retBlog.setEmail(rs.getString("email"));
                retBlog.setPhone(rs.getString("phone"));
                retBlog.setMessage(rs.getString("message"));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return retBlog;
        }

    @Override
    public List<Blog> readList() {
        ArrayList<Blog> blogList = null;
        String sql = "select * from blogb";
        try{
            stmt = conn.createStatement();
        if((rs = stmt.executeQuery(sql)) != null){
            blogList=new ArrayList<Blog>();
            while(rs.next()){
                Blog blog = new Blog();
                blog.setId(rs.getString("id"));
                blog.setName(rs.getString("name"));
                blog.setEmail(rs.getString("email"));
                blog.setPhone(rs.getString("phone"));
                blog.setMessage(rs.getString("Message"));
                blogList.add(blog);
            }
        }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return blogList;
    }

    @Override
    public int update(Blog blog) {
        return 0;
    }

    @Override
    public int delete(Blog blog) {
        return 0;
    }
}
