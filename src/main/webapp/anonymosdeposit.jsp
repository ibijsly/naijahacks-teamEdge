<!DOCTYPE html>
<html lang="en">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>EdgePay Send Money</title>
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

     <style>
        #details, #payMode, #makepayment{
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
                  <h1>Send Money</h1>
                  <small>No Bank? That is why we are here!</small>
               </div>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <div class="col-sm-4 col-sm-offset-4" id = "verification">
                     <div class="panel lobidisable panel-bd">
                        <div class="panel-heading">
                           <div class="panel-title">
                              <h4>Verification</h4>
                           </div>
                        </div>
                        <div class="panel-body">
                           <form>
                              <div class="form-group">
                                 <label>NIN / VIN</label>
                                 <input type="text" class="form-control" placeholder="19618432781" id = "id" required>
                              </div>
                              <div class="form-group">
                                 <label>Identity Type</label>
                                 <div class=" form-group">
                                    <select class="form-control" id = "idType">
                                        <option value = "">Select</option>
                                        <option value = "NIN">NIN (NIMC)</option>
                                        <option value = "VIN">VIN (Voter&quote;s card)</option>
                                    </select>
                                 </div>
                              </div>

                              <div class="form-group">
                                   <button type="button" class="btn btn-add" id = "verify"><i class="fa fa-check"></i> Verify
                                   </button>
                              </div>

<!--
                              <div class="form-group">
                                 <label>Description</label>
                                 <input type="text" class="form-control" placeholder="Enter Short description" required>
                              </div>
                              <div class="form-group">
                                 <label>Amount</label>
                                 <input type="number" class="form-control" placeholder="Enter Amount" required>
                              </div>
-->
                           </form>
                        </div>
                     </div>
                  </div>
                  <div class="col-sm-8 col-sm-offset-2" id = "makepayment">
                     <div class="panel lobidisable panel-bd">
                        <div class="panel-heading">
                           <div class="panel-title">
                              <h4>Details</h4>
                           </div>
                        </div>
                        <div class="panel-body">
                            <!-- This will contain the Receiver Details received from the API -->
                            <form>
                              <div class="form-group">
                                 <label>Identity</label>
                                 <input type="text" class="form-control" placeholder="" id = "identity" readonly required>
                              </div>
                              <div class="form-group">
                                 <label>Identity Type</label>
                                 <input type="text" class="form-control" placeholder="" id = "idType" value = "NIN" readonly required>
                              </div>
                              <div class="form-group">
                                 <label>Surname</label>
                                 <input type="text" class="form-control" placeholder="" id = "surname" readonly required>
                              </div>
                              <div class="form-group">
                                 <label>First Name</label>
                                 <input type="text" class="form-control" placeholder="" id = "fname" readonly required>
                              </div>
                              <div class="form-group">
                                 <label>Middle Name</label>
                                 <input type="text" class="form-control" placeholder="" id = "mname" readonly required>
                              </div>
                              <div class="form-group">
                                 <label>Phone</label>
                                 <input type="text" class="form-control" placeholder="" id = "phone" required>
                              </div>
                              <div class="form-group">
                                 <label>Sender Name</label>
                                 <input type="text" class="form-control" placeholder="Abraham Ibrahim Sangodeyi" id = "senderName" required>
                              </div>
                              <div class="form-group">
                                 <label>Sender Phone</label>
                                 <input type="text" class="form-control" placeholder="09089898989" id = "senderPhone" required>
                              </div>
                              <div class="form-group">
                                 <label>Sender Email</label>
                                 <input type="email" class="form-control" placeholder="whaever@gmail.com" id = "senderEmail" required>
                              </div>
                              <div class="form-group">
                                 <label>Amount</label>
                                 <input type="number" class="form-control" min = "10" placeholder="60000" id = "amount" required>
                              </div>

                            <c:choose>
                                <c:when test="${role !=null}">
                                    <c:if test="${role.equals('AGENT')}">
                                        <!-- Agent -->
                                        <div class="form-group">
                                           <button type="button" class="btn btn-add" id = "send"><i class="fa fa-check"></i> Send
                                           </button>
                                      </div>
                                    </c:if>
                                    <c:if test="${role.equals('ENDUSER') || role.equals('Undefined')}">
                                        <!-- End User -->
                                      <div class="form-group">
                                           <button type="button" class="btn btn-add" id = "endSend"><i class="fa fa-check"></i> Send
                                           </button>
                                      </div>
                                    </c:if>
                                </c:when>
                                <c:when test="${role ==null  || role.isEmpty()}">
                                    <!-- End User -->
                                  <div class="form-group">
                                       <button type="button" class="btn btn-add" id = "endSend"><i class="fa fa-check"></i> Send
                                       </button>
                                  </div>
                                </c:when>
                            </c:choose>

                            </form>
                        </div>
                     </div>
                  </div>
                  <div class="col-sm-4" id = "details">
                     <div class="panel lobidisable panel-bd">
                        <div class="panel-heading">
                           <div class="panel-title">
                              <h4>Quote</h4>
                           </div>
                        </div>
                        <div class="panel-body">
                            <form>
                              <div class="form-group">
                                 <label>NIN / VIN</label>
                                 <input type="text" class="form-control" placeholder="19618432781" id = "rid" readonly required>
                              </div>

                              <div class="form-group">
                                 <label>Name</label>
                                 <input type="text" class="form-control" placeholder="19618432781" id = "rName" readonly required>
                              </div>

                              <div class="form-group">
                                 <label>Amount</label>
                                 <input type="text" class="form-control" placeholder="19618432781" id = "rAmount" readonly required>
                              </div>

                              <div class="form-group">
                                 <label>Convenience Fee</label>
                                 <input type="text" class="form-control" placeholder="19618432781" id = "rFee" readonly required>
                              </div>

                              <div class="form-group">
                                 <label>Payable</label>
                                 <input type="text" class="form-control" placeholder="19618432781" id = "rPay" readonly required>
                              </div>
                           </form>
                        </div>
                     </div>
                  </div>
                  <div class="col-sm-8" id = "payMode">
                     <div class="panel lobidisable panel-bd">
                        <div class="panel-heading">
                           <div class="panel-title">
                              <h4>Mode of Payment</h4>
                           </div>
                        </div>
                        <div class="panel-body">
                            <!-- This will contain The various means of payments (Wallet, Paystack, Masterpass) -->
                            <div id = "payImage">
                                <img src = "/assets/image/wallet.jpg" width = "100%" height = "200px" id = "wallet"/>
                                <p>Wallet</p>
                            </div>
                            <div id = "payImage">
                                <img src = "/assets/image/card.jpg" width = "100%" height = "200px" id = "card" />
                                <p>Card Payment</p>
                            </div>
                            <div id = "payImage">
                                <img src = "/assets/image/qr.png" width = "100%" height = "200px" id = "qrcode" <!--data-toggle="modal" data-target="#exampleModalCenter" -->/>
                                <p>Masterpass</p>
                            </div>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                 <div class="modal-dialog modal-dialog-centered" role="document">
                   <div class="modal-content">
                     <div class="modal-header">
                       <h5 class="modal-title" id="exampleModalLongTitle">Scan to Pay</h5>
                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                         <span aria-hidden="true">&times;</span>
                       </button>
                     </div>
                     <div class="modal-body" style = "text-align: centre;">
                       <img src = "/assets/image/onlineEnvoy.png" />
                     </div>
                     <div class="modal-footer">
                       <button type="button" class="btn btn-primary" data-dismiss="modal" id = "done">Done</button>
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
      <!-- Start Theme label Script
         =====================================================================-->
      <!-- Dashboard js -->
      <script src="/assets/dist/js/dashboard.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->

      <!-- Send js -->
      <script src="https://js.paystack.co/v1/inline.js"></script>
      <script src="/activeJs/sendMoney.js" type="text/javascript"></script>
      <!-- End Theme label Script
         =====================================================================-->
   </body>

</html>