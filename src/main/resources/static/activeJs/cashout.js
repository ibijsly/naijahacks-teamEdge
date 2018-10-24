$(document).ready( function () {
    $('#cashout').on('click', function(){
        cashout();
    });

});

function cashout(){
    var tid = document.getElementById("tid").value;

    url = '/transaction/update?transactionId=' + tid;

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
                    swal({
                      title: "Received",
                      text: data.responseMessage,
                      icon: "success",
                    }).then((value) => {
                        location.reload();
                    });

                }else{
                    swal({
                      title: "Failed!",
                      text: data.responseMessage,
                      icon: "error",
                    });

                    verify.style.display = "none";
                    pay.style.display = "block";
                }
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
            console.log( data );

            swal({
              title: "Failed!",
              text: data.responseMessage,
              icon: "error",
            });

            verify.style.display = "none";
            pay.style.display = "block";

//            swal({
//              title: "Failed!",
//              text: data.responseMessage,
//              icon: "error",
//            });
//
//            verify.style.display = "block";
//            pay.style.display = "none";
        }
    });
}

function myFunction(message){
    swal(message);
}
