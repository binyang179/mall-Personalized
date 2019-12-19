<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ParentCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.CategoryGroup" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ChildCategory" %>
<%@ page import="com.iflysse.viewmodel.GoodsViewModel.Goods4List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<!-- Start Navigation -->
<%@ include file="/WEB-INF/jsp/head-search.jsp"%>
<!-- End Navigation -->
<div style="margin-top: 20px"></div>


<div class="clearfix"></div>


<div class="container">
    <div id="carousel-example-generic" class="carousel slide"
         data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0"
                class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="/static/images/img_fjords_wide.jpg" alt="...">
                <div class="carousel-caption">第一个商品</div>
            </div>
            <div class="item">
                <img src="/static/images/img_nature_wide.jpg" alt="...">
                <div class="carousel-caption">第二个商品</div>
            </div>
            <div class="item">
                <img src="/static/images/img_mountains_wide.jpg" alt="...">
                <div class="carousel-caption">第三个商品</div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic"
           role="button" data-slide="prev"> <span
                class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a> <a class="right carousel-control" href="#carousel-example-generic"
                role="button" data-slide="next"> <span
            class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
    </div>
</div>

<div class="container">
    <h1>热门商品</h1>
    <div class="row">
        <c:forEach items="${hotGoods}" var="hg" varStatus="status" >
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail goods-item">
                <a href="/home/productView/${hg.id}">
                    <img src="/static/images/cover_picture/${hg.category.parentId}/${hg.categoryId}/${hg.id}/${hg.url}" alt="...">
                </a>
                <div class="caption div-desc" id="${hg.id}">
                    <h3>${hg.name}</h3>
                    <p style="font-size:12px; color:dimgray">${hg.description}</p>
                    <p style="color:red">&yen;${hg.price}</p>
                    <p>
                        <button type="button" class="btn btn-danger cart-buy" >立即购买</button>
                        <button type="button" class="btn btn-default cart-add">加购物车</button>
                    </p>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>


    <!-- Jumbotron -->
    <div>
        <h1>新品推荐</h1>
        <div class="row">
            <% for(Goods4List ng:(List<Goods4List>)request.getAttribute("newsGoods")) {%>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <a href="/home/productView/<%=ng.getId()%>">
                        <img src="/static/images/cover_picture/<%=ng.getCategory().getParentId()%>/<%=ng.getCategoryId()%>/<%=ng.getId()%>/<%=ng.getUrl()%>" alt="...">
                    </a>
                    <div class="caption">
                        <h3><%=ng.getName()%></h3>
                        <p style="font-size:12px; color:dimgray"><%=ng.getDescription()%></p>
                        <p style="color:red">&yen;<%=ng.getPrice()%></p>
                        <p>
                            <button type="button" class="btn btn-danger cart-buy" >立即购买</button>
                            <button type="button" class="btn btn-default cart-add">加购物车</button>
                        </p>
                    </div>
                </div>
            </div>
            <%}%>
            <!--
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="/static/images/iphone.png" alt="...">
                    <div class="caption">
                        <h3>Thumbnail label</h3>
                        <p>...</p>
                        <p>
                            <a href="#" class="btn btn-danger" role="button">Button</a> <a
                                href="#" class="btn btn-default" role="button">Button</a>
                        </p>
                    </div>
                </div>

            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="/static/images/iphone.png" alt="...">
                    <div class="caption">
                        <h3>Thumbnail label</h3>
                        <p>...</p>
                        <p>
                            <a href="#" class="btn btn-danger" role="button">Button</a> <a
                                href="#" class="btn btn-default" role="button">Button</a>
                        </p>
                    </div>
                </div>

            </div>
            -->
        </div>
    </div>

    <c:if test="${personalGoods != null}" >
    <div>
        <h1>猜你喜欢</h1>
        <c:forEach items="${personalGoods}" var="ng" varStatus="status">
            <c:if test="${status.index % 3 == 0}">
                <div class="row">
            </c:if>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <a href="/home/productView/${ng.id}">
                        <img src="/static/images/cover_picture/${ng.category.parentId}/${ng.categoryId}/${ng.id}/${ng.url}" alt="...">
                    </a>
                    <div class="caption">
                        <h3>${ng.name}</h3>
                        <p style="font-size:12px; color:dimgray">${ng.description}</p>
                        <p style="color:red">&yen;${ng.price}</p>
                        <p>
                            <button type="button" class="btn btn-danger cart-buy" >立即购买</button>
                            <button type="button" class="btn btn-default cart-add">加购物车</button>
                        </p>
                    </div>
                </div>
                <c:if test="${status.index % 3 == 2}">
                    </div>
                </c:if>



            <!--
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="/static/images/iphone.png" alt="...">
                    <div class="caption">
                        <h3>Thumbnail label</h3>
                        <p>...</p>
                        <p>
                            <a href="#" class="btn btn-danger" role="button">Button</a> <a
                                href="#" class="btn btn-default" role="button">Button</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="/static/images/iphone.png" alt="...">
                    <div class="caption">
                        <h3>Thumbnail label</h3>
                        <p>...</p>
                        <p>
                            <a href="#" class="btn btn-danger" role="button">Button</a> <a
                                href="#" class="btn btn-default" role="button">Button</a>
                        </p>
                    </div>
                </div>

            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <img src="/static/images/iphone.png" alt="...">
                    <div class="caption">
                        <h3>Thumbnail label</h3>
                        <p>...</p>
                        <p>
                            <a href="#" class="btn btn-danger" role="button">Button</a> <a
                                href="#" class="btn btn-default" role="button">Button</a>
                        </p>
                    </div>
                </div>
            </div>
            -->
        </div>
        </c:forEach>
    </div>
    </c:if>


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

</body>
</html>
