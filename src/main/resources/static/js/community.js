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
            else
                alert(data);
        },
        error:function () {
            alert(this.url);
        }
    });
}