function getGoodsAsList() {

    $.ajax({
        type: "GET",
        url: "/goods",
        success: function (result) {
            //局部刷新页面数据
            var userDataHtml = "";
            $.each(result, function (i, seckillGood) {
                userDataHtml += '<tr>';
                userDataHtml += '  <td>' + seckillGood.goodDO.goodName + '</td>';
                userDataHtml += '  <td><img src="' + seckillGood.goodDO.goodImg + '" width="100" height="100" alt="' + seckillGood.goodDO.goodName + '"/></td>';
                userDataHtml += '  <td>' + seckillGood.goodDO.goodPrice + '</td>';
                userDataHtml += '  <td>' + seckillGood.seckillPrice + '</td>';
                userDataHtml += '  <td>' + seckillGood.stockCount + '</td>';
                userDataHtml += '  <td><a href="/goods_detail.htm?goodsId=' + seckillGood.id + '">详情</a></td>';
                userDataHtml += '</tr>';
            });

            $("#goods-list").html(userDataHtml);
        }
    });
}