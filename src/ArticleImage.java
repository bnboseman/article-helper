public class ArticleImage implements Insertable {
    protected String name;
    protected  String altText;

    public ArticleImage(String image)
    {
        setName(image);
    }

    public ArticleImage(String image, String altText) {
        setName(name);
        setAltText(altText);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertHtml()
    {
        return String.format("INSERT INTO article_images (insert_time, article_id, image_file) VALUES " +
                "to_char(current_timestamp, YYYY-MM-DD HH24:MI:SS')::timestamp," + //insert_time
                "currval('article_id_seq')," + //article_id
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
}
