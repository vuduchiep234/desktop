$(document).ready(function () {

    load_data_genre();

    $('#add-genre').click(function (event) {
        $('#genre-type').val("");
        $('#add-modal').modal('show');
    });

    $('#add').click(function(event) {
        var genreType = $('#genre-type').val();
        console.log(genreType);
        var data = {"genreType": genreType};
        $.ajax({
            url: '/api/v1/genres',
            type: 'POST',
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
            console.log(err)
        })
        .always(function() {
            console.log("complete");
        });
        
    });

    $('#edit').click(function(event) {
        var id = $('#edit_id_genre').val();
        var genreType = $('#type-genre').val();
        var data = {"id": id, "genreType": genreType};

        $.ajax({
            url: '/api/v1/genres',
            type: 'PATCH',
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

    $('#delete').click(function(event) {
        var id = $('#delete_id_genre').val();
        $.ajax({
            url: '/api/v1/genres/' + id,
            type: 'DELETE',
            dataType: 'json',
            
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

    function load_data_genre() {
        $.ajax({
            url: '/api/v1/genres',
            type: 'GET',
            dataType: 'json',

        })
        .done(function (data) {
            console.log("success");
            var output = "";

            for (var i = 0; i < data.length; i++) {
                output = "<tr row_id_genre=" + data[i].id + ">"
                        + "<td class='text-center'>" + data[i].id + "</td>"
                        + "<td class='text-center'>" + data[i].genreType + "</td>"

                        + "<td class='text-center'>"
                        + "<a href='#' class='text-primary' data-toggle='modal' id_edit_genre=" + data[i].id + " data-type='update-genre' name=" + data[i].genreType + ">"
                        + "<i class='ace-icon fa fa-pencil bigger-130'></i>"
                        + "</a>"
                        + "</td>"
                        + "<td class='text-center'>"
                        + "<a href='#' class='text-danger' id_delete_genre=" + data[i].id + " data-type='delete-genre'>"
                        + "<i class='ace-icon fa fa-trash-o bigger-130'></i>"
                        + "</a>"
                        + "</td>"

                        + "</tr>";

                        $('#body_genre').append(output);
            }

            $('a[data-type=update-genre]').click(function(event) {
                var id = $(this).attr('id_edit_genre');
                // console.log(id);
                $('#edit_id_genre').val(id);

                $.ajax({
                    url: '/api/v1/genres/' + id,
                    type: 'GET',
                    dataType: 'json',
                    
                })
                .done(function(data) {
                    // console.log(data.genreType);
                    $('#type-genre').val(data.genreType);
                });
                
                $('#edit-modal').modal('show');
                
            });

            $('a[data-type=delete-genre]').click(function(event) {
                var id = $(this).attr('id_delete_genre');
                $('#delete_id_genre').val(id);
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