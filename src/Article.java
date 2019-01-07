
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Article implements Insertable {
    protected String title;
    protected String metaTitle;
    protected String metaDescription;
    protected String slug;
    protected String summary;
    protected ArrayList<ArticleImage> images = new ArrayList<ArticleImage>();
    protected Document content;
    protected boolean isPage = false;
    protected String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replace("'", "''").trim();
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle.replace("'", "''").trim();
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription.replace("'", "''").trim();
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

    public void setContent(String content) {
        this.content = Jsoup.parse(content);
        this.updateContentLinks();
    }

    public void updateContentLinks() {
        Elements links = this.content.getElementsByTag("a");
        for (Element link : links) {
            if (!link.hasAttr("target")) {
                link.attr("target", "_blank");
            }
        }
    }

    public String getInsertHtml() {
        return String.format("INSERT INTO article VALUES (nextval('article_id_seq'::regclass), " +
                        "1, " + // active
                        "to_char(current_timestamp, 'YYYY-MM-DD HH24:MI:SS')::timestamp," + // insert_time
                        "'%s'," + //title
                        "'%s'," + //slug
                        "'%s'," + //summary
                        "'%s'," + //content
                        "'%s'," + // metaTitle
                        "'%s'," + // metaDescription
                        "'', " +
                        "to_char(current_timestamp, 'YYYY-MM-DD HH24:MI:SS')::timestamp," + // modified_time
                        "NULL," +
                        "%s" +
                        "'%s');",
                this.getTitle(),
                this.getSlug(),
                this.getSummary(),
                this.getContent().replace("'", "''"),
                this.getMetaTitle(),
                this.getMetaDescription(),
                this.isPage() ? "true," :"false,",
                this.getUrl() != null ? this.getUrl() : this.getSlug()
        );
    }

    public ArrayList<ArticleImage> getImages() {
        return images;
    }

    public void setImages(String images) {
        if (images.length() == 0) {
            return;
        }

        String[] elements = images.trim().split(",");
        for (String element : elements) {
            this.images.add(new ArticleImage(element.trim()));
        }
    }

    public String getImagesInsert() {
        ArrayList<String> insertStatements = new ArrayList<String>();
        String insertString = "";
        for (ArticleImage image : this.images) {
            insertString = insertString + image.getInsertHtml() + "\n";
        }
        return insertString;
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean page) {
        isPage = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
