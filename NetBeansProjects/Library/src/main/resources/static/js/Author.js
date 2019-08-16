$(document).ready(function () {

   
    load_data_author();


    
    $(".search").attr('id', 'searchAuthor');
    

    $('#add-author').click(function (event) {
        $('#name-author').val("");
        var token = $('.token_header').val();
        alert(token);
        $('#add-modal').modal('show');
    });



    $('#add').click(function(event) {

        $.ajaxSetup({
            headers: {
                'authorization': token
            }

        });

        var name = $('#name-author').val();
        var data = {"name": name};
        // console.log(name);
        $.ajax({
            url: '/api/v1/authors',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
        })
        .done(function(data) {
            // $('#add-modal').modal('hide');
            alert("Success !");
            location.reload();
            
            // var output = "<tr row_id_author=" + data.id + ">"
            //                     + "<td class='text-center'>" + data.id + "</td>"
            //                     + "<td class='text-center'>" + name + "</td>"

            //                     + "<td class='text-center'>"
            //                     + "<a href='#' class='text-primary' data-toggle='modal' id_edit_author=" + data.id + " data-type='update-author' name=" + name + ">"
            //                     + "<i class='ace-icon fa fa-pencil bigger-130'></i>"
            //                     + "</a>"
            //                     + "</td>"
            //                     + "<td class='text-center'>"
            //                     + "<a href='#' class='text-danger' id_delete_author=" + data.id + " data-type='delete-author'>"
            //                     + "<i class='ace-icon fa fa-trash-o bigger-130'></i>"
            //                     + "</a>"
            //                     + "</td>"

            //                     + "</tr>";

            //                     $('#body_author').append(output);

            // $('a[data-type=update-author]').click(function(event) {
               
            //    var id = $(this).attr('id_edit_author');
            //    // console.log(id);
            //    $.ajax({
            //        url: '/api/v1/authors/' + id,
            //        type: 'GET',
            //        dataType: 'json',
                   
            //    })
            //    .done(function(data) {
            //     // console.log(data);
            //        $('#name-author').val(data.name);
            //    })
               
               
            //    $('#edit-modal').modal('show');
            // });
            
        })
        .fail(function(err) {
            console.log(err);
//            alert("Error !");
            alert(err.responseText);
        })
        .always(function() {
            console.log("complete");
        });
        
    });

    $('#edit').click(function(event) {

        var name = $('#author-name').val();
        var id = $('#edit_id_author').val();

        var data = {"id": id, "name": name}
        console.log(data);
        $.ajax({
            url: '/api/v1/authors',
            type: 'PATCH',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
        })
        .done(function() {
            alert("Success !");
            location.reload();
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });
        

    });

    $('#delete').click(function(event) {

        var id = $('#delete_id_author').val();
        $.ajax({
           url: '/api/v1/authors/' + id,
           type: 'DELETE',
           dataType: 'json',
           
       })
       .done(function(data) {
            alert("Success !");
            location.reload();
       })
       .always(function() {
            console.log("complete");
            location.reload();
        });
        
    });

    $('#searchAuthor').click(function(event) {
        var dataSearch = $('#data-search').val();
        var data = {"name": dataSearch};

        $.ajax({
            url: '/api/v1/authors/search',
            type: 'POST',
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
        })
        .done(function(data) {
            // alert("success");
            var output = "";

            for(var i = 0; i < data.length; i++){
                output += "<tr row_id_author=" + data.id + ">"
                            + "<td class='text-center'>" + data[i].id + "</td>"
                            + "<td class='text-center'>" + data[i].name + "</td>"

                            + "<td class='text-center'>"
                            + "<a href='#' class='text-primary' data-toggle='modal' id_edit_author=" + data[i].id + " data-type='update-author' name=" + data[i].name + ">"
                            + "<i class='ace-icon fa fa-pencil bigger-130'></i>"
                            + "</a>"
                            + "</td>"
                            + "<td class='text-center'>"
                            + "<a href='#' class='text-danger' id_delete_author=" + data[i].id + " data-type='delete-author' data-toggle='modal'>"
                            + "<i class='ace-icon fa fa-trash-o bigger-130'></i>"
                            + "</a>"
                            + "</td>"

                            + "</tr>";
            }
            $('#body_author').html(output);

            $('a[data-type=update-author]').click(function(event) {
               
               var id = $(this).attr('id_edit_author');
               $('#edit_id_author').val(id);
               // console.log(id);
               $.ajax({
                   url: '/api/v1/authors/' + id,
                   type: 'GET',
                   dataType: 'json',
                   
               })
               .done(function(data) {
                // console.log(data);
                   $('#author-name').val(data.name);
               })
               
               $('#edit-modal').modal('show');
            });

            $('a[data-type=delete-author]').click(function(event) {
                var id = $(this).attr('id_delete_author');
                $('#delete_id_author').val(id);
                $('#delete-modal').modal('show');
                
            });
                        

        })
        .fail(function(err) {
            console.log(err);
            alert("Data note found !");
        })
        
    });

    function load_data_author() {
        $.ajax({
            url: '/api/v1/authors',
            type: 'GET',
            dataType: 'json',

        })
        .done(function (data) {
            console.log("success");
            var output = "";

            for (var i = 0; i < data.length; i++) {
                output = "<tr row_id_author=" + data[i].id + ">"
                        + "<td class='text-center'>" + data[i].id + "</td>"
                        + "<td class='text-center'>" + data[i].name + "</td>"

                        + "<td class='text-center'>"
                        + "<a href='#' class='text-primary' data-toggle='modal' id_edit_author=" + data[i].id + " data-type='update-author' name=" + data[i].name + ">"
                        + "<i class='ace-icon fa fa-pencil bigger-130'></i>"
                        + "</a>"
                        + "</td>"
                        + "<td class='text-center'>"
                        + "<a href='#' class='text-danger' id_delete_author=" + data[i].id + " data-type='delete-author' data-toggle='modal'>"
                        + "<i class='ace-icon fa fa-trash-o bigger-130'></i>"
                        + "</a>"
                        + "</td>"

                        + "</tr>";
                        
                        $('#body_author').append(output);
            }

            $('a[data-type=update-author]').click(function(event) {
               
               var id = $(this).attr('id_edit_author');
               $('#edit_id_author').val(id);
               // console.log(id);
               $.ajax({
                   url: '/api/v1/authors/' + id,
                   type: 'GET',
                   dataType: 'json',
                   
               })
               .done(function(data) {
                // console.log(data);
                   $('#author-name').val(data.name);
               })
               
               $('#edit-modal').modal('show');
            });

            $('a[data-type=delete-author]').click(function(event) {
                var id = $(this).attr('id_delete_author');
                $('#delete_id_author').val(id);
                $('#delete-modal').modal('show');
                
            });


            // $('#body_author').html(output);
        })
        .fail(function () {
            console.log("error");
        })
        .always(function () {

            console.log("complete");

        });

    }
});