$(function() {

    var scheduleId = '';
    var $this = '';
    $('.deleteSchedule').on('click', function() {

        $this = $(this);
        scheduleId = $this.data('id');
    });

    $('.confirmDelete').on('click', function() {
        $.ajax({
            url: '/web/schedules/' + scheduleId,
            type: 'DELETE',
            success: function(data){

                $this.closest('tr').remove();
                $('#myModal').modal('hide');
            }
        })
    });

    $('.updateUser').on('click', function() {
        $this = $(this);
    })
});
