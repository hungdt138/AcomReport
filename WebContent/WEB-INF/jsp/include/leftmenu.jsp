<%-- 
    Document   : leftmenu
    Created on : Aug 24, 2014, 4:30:56 PM
    Author     : hungdt
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->

        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <c:if test="${role == 'admin'}">
                <li class="active">
                    <a href="index.action">
                        <i class="fa fa-dashboard"></i> <span>Home</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-th"></i> <span>Quản lý dịch vụ</span> 
                    </a>
                </li>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-th"></i> <span>Quản lý tải khoản</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="user.action"><i class="fa fa-angle-double-right"></i> Danh sách</a></li>
                        <li><a href="updateuser.action?act=2"><i class="fa fa-angle-double-right"></i> Tạo mới tài khoản</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>Báo cáo</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="reportnonsub.action"><i class="fa fa-angle-double-right"></i> Thống kê nonsub</a></li>
                        <li><a href="nonsubcmd.action"><i class="fa fa-angle-double-right"></i> Thống kê nonsub theo dv</a></li>
                        <li><a href="substatistic.action"><i class="fa fa-angle-double-right"></i> Thống kê sub</a></li>
                        <li><a href="subcmdcode.action"><i class="fa fa-angle-double-right"></i> Thống kê sub theo dv</a></li>
                        <li><a href="telcodata.action"><i class="fa fa-angle-double-right"></i> Dữ liệu nhà mạng</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>Đối soát</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="doisoat.action"><i class="fa fa-angle-double-right"></i> Số liệu đối soát</a></li>
                    </ul>
                </li>
                <li >
                    <a href="cskh.action">
                        <i class="fa fa-laptop"></i>
                        <span>Chăm sóc khách hàng</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role == 'telco'}">
                <li >
                    <a href="cskh.action">
                        <i class="fa fa-laptop"></i>
                        <span>Chăm sóc khách hàng</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role == 'cp'}">
                <li>
                    <a href="#">
                        <i class="fa fa-th"></i> <span>Quản lý dịch vụ</span> 
                    </a>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>Báo cáo</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="reportnonsub.action"><i class="fa fa-angle-double-right"></i> Thống kê nonsub</a></li>
                        <li><a href="nonsubcmd.action"><i class="fa fa-angle-double-right"></i> Thống kê nonsub theo dv</a></li>
                        <li><a href="substatistic.action"><i class="fa fa-angle-double-right"></i> Thống kê sub</a></li>
                        <li><a href="subcmdcode.action"><i class="fa fa-angle-double-right"></i> Thống kê sub theo dv</a></li>
                    </ul>
                </li>
                <li >
                    <a href="cskh.action">
                        <i class="fa fa-laptop"></i>
                        <span>Chăm sóc khách hàng</span>
                    </a>
                </li>
            </c:if>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
