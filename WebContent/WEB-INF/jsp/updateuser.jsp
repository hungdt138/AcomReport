<%-- 
    Document   : updateuser
    Created on : Sep 1, 2014, 5:32:03 PM
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
                        <c:if test="${act == '2'}">
                            Thêm tài khoản
                        </c:if>
                        <c:if test="${act == '1'}">
                            Sửa tài khoản <b>${editName}</b>
                        </c:if>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.do"><i class="fa fa-dashboard"></i> Home</a></li>
                            <c:if test="${act == '2'}">
                            <li class="active">Thêm tài khoản</li>
                            </c:if>
                            <c:if test="${act == '1'}">
                            <li class="active">Sửa tài khoản</li>
                            </c:if>

                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="box box-primary">
                            <form role="form">
                                <div class="box-body">
                                    <c:if test="${error != ''}">
                                        <div class="alert alert-danger alert-dismissable">
                                            <i class="fa fa-ban"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b>Error!</b> ${error}
                                        </div>
                                    </c:if>
                                    <c:if test="${note != ''}">
                                        <div class="alert alert-success alert-dismissable">
                                            <i class="fa fa-ban"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                            <b>Success!</b> ${note}
                                        </div>
                                    </c:if>
                                    <c:if test="${act == '2'}">
                                        <div class="form-group" style="width: 50%;">
                                            <input type="hidden" name="act" id="orderType" value="${act}">
                                            <input type="hidden" name="a" id="orderType" value="1">
                                            <label for="username">Tên đăng nhập</label>
                                            <input type="text" class="form-control" id="exampleInputEmail1" name="u">
                                            <label for="password">Mật khẩu</label>
                                            <input type="password" class="form-control" id="exampleInputEmail1" name="p">
                                            <label for="exampleInputEmail1">Email</label>
                                            <input type="email" class="form-control" id="exampleInputEmail1" name="e">
                                            <label>Đối tác</label>
                                            <select name="mId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${partner}" var="e" step="1" begin="0">

                                                        <option value="${e.merchantId}">${e.alias}</option>

                                                </c:forEach>
                                            </select>
                                            <label >Nhà mạng</label>
                                            <select name="tId" class="form-control">

                                                <option value="0" selected="selected" >Tất cả</option>
                                                <option value="1" >Mobifone</option>
                                                <option value="2" >Vinaphone</option>
                                                <option value="3" >Viettel</option>
                                                <option value="4">Vietnamobile</option>

                                            </select>
                                            <label >Nhóm quyền</label>
                                            <select name="pId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${role1}" var="e" step="1" begin="0">


                                                    <option value="${e.roleId}">${e.roleName}</option>

                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:if>
                                    <c:if test="${act == '1'}">
                                        <div class="form-group" style="width: 50%;">
                                            <input type="hidden" name="act" id="orderType" value="${act}">
                                            <input type="hidden" name="a" id="orderType" value="2">
                                            <input type="hidden" name="uid" id="orderType" value="${u.userId}">
                                            <label for="username">Tên đăng nhập</label>
                                            <input type="text" class="form-control" name="u" value="${u.screenName}" disabled>
                                            <label for="exampleInputEmail1">Email</label>
                                            <input type="email" class="form-control" name="e" value="${u.email}">
                                            <label>Đối tác</label>
                                            <select name="mId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${partner}" var="e" step="1" begin="0">

                                                    <c:if test="${u.merchantId == e.merchantId}">
                                                        <option value="${e.merchantId}" selected="selected">${e.alias}</option>
                                                    </c:if>
                                                    <c:if test="${u.merchantId != e.merchantId}">
                                                        <option value="${e.merchantId}">${e.alias}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label >Nhà mạng</label>
                                            <select name="tId" class="form-control">
                                                <c:if test="${u.telcoId == 1}">
                                                    <option value="0" >Tất cả</option>
                                                    <option value="1" selected="selected">Mobifone</option>
                                                    <option value="2">Vinaphone</option>
                                                    <option value="3">Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${u.telcoId ==2}">
                                                    <option value="0" >Tất cả</option>
                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" selected="selected">Vinaphone</option>
                                                    <option value="3">Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${u.telcoId ==3}">
                                                    <option value="0" >Tất cả</option>
                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" >Vinaphone</option>
                                                    <option value="3" selected="selected">Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${u.telcoId ==4}">
                                                    <option value="0" >Tất cả</option>
                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" >Vinaphone</option>
                                                    <option value="3" >Viettel</option>
                                                    <option value="4" selected="selected">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${u.telcoId  ==0}">
                                                    <option value="0" selected="selected" >Tất cả</option>
                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" >Vinaphone</option>
                                                    <option value="3" >Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                            </select>
                                            <label >Nhóm quyền</label>
                                            <select name="pId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${role1}" var="e" step="1" begin="0">

                                                    <c:if test="${u.roleId == e.roleId}">
                                                        <option value="${e.roleId}" selected="selected">${e.roleName}</option>
                                                    </c:if>
                                                    <c:if test="${u.roleId != e.roleId}">
                                                        <option value="${e.roleId}">${e.roleName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <br>
                                            <label><b>Đổi mật khẩu</b></label><br>
                                            <label >Mật khẩu mới</label>
                                            <input type="password" class="form-control"  name="p">
                                            <label >Nhập lại mật khẩu mới</label>
                                            <input type="password" class="form-control"  name="p1">
                                        </div>
                                    </c:if>
                                    <p style="font-style: italic;color: red;">* Đối với quyền admin không cần chọn Đối tác và Nhà mạng</p>
                                </div>
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
