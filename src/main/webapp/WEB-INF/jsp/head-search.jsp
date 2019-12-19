<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ParentCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.CategoryGroup" %>
<%@ page import="com.iflysse.viewmodel.CategoryViewModel.ChildCategory" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<!-- Start Navigation -->
<nav class="navbar navbar-default navbar-brand-top bootsnav">
    <div class="container">
        <div style="height: 100px">科大迅飞第三组</div>
        <div id="custom-search-input">
            <div class="input-group col-md-12">
                <input type="text" class="search-query form-control"
                       placeholder="Search"/> <span class="input-group-btn">
					<button class="btn btn-danger btn-search" type="button">
						<span class=" glyphicon glyphicon-search"></span>
					</button>
				</span>
            </div>
        </div>
    </div>

    <div class="container">

        <!-- End Header Navigation -->

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-menu">
            <ul class="nav navbar-nav" data-in="fadeInDown" data-out="fadeOutUp">

                <c:forEach items="${categories}" var="parent" varStatus="status">
                <li class="dropdown megamenu-fw">
                    <a href="#" class=""
                       data-toggle="dropdown">${parent.title}
                    </a>

                    <ul class="dropdown-menu megamenu-content" role="menu">
                        <li>
                            <c:forEach items="${parent.groups}" var="group" varStatus="gStatus">
                               <c:if test="${gStatus.index%4 == 0}">
                                   <div class="row" style="margin-bottom: 10px">
                               </c:if>
                                <div class="col-menu col-md-3"
                                        <c:if test="${gStatus.index % 4 == 1}"> style="border-right: none;"</c:if>
                                    >
                                    <h6 class="title">${group.groupName}
                                    </h6>
                                    <div class="content">
                                        <ul class="menu-col">
                                            <c:forEach items="${group.categories}" var="child" varStatus="cStatus">
                                                <c:if test="${cStatus.index < 5}">
                                                    <li>
                                                        <a href="/home/product/${child.id}/9/0">${child.title}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <!-- end col-3 -->
                               <c:if test="${gStatus.index%4 == 3}">
                                   </div>
                               </c:if>
                            </c:forEach>
                            <!-- end row -->
                        </li>
                    </ul>
                </li>
                </c:forEach>
        </div>
        <!-- /.navbar-collapse -->
    </div>
</nav>
<!-- End Navigation -->



<script src="/static/js/jquery.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/static/js/ie10-viewport-bug-workaround.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/bootsnav.js"></script>
<script type="text/javascript" src="/static/js/search.js"></script>
</body>
</html>
