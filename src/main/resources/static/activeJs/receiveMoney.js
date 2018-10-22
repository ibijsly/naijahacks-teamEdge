$(document).ready( function () {
    $('#verify').on('click', function(){
        verify();
    });

    $('#send').on('click', function(){
        send();
    });

    $('#bioButton').on('click', function(){
        biometrics();
    });

    $('#wallet').on('click', function(){
        send();
    });

    $('#endSend').on('click', function(){
        endPay();
    });

    $('#card').on('click', function(){
        payWithPaystack();
    });

    $('#qrcode').on('click', function(){
        qrPayment();
    });
});

function send(){
    var url = "/transaction/add";

    var identity = document.getElementById('identity').value;
    var surname = document.getElementById('surname').value;
    var fname = document.getElementById('fname').value;
    var mname = document.getElementById('mname').value;
    var phone = document.getElementById('phone').value;
    var senderName = document.getElementById('senderName').value;
    var senderPhone = document.getElementById('senderPhone').value;
    var amount = document.getElementById('amount').value;
    var idType = document.getElementById('idType').value;

    var data = {
        "amount" : amount,
        "receiver" : surname + ', ' + mname + ' ' + fname,
        "receiverPhone" : phone,
        "senderName" : senderName,
        "senderPhone": senderPhone,
        "idType": idType,
        "id" : identity,
        "logMail": 'Swibijsly@yahoo.com'
    };

    console.log(data);

    $.ajax({
        url: url,
        dataType: 'json',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        processData: false,

        success: function( data, textStatus, jQxhr ){
            console.log(data);

            var responseCode = data.responseCode;
            if (responseCode === "00"){
                swal({
                  title: "Sent!",
                  text: "Transaction Successful",
                  icon: "success",
                });

                verify.style.display = "block";
                pay.style.display = "none";
            }else{
                swal({
                  title: "Failed!",
                  text: data.responseMessage,
                  icon: "error",
                });

                verify.style.display = "none";
                pay.style.display = "block";
            }
        },
        error: function( data, textStatus, errorThrown ){
            console.log( errorThrown );
            console.log(textStatus);
            swal({
              title: "Failed!",
              text: data.responseMessage,
              icon: "error",
            });

            verify.style.display = "none";
            pay.style.display = "block";
        }
    });
}


function verify(){
    var url = "/verify";

// biometrics
    var verify = document.getElementById('verification')
    var bio = document.getElementById('biometrics')

    var id = document.getElementById('id').value;
    var idType = document.getElementById('idType');
    var idTypeValue = idType.options[idType.selectedIndex].value;

    /*Where to set values after Verification*/
    var identity = document.getElementById('nrid');
    var rname = document.getElementById('nrName');

    url += '/' + idTypeValue + '/' + id;

    $.ajax({
        url: url,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,

        success: function( data, textStatus, jQxhr ){
            console.log(data);
            var responseCode = data.responseCode;
            if (responseCode === "00"){
                var name = data.data.firstName + ' ' + data.data.middleName + ' ' + data.data.lastName;

                swal({
                  title: "Verified!",
                  text: name,
                  icon: "success",
                });

                identity.value = data.data.nin;
                rname.value = name;

                verify.style.display = "none";
                bio.style.display = "block";

            }else{
                swal({
                  title: "Failed!",
                  text: "Verification of " + id + " Failed",
                  icon: "error",
                });

                verify.style.display = "block";
                pay.style.display = "none";
            }

        },
        error: function( data, textStatus, errorThrown ){
            console.log(textStatus);
            console.log( errorThrown );

            swal({
              title: "Failed!",
              text: data.responseMessage,
              icon: "error",
            });

            verify.style.display = "block";
            pay.style.display = "none";
        }
    });
}

function biometrics(){
    document.getElementById('spin').style.display = "block";
    setTimeout(function(){
        document.getElementById('spin').style.display = "none";
        swal({
          title: "Verified!",
          text: "Biometrics Successfully Verified",
          icon: "success",
        });

        /*Call all transactions for the NIN*/
        var url = "/transaction/fetch/nin";
//        nin

        var id = document.getElementById('id').value;
        url += "?nin=" + id;
//        var data = {
//            "nin" : id
//        };

        $.ajax({
        url: url,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
//        data: JSON.stringify(data),
        processData: false,

        success: function( data, textStatus, jQxhr ){
            console.log(data);
//            var responseCode = data.responseCode;
//            if (responseCode === "00"){
//                var name = data.data.firstName + ' ' + data.data.middleName + ' ' + data.data.lastName;
//
//                swal({
//                  title: "Verified!",
//                  text: name,
//                  icon: "success",
//                });
//
//                identity.value = data.data.nin;
//                rname.value = name;
//
//                verify.style.display = "none";
//                bio.style.display = "block";
//
//            }else{
//                swal({
//                  title: "Failed!",
//                  text: "Verification of " + id + " Failed",
//                  icon: "error",
//                });
//
//                verify.style.display = "block";
//                pay.style.display = "none";
//            }

        },
        error: function( data, textStatus, errorThrown ){
            console.log(textStatus);
            console.log( errorThrown );
            console.log(data);

            swal({
              title: "Failed!",
              text: data.responseMessage,
              icon: "error",
            });

//            verify.style.display = "block";
//            pay.style.display = "none";
        }
    });
    }, 2000);
}

function endPay(){
    document.getElementById('verification').style.display = "none";
    document.getElementById('makepayment').style.display = "none";

    document.getElementById('rid').value = document.getElementById('identity').value;
    document.getElementById('rName').value = document.getElementById('surname').value + ", " + document.getElementById('fname').value + " " + document.getElementById('mname').value;
    document.getElementById('rAmount').value = document.getElementById('amount').value;
    document.getElementById('rFee').value = '50.00';
    var sell = parseFloat(document.getElementById('amount').value) + 50.00;
    console.log(sell);
    document.getElementById('rPay').value = sell;


    document.getElementById('payMode').style.display = "block";
    document.getElementById('details').style.display = "block";


}

function payWithPaystack(){

    var amount = parseFloat(document.getElementById('rPay').value) * 100;
    var senderName = document.getElementById('senderName').value;
    var senderPhone = document.getElementById('senderPhone').value;
    var senderEmail = document.getElementById('senderEmail').value;

    var handler = PaystackPop.setup({
      key: 'pk_test_59b2c668b54f5de36ef15b92c218000c8993cfe4',
      email: senderEmail,
      amount: amount,
      ref: 'EP'+new Date().getTime(), // generates a pseudo-unique reference. Please replace with a reference you generated. Or remove the line entirely so our API will generate one for you
      metadata: {
         custom_fields: [
            {
                display_name: senderName,
                variable_name: "mobile_number",
                value: "+234" + senderPhone.slice(1, 10)
            }
         ]
      },
      callback: function(response){
          alert('success. transaction ref is ' + response.reference);
      },
      onClose: function(){
          alert('window closed');
      }
    });
    handler.openIframe();
  }


function qrPayment(){
    $('#exampleModalCenter').modal('show');
}