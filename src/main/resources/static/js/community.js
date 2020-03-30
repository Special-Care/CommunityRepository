function onSubmitComment() {
    var questionId = $("#questionId").val();
    var content = $("#comment").val();
    var type = 1;

    // alert(questionId + "-" + content + "-" + type);

    $.ajax({
        type:"POST",
        url:"http://localhost:8080/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentID":questionId,
            "content":content,
            "type":type
        }),
        dataType:"JSON",
        success:function (data) {
            if (data.code == 520)
                $("#comments").hide();
            else {
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