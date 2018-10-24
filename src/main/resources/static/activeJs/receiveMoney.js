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
        document.getElementById("first").style.display = "none";
    loadDT();
        document.getElementById("second").style.display = "block";
    }, 2000);
}

function loadDT(){
    var id = document.getElementById('id').value;
    var data = {
        "id" : id
    }

    console.log(data);

    var table = $('#transactionTable').DataTable({
        "destroy": true,
        "responsive": true,
        "processing":true,
            "language": {
                    processing: '<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><span class="sr-only">Loading...</span> '},
        "serverSide": true,
        "bFilter": false,
        "lengthMenu": [[10, 25, 50, 100, 200], [10, 25, 50, 100, "All"]],
        "ajax": {
            "url": "/transaction/fetch/nin",
            "data": data
        },
        dom: 'Bfrltip',
        lengthChange: true,

        buttons: [
                  { "extend": 'copy', "text":'Copy to Clipboard',"className": 'btn btn-default btn-md' },
                    { "extend": 'pdf', "text":'Export PDF',"className": 'btn btn-success btn-md' },
                    { "extend": 'excel', "text":'Export Excel',"className": 'btn btn-danger btn-md' },
                    { "extend": 'csv', "text":'Export CSV',"className": 'btn btn-success btn-md' },
                    { "extend": 'print', "text":'Print',"className": 'btn btn-primary btn-md' },
                    { "extend": 'colvis', "text":'Show/Hide Columns',"className": 'btn btn-warning btn-md' }
                    /*'copy',  'excel',   'csv',   'pdf', 'print', 'colvis'*/
                ],
        "aaData":data,
        "aoColumns": [
            { "data" : null, render: function(data, type, row){return '<a href ="/transaction/details?id=' + data.transactionId + '" target="_blank" >' + data.transactionId + '</a>';}},
            { "data": "senderName"},
            { "data": "senderPhone"},
            { "data": "receiverId"},
            { "data": "receiverIdType" },
            { "data" : "receiverName" },
            { "data" : "receiverPhone" },
            { "data": "amount"/*, 'render': $.fn.dataTable.render.number( ',', '.', 2, '₦' )*/},
            { "data": "status.value",
              "render": function(data,type, full, meta){
                    if(data.toUpperCase() == 'SENT')
                       return '<span class="label label-warning">' + data.toUpperCase() + '</span>';
                    else if(data.toUpperCase() == 'RECEIVED')
                        return '<span class="label label-success">' + data.toUpperCase() + '</span>';
                    else
                        return '<span class="label label-danger">' + data.toUpperCase() + '</span>';
              }
            },

            { "data": "transactionInitiationDate" },
            { "data" : "valueReceivedDate" }
        ]
    })

    table.buttons().container()
      .insertBefore( '#filtery' );
}