<!DOCTYPE html>
<html lang="en">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>EdgePay User Dashboard</title>
      <!-- Favicon and touch icons -->
      <link rel="shortcut icon" href="/assets/dist/img/ico/favicon.png" type="image/x-icon">
      <!-- Start Global Mandatory Style
         =====================================================================-->
      <!-- jquery-ui css -->
      <link href="/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
      <!-- Bootstrap -->
      <link href="/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      <!-- Bootstrap rtl -->
      <!--<link href="/assets/bootstrap-rtl/bootstrap-rtl.min.css" rel="stylesheet" type="text/css"/>-->
      <!-- Lobipanel css -->
      <link href="/assets/plugins/lobipanel/lobipanel.min.css" rel="stylesheet" type="text/css"/>
      <!-- Pace css -->
      <link href="/assets/plugins/pace/flash.css" rel="stylesheet" type="text/css"/>
      <!-- Font Awesome -->
      <link href="/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
      <!-- Pe-icon -->
      <link href="/assets/pe-icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet" type="text/css"/>
      <!-- Themify icons -->
      <link href="/assets/themify-icons/themify-icons.css" rel="stylesheet" type="text/css"/>
      <!-- End Global Mandatory Style
         =====================================================================-->
      <!-- Start Theme Layout Style
         =====================================================================-->
      <!-- Theme style -->
      <link href="/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
      <!-- Theme style rtl -->
      <!--<link href="/assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
      <!-- End Theme Layout Style
         =====================================================================-->
   </head>
   <body class="hold-transition sidebar-mini">
   <!--preloader-->
      <div id="preloader">
         <div id="status"></div>
      </div>
      <!-- Site wrapper -->
      <div class="wrapper">
         <header class="main-header">
            <%@ include file="/layout/header.jsp" %>
         </header>
         <!-- =============================================== -->
         <!-- Left side column. contains the sidebar -->
         <aside class="main-sidebar">
            <!-- sidebar -->
            <%@ include file="/layout/sideNav.jsp" %>
            <!-- /.sidebar -->
         </aside>
         <!-- =============================================== -->
         <!-- Content Wrapper. Contains page content -->
         <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
               <div class="header-icon"><i class="fa fa-handshake-o"></i></div>
               <div class="header-title">
                  <h1>User Dashboard</h1>
                  <small>No Banks? That is why we are here!</small>
               </div>
            </section>
            <!-- Main content -->
            <section class="content">

               <!--
               <div class="row">
                    <div class="col-sm-3 col-md-6">
                       <div class="rating-block">
                          <h4>Wallet Balance</h4>
                          <h2 class="m-b-20">${balance}</h2>
                       </div>
                    </div>
                    <div class="col-sm-3 col-md-6">
                       <div class="rating-block">
                          <h4>Unclaimed Money</h4>
                          <h2 class="m-b-20">${unclaimedSum}</small></h2>
                       </div>
                    </div>
                    <div class="col-sm-3 col-md-6">
                       <div class="rating-block">
                          <h4>Transaction Volume</h4>
                          <h2 class="m-b-20">${tVolume}</small></h2>
                       </div>
                    </div>
                    <div class="col-sm-3 col-md-6">
                       <div class="rating-block">
                          <h4>Transaction Value</h4>
                          <h2 class="m-b-20">${tValue}</small></h2>
                       </div>
                    </div>
               </div>
               -->

               <div class="row">
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                     <div id="cardbox1">
                        <div class="statistic-box">
                           <i class="fa fa-money fa-3x"></i>
                           <div class="counter-number pull-right">
                              <i class="ti ti-money"></i><span class="count-number">${balance}</span>
                              <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                           </div>
                           <h3> Wallet Balance</h3>
                        </div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                     <div id="cardbox2">
                        <div class="statistic-box">
                           <i class="fa fa-money fa-3x"></i>
                           <div class="counter-number pull-right">
                              <i class="ti ti-money"></i><span class="count-number">${unclaimedSum}</span>
                              <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                           </div>
                           <h3> Unclaimed Money</h3>
                        </div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                     <div id="cardbox3">
                        <div class="statistic-box">
                           <i class="fa fa-bar-chart-o fa-3x"></i>
                           <div class="counter-number pull-right">
                              <span class="count-number">${tVolume}</span>
                              <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                           </div>
                           <h3>  Transaction Volume</h3>
                        </div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
                     <div id="cardbox4">
                        <div class="statistic-box">
                           <i class="fa fa-money fa-3x"></i>
                           <div class="counter-number pull-right">
                              <i class="ti ti-money"></i><span class="count-number">${tValue}</span>
                              <span class="slight"><i class="fa fa-play fa-rotate-270"> </i>
                              </span>
                           </div>
                           <h3> Transaction Value</h3>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="row">
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                     <div class="panel panel-bd lobidisable">
                        <div class="panel-heading">
                           <div class="panel-title">
                              <h4>Recent Transactions</h4>
                           </div>
                        </div>
                        <div class="panel-body">
                            <div class="Workslist">
                              <div class="worklistdate">
                                 <table class="table table-hover">
                                    <thead>
                                       <tr>
                                          <th>Sender</th>
                                          <th>Amount</th>
                                          <th>Status</th>
                                          <th>Date</th>
                                       </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${recent}" var="rtnx">
                                        <tr>
                                            <td>
                                                <c:out value="${rtnx.senderName}" />
                                            </td>
                                            <td>
                                                <c:out value="${rtnx.amount}" />
                                            </td>
                                            <td>
                                                <c:out value="${rtnx.status.value}" />
                                            </td>
                                            <td>
                                                <c:out value="${rtnx.transactionInitiationDate}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                 </table>
                              </div>
                           </div>
                        </div>

                        <div class="panel-footer" style = "text-align: right;">
                           Total Sent Value: <span style = "color:blue;" >${sentAmount}</span>
                        </div>
                     </div>
                  </div>
                  <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                     <div class="panel panel-bd lobidisable">
                        <div class="panel-heading">
                           <div class="panel-title">
                              <h4>Recent Pending Transactions</h4>
                           </div>
                        </div>
                        <div class="panel-body">
                            <div class="Workslist">
                              <div class="worklistdate">
                                 <table class="table table-hover">
                                    <thead>
                                       <tr>
                                          <th>Sender</th>
                                          <th>Amount</th>
                                          <th>Status</th>
                                          <th>Date</th>
                                       </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${unclaimed}" var="rtnx">
                                        <tr>
                                            <td>
                                                <c:out value="${rtnx.senderName}" />
                                            </td>
                                            <td>
                                                <c:out value="${rtnx.amount}" />
                                            </td>
                                            <td>
                                                <c:out value="${rtnx.status.value}" />
                                            </td>
                                            <td>
                                                <c:out value="${rtnx.transactionInitiationDate}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                 </table>
                              </div>
                           </div>
                        </div>

                        <div class="panel-footer" style = "text-align: right;">
                           Total Received Value: <span style = "color:blue;" >${receivedAmount}</span>
                        </div>
                     </div>
                  </div>
               </div>
            </section>
            <!-- /.content -->
         </div>
         <!-- /.content-wrapper -->
         <footer class="main-footer">
            <%@ include file="/layout/footer.jsp" %>
         </footer>
      </div>
      <!-- ./wrapper -->
      <!-- Start Core Plugins
         =====================================================================-->
      <!-- jQuery -->
      <script src="/assets/plugins/jQuery/jquery-1.12.4.min.js" type="text/javascript"></script>
      <!-- jquery-ui -->
      <script src="/assets/plugins/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
      <!-- Bootstrap -->
      <script src="/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
      <!-- lobipanel -->
      <script src="/assets/plugins/lobipanel/lobipanel.min.js" type="text/javascript"></script>
      <!-- Pace js -->
      <script src="/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
      <!-- SlimScroll -->
      <script src="/assets/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
      <!-- FastClick -->
      <script src="/assets/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
      <!-- CRMadmin frame -->
      <script src="/assets/dist/js/custom.js" type="text/javascript"></script>
      <!-- End Core Plugins
         =====================================================================-->
      <!-- Start Theme label Script
         =====================================================================-->
      <!-- Dashboard js -->
      <script src="/assets/dist/js/dashboard.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->
   </body>

</html>