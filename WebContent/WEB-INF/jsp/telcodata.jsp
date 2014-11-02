<%-- 
    Document   : telcodata
    Created on : Oct 5, 2014, 2:39:00 PM
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
                        Dữ liệu nhà mạng
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Telco Data</li>
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

                                        <td>ISDN</td>
                                        <td style="padding-left: 10px;"> 
                                            <input class="form-control"  name ="isdn" value="${isdn}">
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



                                    </tr>
                                    <tr>
                                        <td>Từ ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker" type="text" name="f" value="${fromDate}"/></td>
                                        <td style="padding-left: 15px;">Đến ngày</td>
                                        <td style="padding-left: 10px;"><input class="form-control date-picker" id="datepicker1" type="text " name="t" value="${toDate}"/></td>
                                        <td style="padding-left: 15px;">ProductID</td>
                                        <td style="padding-left: 10px;">
                                            <select  name="pId" class="form-control">
                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${lstProductCat}" var="e" step="1" begin="0">

                                                    <c:if test="${productId == e.alias}">
                                                        <option value="${e.alias}" selected="selected">${e.title}</option>
                                                    </c:if>
                                                    <c:if test="${productId != e.alias}">
                                                        <option value="${e.alias}">${e.title}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td >
                                            Giá tiền
                                        </td>
                                        <td style="padding-left: 10px;">
                                            <select class="form-control" name="price">

                                                <option value="0">Tất cả</option>
                                                <c:forEach items="${lstPrice}" var="e" step="1" begin="0">

                                                    <c:if test="${price == e}">
                                                        <option value="${e}" selected="selected">${e}</option>
                                                    </c:if>
                                                    <c:if test="${price != e}">
                                                        <option value="${e}">${e}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>

                                        </td> 

                                        <td style="padding-left: 15px;">
                                            Trạng thái
                                        </td>
                                        <td style="padding-left: 10px;">
                                            <select class="form-control" name="status">

                                               <option value="5">Tất cả</option>
                                                <c:forEach items="${lstStatus}" var="e" step="1" begin="0">

                                                    <c:if test="${status == e}">
                                                        <option value="${e}" selected="selected">
                                                            <c:if test="${e == 0}">Thành công</c:if>
                                                            <c:if test="${e != 0}">Không thành công</c:if>
                                                        </option>
                                                    </c:if>
                                                    <c:if test="${status != e}">
                                                        <option value="${e}">
                                                            <c:if test="${e == 0}">Thành công</c:if>
                                                            <c:if test="${e != 0}">Không thành công</c:if>
                                                        </option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>

                                        </td> 
                                    </tr>


                                </table>
                                <span style="font-size: 13px; font-style: italic;color: red;">- Để chọn trạng thái charge tiền cần phải chọn nhà mạng trước.</span>
                            </div>
                            <div class="box-footer">
                                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                            </div>
                        </form>
                    </div>

                    <div class="filter-container" style="margin-bottom: 10px;">
                        <span style="font-size: 20px;"><a href="revenue.action">Biểu đồ sản lượng</a></span><br>
                        <span style="font-size: 17px;">Có <b>${totalRc}</b> bản ghi.</span>

                    </div>

                    <div class="box">
                        <div class="box-header">
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped" style="font-size: 12px;">
                                <thead>

                                    <tr>

                                        <th>Số thuê bao</th>
                                        <th>Thời gian</th>
                                        <th>Mã dv nhà mạng</th>
                                        <th>Mã dv hệ thống</th>
                                        <th>Kiểu trừ tiền</th>
                                        <th>Giá tiền</th>
                                        <th>Nhà mạng</th>
                                        <th>Thuê bao nhận MT</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${lstSize != 0}">
                                        <c:forEach items="${lst}" var="l">
                                            <tr style="text-align: center;">
                                                <td>${l.isdn}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${l.orderDate}" /></td>
                                                <td>${l.serviceID}</td>
                                                <td>${l.productId}</td>
                                                <td>${l.chargeMode}</td>
                                                <td>${l.price}</td>
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
                                                <td>${l.thirdparty}</td>
                                                <td><c:if test="${l.telcoId == 1}">
                                                        <c:if test="${l.chargeResult == 1}">
                                                            Thành công
                                                        </c:if>
                                                        <c:if test="${l.chargeResult == 0}">
                                                            Không thành công
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${l.telcoId == 2}">
                                                        <c:if test="${l.chargeResult == 0}">
                                                            Thành công
                                                        </c:if>
                                                        <c:if test="${l.chargeResult != 0}">
                                                            Không thành công
                                                        </c:if>
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
                                            <li><a href="?p=1&isdn=${isdn}&pId=${productId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&price=${price}&status=${status}">Trang đầu</a></li>
                                            <li><a href="?p=${page - 1}&isdn=${isdn}&pId=${productId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&price=${price}&status=${status}">&laquo;</a></li>
                                            </c:if>

                                        <c:if test="${page <= 1}">
                                            <li class="disabled"><span>Trang đầu</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                            </c:if>

                                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                                        <c:if test="${page < totalPage}">
                                            <li><a href="?p=${page + 1}&isdn=${isdn}&pId=${productId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&price=${price}&status=${status}">&raquo;</a></li>
                                            <li><a href="?p=${totalPage}&isdn=${isdn}&pId=${productId}&tId=${tId}&f=${fromDate}&t=${toDate}&r=${rowDisplay}&price=${price}&status=${status}">Trang cuối</a></li>
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
