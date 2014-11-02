<%-- 
    Document   : user
    Created on : Sep 1, 2014, 3:39:18 PM
    Author     : hungdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/prelude.jsp" %>
<html>
    <head>
        <%@include file="include/headinc.jsp" %>
    </head>
    <body class="skin-blue">
        <%@include file="include/header.jsp" %>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->

            <%@include file="include/leftmenu.jsp" %>
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Quản lý User
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.do"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Quản lý User</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="filter-container" style="margin-bottom: 10px;">
                        <span style="font-size: 17px;">Có <b>${totalRc}</b> User.</span>

                    </div>
                    <div class="box">
                        <div class="box-header">
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped" style="font-size: 12px;">
                                <thead>

                                    <tr>
                                        <th>Mã người dùng</th>
                                        <th>Tên tài khoản</th>
                                        <th>Ngày tạo</th>
                                        <th>Email</th>
                                        <th>Nhóm quyền</th>
                                        <th>Trạng thái</th>

                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${lstSize != 0}">
                                        <c:forEach items="${lstUser}" var="l">
                                            <tr style="text-align: center;">
                                                <td>${l.userId}</td>
                                                <td>${l.screenName}</td>
                                                <td>${l.createDate}</td>
                                                <td>${l.email}</td>
                                                <td>${l.roleName}</td>
                                                <td><c:if test="${l.status == 0}">
                                                        Đang mở
                                                    </c:if>
                                                    <c:if test="${l.status == 1}">
                                                        Đang khóa
                                                    </c:if></td>
                                                <td><a href="updateuser.action?act=1&uid=${l.userId}">Sửa</a></td>
                                                <td>
                                                    <c:if test="${l.status == 0}">
                                                        <a href="user.action?action=1&uid=${l.userId}">Khóa</a>
                                                    </c:if>
                                                    <c:if test="${l.status == 1}">
                                                        <a href="user.action?action=2&uid=${l.userId}">Mở khóa</a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                            <div class="row">
                            	 <div class="text-right" style="padding-right: 20px;">
                                    <ul class="pagination">
                                        <c:if test="${page > 1}">
                                            <li><a href="?p=1">Trang đầu</a></li>
                                            <li><a href="?p=${page - 1}">&laquo;</a></li>
                                         </c:if>

                                        <c:if test="${page <= 1}">
                                            <li class="disabled"><span>Trang đầu</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                            </c:if>
                                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                                        <c:if test="${page < totalPage}">
                                            <li><a href="?p=${page + 1}">&raquo;</a></li>
                                            <li><a href="?p=${totalPage}">Trang cuối</a></li>
                                        </c:if>
                                        <c:if test="${page >= totalPage}">
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Trang cuối</span></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
