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

    if (content == null) {
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
        $.getJSON("/comment/" + id, function (data) {
            //console.log(data);

            var html = "";

            for (var i = 0; i < data.data.length; i++) {
                //console.log((data.data)[i].user.photoUrl);
                html = html + '<hr>';
                html = html + '<div class="media">';
                html = html + '<div class="media-left media-middle">';
                html = html + '<a href="#">';
                html = html + '<img class="media-object img-thumbnail img" src="' + (data.data)[i].user.photoUrl + '">';
                html = html + '</a>';
                html = html + '</div>';
                html = html + '<div class="media-body">';
                html = html + '<h5 class="media-heading"><span>' + (data.data)[i].user.name + '</span></h5>';
                html = html + '<span class="comment_content">' + (data.data)[i].content + '</span><br>';
                html = html + '<span class="text">';
                html = html + '<span class="glyphicon glyphicon-thumbs-up icon"></span> &nbsp;';
                html = html + '<span class="time">' + (new Date((data.data)[i].gmtCreate).toLocaleString()) + ' </span>';
                html = html + '</span>';
                html = html + '</div>';
                html = html + '</div>';
            }
            $("div.temp").html(html);
            //console.log(html);

            // var secondComment  = $("#comment-" + id);
            // $.each(data.data, function (index, comment) {
            //     console.log(comment.content);
            //     var t = $("<div/>", {
            //        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comment",
            //        html:comment.content
            //     });
            //     secondComment.prepend(t);
            // });

            $("#comment-" + id).addClass("in");
            d.setAttribute("data-collapse", "in");
            d.classList.add("active");
        });
    }

}