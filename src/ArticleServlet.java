import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

@WebServlet(name = "ArticleServlet")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setSlug(request.getParameter("slug"));
        article.setMetaDescription(request.getParameter("metaDescription"));
        article.setSummary(request.getParameter("summary"));
        article.setMetaTitle(request.getParameter("metaTitle"));
        article.setContent(request.getParameter("content"));
        article.setImages(request.getParameter("images"));
        String[] isPage = request.getParameterValues("is_page");
        article.setPage(!(isPage == null));
        article.setUrl(request.getParameter("url"));

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(buildJsonString(article));
        out.flush();
    }

    protected String buildJsonString(Article article) {
        JSONObject json = new JSONObject();
        json.put("insert", article.getInsertHtml());
        json.put("images_insert", article.getImagesInsert());
        return json.toString();
    }
}
