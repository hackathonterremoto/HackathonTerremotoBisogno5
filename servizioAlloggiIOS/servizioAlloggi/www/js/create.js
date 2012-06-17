/**
 * @author chiara
 */


/**
 * Calendario: disponibilita dal
 * rif. http://demo.mobiscroll.com/
 */
$(function(){
    $('#offerForm_dispDal').scroller({
        preset: 'date',
        invalid: { daysOfWeek: [0, 6], daysOfMonth: ['5/1', '12/24', '12/25'] },
        theme: 'default',
        display: 'modal',
        mode: 'scroller',
        dateOrder: 'mmD ddy'
    });
    

    $('#calendarDal').click(function(){
        $('#offerForm_dispDal').scroller('calendarDal'); 
        return false;
    });
    $('#clear').click(function () {
        $('#offerForm_dispDal').val('');
        return false;
    });
});


/**
 * Calendario: disponibilita al
 * rif. http://demo.mobiscroll.com/
 */
$(function(){
    $('#offerForm_dispAl').scroller({
        preset: 'date',
        invalid: { daysOfWeek: [0, 6], daysOfMonth: ['5/1', '12/24', '12/25'] },
        theme: 'default',
        display: 'modal',
        mode: 'scroller',
        dateOrder: 'mmD ddy'
    });
    

    $('#calendarAl').click(function(){
        $('#offerForm_dispAl').scroller('calendarAl'); 
        return false;
    });
    $('#clear').click(function () {
        $('#offerForm_dispAl').val('');
        return false;
    });
});