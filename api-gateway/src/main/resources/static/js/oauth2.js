function requestOauthToken(username, password) {

    var success = false;

    $.ajax({
        url: 'auth/oauth/token',
        contentType: 'application/x-www-form-urlencoded',
        type: 'post',
        headers: {'Authorization': 'Basic dWk6cmFkb24='},
        async: false,
        data: 'username='+username+'&password='+password+'&grant_type=password&scope=ui',
        success: function (data) {
            localStorage.setItem('token', data.access_token);
            success = true;
        },
        error: function () {
            removeOauthTokenFromStorage();
        }
    });

    return success;
}

function getOauthTokenFromStorage() {
    const token = localStorage.getItem('token');
    if (token == null) {
        layer.msg("请先登录!");
        window.location.href = "/login.html";
    } else {
        return token;
    }
}

function removeOauthTokenFromStorage() {
    return localStorage.removeItem('token');
}

function checkError (xhr) {
    if (xhr.status == "401") {
        layer.msg("请先登录!");
        window.location.href = "/login.html";
    }
    else {
        layer.msg("客户端请求有误");
    }
}