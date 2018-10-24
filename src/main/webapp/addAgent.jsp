<!DOCTYPE html>
<html lang="en">
   
<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>EdgePay Agent Registration</title>
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
               <div class="header-icon">
                  <i class="fa fa-users"></i>
               </div>
               <div class="header-title">
                  <h1>Agent Registration</h1>
                  <small>Cash Point Players</small>
               </div>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <!-- Form controls -->
                  <div class="col-sm-8 col-sm-offset-2">
                     <div class="panel panel-bd lobidrag">
                        <div class="panel-heading">
                           <div class="btn-group" id="buttonlist"> 
                              <a class="btn btn-add " href="clist.html"> 
                              <i class="fa fa-list"></i>  Agent List </a>
                           </div>
                        </div>
                        <div class="panel-body">
                            <div id = "message"></div>
                           <form class="col-sm-8">
                              <div class="form-group">
                                 <label>First Name</label>
                                 <input type="text" class="form-control" placeholder="Enter First Name" id = "firstname" required>
                              </div>
                              <div class="form-group">
                                 <label>Last Name</label>
                                 <input type="text" class="form-control" placeholder="Enter last Name" id = "lastname" required>
                              </div>
                              <div class="form-group">
                                 <label>Email</label>
                                 <input type="email" class="form-control" placeholder="Enter Email" id = "email" required>
                              </div>
                              <div class="form-group">
                                   <label>Gender</label><br>
                                   <label class="radio-inline"><input name="sex" value="1" checked="checked" type="radio">Male</label>
                                   <label class="radio-inline"><input name="sex" value="0" type="radio">Female</label>
                              </div>
                              <div class="form-group">
                                 <label>Mobile</label>
                                 <input type="number" class="form-control" placeholder="Enter Mobile" id = "phone" required>
                              </div>
                              <!--
                              <div class="form-group">
                                 <label>Picture upload</label>
                                 <input type="file" name="picture">
                                 <input type="hidden" name="old_picture">
                              </div>
                              -->

                              <legend>Bank Details</legend>
                              <div class="form-group">
                                 <label>Bank</label>
                                 <select class="form-control" id = "bank">
                                   <option value = "">Select</option>
                                   <option value = "058">GTBank</option>
                                   <option value = "011">FBN</option>
                                   <option value = "033">UBA</option>
                                </select>
                              </div>
                              <div class="form-group">
                                 <label>Account Name</label>
                                 <input type="text" class="form-control" placeholder="Account Name" id = "accountName" required>
                              </div>
                              <div class="form-group">
                                 <label>Account Number</label>
                                 <input type="text" class="form-control" placeholder="0102038989" id = "accountNumber" required>
                              </div>

                              <legend>Location Details</legend>
                              <div class="form-group">
                                   <label>State</label>
                                   <select class="form-control" id = "state">
                                      <option value = "">Select</option>
                                      <option value = "Lagos">Lagos</option>
                                      <option value = "Oyo">Oyo</option>
                                      <option value = "Ogun">Ogun</option>
                                      <option value = "Abia">Abia</option>
                                      <option value = "Abuja">Abuja</option>
                                      <option value = "Kaduna">Kaduna</option>
                                      <option value = "Plateau">Plateau</option>
                                   </select>
                              </div>
                              <div class="form-group">
                                   <label>LG</label>
                                   <select class="form-control" id = "lg">
                                      <option value = "">Select</option>
                                      <option value = "Lagos Mainland">Lagos Mainland</option>
                                      <option value = "Yaba">Yaba</option>
                                      <option value = "Alimosho">Alimosho</option>
                                      <option value = "Ikorodu">Ikorodu</option>
                                      <option value = "Kosofe">Kosofe</option>
                                      <option value = "Ikeja">Ikeja</option>
                                   </select>
                              </div>
                              <div class="form-group">
                                 <label>Address</label>
                                 <textarea class="form-control" rows="3" id = "address" required></textarea>
                              </div>

                              <div class="reset-button">
                                <button type = "reset" class="btn btn-warning" >Reset</button>
                                <button type = "button" class="btn btn-success" id = "submit" >Submit</button>

                              </div>
                           </form>
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
      <!-- Sweetalert -->
            <script src="/assets/sweetalert/sweetalert.js" type="text/javascript"></script>

      <!-- End Core Plugins
         =====================================================================-->
      <!-- Start Theme label Script
         =====================================================================-->
      <!-- Dashboard js -->
      <script src="/assets/dist/js/dashboard.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->

     <!-- Registration js -->
      <script src="/activeJs/agentJs.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->
   </body>

</html>

