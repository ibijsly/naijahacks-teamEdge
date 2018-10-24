<!DOCTYPE html>
<html lang="en">

<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>EdgePay Receive Money</title>
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
     <!-- dataTables css -->
           <link href="/assets/plugins/datatables/dataTables.min.css" rel="stylesheet" type="text/css"/>

      <!-- Start Theme Layout Style
         =====================================================================-->
      <!-- Theme style -->
      <link href="/assets/dist/css/stylecrm.css" rel="stylesheet" type="text/css"/>
      <!-- Theme style rtl -->
      <!--<link href="/assets/dist/css/stylecrm-rtl.css" rel="stylesheet" type="text/css"/>-->
      <!-- End Theme Layout Style
         =====================================================================-->

     <style>
        #details, #payMode, #makepayment, #biometrics{
            display: none;
        }

        #spin{
            margin: 20px;
            display: none;
        }

        #payImage{
            display: inline;
            margin: 10px;
            width: 30%;
            float: left;
            text-align: center;
            color: #00969a;
            cursor: pointer;
        }
     </style>
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
               <div class="header-icon">
                  <i class="fa fa-shopping-basket"></i>
               </div>
               <div class="header-title">
                  <h1>Enduser Receive Money</h1>
                  <small>No Bank? That is why we are here!</small>
               </div>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row"  id = "second">
                  <div class="col-sm-12">
                     <div class="panel panel-bd lobidrag">
                        <div class="panel-heading">
                           <div class="btn-group" id="buttonexport">
                              <a href="#">
                                 <h4>Transaction list</h4>
                                 <input type = "hidden" value = "${nin}" id = "nin"/>
                              </a>
                           </div>
                        </div>
                        <div class="panel-body">
                           <!-- ./Plugin content:powerpoint,txt,pdf,png,word,xl -->
                           <div class="table-responsive">
                              <table id="transactionTable" class="table table-bordered table-striped table-hover">
                                 <thead>
                                    <tr class="info">
                                       <th>transaction Id</th>
                                       <th>Sender Name</th>
                                       <th>Sender Phone</th>
                                       <th>Receiver Id</th>
                                       <th>Receiver Type</th>
                                       <th>Receiver Name</th>
                                       <th>Receiver Phone</th>
                                       <th>Amount</th>
                                       <th>status</th>
                                       <th>transaction Date</th>
                                       <th>Received Date</th>
                                    </tr>
                                 </thead>
                                 <tbody>

                                 </tbody>
                              </table>
                           </div>
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
      <!-- Sweetalert -->
      <script src="/assets/sweetalert/sweetalert.js" type="text/javascript"></script>
      <script src="sweetalert2.all.min.js"></script>
      <!-- Optional: include a polyfill for ES6 Promises for IE11 and Android browser -->
      <script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
      <!-- FastClick -->
      <script src="/assets/plugins/fastclick/fastclick.min.js" type="text/javascript"></script>
      <!-- CRMadmin frame -->
      <script src="/assets/dist/js/custom.js" type="text/javascript"></script>
      <!-- End Core Plugins
         =====================================================================-->
      <!-- dataTables js -->
      <script src="/assets/plugins/datatables/dataTables.min.js" type="text/javascript"></script>
      <script src="https://cdn.datatables.net/buttons/1.0.3/js/buttons.colVis.js"></script>

      <!-- Start Theme label Script
         =====================================================================-->
      <!-- Dashboard js -->
      <script src="/assets/dist/js/dashboard.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->

      <!-- Send js -->
      <script src="https://js.paystack.co/v1/inline.js"></script>
      <script src="/activeJs/uReceiveMoney.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->
   </body>

</html>