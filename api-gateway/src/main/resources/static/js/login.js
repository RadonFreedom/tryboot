function login() {
    //字符串非空校验
    var username = $("#login-username").val();
    if (username === "") {
        layer.msg("用户名不能为空，请输入", {time: 1000, icon: 0, shift: 5});
        return;
    }
    var password = $("#login-password").val();
    if (password === "") {
        layer.msg("登录密码不能为空，请输入", {time: 1000, icon: 0, shift: 5});
        return;
    }

    var holder = layer.msg("处理中...", {icon: 16});
    if (requestOauthToken(username, password)) {
        layer.close(holder);
        return getCurrentAccount();
    }
    else {
        layer.close(holder);
        layer.msg("用户名不存在或密码错误!", {time: 1000, icon: 2, shift: 6});
    }
}

function register() {
    //字符串非空校验
    var username = $("#signup-username").val();
    if (username === "") {
        layer.msg("用户名不能为空，请输入", {time: 1000, icon: 0, shift: 5});
        return;
    }
    var email = $("#signup-email").val();
    if (email === "") {
        layer.msg("邮箱不能为空，请输入", {time: 1000, icon: 0, shift: 5});
        return;
    }
    var password = $("#signup-password").val();
    if (password === "") {
        layer.msg("登录密码不能为空，请输入", {time: 1000, icon: 0, shift: 5});
        return;
    }

    var holder = null;
    $.ajax({
        type: "POST",
        url: "register",
        data: {
            username: username,
            email: email,
            password: password
        },
        beforeSend: function () {
            holder = layer.msg("处理中...", {icon: 16});
        },
        success: function (result) {
            layer.close(holder);
            if (result === true) {
                requestOauthToken(username, password);
                return getCurrentAccount();
            } else {
                layer.msg("用户名或邮箱已存在!", {time: 1000, icon: 2, shift: 6});
            }
        }
    });
}