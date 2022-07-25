$(document).ready(function() {
    //função que esconde e mostra as opções de tipo de pessoa fisica
    $('#pessoaFisica').hide();
    $('#instituicao').hide();
    $('#inst').remove();
    $('#tipo').change(function(){
        if ($('#tipo option:selected').val() === '1'){
            $('#pessoaFisica').show();
            $('#instituicao').hide();
            $('#inst').prop('selected', false);
            $('#inst').remove();
            $('#perfil').prop('disabled', false);
        } if($('#tipo option:selected').val() === '0'){
            $('#pessoaFisica').hide();
            $('#instituicao').show();
            $("#perfil").append("<option value='instituicao' id='inst'>Instituição</option>");
            $('#inst').prop('selected', true);
            $('#perfil').prop('disabled', true);
        }
    });

});