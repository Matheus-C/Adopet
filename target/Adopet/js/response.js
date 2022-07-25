$(document).ready(function() {
        
    
    $("#buttonLogin").click(function(e) {
                    e.preventDefault();
                    var url = "sample/auth/login";
                    var formData = $("#formLogin").serialize();
                    $.ajax({
                        url: url,
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">Login realizado com sucesso!</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
       
    
    $("#buttonFormUsuario").click(function(e) {
                    e.preventDefault();
                    var url = "sample/auth/register";
                    var formData = $("#formUsuario").serialize();
                    $.ajax({
                        url: url,
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
    
    
    $("#buttonNovaVariacao").click(function(e) {
                    e.preventDefault();
                    var url = "sample/pets/variacoes";
                    var formData = $("#formVariacao").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
    
    
    $("#buttonEditarFiltro").click(function(e) {
                    e.preventDefault();
                    var url = "sample/adotantes/filtro";
                    var formData = $("#formEditarFiltro").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXdhbnRzcGV0czEyMyIsImlzcyI6Im15X2FwcCIsImlhdCI6MTY1ODc1Mjk5Mn0.PgU7fe2ep4qEkQ8JoSz374QL6tV251LOpwOloy0U7fc'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
                
    
    $("#buttonNovoPet").click(function(e) {
                    e.preventDefault();
                    var url = "sample/pets";
                    var formData = $("#formPet").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });            
         
    
    $("#buttonContatoDel").click(function(e) {
                    e.preventDefault();
                    var url = "sample/users/2/contatos/Atendimento";
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        dataType: "json",
                        type: "DELETE",
                        success: function( data, status, jqXhr ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
           
    
    $("#buttonNovoContato").click(function(e) {
                    e.preventDefault();
                    var url = "sample/users/2/contatos";
                    var formData = $("#formContato").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
    
             
    $("#buttonNovaInstituicao").click(function(e) {
                    e.preventDefault();
                    var url = "sample/users/2";
                    var formData = $("#formInstituicao").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
                
                
    $("#buttonPetDel").click(function(e) {
                    e.preventDefault();
                    var url = "sample/pets/14";
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        dataType: "json",
                        type: "DELETE",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });                
                
                
    $("#buttonEditarPet").click(function(e) {
                    e.preventDefault();
                    var url = "sample/pets/14";
                    var formData = $("#formEditarPet").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });
                
                
    $("#buttonEditarUsuario").click(function(e) {
                    e.preventDefault();
                    var url = "sample/users/2";
                    var formData = $("#formEditarUsuario").serialize();
                    $.ajax({
                        url: url,
                        headers: {
                            Authorization:
                        'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWlnb3NhbmltYWlzMTIzIiwiaXNzIjoibXlfYXBwIiwiaWF0IjoxNjU4NTU1NzQ2fQ.qM2143YvP47bktkUcwkIxi2TUuLjIN-EcDwrZI2KvSU'
                        },
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        success: function( data ) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + data.mensagem + '</h1>');
                        },
                        error: function(jqxhr, status, errorMsg) {
                            $("#content").empty()
                                    .append('<h1 class="text-center mb-4 pb-5">' + errorMsg + '</h1>');
                        }
                    });
                });           
});