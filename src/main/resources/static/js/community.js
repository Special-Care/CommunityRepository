//提交一级评论
function onSubmitComment() {
    var questionId = $("#questionId").val();
    var content = $("#topComment").val();
    var type = 1;

    myAjax(questionId, content, type);
}

//提交二级评论
function submitSecondComment(data) {
    var commentId = data.getAttribute("data-id");
    var content = $("#secondComment").val();
    var type = 2;

    myAjax(commentId, content, type);
}

//提交评论的Ajax
function myAjax(parent, content, type) {

    if (content == null || content == "") {
        alert("评论内容是空的^_^");
        return;
    }
    // alert(questionId + "-" + content + "-" + type);

    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentID":parent,
            "content":content,
            "type":type
        }),
        dataType:"JSON",
        success:function (data) {
            if (data.code == 520) {
                window.document.location.reload();
            } else {
                if(data.code == 502) {
                    var onLogin = confirm(data.message);
                    if (onLogin)
                        window.open("https://github.com/login/oauth/authorize?client_id=ad66024838c4b2109912&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                    window.localStorage.setItem("closable", true);
                } else {
                    alert(data.message);
                }
            }
        },
        error:function () {
            alert(this.url);
        }
    });
}

//二级评论展开/折叠
function onCollapse(d) {
    var id = d.getAttribute("data-id");

    if (d.getAttribute("data-collapse")) {
        //折叠评论
        $("#comment-" + id).removeClass("in");
        d.removeAttribute("data-collapse");
        d.classList.remove("active");
    } else {
        //展开评论
        var secondComment  = $("#comment-" + id);

        if (secondComment.children().length != 1) {
            $("#comment-" + id).addClass("in");
            d.setAttribute("data-collapse", "in");
            d.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                console.log(data);

                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class":"media-left"
                    }).append($("<img/>", {
                        "class":"media-object img-thumbnail img",
                        "src":comment.user.photoUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class":"media-body"
                    }).append($("<h5/>", {
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<span/>", {
                        "class":"comment_content",
                        "html":comment.content
                    }).append("<br>")).append($("<span/>", {
                        "class":"text",
                    }).append($("<span/>", {
                        "class":"glyphicon glyphicon-thumbs-up icon",
                    })).append($("<span/>", {
                        "class":"time",
                        "html":(new Date(comment.gmtCreate).toLocaleString())
                    })));

                    var mediaElement = $("<div/>", {
                        "class":"media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<hr><div/>", {
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12"
                    }).append(mediaElement);

                    secondComment.prepend(commentElement);
                });

                $("#comment-" + id).addClass("in");
                d.setAttribute("data-collapse", "in");
                d.classList.add("active");
            });
        }
    }
}

function showTags() {
    $("#tags").show();
}

function selectTag(data) {
    var value = data.getAttribute("data-tag");
    var val = $("#tag").val();

    if(val.indexOf(value) == -1) {
        if (val)
            $("#tag").val(val + ',' + value);
        else
            $("#tag").val(value);
    }

}