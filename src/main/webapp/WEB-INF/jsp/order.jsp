<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ParentCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.CategoryGroup" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ChildCategory" %>
<%@ page import="com.iflysse.viewmodel.GoodsViewModel.Goods4List" %>
<%@ page import="com.iflysse.viewmodel.EvaluateViewModel.EvaluateList" %>
<%@ page import="com.iflysse.viewmodel.GoodsCartModel.GoodsCartList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Justified Nav Template for Bootstrap</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet"/>

    <link href="/static/css/font-awesome.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700'
          rel='stylesheet' type='text/css'>
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/bootsnav.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="/static/css/htmleaf-demo.css">
    <link href="/static/css/overwrite.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/color.css" rel="stylesheet">
    <link type="text/css" href="/static/css/evaluatestyle.css" rel="stylesheet" />

    <style type="text/css">
        .container h1 {
            font-size: 20px;
        }

        .caption {
            text-align: center;
        }

        .caption h3 {
            font-size: 14px;
        }

        #carousel-example-generic img {
            width: 100%;
        }

        #header_info a {
            color: #C9C9C9;
        }

        #header_info a:hover {
            color: #FFF;
            text-decoration: none;
        }
    </style>


</head>

<body id="home">
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<%@ include file="/WEB-INF/jsp/head-search.jsp"%>
<div style="margin-top: 20px"></div>

<div class="container">
    <h1>我的订单</h1>
    <div class="shopping-car-container">
        <div class="car-headers-menu">
            <div class="row">
                <div class="col-md-2 car-menu">图片</div>
                <div class="col-md-3 car-menu">商品名称</div>
                <div class="col-md-2 car-menu">单价</div>
                <div class="col-md-1 car-menu">数量</div>
                <div class="col-md-2 car-menu">金额</div>
                <div class="col-md-2 car-menu">操作</div>
            </div>
        </div>
        <div class="goods-content">
            <!--goods display-->
            <% for(GoodsCartList cart:(List<GoodsCartList>)request.getAttribute("carts")) {%>
            <div class="goods-item">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-2 car-goods-info goods-image-column">
                            <img class="goods-image"
                                 src="/static/images/cover_picture/<%=cart.getParentCategoryId()%>/<%=cart.getGoods().getCategoryId()%>/<%=cart.getGoods().getId()%>/<%=cart.getGoods().getUrl()%>"
                                 style="width: 100px; height: 100px;" />
                        </div>
                        <div class="col-md-3 car-goods-info goods-params"><%=cart.getGoods().getGoodsName()%></div>
                        <div class="col-md-2 car-goods-info goods-price"><span>￥</span><span class="single-price"><%=cart.getGoods().getGoodsPrice()%></span></div>
                        <div class="col-md-1 car-goods-info goods-counts"><%=cart.getNumber()%></div>
                        <div class="col-md-2 car-goods-info goods-money-count"><span>￥</span><span class="single-total"><%=cart.getGoods().getGoodsPrice()*cart.getNumber()%></span></div>
                        <div class="col-md-2 car-goods-info goods-operate" id="<%=cart.getId()%>,<%=cart.getGoodsId()%>">
                            <%if(cart.getEvaluateId()==0){%>
                            <button type="button" class="btn btn-danger item-evaluate" style="" id="evaluate-<%=cart.getId()%>" >待评价</button>
                            <button type="button" class="btn btn-primary item-detail" style="display: none" id="detail-<%=cart.getId()%>">评价详情</button>
                            <%}else{ %>
                            <button type="button" class="btn btn-danger item-evaluate" style="display: none">待评价</button>
                            <button type="button" class="btn btn-primary item-detail" style="">评价详情</button>
                            <%} %>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>

    </div>
    <!-- 评价界面 -->
    <div class="modal fade" id="myEva">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">

                    <div class="order-evaluation clearfix">
                        <h4>评价信息</h4>
                        <div class="block">
                            <ul>
                                <li data-default-index="0">
                                    <span>
                                        <img src="/static/images/x1.png">
                                        <img src="/static/images/x1.png">
                                        <img src="/static/images/x1.png">
                                        <img src="/static/images/x1.png">
                                        <img src="/static/images/x1.png">
                                    </span>
                                    <em class="level"></em>
                                </li>
                            </ul>
                        </div>
                        <%--
                        <div class="order-evaluation-text">
                            本次交易，乖，摸摸头 给您留下了什么印象呢？
                        </div>
                        <div class="order-evaluation-checkbox">
                            <ul class="clearfix">
                                <li class="order-evaluation-check" data-impression="1">专业水平高<i class="iconfont icon-checked"></i></li>
                                <li class="order-evaluation-check" data-impression="2">交付准时<i class="iconfont icon-checked"></i></li>
                                <li class="order-evaluation-check" data-impression="3">效果明显<i class="iconfont icon-checked"></i></li>
                                <li class="order-evaluation-check" data-impression="4">数据分析准确<i class="iconfont icon-checked"></i></li>
                                <li class="order-evaluation-check" data-impression="5">能力待提高<i class="iconfont icon-checked"></i></li>
                                <li class="order-evaluation-check" data-impression="6">工期延误<i class="iconfont icon-checked"></i></li>
                            </ul>
                        </div>
                        --%>
                        <div class="order-evaluation-textarea">
                            <textarea name="content" id="TextArea1" onKeyUp="words_deal();" ></textarea>
                            <span>输入<em id="textCount">140</em>个字</span>
                        </div>
                        <a href="javascript:;" id="order_evaluation">评价完成</a>
                    </div>

                    <div id="order_evaluate_modal" class="dmlei_tishi_info"></div>
                </div>
                <div class="modal-footer">
                </div>
            </div>
            <!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- Site footer -->
    <footer class="footer">
        <p>&copy; hahah</p>
    </footer>

</div>


<!-- /container -->


<script src="/static/js/jquery.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/static/js/ie10-viewport-bug-workaround.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/bootsnav.js"></script>
<script type="text/javascript" src="/static/js/order.js"></script>
<script src="/demos/googlegg.js"></script>
<script type="text/javascript" >
    /*
        * 根据index获取 str
        * **/
    function byIndexLeve(index){
        var str ="";
        switch (index)
        {
            case 0:
                str="差评";
                break;
            case 1:
                str="较差";
                break;
            case 2:
                str="中等";
                break;
            case 3:
                str="一般";
                break;
            case 4:
                str="好评";
                break;
        }
        return str;
    }
    //  星星数量
    var stars = [
        ['x2.png', 'x1.png', 'x1.png', 'x1.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x1.png', 'x1.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x2.png', 'x1.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x1.png'],
        ['x2.png', 'x2.png', 'x2.png', 'x2.png', 'x2.png'],
    ];
    $(".block li").find("img").hover(function(e) {
        var obj = $(this);
        var index = obj.index();
        if(index < (parseInt($(".block li").attr("data-default-index")) -1)){
            return ;
        }
        var li = obj.closest("li");
        var star_area_index = li.index();
        for (var i = 0; i < 5; i++) {
            li.find("img").eq(i).attr("src", "/static/images/" + stars[index][i]);//切换每个星星
        }
        $(".level").html(byIndexLeve(index));
    }, function() {
    })

    $(".block li").hover(function(e) {
    }, function() {
        var index = $(this).attr("data-default-index");//点击后的索引
        index = parseInt(index);
        console.log("index",index);
        $(".level").html(byIndexLeve(index-1));
        console.log(byIndexLeve(index-1));
        $(".order-evaluation ul li:eq(0)").find("img").attr("src","/static/images/x1.png");
        for (var i=0;i<index;i++){

            $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","/static/images/x2.png");
        }
    })

    $(".block li").find("img").click(function() {
        var obj = $(this);
        var li = obj.closest("li");
        var star_area_index = li.index();
        var index1 = obj.index();
        li.attr("data-default-index", (parseInt(index1)+1));
        var index = $(".block li").attr("data-default-index");//点击后的索引
        index = parseInt(index);
        console.log("index",index);
        $(".level").html(byIndexLeve(index-1));
        console.log(byIndexLeve(index-1));
        $(".order-evaluation ul li:eq(0)").find("img").attr("src","/static/images/x1.png");
        for (var i=0;i<index;i++){
            $(".order-evaluation ul li:eq(0)").find("img").eq(i).attr("src","/static/images/x2.png");
        }

    });
    //印象
   // $(".order-evaluation-check").click(function(){
        //if($(this).hasClass('checked')){
            //当前为选中状态，需要取消
           // $(this).removeClass('checked');
        //}else{
           // //当前未选中，需要增加选中
           // $(this).addClass('checked');
       // }
   // });
    //评价字数限制
    function words_deal()
    {
        var curLength=$("#TextArea1").val().length;
        if(curLength>140)
        {
            var num=$("#TextArea1").val().substr(0,140);
            $("#TextArea1").val(num);
            alert("超过字数限制，多出的字将被截断！" );
        }
        else
        {
            $("#textCount").text(140-$("#TextArea1").val().length);
        }
    }
</script>
</body>
</html>
