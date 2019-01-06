
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="https://cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <style>
        #insertModel textarea {
            height: 20vh;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <span class="navbar-brand">Article SQL</span>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-12">
        <div class="col-xs-12">
            <form id="articleSubmit" name="articleForm" method="post" action="/article">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="Title">
                </div>
                <div class="form-group">
                    <label for="metaTitle">Meta Title</label>
                    <input type="text" class="form-control" id="metaTitle" name="metaTitle" placeholder="Meta Title">
                </div>
                <div class="form-group">
                    <label for="metaDescription">Meta Description</label>
                    <input type="metaDescription" class="form-control" id="metaDescription" name="metaDescription"
                           placeholder="Meta Description">
                </div>
                <div class="form-group">
                    <label for="title">Slug</label>
                    <input type="text" class="form-control" id="slug" name="slug" placeholder="Slug">
                </div>
                <div class="form-group">
                    <label for="summary">Summary</label>
                    <input type="text" class="form-control" id="summary" name="summary" placeholder="Summary">
                </div>
                <div class="form-group">
                    <label for="title">Images</label>
                    <input type="text" class="form-control" id="images" name="images"
                           placeholder="Images (Comma seperated list)">
                </div>
                <div class="form-group">
                    <label for="content">Content</label>
                    <textarea class="form-control" name="content" id="content" rows="10"></textarea>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-info" onclick="callServlet();">View Article SQL</button>
                </div>
            </form>
            <div class="modal fade" id="insertModel" tabindex="-1" role="dialog" aria-labelledby="insertModelTitle"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="insertModelTitle">Insert Text</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="insert">Article Insert</label>
                                <textarea readonly id="insert" class="form-control" cols="10"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="images_insert">Image Inserts</label>
                                <textarea readonly id="images_insert" class="form-control" cols="10"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div><!-- /.container -->

<script
        src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
<script>
    CKEDITOR.replace('content');

    function callServlet() {
        $("#content").html(CKEDITOR.instances.content.getData());
        $("#articleSubmit").submit();
    }

    $("#articleSubmit").submit(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "/article",
            data: $(this).serialize(),
            success: function (response) {
                $("#insert").html(response.insert);
                $("#images_insert").html(response.images_insert);
                $('#insertModel').modal()
            }
        });
    });
</script>

</body>
</html>
