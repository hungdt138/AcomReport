<%-- 
    Document   : subreportbycmdcode
    Created on : Oct 9, 2014, 10:27:48 PM
    Author     : hungdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/prelude.jsp" %>
<html>
    <head>
        <%@include file="include/headinc.jsp" %>
        <script>
            $(function() {
                $("#datepicker").datepicker("option", "dateFormat", "dd/mm/yyyy");
                $("#datepicker1").datepicker("option", "dateFormat", "dd/mm/yyyy");
            });
        </script>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <%@include file="include/header.jsp" %>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <%@include file="include/leftmenu.jsp" %>
            <!-- Right side column. Contains the navbar and content of the page -->

            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Báo cáo thuê bao Subscription
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Sub Report</li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <!-- general form elements -->
                    <div class="box box-primary">
                        <!-- form start -->
                        <form>
                            <div class="box-body">
                                <table border = "0" cellpadding = "0" cellspacing = "0">
                                    <tr>


                                        <td >
                                            Nhà mạng 
                                        </td>
                                        <td style="padding-left: 10px;">
                                            <select class="form-control" name="tId">
                                                <c:if test="${tId == 1}">

                                                    <option value="1" selected="selected">Mobifone</option>
                                                    <option value="2">Vinaphone</option>
                                                    <option value="3">Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${tId ==2}">

                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" selected="selected">Vinaphone</option>
                                                    <option value="3">Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${tId ==3}">

                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" >Vinaphone</option>
                                                    <option value="3" selected="selected">Viettel</option>
                                                    <option value="4">Vietnamobile</option>
                                                </c:if>
                                                <c:if test="${tId ==4}">

                                                    <option value="1" >Mobifone</option>
                                                    <option value="2" >Vinaphone</option>
                                                    <option value="3" >Viettel</option>
                                                    <option value="4" selected="selected">Vietnamobile</option>
                                                </c:if>

                                            </select>

                                        </td> 
                                        <c:choose>
                                            <c:when test="${role == 'cp'}">

                                            </c:when>
                                            <c:when test="${role == 'admin'}">
                                                <td style="padding-left: 15px;">
                                                    Đối tác:
                                                </td>
                                                <td style="padding-left: 10px;">
                                                    <select  name="mId" class="form-control">
                                                        <option value="0">Tất cả</option>
                                                        <c:forEach items="${lstmerchant}" var="e" step="1" begin="0">

                                                            <c:if test="${merchantId == e.merchantId}">
                                                                <option value="${e.merchantId}" selected="selected">${e.alias}</option>
                                                            </c:if>
                                                            <c:if test="${merchantId != e.merchantId}">
                                                                <option value="${e.merchantId}">${e.alias}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </c:when>
                                        </c:choose>


                                    </tr>

                                    <tr>
                                        <td>Từ ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker" type="text" name="f" value="${fromDate}"/></td>
                                        <td style="padding-left: 15px;">Đến ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker1" type="text " name="t" value="${toDate}"/></td>
                                        <td style="padding-left: 15px;">Product</td>
                                        <td style="padding-left: 10px;" colspan="2">
                                            <select  name="pId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${lstProductCat}" var="e" step="1" begin="0">

                                                    <c:if test="${productId == e.productId}">
                                                        <option value="${e.productId}" selected="selected">${e.title}</option>
                                                    </c:if>
                                                    <c:if test="${productId != e.productId}">
                                                        <option value="${e.productId}">${e.title}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>

                                </table>
                            </div>

                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Thống kê</button>
                            </div>
                        </form>
                    </div>

                    <div class="row">
                        <div class="box-header">
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>

                                    <tr>
                                        <th style="width: 10%">Mã dịch vụ</th>
                                        <th>Kênh SMS</th>
                                        <th>Kênh OTP</th>
                                        <th>Tổng đăng ký</th>
                                        <th>Tăng trưởng thuê bao</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${lst}" var="l">
                                        <tr>
                                            <td>${l.cmdCode}</td>
                                            <td>${l.smsChannel}</td>
                                            <td>${l.otpChannel}</td>
                                            <td>${l.reg}</td>
                                            <td>${l.total}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div><!-- /.row (main row) -->
                </section>
            </aside> 
        </div>

        <%@include file="include/footer.jsp" %>
    </body>
</html>
