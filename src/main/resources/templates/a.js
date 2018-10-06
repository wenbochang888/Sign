function getInfo(username) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://120.78.159.149:8080/sign/123",
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert("出错");
            alert(username);
        }
    })
}





