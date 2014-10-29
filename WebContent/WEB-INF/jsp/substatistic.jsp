<%-- 
    Document   : substatistic
    Created on : Sep 7, 2014, 10:18:37 PM
    Author     : hungdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/prelude.jsp" %>
<html>
    <head>
        <%@include file="include/headinc.jsp" %>
        <script src="./js/highcharts.js"></script>
        <script src="./js/modules/exporting.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#container1').highcharts({
                    title: {
                        text: 'Thống kê subscription',
                        x: -20 //center
                    },
                    subtitle: {
                        text: 'Acom',
                        x: -20
                    },
                    xAxis: {
                        categories: ${lstDate}
                    },
                    yAxis: {
                        title: {
                            text: 'Thuê bao active'
                        },
                        plotLines: [{
                                value: 0,
                                width: 1,
                                color: '#808080'
                            }]
                    },
                    tooltip: {
                        valueSuffix: 'sub'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: [{
                            name: 'Mobifone',
                            data: ${lstMobifone}
                        }, {
                            name: 'Vinaphone',
                            data: ${lstVinaphone}
                        },
//                                {
//                            name: 'Viettel',
//                            data: ${lstViettel}
//                        }, 
                        {
                            name: 'Vietnamobile',
                            data: ${lstVNM}
                        }]
                });
            });

            /**
             * Grid-light theme for Highcharts JS
             * @author Torstein Honsi
             */

// Load the fonts
            Highcharts.createElement('link', {
                href: 'http://fonts.googleapis.com/css?family=Dosis:400,600',
                rel: 'stylesheet',
                type: 'text/css'
            }, null, document.getElementsByTagName('head')[0]);

            Highcharts.theme = {
                colors: ["#7cb5ec", "#f7a35c", "#90ee7e", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
                    "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
                chart: {
                    backgroundColor: null,
                    style: {
                        fontFamily: "Dosis, sans-serif"
                    }
                },
                title: {
                    style: {
                        fontSize: '16px',
                        fontWeight: 'bold',
                        textTransform: 'uppercase'
                    }
                },
                tooltip: {
                    borderWidth: 0,
                    backgroundColor: 'rgba(219,219,216,0.8)',
                    shadow: false
                },
                legend: {
                    itemStyle: {
                        fontWeight: 'bold',
                        fontSize: '13px'
                    }
                },
                xAxis: {
                    gridLineWidth: 1,
                    labels: {
                        style: {
                            fontSize: '12px'
                        }
                    }
                },
                yAxis: {
                    minorTickInterval: 'auto',
                    title: {
                        style: {
                            textTransform: 'uppercase'
                        }
                    },
                    labels: {
                        style: {
                            fontSize: '12px'
                        }
                    }
                },
                plotOptions: {
                    candlestick: {
                        lineColor: '#404048'
                    }
                },
                // General
                background2: '#F0F0EA'

            };

// Apply the theme
            Highcharts.setOptions(Highcharts.theme);

        </script>
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
                                    <c:choose>
                                        <c:when test="${role == 'cp'}">

                                        </c:when>
                                        <c:when test="${role == 'admin'}">
                                            <tr>
                                                <td>
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

                                            </tr>
                                        </c:when>
                                    </c:choose>


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
                    <div class="filter-container" style="margin-bottom: 10px;">
                        <div id="container1" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

                    </div>
                    <div class="row">
                        <div class="box-header">
                        </div><!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>

                                        <th colspan="5" >Mobifone</th>
                                    </tr>
                                    <tr>
                                        <th style="width: 10%">Thời gian</th>
                                        <th>Kênh SMS</th>
                                        <th>Kênh OTP</th>
                                        <th>Tổng đăng ký</th>
                                        <th>Tăng trưởng thuê bao</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${mobi}" var="l">
                                        <tr>
                                            <td>${l.dateTime}</td>
                                            <td>${l.smsChannel}</td>
                                            <td>${l.otpChannel}</td>
                                            <td>${l.reg}</td>
                                            <td>${l.total}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th colspan="5" >Vinaphone</th>
                                    </tr>
                                    <tr>
                                        <th style="width: 10%">Thời gian</th>
                                        <th>Kênh SMS</th>
                                        <th>Kênh OTP</th>
                                        <th>Tổng đăng ký</th>
                                        <th>Tăng trưởng thuê bao</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${vina}" var="l">
                                        <tr>
                                            <td>${l.dateTime}</td>
                                            <td>${l.smsChannel}</td>
                                            <td>${l.otpChannel}</td>
                                            <td>${l.reg}</td>
                                            <td>${l.total}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <table id="example1" class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th colspan="5" >Vietnamobile</th>
                                    </tr>
                                    <tr>
                                        <th style="width: 10%">Thời gian</th>
                                        <th>Kênh SMS</th>
                                        <th>Kênh OTP</th>
                                        <th>Tổng đăng ký</th>
                                        <th>Tăng trưởng thuê bao</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${vnm}" var="l">
                                        <tr>
                                            <td>${l.dateTime}</td>
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
