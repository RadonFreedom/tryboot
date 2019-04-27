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
        url: "/seckill",
        type: "POST",
        headers: {'Authorization': 'Bearer ' + token},
        data: {
            seckillGoodId: $("#goodId").val(),
            goodCnt: 1,
            deliveryInfoId: 1,
            orderChannel: 1
        },
        success: function (data) {
            if (data.success == true) {
                window.location.href = "/order_detail.html?seckillGoodId=" + seckillGoodId;
            } else {
                layer.msg("秒杀失败! 库存不足或者已参加过本商品的秒杀");
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