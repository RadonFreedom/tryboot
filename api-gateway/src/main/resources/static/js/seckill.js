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
        window.location.href = "/login.html";
    }

    var seckillGoodId = $("#seckillGoodId").val();
    $.ajax({
        url: "/seckill",
        type: "POST",
        headers: {'Authorization': 'Bearer ' + token},
        data: {
            seckillGoodId: seckillGoodId,
            goodCnt: 1,
            deliveryInfoId: 1,
            orderChannel: 1
        },
        success: function (result) {
            if (result.success == true) {
                getSeckillResult(token, seckillGoodId);
            } else {
                layer.msg(result.msg);
            }
        },
        error: function (xhr) {
            if (xhr.status == "401") {
                layer.msg("请先登录!");
                window.location.href = "/login.html";
            }
            else {
                layer.msg("客户端请求有误");
            }
        }
    });
}

function getSeckillResult(token, seckillGoodId) {

    $.ajax({
        url: "/seckill/result",
        type: "POST",
        headers: {'Authorization': 'Bearer ' + token},
        data: {
            seckillGoodId: seckillGoodId
        },
        success: function (result) {
            if (result.success == true) {
                if (result.data != null) {
                    if (result.msg != null) {
                        layer.confirm(result.msg + " 查看订单?", {btn: ["确定", "取消"]},
                            function () {
                                window.location.href = "/order_detail.html?orderId=" + result.data;
                            },
                            function () {
                                layer.closeAll();
                            });
                    } else {
                        window.location.href = "/order_detail.html?orderId=" + result.data;
                    }
                }
                else {
                    layer.msg("正在等待下单结果，请稍等...");
                    setTimeout(function () {
                        getSeckillResult(token, seckillGoodId);
                    }, 200);
                }

            } else {
                layer.msg(result.msg);
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