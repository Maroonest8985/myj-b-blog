package induk.jsp.ab.myjbblog.repository;
import java.util.List;

import induk.jsp.ab.myjbblog.model.Blog;

public interface BlogDAO {

    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);
}
