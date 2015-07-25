$(function() {

    var scheduleIdDelete = '';
    var $this = '';
    $('.deleteSchedule').on('click', function() {

        $this = $(this);
        scheduleIdDelete = $this.data('id');
    });

    $('.confirmDelete').on('click', function() {
        $.ajax({
            url: '/web/schedules/' + scheduleIdDelete,
            type: 'DELETE',
            success: function(data){

                $this.closest('tr').remove();
                $('#myModal').modal('hide');
            }
        })
    });
});
