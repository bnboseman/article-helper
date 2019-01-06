import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;
import org.json.JSONWriter;

@WebServlet(name = "ArticleServlet")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setSlug(request.getParameter("slug"));
        article.setMeta_description(request.getParameter("meta_description"));
        article.setSummary(request.getParameter("summary"));
        article.setMeta_title(request.getParameter("meta_title"));
        article.setContent(request.getParameter("content"));
       JSONObject json = new JSONObject();
        json.put("insert_string",article.getInsertString());

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
