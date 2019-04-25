// function getSeckillPath() {
//     var goodId = $("#goodId").val();
//     g_showLoading();
//     $.ajax({
//         url: "/seckill/path",
//         type: "GET",
//         data: {
//             goodId: goodId,
//             verifyCode: $("#verifyCode").val()
//         },
//         success: function (data) {
//             if (data.code == 0) {
//                 var path = data.data;
//                 doSeckill(path);
//             } else {
//                 layer.msg(data.msg);
//             }
//         },
//         error: function () {
//             layer.msg("客户端请求有误");
//         }
//     });
// }

// function doSeckill(path) {
//     $.ajax({
//         url: "/seckill/" + path + "/do_seckill",
//         type: "POST",
//         data: {
//             goodId: $("#goodId").val()
//         },
//         success: function (data) {
//             if (data.code == 0) {
//                 getSeckillResult($("#goodId").val());
//             } else {
//                 layer.msg(data.msg);
//             }
//         },
//         error: function () {
//             layer.msg("客户端请求有误");
//         }
//     });
// }

function doSeckill() {
    var token = getOauthTokenFromStorage();
    if (token == null) {
        layer.msg("请先登录!");
        window.location.href="/login.html";
    }
    $.ajax({
        // url: "/seckill/" + path + "/do_seckill",
        url: "/seckill",
        type: "POST",
        headers: {'Authorization': 'Bearer ' + token},
        data: {
            goodId: $("#goodId").val()
        },
        success: function (data) {
            if (data.code == 0) {
                getSeckillResult($("#goodId").val());
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function getSeckillResult(goodId) {
    g_showLoading();
    $.ajax({
        url: "/seckill/result",
        type: "GET",
        data: {
            goodId: $("#goodId").val(),
        },
        success: function (data) {
            if (data.code == 0) {
                var result = data.data;
                if (result < 0) {
                    layer.msg("对不起，秒杀失败");
                } else if (result == 0) {//继续轮询
                    setTimeout(function () {
                        getSeckillResult(goodId);
                    }, 200);
                } else {
                    layer.confirm("恭喜你，秒杀成功！查看订单？", {btn: ["确定", "取消"]},
                        function () {
                            window.location.href = "http://47.107.38.165:8081/order_detail.htm?orderId=" + result;
                        },
                        function () {
                            layer.closeAll();
                        });
                }
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function refreshVerifyCode() {
    $("#verifyCodeImg").attr("src", "/seckill/verifyCode?goodId=" + $("#goodId").val() + "&timestamp=" + new Date().getTime());
}