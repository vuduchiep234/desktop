var presentName = '';
var activeName = 'name';
var nameButton = 'button-payment';

function functionShow(param) {
    var width = $(window).width();
    var name = nameButton + param;
    presentName = name;
    if (width < 990)
        return;
    else {
        css(name, `show`);
    }
}

function functionHide(param) {
    var width = $(window).width();
    var name = nameButton + param;
    presentName = name;
    if (width < 990)
        return;
    else {

        css(name, `hide`);
    }
}

const css = (nameButton, situation) => {
    switch (situation) {
        case 'show':
            $(`.${nameButton}`).css({ "display": "inline-block" });
            break;
        case 'hide':
            $(`.${nameButton}`).css("display", "none");
            break;
        default:

    }
}

$('.box-payment').click(function () {
    var width = $(window).width();

    if (activeName != presentName)
        $(`.${activeName}`).hide(500);

    if (width < 990) {
        $(`.${presentName}`).show('slow');
        activeName = presentName;
    }
    else
        return;
});


