
$(document).ready(function(){

    var base_url = 'http://localhost:8080/';

    $('.input-number').on('input', function () { 
    	this.value = this.value.replace(/[^0-9]/g,'');
    });

    $('#div-diagnostico').hide();
    
    var Fn = {
        // Valida el rut con su cadena completa "XXXXXXXX-X"
        validaRut : function (rutCompleto) {
            if (!/^[0-9]+[-|‐]{1}[0-9kK]{1}$/.test( rutCompleto ))
                return false;
            var tmp 	= rutCompleto.split('-');
            var digv	= tmp[1]; 
            var rut 	= tmp[0];
            if ( digv == 'K' ) digv = 'k' ;
            return (Fn.dv(rut) == digv );
        },
        dv : function(T){
            var M=0,S=1;
            for(;T;T=Math.floor(T/10))
                S=(S+T%10*(9-M++%6))%11;
            return S?S-1:'k';
        }
    }
    
    // Uso de la función
    $('#txtDigIngreso').focusout(function(e){
        e.stopImmediatePropagation();
        //alert( Fn.validaRut($('#txtRutIngreso').val()+'-'+$('#txtDigIngreso').val()) ? 'Valido' : 'Rut inválido');
        if(!Fn.validaRut($('#txtRutIngreso').val()+'-'+$('#txtDigIngreso').val())){
            alert('Rut inválido');
            $("#btnGuardaIngreso").attr("disabled", true);
            $('#txtRutIngreso').focus();
        }else{
            $("#btnGuardaIngreso").attr("disabled", false);
        }
    });


    $('#sidebarCollapse').on('click', function () {
        $('#sidebar, #content').toggleClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });

    //INGRESO PACIENTE
    $('#btnGuardaIngreso').on('click', function(e){
        e.stopImmediatePropagation();

        var r = confirm("¿Confirma que desea guardar el formulario?");
        if (r == true) {
            if($('#txtRutIngreso').val() == '' || $('#txtNombreIngreso').val() == '' || $('#txtApatIngreso').val() == ''){ // valida campos vacios
                alert('Debe completar los datos del paciente!');
            }else{
                var form_pac = {
                    'nombre'      : $('#txtNombreIngreso').val(),
                    'aPat'        : $('#txtApatIngreso').val(),
                    'aMat'        : $('#txtAmatIngreso').val(),
                    'rutNum'      : $('#txtRutIngreso').val(),
                    'rutDig'      : $('#txtDigIngreso').val(),
                    'direccion'   : $('#txtDireccionIngreso').val(),
                    'fechaNac'    : $('#txtFnacIngreso').val(),
                    'telefono'    : $('#txtFonoUsuIngreso').val(),
                    'email'       : $('#txtEmailIngreso').val(),
                    'idPrevision' : $('#cboPrevisionIngreso').val()
                }
    
                var rutnum = $('#txtRutIngreso').val();
    
                //verifica si el paciente existe
                $.ajax({
                    type: 'get',
                    url: base_url + 'ingreso/verifica-paciente/'+rutnum,
                    data: {rutnum: rutnum},
                    success: function(data){
                        if(data != null){
                            console.log('el paciente ya existe');
    
                            var form_ficha = {
                                'rutPac'   : $('#txtRutIngreso').val(),
                                'fecha' : new Date(),
                                'estado': 1  
                            }
                            
                            $.ajax({
                                type: 'post',
                                url: base_url + 'ingreso/add-ficha',
                                contentType: 'application/json; charset=utf-8',
                                dataType: 'json',
                                data: JSON.stringify(form_ficha),
                                success: function(data){
                                    var form_ingreso = {
                                        'idFicha'           : data.idFicha,
                                        'fecha'             : new Date(),
                                        'alergias'          : $('#txtAlergiasIngreso').val(),
                                        'telefonoCuidador'  : $('#txtFonoCuidaIngreso').val(),
                                        'diag1'             : $('#txtDiag1').val(),
                                        'diag2'             : $('#txtDiag2').val(),
                                        'diag3'             : $('#txtDiag3').val(),
                                        'diag4'             : $('#txtDiag4').val(),
                                        'planTto1'          : $('#txtTrat1').val(),
                                        'planTto2'          : $('#txtTrat2').val(),
                                        'planTto3'          : $('#txtTrat3').val(),
                                        'planTto4'          : $('#txtTrat4').val(),
                                        'rutUsu'            : $('#txtRutUsu').val()
                                    }
    
                                    $.ajax({
                                        type: 'post',
                                        url: base_url + 'ingreso/add-ingreso',
                                        contentType: 'application/json; charset=utf-8',
                                        dataType: 'json',
                                        data: JSON.stringify(form_ingreso),
                                        success: function(){
                                            console.log('ingreso guardado exitosamente');
                                            $('#txtNombreIngreso').val('');
                                            $('#txtApatIngreso').val('');
                                            $('#txtAmatIngreso').val('');
                                            $('#txtRutIngreso').val('');
                                            $('#txtDigIngreso').val('');
                                            $('#txtDireccionIngreso').val('');
                                            $('#txtFnacIngreso').val('');
                                            $('#txtAlergiasIngreso').val('');
                                            $('#txtFonoUsuIngreso').val('');
                                            $('#txtEmailIngreso').val('');
                                            $('#cboPrevisionIngreso').val('');
                                            $('#txtDiag1').val('');
                                            $('#txtDiag2').val('');
                                            $('#txtDiag3').val('');
                                            $('#txtDiag4').val('');
                                            $('#txtTrat1').val('');
                                            $('#txtTrat2').val('');
                                            $('#txtTrat3').val('');
                                            $('#txtTrat4').val('');
                                        },
                                        error: function(){
                                            console.log('error al guardar ingreso');
                                        }
                                    });
                                    console.log('paciente y ficha guardados exitosamente');
                                }
                            });
                            
                        }else{
                            console.log('el paciente no existe');
                        }
                        
                    },
                    error: function(){
                        console.log('El paciente no existe');
    
                        $.ajax({
                            type: 'post',
                            url: base_url + 'ingreso/add-paciente',
                            contentType: 'application/json; charset=utf-8',
                            dataType: 'json',
                            data: JSON.stringify(form_pac),
                            success: function(){
                                console.log('paciente guardado');
                            },
                            error: function(){
                                console.log('error al guardar ingreso');
                            }
                        }).done(function(){
                            var form_ficha = {
                                'rutPac'   : $('#txtRutIngreso').val(),
                                'fecha' : new Date(),
                                'estado': 1  
                            }
            
                            $.ajax({
                                type: 'post',
                                url: base_url + 'ingreso/add-ficha',
                                contentType: 'application/json; charset=utf-8',
                                dataType: 'json',
                                data: JSON.stringify(form_ficha),
                                success: function(data){
                                
                                    var form_ingreso = {
                                        'idFicha'           : data.idFicha,
                                        'fecha'             : new Date(),
                                        'alergias'          : $('#txtAlergiasIngreso').val(),
                                        'telefonoCuidador'  : $('#txtFonoCuidaIngreso').val(),
                                        'diag1'             : $('#txtDiag1').val(),
                                        'diag2'             : $('#txtDiag2').val(),
                                        'diag3'             : $('#txtDiag3').val(),
                                        'diag4'             : $('#txtDiag4').val(),
                                        'planTto1'          : $('#txtTrat1').val(),
                                        'planTto2'          : $('#txtTrat2').val(),
                                        'planTto3'          : $('#txtTrat3').val(),
                                        'planTto4'          : $('#txtTrat4').val(),
                                        'rutUsu'            : 1
                                    }
    
                                    $.ajax({
                                        type: 'post',
                                        url: base_url + 'ingreso/add-ingreso',
                                        contentType: 'application/json; charset=utf-8',
                                        dataType: 'json',
                                        data: JSON.stringify(form_ingreso),
                                        success: function(){
                                            console.log('ingreso guardado exitosamente');
                                            $('#txtNombreIngreso').val('');
                                            $('#txtApatIngreso').val('');
                                            $('#txtAmatIngreso').val('');
                                            $('#txtRutIngreso').val('');
                                            $('#txtDigIngreso').val('');
                                            $('#txtDireccionIngreso').val('');
                                            $('#txtFnacIngreso').val('');
                                            $('#txtAlergiasIngreso').val('');
                                            $('#txtFonoUsuIngreso').val('');
                                            $('#txtEmailIngreso').val('');
                                            $('#cboPrevisionIngreso').val('');
                                            $('#txtDiag1').val('');
                                            $('#txtDiag2').val('');
                                            $('#txtDiag3').val('');
                                            $('#txtDiag4').val('');
                                            $('#txtTrat1').val('');
                                            $('#txtTrat2').val('');
                                            $('#txtTrat3').val('');
                                            $('#txtTrat4').val('');
                                        },
                                        error: function(){
                                            console.log('error al guardar ingreso');
                                        }
                                    });

                                    console.log('paciente y ficha guardados exitosamente');

                                }
                            });
                        });
                    }
                });
            }
        } 
    });
});