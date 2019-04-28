function getOrderDetail() {
    var token = getOauthTokenFromStorage();
    if (token == null) {
        layer.msg("请先登录!");
        window.location.href = "/login.html";
    }

    var seckillGoodId = g_getQueryString("orderId");
    $.ajax({
        url: "/order?orderId=" + seckillGoodId,
        type: "GET",
        headers: {'Authorization': 'Bearer ' + token},
        success: function (data) {
            if (data.success == true) {
                render(data.data);
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function render(data) {
    var good = data.good;
    var order = data.order;
    $("#goodName").text(good.goodName);
    $("#goodImg").attr("src", good.goodImg);
    $("#goodPrice").text(order.seckillPrice);
    $("#orderPrice").text(order.seckillPrice);
    $("#createDate").text(new Date(order.gmtCreate).format("yyyy-MM-dd hh:mm:ss"));
    var status = "";
    if (order.status == 0) {
        status = "未支付"
    } else if (order.status == 1) {
        status = "待发货";
    }
    $("#orderStatus").text(status);

}