<%-- 
    Document   : cskh
    Created on : Aug 26, 2014, 10:38:30 PM
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
                $("#da tepicker1").datepicker("option", "dateFormat", "dd/mm/yyyy");
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
                        Chăm sóc khách hàng
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">CSKH</li>
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
                                        <c:if test="${role == 'admin'}">


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
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" selected="selected">Mobifone</option>
                                                        <option value="2">Vinaphone</option>
                                                        <option value="3">Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==2}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" selected="selected">Vinaphone</option>
                                                        <option value="3">Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==3}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" >Vinaphone</option>
                                                        <option value="3" selected="selected">Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==4}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" >Vinaphone</option>
                                                        <option value="3" >Viettel</option>
                                                        <option value="4" selected="selected">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==0}">
                                                        <option value="0" selected="selected" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" >Vinaphone</option>
                                                        <option value="3" >Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                </select>

                                            </td> 
                                        </c:if>
                                        <c:if test="${role == 'cp'}">
                                            <td >
                                                Nhà mạng 
                                            </td>
                                            <td style="padding-left: 10px;">
                                                <select class="form-control" name="tId">

                                                    <c:if test="${tId == 1}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" selected="selected">Mobifone</option>
                                                        <option value="2">Vinaphone</option>
                                                        <option value="3">Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==2}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" selected="selected">Vinaphone</option>
                                                        <option value="3">Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==3}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" >Vinaphone</option>
                                                        <option value="3" selected="selected">Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==4}">
                                                        <option value="0" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" >Vinaphone</option>
                                                        <option value="3" >Viettel</option>
                                                        <option value="4" selected="selected">Vietnamobile</option>
                                                    </c:if>
                                                    <c:if test="${tId ==0}">
                                                        <option value="0" selected="selected" >Tất cả</option>
                                                        <option value="1" >Mobifone</option>
                                                        <option value="2" >Vinaphone</option>
                                                        <option value="3" >Viettel</option>
                                                        <option value="4">Vietnamobile</option>
                                                    </c:if>
                                                </select>

                                            </td> 
                                        </c:if>
                                    </tr>
                                    <tr>
                                        <td>Từ ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker" type="text" name="f" value="${fromDate}"/></td>
                                        <td style="padding-left: 15px;">Đến ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker1" type="text " name="t" value="${toDate}"/></td>
                                    </tr>
                                    <tr>
                                        <td>ISDN</td>
                                        <td style="padding-left: 10px;"> 
                                            <input class="form-control"  name ="isdn" value="${isdn}">
                                        </td>
                                        <td style="padding-left: 15px;">Telco ServiceID</td>
                                        <td style="padding-left: 10px;"> 
                                            <input class="form-control"  name ="tsId" value="${telcoServiceId}">
                                        </td>
                                        <td style="padding-left: 15px;">ProductID</td>
                                        <td style="padding-left: 10px;">
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
                                    <tr>
                                        <td>Hành động</td>
                                        <td style="padding-left: 10px;"> 
                                            <select class="form-control" name="action">


                                                <c:if test="${action == ''}">
                                                    <option value="" selected="selected">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt">Gửi SMS</option>
                                                    <option value="confirm">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action == 'register'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" selected="selected">Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt">Gửi SMS</option>
                                                    <option value="confirm">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action == 'unregister'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister" selected="selected">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt">Gửi SMS</option>
                                                    <option value="confirm">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action =='otp-req'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req" selected="selected">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt">Gửi SMS</option>
                                                    <option value="confirm">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action == 'sub-req'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req" selected="selected">Request Sub</option>
                                                    <option value="send-mt">Gửi SMS</option>
                                                    <option value="confirm">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action == 'send-mt'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt" selected="selected">Gửi SMS</option>
                                                    <option value="confirm">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action == 'confirm'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt" >Gửi SMS</option>
                                                    <option value="confirm" selected="selected">Trả lời câu hỏi</option>
                                                    <option value="smsdaily">Gửi tin hằng ngày</option>
                                                </c:if>
                                                <c:if test="${action == 'smsdaily'}">
                                                    <option value="0">Tất cả</option>
                                                    <option value="register" >Đăng ký</option>
                                                    <option value="unregister">Hủy dv</option>
                                                    <option value="otp-req">Request OTP</option>
                                                    <option value="sub-req">Request Sub</option>
                                                    <option value="send-mt" >Gửi SMS</option>
                                                    <option value="confirm" >Trả lời câu hỏi</option>
                                                    <option value="smsdaily" selected="selected">Gửi tin hằng ngày</option>
                                                </c:if>
                                            </select>
                                        </td>
                                    </tr>

                                </table>

                            </div><!-- /.box-body -->

                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                            </div>
                        </form>
                    </div><!-- /.box -->
                    <div class="filter-container" style="margin-bottom: 10px;">
                        <span style="font-size: 17px;">Có <b>${totalRc}</b> bản ghi.</span>

                    </div>
                    <div class="box">
                        <div class="box-header">
                        </div><!-- /.box-header -->

                        <div class="box-body table-responsive">
                            <!--                            <div class="row"><div class="col-xs-6"><div id="example1_length" class="dataTables_length">
                                                                    <label>
                                                                        <form>
                                                                        <select size="1" name="r" onchange="javascript: if(this.value != '0') this.form.submit(); ">
                            <c:if test="${rowDisplay == 20}">
                                <option value="0">0</option>
                                <option value="20" selected="selected">20</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </c:if>
                            <c:if test="${rowDisplay == 25}">
                                <option value="0">0</option>
                                <option value="20">20</option>
                                <option value="25"  selected="selected">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </c:if>
                            <c:if test="${rowDisplay == 50}">
                                <option value="0">0</option>
                                <option value="20">20</option>
                                <option value="25" >25</option>
                                <option value="50"  selected="selected">50</option>
                                <option value="100">100</option>
                            </c:if>
                            <c:if test="${rowDisplay == 100}">
                                <option value="0">0</option>
                                <option value="20">20</option>
                                <option value="25" >25</option>
                                <option value="50"  >50</option>
                                <option value="100" selected="selected">100</option>
                            </c:if>


                        </select>
                        </form>
                            records per page</label>
                </div></div>
        </div>-->
                            <table id="example1" class="table table-bordered table-striped" style="font-size: 12px;">
                                <thead>

                                    <tr>
                                        <th>ID</th>

                                        <th>Đối tác</th>
                                        <th>Thời gian</th>
                                        <th>Kênh</th>
                                        <th>Hành động</th>
                                        <th>Số thuê bao</th>
                                        <th>Tên dv</th>
                                        <th>Đầu số</th>
                                        <th>Nhà mạng</th>
                                        <th>Nội dung</th>
                                        <th>Status</th>
                                        <th>Cause</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${lstSize != 0}">
                                        <c:forEach items="${lst}" var="l">
                                            <tr style="text-align: center;">
                                                <td>${l.orderId}</td>

                                                <td>${l.merchant}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${l.createDate}" /></td>
                                                <td><c:if test="${l.motype == 0}">
                                                        SMS
                                                    </c:if>
                                                    <c:if test="${l.motype == 1}">
                                                        OTP
                                                    </c:if></td>
                                                <td><c:if test="${l.orderType == 'otp-req'}">
                                                        Request OTP
                                                    </c:if>
                                                    <c:if test="${l.orderType == 'sub-req'}">
                                                        Request Sub
                                                    </c:if>
                                                    <c:if test="${l.orderType == 'unregister'}">
                                                        Hủy dv
                                                    </c:if>
                                                    <c:if test="${l.orderType == 'register'}">
                                                        DK dv
                                                    </c:if>
                                                    <c:if test="${l.orderType == 'send-mt'}">
                                                        Gửi sms
                                                    </c:if>
                                                    <c:if test="${l.orderType == 'confirm'}">
                                                        Trả lời câu hỏi
                                                    </c:if>

                                                </td>
                                                <td>${l.isdn}</td>
                                                <td>${l.productTitle}</td>
                                                <td>${l.serviceAddr}</td>
                                                <td><c:if test="${l.telcoId == 1}">
                                                        Mobifone
                                                    </c:if>
                                                    <c:if test="${l.telcoId == 2}">
                                                        Vinaphone
                                                    </c:if>
                                                    <c:if test="${l.telcoId == 3}">
                                                        Viettel
                                                    </c:if>
                                                    <c:if test="${l.telcoId == 4}">
                                                        Vietnamobile
                                                    </c:if></td>
                                                <td>${l.shipTo}</td>
                                                <td>${l.status}</td>
                                                <td>${l.cause}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>

                                </tbody>

                            </table>
                            <div class="row">
                                <!--                                <div class="text-left" style="padding-left: 20px;padding-top: 10px">
                                                                    <div class="dataTables_info" id="example1_info">
                                <c:if test="${page == 1}">
                                    Showing 1 to ${rowDisplay} of ${totalRc} entries
                                </c:if>
                                <c:if test="${page >= 2}">
                                    Showing ${rowDisplay * page - rowDisplay + 1} to ${rowDisplay * page} of ${totalRc} entries
                                </c:if>
                            </div>

                        </div>-->
                                <div class="text-right" style="padding-right: 20px;">
                                    <ul class="pagination">
                                        <c:if test="${page > 1}">
                                            <li><a href="?p=1&isdn=${isdn}&pId=${productId}&tsId=${telcoServiceId}&mId=${merchantId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&action=${action}">Trang đầu</a></li>
                                            <li><a href="?p=${page - 1}&isdn=${isdn}&pId=${productId}&tsId=${telcoServiceId}&mId=${merchantId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&action=${action}">&laquo;</a></li>
                                            </c:if>

                                        <c:if test="${page <= 1}">
                                            <li class="disabled"><span>Trang đầu</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                            </c:if>

                                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                                        <c:if test="${page < totalPage}">
                                            <li><a href="?p=${page + 1}&isdn=${isdn}&pId=${productId}&tsId=${telcoServiceId}&mId=${merchantId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&action=${action}">&raquo;</a></li>
                                            <li><a href="?p=${totalPage}&isdn=${isdn}&pId=${productId}&tsId=${telcoServiceId}&mId=${merchantId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&action=${action}">Trang cuối</a></li>
                                            </c:if>
                                            <c:if test="${page >= totalPage}">
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Trang cuối</span></li>
                                            </c:if>
                                    </ul>

                                </div>
                            </div>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <!-- add new calendar event modal -->


        <%@include file="include/footer.jsp" %>
    </body>
</html>
