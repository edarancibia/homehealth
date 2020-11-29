
$(document).ready(function(){

    var base_url = 'http://localhost:8080/';
    //var base_url = 'https://hhosorno.herokuapp.com/';

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
                    url: base_url + 'paciente/verifica-paciente/'+rutnum,
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
                                        'rutUsu'            : $('#txtRutUsu').val(),
                                        'edad'              : $('#txtEdad').val()
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
                            url: base_url + 'paciente/add-paciente',
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
                                        'edad'              : $('#txtEdad').val(),
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

    // - - - - -Atencion medica - - - - - - - -
    $('#btnGuardaAtencionM').on('click', function(e){
        e.stopImmediatePropagation();

        var r = confirm("¿Confirma que desea guardar la atenciòn?");
        if (r == true) {
            if($('#txtAnamnesis').val() == '' || $('#txtExamen').val() == '' || $('#txtDiagnostico').val() == ''){
                alert('Debe completar los datos del formulario!')
            }else{
                var dm,ca,cardio,hta,dld,tbc,epoc,lcfa,acxfa,acv,depre,ob;

                if ($('#dm').is(':checked')) {
                    dm = 1;
                }
                if ($('#ca').is(':checked')) {
                    ca = 1;
                }
                if ($('#cardio').is(':checked')) {
                    cardio = 1;
                }
                if ($('#hta').is(':checked')) {
                    hta = 1;
                }
                if ($('#dld').is(':checked')) {
                    dld = 1;
                }
                if ($('#tbc').is(':checked')) {
                    tbc = 1;
                }
                if ($('#epoc').is(':checked')) {
                    epoc = 1;
                }
                if ($('#lcfa').is(':checked')) {
                    lcfa = 1;
                }
                if ($('#acxfa').is(':checked')) {
                    acxfa = 1;
                }
                if ($('#acv').is(':checked')) {
                    acv = 1;
                }
                if ($('#depre').is(':checked')) {
                    depre = 1;
                }if ($('#ob').is(':checked')) {
                    ob = 1;
                }
    
                var form_atencion = {
                    'id_ficha'       : $('#txtHiddenFicha').val(),
                    'fecha'         : new Date(),
                    'anamnesis'     : $('#txtAnamnesis').val(),
                    'examenFisico'  : $('#txtExamen').val(),
                    'diagPresuntivo': $('#txtDiagnostico').val(),
                    'indDomicilio'  : $('#txtIndicaciones').val(),
                    'rutUsu'        : 1, //cambiar
                    'estado'        : 1,
                    'exmenes'      : $('#txtExamenes').val(),
                    'dm'            : dm,
                    'ca'            : ca,
                    'cardio'        : cardio,
                    'hta'           : hta,
                    'dld'           : dld,
                    'tbc'           : tbc,
                    'lcfa'          : lcfa,
                    'acxfa'         : acxfa,
                    'acv'           : acv,
                    'depre'         : depre,
                    'ob'            : ob
                }
    
                $.ajax({
                    type: 'post',
                    url: base_url + 'atencion-m/save',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify(form_atencion),
                    success: function(){
                        console.log('atencion guardada exitosamente');
                        $('#txtHiddenFicha').attr('readonly', true);
                        $('#txtAnamnesis').attr('readonly', true);
                        $('#txtExamen').attr('readonly', true);
                        $('#txtDiagnostico').attr('readonly', true);
                        $('#txtIndicaciones').attr('readonly', true);
                        $('#txtExamenes').attr('readonly', true);
                        $('#btnGuardaAtencionM').attr('readonly', true);
                    },
                    error: function(){
                        console.log('error al guardar atencion');
                    }
                });
            }
        }
    });

    // - - - - - SIGNOS VITALES - - - - - - - - -
    $('#btnSaveSignos').on('click', function(e){
        e.stopImmediatePropagation();

        var r = confirm("¿Confirma que desea guardar los signos vitales?");
        if (r == true) {
            var form_signos = {
                'idFicha'   : $('#txtHiddenFicha').val(),
                'fecha'     : new Date(),
                'fc'        : $('#txtFc').val(),
                'sat'       : $('#txtSat').val(),
                'pa'        : $('#txtPa').val(),
                'tax'       : $('#txtTax').val(),
                'hgt'       : $('#txtHgt').val(),
                'resp'      : $('#txtResp').val(),
                'glasgow'   : $('#txtGlasgow').val(),
                'rutUsu'    : 1
            }

            $.ajax({
                type: 'post',
                url: base_url + 'signos/add',
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                data: JSON.stringify(form_signos),
                success: function(){
                    $('#modalSignos').modal('hide');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();

                    $.ajax({
                        type: 'get',
                        url: base_url + 'signos/list/' + $('#txtHiddenFicha').val(),
                        success: function(data){
                            //$('#tabla-signos thead').remove();
                            //$("#tabla-signos thead").append("<th>Fecha</th><th>FC</th><th>SAT. O2</th><th>P/A</th><th>Tº AX.</th><th>HGT</th><th>RESP.</th>");
                            $('#tabla-signos tr').remove();
                            $.each(data, function(i, item){
                                $('<tr>').html(
                                    "<td>"+data[i].fecha+"</td>" +
                                    "<td>"+data[i].fc+"</td>" +
                                    "<td>"+data[i].sat+"</a></td>"+
                                    "<td>"+data[i].pa+"</td>"+"<td>"+data[i].tax+"</td>"+
                                    "<td>"+data[i].hgt+"</td>"+ "<td>"+data[i].resp+"</td>"+
                                     "</tr>").appendTo('#tabla-signos');
                            });
                        }
                    });
                },
                error: function(){
                    console.log('error al guardar signos');
                }
            });
        }
        
    });

    //- - - - EVOLUCION MEDICA - - - - - - - - --  
    $('#btnSaveEvMedica').on('click', function(e){
        e.stopImmediatePropagation();

        var r = confirm("¿Confirma que desea guardar la Evoluciòn?");
        if (r == true) {
            var form_evolucion_m = {
                'idFicha'     : $('#txtHiddenFicha').val(),
                'fecha'       : new Date(),
                'descripcion' : $('#txtEvolucionM').val(),
                'indicaciones' : $('#txtIndicaciones').val(),
                'rutUsu'      : 1
            }

            $.ajax({
                type: 'post',
                url: base_url + 'evolucion-m/add',
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                data: JSON.stringify(form_evolucion_m),
                success: function(){
                    $('#modalEvMedica').modal('hide');
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#txtEvolucionM').val('');
                    $('#txtIndicaciones').val('');

                    $.ajax({
                        type: 'get',
                        url: base_url + 'evolucion-m/list/' + $('#txtHiddenFicha').val(),
                        success: function(data){
                            //$('#tabla-evMedica thead').remove();
                            //$("#tabla-evMedica thead").append("<th>Ficha</th><th>Fecha</th><th>Comentarios</th>");
                            $('#tabla-evMedica tr').remove();
                            $.each(data, function(i, item){
                                $('<tr>').html(
                                    "<td>"+data[i].id_ficha+"</td>" +
                                    "<td>"+data[i].fecha+"</td>" +
                                    "<td>"+data[i].descripcion+"</td>" +
                                    "<td>"+data[i].indicaciones+"</td>" +
                                     "</tr>").appendTo('#tabla-evMedica');
                            });
                        }
                    });
                },
                error: function(){
                    console.log('error al guardar evolucion');
                }
            });
        }
        
    });

        //- - - - EVOLUCION ENFERMERIA - - - - - - - - --  
        $('#btnSaveEvEnfermeria').on('click', function(e){
            e.stopImmediatePropagation();
    
            var r = confirm("¿Confirma que desea guardar la Evoluciòn?");
            if (r == true) {
                var form_evolucion_e = {
                    'idFicha'    : $('#txtHiddenFicha').val(),
                    'fecha'      : new Date(),
                    'descripcion': $('#txtEvolucionE').val(),
                    'rutUsu'     : 1
                }
    
                $.ajax({
                    type: 'post',
                    url: base_url + 'evolucion-e/add',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify(form_evolucion_e),
                    success: function(){
                        $('#modalEvEnfermeria').modal('hide');
                        $('body').removeClass('modal-open');
                        $('.modal-backdrop').remove();
                        $('#txtEvolucionE').val('');
                        $.ajax({
                            type: 'get',
                            url: base_url + 'evolucion-e/list/' + $('#txtHiddenFicha').val(),
                            success: function(data){
                                $('#tabla-evEnfermeria tr').remove();
                                $.each(data, function(i, item){
                                    $('<tr>').html(
                                        "<td>"+data[i].id_ficha+"</td>" +
                                        "<td>"+data[i].fecha+"</td>" +
                                        "<td>"+data[i].descripcion+"</td>" +
                                         "</tr>").appendTo('#tabla-evEnfermeria');
                                });
                            }
                        });
                    },
                    error: function(){
                        console.log('error al guardar evolucion');
                    }
                });
            }
            
        });

        //- - - - EVOLUCION KINESICA - - - - - - - - --  
        $('#btnSaveEvKine').on('click', function(e){
            e.stopImmediatePropagation();
    
            var r = confirm("¿Confirma que desea guardar la Evoluciòn?");
            if (r == true) {
                var form_evolucion_k = {
                    'idFicha'    : $('#txtHiddenFicha').val(),
                    'fecha'      : new Date(),
                    'descripcion': $('#txtEvolucionK').val(),
                    'rutUsu'     : 1
                }
    
                $.ajax({
                    type: 'post',
                    url: base_url + 'evolucion-k/add',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify(form_evolucion_k),
                    success: function(){
                        $('#modalEvKine').modal('hide');
                        $('body').removeClass('modal-open');
                        $('.modal-backdrop').remove();
                        $('#txtEvolucionK').val('');
                        $.ajax({
                            type: 'get',
                            url: base_url + 'evolucion-k/list/' + $('#txtHiddenFicha').val(),
                            success: function(data){
                                $('#tabla-evKine tr').remove();
                                $.each(data, function(i, item){
                                    $('<tr>').html(
                                        "<td>"+data[i].id_ficha+"</td>" +
                                        "<td>"+data[i].fecha+"</td>" +
                                        "<td>"+data[i].descripcion+"</td>" +
                                         "</tr>").appendTo('#tabla-evKine');
                                });
                            }
                        });
                    },
                    error: function(){
                        console.log('error al guardar evolucion');
                    }
                });
            }
            
        });

            //- - - - EDUCACION DE ENFERMERIA - - - - - - - - --  
            $('#btnSaveEducacion').on('click', function(e){
                e.stopImmediatePropagation();
        
                var r = confirm("¿Confirma que desea guardar la Educación?");
                if (r == true) {
                    var form_educacion = {
                        'idFicha'    : $('#txtHiddenFicha').val(),
                        'fecha'      : new Date(),
                        'descripcion': $('#txtEducacion').val(),
                        'rutUsu'     : 1
                    }
        
                    $.ajax({
                        type: 'post',
                        url: base_url + 'educacion-enf/add',
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(form_educacion),
                        success: function(){
                            $('#modalEducacion').modal('hide');
                            $('body').removeClass('modal-open');
                            $('.modal-backdrop').remove();
                            $('#txtEducacion').val('');
                            $.ajax({
                                type: 'get',
                                url: base_url + 'educacion-enf/list/' + $('#txtHiddenFicha').val(),
                                success: function(data){
                                    $('#tabla-educacion tr').remove();
                                    $.each(data, function(i, item){
                                        $('<tr>').html(
                                            "<td>"+data[i].id_ficha+"</td>" +
                                            "<td>"+data[i].fecha+"</td>" +
                                            "<td>"+data[i].descripcion+"</td>" +
                                             "</tr>").appendTo('#tabla-educacion');
                                    });
                                }
                            });
                        },
                        error: function(){
                            console.log('error al guardar educacion');
                        }
                    });
                }
                
            });
    
            //- - - - EVOLUCION OTROS PROFESIONALES - - - - - - - - --  
            $('#btnSaveEvOtros').on('click', function(e){
                e.stopImmediatePropagation();
        
                var r = confirm("¿Confirma que desea guardar la Evolución?");
                if (r == true) {
                    var form_evolucion_o = {
                        'idFicha'    : $('#txtHiddenFicha').val(),
                        'fecha'      : new Date(),
                        'descripcion': $('#txtEvolucionO').val(),
                        'rutUsu'     : 1
                    }
        
                    $.ajax({
                        type: 'post',
                        url: base_url + 'evolucion-o/add',
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(form_evolucion_o),
                        success: function(){
                            $('#modalEvOtros').modal('hide');
                            $('body').removeClass('modal-open');
                            $('.modal-backdrop').remove();
                            $('#txtEvolucionO').val('');
                            $.ajax({
                                type: 'get',
                                url: base_url + 'evolucion-o/list/' + $('#txtHiddenFicha').val(),
                                success: function(data){
                                    $('#tabla-evOtros tr').remove();
                                    $.each(data, function(i, item){
                                        $('<tr>').html(
                                            "<td>"+data[i].id_ficha+"</td>" +
                                            "<td>"+data[i].fecha+"</td>" +
                                            "<td>"+data[i].descripcion+"</td>" +
                                             "</tr>").appendTo('#tabla-evOtros');
                                    });
                                }
                            });
                        },
                        error: function(){
                            console.log('error al guardar evolucion');
                        }
                    });
                }
                
            });
    
        //- - - - EVOLUCION OTROS PROFESIONALES - - - - - - - - --  
         /*   $('#btnSaveEvOtros').on('click', function(e){
                e.stopImmediatePropagation();
        
                var r = confirm("¿Confirma que desea guardar la Evoluciòn?");
                if (r == true) {
                    var form_evolucion_o = {
                        'idFicha'    : $('#txtHiddenFicha').val(),
                        'fecha'      : new Date(),
                        'descripcion': $('#txtEvolucionO').val(),
                        'rutUsu'     : 1
                    }
        
                    $.ajax({
                        type: 'post',
                        url: base_url + 'evolucion-o/add',
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(form_evolucion_o),
                        success: function(){
                            $('#modalEvOtros').modal('hide');
                            $('body').removeClass('modal-open');
                            $('.modal-backdrop').remove();
                            $('#txtEvolucionO').val('');
                            $.ajax({
                                type: 'get',
                                url: base_url + 'evolucion-o/list/' + $('#txtHiddenFicha').val(),
                                success: function(data){
                                    $('#tabla-evOtros tr').remove();
                                    $.each(data, function(i, item){
                                        $('<tr>').html(
                                            "<td>"+data[i].id_ficha+"</td>" +
                                            "<td>"+data[i].fecha+"</td>" +
                                            "<td>"+data[i].descripcion+"</td>" +
                                             "</tr>").appendTo('#tabla-evOtros');
                                    });
                                }
                            });
                        },
                        error: function(){
                            console.log('error al guardar evolucion');
                        }
                    });
                }
                
            });*/

            //- - - - PROCEDIMIENTOS - - - - - - - - --  
            $('#btnSaveProce').on('click', function(e){
                e.stopImmediatePropagation();
            
                var r = confirm("¿Confirma que desea guardar el procedimiento?");
                if (r == true) {
                    var form_evolucion_o = {
                        'idFicha'    : $('#txtHiddenFicha').val(),
                        'fecha'      : new Date(),
                        'descripcion': $('#txtProce').val(),
                        'rutUsu'     : 1
                    }
            
                    $.ajax({
                        type: 'post',
                        url: base_url + 'proc-enf/add',
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        data: JSON.stringify(form_evolucion_o),
                        success: function(){
                            $('#modalProce').modal('hide');
                            $('body').removeClass('modal-open');
                            $('.modal-backdrop').remove();
                            $('#txtProce').val('');
                            $.ajax({
                                type: 'get',
                                url: base_url + 'proc-enf/list/' + $('#txtHiddenFicha').val(),
                                success: function(data){
                                    $('#tabla-proce tr').remove();
                                    $.each(data, function(i, item){
                                        $('<tr>').html(
                                            "<td>"+data[i].id_ficha+"</td>" +
                                            "<td>"+data[i].fecha+"</td>" +
                                            "<td>"+data[i].descripcion+"</td>" +
                                            "</tr>").appendTo('#tabla-proce');
                                    });
                                }
                            });
                        },
                        error: function(){
                            console.log('error al guardar evolucion');
                        }
                    });
                }    
            });

                       //- - - - EPICRISIS MEDICA - - - - - - - - --  
                       $('#btnGuardaEpicrisisM').on('click', function(e){
                        e.stopImmediatePropagation();
                    
                        var r = confirm("¿Confirma que desea guardar la epicrisis? Al hacerlo dará de alta al paciente.");
                        if (r == true) {
                            if($())
                            var form_epicrisis = {
                                'idFicha'      : $('#txtHiddenFicha').val(),
                                'fecha'        : new Date(),
                                'resumen'      : $('#txtResumen').val(),
                                'indicaciones' : $('#txtIndicaciones').val(),
                                'rutUsu'       : 1
                            }
                    
                            $.ajax({
                                type: 'post',
                                url: base_url + 'epicrisis-med/add',
                                contentType: 'application/json; charset=utf-8',
                                dataType: 'json',
                                data: JSON.stringify(form_epicrisis),
                                success: function(){
                                    $('#txtResumen').attr('disabled', true);
                                    $('#txtIndicaciones').attr('disabled', true);
                                    $('#btnGuardaEpicrisisM').attr('disabled', true);
                                },
                                error: function(){
                                    console.log('error al guardar epicrisis');
                                }
                            }).done(function(){
                                //DA DE ALTA AL PACIENTE CERRANDO LA FICHA
                                $.ajax({
                                    type: 'put',
                                    url: base_url + 'epicrisis-med/alta/'+$('#txtHiddenFicha').val(),
                                    success: function(){
                                        console.log('dado de alta');
                                    },
                                    error: function(){
                                        console.log('error al cerrar ficha');
                                    }
                                });
                            });
                        }    
                    });

                //- - - - EVOLUCION TENS - - - - - - - - -- - - - 
                $('#btnSaveEvTens').on('click', function(e){
                    e.stopImmediatePropagation();
                
                    var r = confirm("¿Confirma que desea guardar la evolución?");
                    if (r == true) {
                        var form_evolucion_t = {
                            'idFicha'    : $('#txtHiddenFicha').val(),
                            'fecha'      : new Date(),
                            'descripcion': $('#txtEvTens').val(),
                            'rutUsu'     : 1
                        }
                
                        $.ajax({
                            type: 'post',
                            url: base_url + 'ev-tens/add',
                            contentType: 'application/json; charset=utf-8',
                            dataType: 'json',
                            data: JSON.stringify(form_evolucion_t),
                            success: function(){
                                $('#modalEvTens').modal('hide');
                                $('body').removeClass('modal-open');
                                $('.modal-backdrop').remove();
                                $('#txtEvTens').val('');
                                $.ajax({
                                    type: 'get',
                                    url: base_url + 'ev-tens/list/' + $('#txtHiddenFicha').val(),
                                    success: function(data){
                                        $('#tabla-evTens tr').remove();
                                        $.each(data, function(i, item){
                                            $('<tr>').html(
                                                "<td>"+data[i].id_ficha+"</td>" +
                                                "<td>"+data[i].fecha+"</td>" +
                                                "<td>"+data[i].descripcion+"</td>" +
                                                "</tr>").appendTo('#tabla-evTens');
                                        });
                                    }
                                });
                            },
                            error: function(){
                                console.log('error al guardar evolucion');
                            }
                        });
                    }    
                });
});