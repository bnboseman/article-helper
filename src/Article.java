
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.StringReader;
import java.io.StringWriter;

public class Article {
    protected String title;
    protected String meta_title;
    protected String meta_description;
    protected String slug;
    protected String summary;
    protected Document content;
    protected HTMLEditorKit editorKit = new HTMLEditorKit();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replace("'", "''").trim();
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title.replace("'", "''").trim();
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description.replace("'", "''").trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary.replace("'", "''");
    }

    public String getContent() {
        return this.content.body().html();
    }

    public void setContent(String content)
    {
        this.content = Jsoup.parse(content);
        this.updateContentLinks();
    }

    public void updateContentLinks()
    {
        Elements links = this.content.getElementsByTag("a");
        for (Element link : links) {
            if (!link.hasAttr("target")) {
                link.attr("target", "_blank");
            }
        }
    }

    public String getInsertString()
    {
        return String.format("INSERT INTO article VALUES (nextval('article_id_seq'::regclass), " +
                "1, " + // active
                "to_char(current_timestamp, YYYY-MM-DD HH24:MI:SS')::timestamp," + // insert_time
                "'%s'," + //title
                "'%s'," + //slug
                "'%s'," + //summary
                "'%s'," + //content 
                "'%s'," + // meta_title
                "'%s'," + // meta_description
                "'', " +
                "to_char(current_timestamp, YYYY-MM-DD HH24:MI:SS')::timestamp," + // modified_time
                "NULL," +
                "false," + // is_page
                "'');",
                this.getTitle(),
                this.getSlug(),
                this.getSummary(),
                this.getContent().replace("'", "''"),
                this.getMeta_title(),
                this.getMeta_description()
                );
    }
}
