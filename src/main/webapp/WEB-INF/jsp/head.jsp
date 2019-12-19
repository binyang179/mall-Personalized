
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <script src="${pageContext.request.contextPath}/static/js/capture.js"></script>
    <link href="/static/css/chooseprefer.css" rel="stylesheet">
</head>
<html lang="en">
<div style="background-color: black;height:50px;">
    <div class="container" id="header_info" style="line-height:50px;text-align: right;color:#C9C9C9;">
        <a class="a-order" href="http://localhost:8080/home">主页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a class="a-order">我的订单</a> &nbsp;&nbsp;|&nbsp;&nbsp;
        <a class="a-cart">购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <%if(session.getAttribute("userName")==null){%>
        <a class="a-login">登录 / 注册 </a> <a class="a-loginOut" style="display: none">注销</a>
         <%}else{ %>
        <a class="a-login" style="display: none">登录 / 注册 </a> <a class="a-loginOut" >注销</a>
        <%} %>

    </div>
    <!-- 登陆界面 -->
    <div class="modal fade" id="myLogin">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title" align="center">登录</h4>
                    <br/>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="name" class="col-sm-offset-2 col-sm-2 control-label">账号</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="name" placeholder="请输您的入账号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="password" placeholder="请输入您的密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-5">
                                <button id="btn-login" type="button" class="btn btn-default btn-block btn-primary">登陆</button>
                                <a id="jump">跳转至注册页面</a>
                                <br/>
                                <a id="jumpface"  class="btn btn-default btn-block btn-primary">跳转至人脸注册和登录页面</a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                </div>
            </div>
            <!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->








    <!-- 注册界面 -->
    <div class="modal fade" id="myReg">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title" align="center">注册</h4>
                    <br/>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="name" class="col-sm-offset-2 col-sm-2 control-label">账号</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="regName" placeholder="请输您的入账号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-offset-2 col-sm-2 control-label">密码</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="regPwd" placeholder="请输入您的密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-offset-2 col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="regPwdRepeat" placeholder="再次输入您的密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-5">
                                <button id="btn-reg" type="button" class="btn btn-default btn-block btn-primary">注册</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                </div>
            </div>
            <!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->



    <!-- 人脸注册界面 -->
    <div class="modal fade" id="myRegface">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title" align="center">人脸注册和登录</h4>
                    <br/>

                    <form class="form-horizontal" role="form" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="name" class="col-sm-offset-2 col-sm-2 control-label">输入账号</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="regnameface" id="regnameface" placeholder="请输您的入账号">
                            </div>
                        </div>
                        <div>
                            <%--                            <input id="upload" type="file" accept="image/*;" capture="camera" >--%>

                            <div class="camera">
                                <video id="video">Video stream not available.</video>
                                <br/>
                                <button id="startbutton">Take photo</button>
                            </div>
                            <canvas id="canvas" hidden ></canvas>
                            <br/>
                            <div class="output">
                                <%--                                <img style="display: none;" id="photo"--%>
                                <img id="photo" name="photo"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-5">
                                <button id="btn-regface" class="btn btn-default btn-block btn-primary">
                                    人脸注册
                                </button>
                            </div>
                            <br/>
                            <br/>
                            <div class="col-sm-offset-4 col-sm-5">
                                <button id="btn-loginFace" class="btn btn-default btn-block btn-primary">
                                    人脸登录
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                </div>
            </div>
            <!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->





    <!--用户偏号选择-->
    <div class="modal fade" tabindex="-1" role="dialog" id="myPrefer" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">用户偏好选择</h4>
                </div>
                <div class="modal-body" style="height: 300px;" >
                    <div class="prefer-choose clearprefer">
                        <div class="prefer-choose-checkbox">
                            <ul class="clearprefer">
                                <% if (request.getAttribute("categories") != null) { %>
                                <% for(ParentCategory parent:(List<ParentCategory>)request.getAttribute("categories")) {%>
                                <li class="prefer-choose-check" id="<%=parent.getId()%>"><%=parent.getTitle()%><i class="iconfont icon-checked"></i></li>
                                <% } %>
                                <% }%>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btnPrefer" type="button" class="btn btn-primary" >确定</button>
                </div>
            </div>
        </div>
    </div>

    <!--信息提示-->
    <div class="modal fade" tabindex="-1" role="dialog" id="infoTip" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                </div>
                <div class="modal-body" id="divTip">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

</div>



<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/ie10-viewport-bug-workaround.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/bootsnav.js"></script>
<script type="text/javascript" src="/static/js/login.js"></script>

</body>
</html>
