$(document).ready( function () {
    loadDT();
});

function loadDT(){
    var id = document.getElementById('nin').value;
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