import com.sun.javaws.exceptions.InvalidArgumentException;

public class ArticleImage implements Insertable {
    protected String name;
    protected  String altText;
    protected String articleSlug;

    public ArticleImage(String image)
    {
        setName(image);
    }

    public ArticleImage(String image, String articleSlug) {
        setName(image);
        setArticleSlug(articleSlug);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertHtml()
    {
        if (this.getName().isEmpty()) {
            throw new IllegalArgumentException("Article Image is empty!");
        }

        String articleIdInsert = articleSlug.isEmpty() ? "currval('article_id_seq'),"  : "(select id from article where slug = '" + articleSlug + "'),";
        return String.format("INSERT INTO article_images (insert_time, article_id, image_file) VALUES (" +
                "to_char(current_timestamp, 'YYYY-MM-DD HH24:MI:SS')::timestamp," + //insert_time
                 articleIdInsert + //article_id
                "'%s');",
                this.getName()
        );
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String alt_text) {
        this.altText = alt_text;
    }

    public String getArticleSlug() {
        return articleSlug;
    }

    public void setArticleSlug(String articleSlug) {
        this.articleSlug = articleSlug;
    }
}
