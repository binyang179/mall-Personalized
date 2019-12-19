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
    <link href="/static/css/cartstyle.css" rel="stylesheet">

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


<div class="container">
    <div class="shopping-car-container">
        <div class="car-headers-menu">
            <div class="row">
                <div class="col-md-1 car-menu">
                    <label><input type="checkbox" id="check-goods-all" /><span id="checkAll">全选</span></label>
                </div>
                <div class="col-md-2 car-menu">图片</div>
                <div class="col-md-3 car-menu">商品名称</div>
                <div class="col-md-1 car-menu">单价</div>
                <div class="col-md-2 car-menu">数量</div>
                <div class="col-md-1 car-menu">金额</div>
                <div class="col-md-2 car-menu">操作</div>
            </div>
        </div>
        <div class="goods-content">
            <!--goods display-->
            <% for(GoodsCartList cart:(List<GoodsCartList>)request.getAttribute("carts")) {%>
            <div class="goods-item">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-1 car-goods-info">
                            <label><input type="checkbox" class="goods-list-item" value="<%=cart.getId()%>"/></label>
                        </div>
                        <div class="col-md-2 car-goods-info goods-image-column">
                            <img class="goods-image"
                                 src="/static/images/cover_picture/<%=cart.getParentCategoryId()%>/<%=cart.getGoods().getCategoryId()%>/<%=cart.getGoods().getId()%>/<%=cart.getGoods().getUrl()%>"
                                 style="width: 100px; height: 100px;" />
                        </div>
                        <div class="col-md-3 car-goods-info goods-params"><%=cart.getGoods().getGoodsName()%></div>
                        <div class="col-md-1 car-goods-info goods-price"><span>￥</span><span class="single-price"><%=cart.getGoods().getGoodsPrice()%></span></div>
                        <div class="col-md-2 car-goods-info goods-counts">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default car-decrease">-</button>
                                </div>
                                <input type="text" class="form-control goods-count" value="<%=cart.getNumber()%>">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default car-add">+</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1 car-goods-info goods-money-count"><span>￥</span><span class="single-total"><%=cart.getGoods().getGoodsPrice()*cart.getNumber()%></span></div>
                        <div class="col-md-2 car-goods-info goods-operate">
                            <button type="button" class="btn btn-danger item-delete">删除</button>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>

    <div class="panel panel-default">
        <div class="panel-body bottom-menu-include">
            <div class="col-md-1 check-all-bottom bottom-menu">
                <label>
                    <input type="checkbox" id="checked-all-bottom" />
                    <span id="checkAllBottom">全选</span>
                </label>
            </div>
            <div class="col-md-1 bottom-menu">
				<span id="deleteMulty">
						删除
				</span>
            </div>
            <div class="col-md-4 bottom-menu">

            </div>
            <div class="col-md-2 bottom-menu">
                <span>已选商品 <span id="selectGoodsCount">0</span> 件</span>
            </div>
            <div class="col-md-2 bottom-menu">
                <span>合计：<span id="selectGoodsMoney">0.00</span></span>
            </div>
            <div class="col-md-2 bottom-menu submitData submitDis" id="updateMulty">
                结算
            </div>
        </div>
    </div>
        <!--删除确认弹框-->
        <div class="modal fade" tabindex="-1" role="dialog" id="deleteItemTip" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                    </div>
                    <div class="modal-body">
                        确认删除此商品？
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary deleteSure">确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!--是否勾选商品提示-->
        <div class="modal fade" tabindex="-1" role="dialog" id="selectItemTip" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                    </div>
                    <div class="modal-body">
                        请选择要删除的商品！
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!--批量删除商品-->
        <div class="modal fade" tabindex="-1" role="dialog" id="deleteMultyTip" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                    </div>
                    <div class="modal-body">
                        确认删除选择的商品！
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary deleteMultySure">确定</button>
                    </div>
                </div>
            </div>
        </div>
        <!--批量购买商品-->
        <div class="modal fade" tabindex="-1" role="dialog" id="updateMultyTip" aria-labelledby="gridSystemModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                    </div>
                    <div class="modal-body">
                        确认购买这些商品？
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary updateMultySure">确定</button>
                    </div>
                </div>
            </div>
        </div>


    </div>
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
<script type="text/javascript" src="/static/js/cart.js"></script>
<script type="text/javascript" >

</script>
</body>
</html>
