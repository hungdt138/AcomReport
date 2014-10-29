<%-- 
    Document   : doisoat
    Created on : Oct 2, 2014, 11:29:51 PM
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
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dữ liệu đối soát
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Đối soát</li>
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


                                        <td>
                                            Đối tác:
                                        </td>
                                        <td style="padding-left: 10px;">
                                            <select  name="mId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${partner}" var="e" step="1" begin="0">

                                                    <c:if test="${merchantId == e.merchantId}">
                                                        <option value="${e.merchantId}" selected="selected">${e.alias}</option>
                                                    </c:if>
                                                    <c:if test="${merchantId != e.merchantId}">
                                                        <option value="${e.merchantId}">${e.alias}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td style="padding-left: 15px;">
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
                                          <td style="padding-left: 15px;">
                                            Kiểu dịch vụ 
                                        </td>
                                        <td style="padding-left: 10px;">
                                            <select class="form-control" name="type">
                                                 <c:if test="${type == 'sub'}">
                                                    <option value="sub" selected="selected">Sub</option>
                                                    <option value="nonsub">Nonsub</option>
                                                </c:if>
                                                    <c:if test="${type == 'nonsub'}">
                                                    <option value="sub" >Sub</option>
                                                    <option value="nonsub" selected="selected">Nonsub</option>
                                                </c:if>
                                            </select>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>Từ ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker" type="text" name="f" value="${fromDate}"/></td>
                                        <td style="padding-left: 15px;">Đến ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker1" type="text " name="t" value="${toDate}"/></td>
                                    </tr>

                                </table>

                            </div><!-- /.box-body -->

                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                            </div>
                        </form>
                    </div>

                    <div class="box">
                        <div class="box-header">
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped" style="font-size: 12px;">
                                <thead>
                                    <tr>
                                        <th>Giá cước</th>
                                         <th>Kênh SMS</th>
                                        <th>Kênh OTP</th>
                                        <th>Tổng MO</th>
                                        <th>Tổng MT</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${lstSubSize != 0}">
                                        <c:forEach items="${lstSub}" var="l">
                                        <td>${l.price}</td>
                                        <td>${l.smsChannel}</td>
                                        <td>${l.otpChannel}</td>
                                        <td>${l.mo}</td>
                                        <td>${l.mt}</td>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${lstNonSubSize != 0}">
                                    <c:forEach items="${lstNonsub}" var="l">
                                        <td>${l.price}</td>
                                        <td>${l.smsChannel}</td>
                                        <td>${l.otpChannel}</td>
                                         <td>${l.mo}</td>
                                        <td>${l.mt}</td>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </aside>
        </div>
                                    <%@include file="include/footer.jsp" %>
    </body>
</html>
