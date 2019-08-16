$(document).ready(function () {

    load_data_publisher();

    $('#add-publisher').click(function (event) {
        $('#name-publisher').val("");
        $('#add-modal').modal('show');
    });

    $('#add').click(function(event) {
        var publisherName = $('#name-publisher').val();
        var data = {"publisherName": publisherName};

        $.ajax({
            url: '/api/v1/publishers',
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
        })
        .done(function() {
            alert("Success !");
            location.reload();
        })
        .fail(function() {
            alert("Error !");
        })
        .always(function() {
            console.log("complete");
        });
        
    });

    $('#edit').click(function(event) {
        var id = $('#edit_id_publisher').val();
        var publisherName = $('#publisher-name').val();
        var data = {"id": id, "publisherName": publisherName};

        $.ajax({
            url: '/api/v1/publishers',
            type: 'PATCH',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
        })
        .done(function() {
            alert("Success !");
            location.reload();
        })
        .fail(function(err) {
            alert("Error !");
            console.log(err);
        })
        .always(function() {
            console.log("complete");
        });
        
    });

    $('#delete').click(function(event) {
        var id = $('#delete_id_publisher').val();

        $.ajax({
            url: '/api/v1/publishers/' + id,
            type: 'DELETE',
            dataType: 'json',
            
        })
        .done(function() {
            alert("Success !");
            
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
            location.reload();
        });
        
    });

    function load_data_publisher() {
        $.ajax({
            url: '/api/v1/publishers',
            type: 'GET',
            dataType: 'json',

        })
        .done(function (data) {
            console.log("success");
            var output = "";

            for (var i = 0; i < data.length; i++) {
                output = "<tr row_id_publisher=" + data[i].id + ">"
                        + "<td class='text-center'>" + data[i].id + "</td>"
                        + "<td class='text-center'>" + data[i].publisherName + "</td>"

                        + "<td class='text-center'>"
                        + "<a href='#' class='text-primary' data-toggle='modal' id_edit_publisher=" + data[i].id + " data-type='update-publisher' name=" + data[i].publisherName + ">"
                        + "<i class='ace-icon fa fa-pencil bigger-130'></i>"
                        + "</a>"
                        + "</td>"
                        + "<td class='text-center'>"
                        + "<a href='#' class='text-danger' id_delete_publisher=" + data[i].id + " data-type='delete-publisher' data-toggle='modal'>"
                        + "<i class='ace-icon fa fa-trash-o bigger-130'></i>"
                        + "</a>"
                        + "</td>"

                        + "</tr>";
                        
                        $('#body_publisher').append(output);
            }

            $('a[data-type=update-publisher]').click(function(event) {
                var id = $(this).attr('id_edit_publisher');
                console.log(id);
                $('#edit_id_publisher').val(id);
                $.ajax({
                    url: '/api/v1/publishers/' + id,
                    type: 'GET',
                    dataType: 'json',
                    
                })
                .done(function(data) {
                    $('#publisher-name').val(data.publisherName);
                });
                
                $('#edit-modal').modal('show');
            });

            $('a[data-type=delete-publisher]').click(function(event) {
                var id = $(this).attr('id_delete_publisher');
                $('#delete_id_publisher').val(id);
                $('#delete-modal').modal('show');
            });
        })
        .fail(function () {
            console.log("error");
        })
        .always(function () {
            console.log("complete");
        });

    }
});