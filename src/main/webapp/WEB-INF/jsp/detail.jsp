<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ParentCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.CategoryGroup" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ChildCategory" %>
<%@ page import="com.iflysse.viewmodel.GoodsViewModel.Goods4List" %>
<%@ page import="com.iflysse.viewmodel.EvaluateViewModel.EvaluateList" %>
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
    <link href="/static/css/detail.css" rel="stylesheet">
    <link href="/static/css/shouye.css" rel="stylesheet">
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
<div class="mhc"></div>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<!-- Start Navigation -->
<%@ include file="/WEB-INF/jsp/head-search.jsp"%>
<!-- End Navigation -->
<div style="margin-top: 20px"></div>

<% Goods4List goods=(Goods4List)request.getAttribute("goods"); {%>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="ban" id="demo1">
                <div class="ban2" id="ban_pic1">
                    <div class="prev1" id="prev1"><img src="/static/images/index_tab_l.png" width="28" height="51"  alt=""></div>
                    <div class="next1" id="next1"><img src="/static/images/index_tab_r.png" width="28" height="51"  alt=""></div>
                    <ul>
                        <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_1()%>" width="500" height="500" alt=""></a></li>
                        <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_2()%>" width="500" height="500" alt=""></a></li>
                        <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_3()%>" width="500" height="500" alt=""></a></li>
                        <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_4()%>" width="500" height="500" alt=""></a></li>
                    </ul>
                </div>
                <div class="min_pic">
                    <div class="prev_btn1" id="prev_btn1"><img src="/static/images/feel3.png" width="9" height="18"  alt=""></div>
                    <div class="num clearfix" id="ban_num1">
                        <ul>
                            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_1()%>" width="80" height="80" alt=""></a></li>
                            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_2()%>" width="80" height="80" alt=""></a></li>
                            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_3()%>" width="80" height="80" alt=""></a></li>
                            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_4()%>" width="80" height="80" alt=""></a></li>
                        </ul>
                    </div>
                    <div class="next_btn1" id="next_btn1"><img src="/static/images/feel4.png" width="9" height="18"  alt=""></div>
                </div>
            </div>
        </div>
        <div class="col-md-6 divGetId" id="<%=goods.getId()%>">
            <ol class="Xcontent13">
                <div class="Xcontent14"><a href="#"><p><%=goods.getName()%></p></a></div>
                <div class="Xcontent16"><p><%=goods.getDescription()%></p></div>
                <div class="Xcontent17">
                    <p class="Xcontent18">售价</p>
                    <p class="Xcontent19">￥<span><%=goods.getPrice()%></span></p>
                    <div class="Xcontent20">
                        <p class="Xcontent21">促销</p>
                        <p class="Xcontent22">无</p>
                    </div>
                    <div class="Xcontent23">
                        <p class="Xcontent24">服务</p>
                        <p class="Xcontent25">30天无忧退货&nbsp;&nbsp;&nbsp;&nbsp;       48小时快速退款 &nbsp;&nbsp;&nbsp;&nbsp;        免邮</p>
                    </div>

                </div>
                <div class="Xcontent30">
                    <p class="Xcontent31">数量</p>
                    <div class="Xcontent32"><button type="button" class="btn btn-default" id="count-decrease">-</button></div>
                    <form>
                        <input class="input" value="1" id="goodCount"></form>
                    <div class="Xcontent33"><button type="button" class="btn btn-default" id="count-add">+</button></div>

                </div>
                <div class="Xcontent34"><button type="button" class="btn btn-danger " id="cart-buy" >立即购买</button></div>
                <div class="Xcontent35"> <button type="button" class="btn btn-default cart-add" id="cart-add">加购物车</button></div>
            </ol>
        </div>
    </div>
</div>
<div class="pop_up" id="demo2">
    <div class="pop_up_xx"><img src="/static/images/chacha3.png" width="40" height="40"  alt=""></div>
    <div class="pop_up2" id="ban_pic2">
        <div class="prev1" id="prev2"><img src="/static/images/index_tab_l.png" width="28" height="51"  alt=""></div>
        <div class="next1" id="next2"><img src="/static/images/index_tab_r.png" width="28" height="51"  alt=""></div>
        <ul>
            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_1()%>" width="500" height="500" alt=""></a></li>
            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_2()%>" width="500" height="500" alt=""></a></li>
            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_3()%>g" width="500" height="500" alt=""></a></li>
            <li><a href="javascript:;"><img src="/static/images/slide_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=goods.getSlide_4()%>" width="500" height="500" alt=""></a></li>
        </ul>
    </div>
</div>
</div>
<div class="container">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active" data-id="tabContent1"><a href="javascript:void(0)">详情</a></li>
        <li role="presentation" data-id="tabContent2"><a href="javascript:void(0)">评价</a></li>
        <li role="presentation" data-id="tabContent3"><a href="javascript:void(0)">相似商品</a></li>
    </ul>
    <div class="tabs-contents">
        <!-- 标题1内容区域 -->
        <div class="tab-content active" id="tabContent1">
            <!--goods display-->
            <%if(goods.getDetailPicture().isEmpty()){%>
            <p><img src="/static/images/no_data.png" width="400" height="180" alt=""></p>
            <%}else{ %>
            <% for(String pic:goods.getDetailPicture()) {%>
            <p><img src="/static/images/detail_picture/<%=goods.getCategory().getParentId()%>/<%=goods.getCategoryId()%>/<%=goods.getId()%>/<%=pic%>" width="800" height="180" alt=""></p>
            <%}%>
            <%} %>
        </div>

        <!-- 标题2内容区域 -->
        <div class="tab-content" id="tabContent2">
            <div class="container">
                <div class="shopping-car-container">
                    <div class="car-headers-menu">
                        <div class="row">
                            <div class="col-md-1 car-menu">用户名</div>
                            <div class="col-md-3 car-menu">商品评分</div>
                            <div class="col-md-6 car-menu">商品评价</div>
                        </div>
                    </div>
                    <div class="goods-content">
                        <!--goods display-->
                        <% for(EvaluateList evaluate:(List<EvaluateList>)request.getAttribute("evaluates")) {%>
                        <div class="col-md-1 goods-params"><%=evaluate.getUserName()%> </div>
                        <div class="col-md-3 goods-params">
                            <li data-default-index="0">
                                    <span>
                                         <%  for(int i = 0; i < (int)evaluate.getGrade(); i++){%>
                                        <img src="/static/images/x2.png">
                                        <%}%>
                                         <%  for(int i = (int)evaluate.getGrade(); i < 5; i++){%>
                                        <img src="/static/images/x1.png">
                                        <%}%>
                                    </span>
                                <em class="level"></em>
                            </li>

                        </div>
                        <div class="col-md-6 goods-params"><%=evaluate.getComment()%></div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>

        <!-- 标题2内容区域 -->
        <div class="tab-content" id="tabContent3">
            <div class="container">
                <div class="shopping-car-container">
                    <h1>买了该商品的人还买过</h1>
                    <div class="row">
                        <% if (request.getAttribute("alsobuyGoods") != null) { %>

                        <% for(Goods4List hg:(List<Goods4List>)request.getAttribute("alsobuyGoods")) {%>
                        <div class="col-sm-6 col-md-4">
                            <div class="thumbnail goods-item">
                                <a href="/home/productView/<%=hg.getId()%>">
                                    <img src="/static/images/cover_picture/<%=hg.getCategory().getParentId()%>/<%=hg.getCategoryId()%>/<%=hg.getId()%>/<%=hg.getUrl()%>" alt="...">
                                </a>
                                <div class="caption div-desc" id="<%=hg.getId()%>">
                                    <h3><%=hg.getName()%></h3>
                                    <p style="font-size:12px; color:dimgray"><%=hg.getDescription()%></p>
                                    <p style="color:red">&yen;<%=hg.getPrice()%></p>
                                    <p>
                                        <button type="button" class="btn btn-danger cart-buy" >立即购买</button>
                                        <button type="button" class="btn btn-default cart-add">加购物车</button>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <%}
                        }
                        %>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>

<% }%>
<!-- Site footer -->
<footer class="footer">
    <p>&copy;</p>
</footer>

</div>


<!-- /container -->


<script src="/static/js/jquery.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/static/js/ie10-viewport-bug-workaround.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/bootsnav.js"></script>
<script type="text/javascript" src="/static/js/pic_tab.js"></script>
<script type="text/javascript" src="/static/js/detail.js"></script>
<script type="text/javascript">
    jq('#demo1').banqh({
        box:"#demo1",//总框架
        pic:"#ban_pic1",//大图框架
        pnum:"#ban_num1",//小图框架
        prev_btn:"#prev_btn1",//小图左箭头
        next_btn:"#next_btn1",//小图右箭头
        pop_prev:"#prev2",//弹出框左箭头
        pop_next:"#next2",//弹出框右箭头
        prev:"#prev1",//大图左箭头
        next:"#next1",//大图右箭头
        pop_div:"#demo2",//弹出框框架
        pop_pic:"#ban_pic2",//弹出框图片框架
        pop_xx:".pop_up_xx",//关闭弹出框按钮
        mhc:".mhc",//朦灰层
        autoplay:true,//是否自动播放
        interTime:5000,//图片自动切换间隔
        delayTime:400,//切换一张图片时间
        pop_delayTime:400,//弹出框切换一张图片时间
        order:0,//当前显示的图片（从0开始）
        picdire:true,//大图滚动方向（true为水平方向滚动）
        mindire:true,//小图滚动方向（true为水平方向滚动）
        min_picnum:5,//小图显示数量
        pop_up:true//大图是否有弹出框
    })

    $('.nav-tabs li').click(function(){
        $(this).addClass('active').siblings().removeClass('active');
        var _id = $(this).attr('data-id');
        $('.tabs-contents').find('#'+_id).addClass('active').siblings().removeClass('active');

    });
</script>
</body>
</html>
