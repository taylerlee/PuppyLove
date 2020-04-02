/**
 * Created by danielhsieh on 2014/6/12.
 */

/* 欄位加入成功回饋 */
function addSuccessFeedback(input) {
    input.parents('.form-group').addClass('has-success has-feedback');
    input.parent('div').append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
}

/* 欄位加入失敗回饋 */
function addErrorFeedback(input) {
    input.parents('.form-group').addClass('has-error has-feedback');
    input.parent('div').append('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
}

/* 欄位清除回饋 */
function clearnFeedback(input) {
    var columnDiv = input.parents('.form-group');
    var inputDiv = input.parent('div');

    if (columnDiv.hasClass('has-feedback')) {
        columnDiv.removeClass('has-feedback');
        if (columnDiv.hasClass('has-success')) {
            columnDiv.removeClass('has-success');
        } else if (columnDiv.hasClass('has-error')) {
            columnDiv.removeClass('has-error');
        }
    }

    if (inputDiv.children('span').length > 0) {
        inputDiv.children('span').remove('span');
    }
}

/* 驗證Email格式 */
function isEmail(email) {
    var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!regex.test(email)) {
        return false;
    } else {
        return true;
    }
}

/* 插入表單最上方的提示訊息 */
function insertAlert(alertType, message, jqueryElement, location) {
    var alertElement = '';
    if (alertType == 'success') {
        alertElement = '<div class="alert alert-success alert-dismissable">' +
            '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
            '<span class="glyphicon glyphicon-ok-sign"></span> ' + message + '</div>';
    } else if (alertType == 'danger') {
        alertElement = '<div class="alert alert-danger alert-dismissable">' +
            '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
            '<span class="glyphicon glyphicon-warning-sign"></span> ' + message + ' </div>';
    } else if (alertType == 'info') {
        alertElement = '<div class="alert alert-info alert-dismissable">' +
            '<span class="glyphicon glyphicon-info-sign"></span> ' + message +' </div>';
    } else if (alertType == 'warning') {
        alertElement = '<div class="alert alert-danger alert-dismissable">' +
            '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
            '<span class="glyphicon glyphicon-warning-sign"></span> ' + message + ' </div>';
    }
    if (location == 'append') {
        jqueryElement.append(alertElement);
    } else if (location == 'before') {
        jqueryElement.before(alertElement);
    }
}

function getTabSelectValue(name){
    var result = '';
    var selectValues = $('#' + name + 'Tabs').children('.active');
    var optionSize = $('#' + name + 'Tabs').children('span').size();
    if (selectValues.size() > 0 && selectValues.size() < optionSize) {
        selectValues.each(function (i) {
            result = result + $(this).text();
            if (i < selectValues.size() - 1) {
                result = result + ";";
            }
        });
    } else {
        result = 'all';
    }
    return result;
}

function showInfo($this) {
    var animalInfo = JSON.parse($this.prev('input').val());
    $('#pet-image1').attr('src', animalInfo.imageName1);
    $('#pet-image2').attr('src', animalInfo.imageName2);
    $('#pet-image3').attr('src', animalInfo.imageName3);
    $('#pet-name').text(animalInfo.name == '' ? '無' : animalInfo.name);
    $('#description').text(animalInfo.note == '' ? '無' : animalInfo.note);
    $('#lost-location').text(animalInfo.resettlement == '' ? '無' : animalInfo.resettlement);
    $('#pet-type').text(animalInfo.type == '' ? '無' : animalInfo.type);
    $('#pet-variety').text(animalInfo.variety == '' ? '無' : animalInfo.variety);
    $('#pet-gender').text(animalInfo.sex == '' ? '無' : animalInfo.sex);
    $('#pet-age').text(animalInfo.age == '' ? '無' : animalInfo.age);
    $('#pet-build').text(animalInfo.build == '' ? '無' : animalInfo.build);
    $('#chip-num').text(animalInfo.chipNum == '' ? '無' : animalInfo.chipNum);
    $('#hair-type').text(animalInfo.hairType == '' ? '無' : animalInfo.hairType);
    $('#sterilization').text(animalInfo.isSterilization == '' ? '無' : animalInfo.isSterilization);
    $('#childre-Anlong').text(animalInfo.childreAnlong == '' ? '無' : animalInfo.childreAnlong);
    $('#animal-Anlong').text(animalInfo.animalAnlong == '' ? '無' : animalInfo.animalAnlong);
    $('#contact').text(animalInfo.contact == '' ? '無' : animalInfo.contact);
    $('#phone').text(animalInfo.phone == '' ? '無' : animalInfo.phone).attr('href', 'tel:' + animalInfo.phone);
    $('#email').text(animalInfo.email == '' ? '無' : animalInfo.email).attr('href', 'mailto:' + animalInfo.email);
    $('#share').attr('href', 'javascript: void(window.open(' +
        '"http://www.facebook.com/share.php?u=http://2595.tw/web/animal/info.jsp?' +
        'animalId=' + animalInfo.id + '"))');
}

function loadPetInfo(mainType){
    listPetInfo = true;
    var petTypeValue = $('.btn-group-justified .active').text();
    var pageValue = parseInt($('#navigation').val());
    $('.full-overlay').show();
    $.post('./list.jsp', {mainType:mainType, petType:petTypeValue, page:pageValue})
        .done(function (data) {

//            if (pageValue == 1 || data.indexOf("<div>") > -1) {
            if (pageValue == 1){
                if (data.indexOf("div") > -1) {
                    var $moreBlocks = $(data).filter('div');
                    $('#petcontainer').append($moreBlocks).imagesLoaded( function() {
                        $('#petcontainer').masonry( 'appended', $moreBlocks, true );
                    });
                    $('#navigation').val(pageValue + 1);
                    if ($moreBlocks.size() == 10) {
                        listPetInfo = false;
                    }
                } else {
                    insertAlert('info', '目前無相關資料', $('#petcontainer'), 'before');
                }
            } else {
                if (data.indexOf("div") > -1) {
                    var $moreBlocks = $(data).filter('div');
                    $('#petcontainer').append($moreBlocks).imagesLoaded( function() {
                        $('#petcontainer').masonry( 'appended', $moreBlocks, true );
                    });
                    $('#navigation').val(pageValue + 1);
                    listPetInfo = false;
                }
            }
        })
        .fail(function () {
            insertAlert('danger', '查詢錯誤', $('#petcontainer'), 'before');
        })
        .always(function(){
            $('.full-overlay').hide();
        });

}
function loadSearchInfo(){
    listPetInfo = true;
    $('.full-overlay').show();
    $.post('/animal/search_result.jsp', $('.form-horizontal').serializeArray())
        .done(function (data) {
//                            $('input').val('');
            location.href = '#result';
            if (pageValue < 2 || data.indexOf("<script>") == -1) {
                var $moreBlocks = $(data).filter('div');
                $('#petcontainer').append($moreBlocks).masonry( 'appended', $moreBlocks, true );
                $('#page').val(parseInt($('#page').val()) + 1);
                listPetInfo = false;
            }
        })
        .fail(function () {
            insertAlert('danger', '資料輸入不完全，請檢查欄位', $('#petcontainer'), 'append');
        })
        .always(function(){
            $('.full-overlay').hide();
        });

}

function changePetType($this){
    var $petcontainer = $('#petcontainer');
    $this.siblings(".active").removeClass("active");
    $this.addClass("active");
    $petcontainer.empty();
    $('#navigation').val(1);
    $petcontainer.masonry();
    $petcontainer.prevAll('.alert').each(function () {
        $(this).remove();
    });
}

