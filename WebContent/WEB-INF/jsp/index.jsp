<!DOCTYPE html>
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
                            text: 'Thuê bao tăng trưởng trong ngày'
                        },
                        plotLines: [{
                                value: 0,
                                width: 1,
                                color: '#808080'
                            }]
                    },
                    tooltip: {
                        valueSuffix: ' sub'
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

        <!--        <script type="text/javascript">
                    $(function() {
                        $('#container2').highcharts({
                            title: {
                                text: 'Thống kê nonsub',
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
                                min: 0,
                                title: {
                                    text: 'Thống kê nonsub'
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
                                    name: 'Viettel',
                                    data: ${lstViettel}
                                }]
                        });
                    });
        
        
                </script>-->
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
                        Dashboard
                        <small>Control panel</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Dashboard</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-aqua">
                                <div class="inner">
                                    <h3>
                                        ${allSubMobi.subActive}
                                    </h3>
                                    <p>Sub ${allSubMobi.subInDay} | ${allSubMobi.subInMonth}<br> Reg ${allSubMobi.regInDay} | ${allSubMobi.regInMonth}</p>
                                    <p>
                                        Mobifone
                                    </p>
                                </div>

                                <div class="icon">
                                    <i class="ion ion-person-add"></i>
                                </div>

                            </div>
                        </div><!-- ./col -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-yellow">
                                <div class="inner">
                                    <h3>
                                        ${allSubVina.subActive}
                                    </h3>
                                    <p>Sub ${allSubVina.subInDay} | ${allSubVina.subInMonth}<br> Reg ${allSubVina.regInDay} | ${allSubVina.regInMonth}</p>
                                    <p>
                                        Vinaphone
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-person-add"></i>
                                </div>

                            </div>
                        </div><!-- ./col -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-red">
                                <div class="inner">
                                    <h3>
                                        0
                                    </h3>
                                    <p>
                                        Viettel
                                    </p>
                                    <h3>
                                        ${nonSubVt.subActive}
                                    </h3>
                                    <p>MO ${nonSubVt.regInDay} | ${nonSubVt.regInMonth}</p>
                                    <p>
                                        Viettel nonsub
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-person-add"></i>
                                </div>

                            </div>
                        </div><!-- ./col -->
                        <div class="col-lg-3 col-xs-6">
                            <!-- small box -->
                            <div class="small-box bg-green">
                                <div class="inner">
                                    <h3>
                                        ${allSubVnm.subActive}
                                    </h3>
                                    <p>Sub ${allSubVnm.subInDay} | ${allSubVnm.subInMonth}<br> Reg ${allSubVnm.regInDay} | ${allSubVnm.regInMonth}</p>
                                    <p>
                                        Vietnamobile
                                    </p>
                                </div>
                                <div class="icon">
                                    <i class="ion ion-person-add"></i>
                                </div>

                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->



                    <!-- Main row -->
                    <div class="row">
                        <div id="container1" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        <!--                        <br>
                                                <div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>-->
                    </div><!-- /.row (main row) -->

                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <!-- add new calendar event modal -->


        <%@include file="include/footer.jsp" %>

    </body>
</html>