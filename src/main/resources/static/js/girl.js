$(document).ready(function () {
    $('#query').click(function () {
        query();
    });
    $('p').click(function () {
        $(this).html("");
    });
    $('#add').click(function () {
        add();
    });
    $('#find').click(function () {
        select();
    });
    $('#update').click(function () {
        update();
    });
    $('#update_id').focusout(function () {
        select_update();
    });
    $('#update_id').focusout();
    $('#delete').click(function () {
        del();
    });
    $('#delete_id').change(function () {
        $.ajax({
            type: 'get',
            url: '/girls/' + $(this).val(),
            success: function (data) {
                if (data === null) {
                    $('#delete').attr('disabled', true);
                } else {
                    $('#delete').attr('disabled', false);
                }
            },
            error: function (e) {
                console.log(e.responseText);
            }
        })

    });
    $('#age_query').click(function () {
        query_age();
    });
});

function query() {
    $.ajax({
        type: 'get',
        url: '/girls',
        success: function (data) {
            var all = $('#all');
            all.empty();
            all.append('<table class="table table-striped table-hover table-bordered" id="all_table"></table>');
            var table = $('#all_table');
            table.append('<thead><tr><th>ID</th><th>Age</th><th>cupSize</th></tr></thead><tbody>');
            for (var i = 0; i < data.length; i++) {
                table.append('<tr><td>' + data[i].id + '</td><td>' + data[i].age + '</td><td>' + data[i].cupSize +
                    '</td></tr>');
            }
            table.append('</tbody>');
        },
        error: function (e) {
            alert(e);
        }
    })
}

function add() {
    $.ajax({
        type: 'post',
        url: '/girls',
        data: {age: $('#age').val(), cupSize: $('#cup').val()},
        success: function (data) {
            $('#the').html(JSON.stringify(data));
        },
        error: function (e) {
            $('#the').html(JSON.stringify(e.responseText));
            console.log(e.responseText);
        }
    })

}

function select() {
    if ($('#id').val().trim() === '') {
        $('#select').html('<p class="text-warning">请输入Girl ID</p>');
        return;
    }
    $.ajax({
        type: 'get',
        url: '/girls/' + $('#id').val(),
        success: function (data) {
            $("#select").html('<p class="text-success">' + JSON.stringify(data) + '</p>');
        },
        error: function (e) {
            console.log(e);
        }
    })

}

function select_update() {
    $.ajax({
        type: 'get',
        url: '/girls/' + $('#update_id').val(),
        success: function (data) {
            if (data === null) {
                $('#update_none').html('没有该ID的女生');
                $('#update_cup').val('');
                $('#update_age').val('');
                $('#update').attr('disabled', true);
            } else {
                $('#update_none').html('');
                $('#update_cup').val(data.cupSize);
                $('#update_age').val(data.age);
                $('#update').attr('disabled', false);
            }
        },
        error: function (e) {
            console.log(e);
        }
    })

}

function update() {
    $.ajax({
        type: 'put',
        url: '/girls/',
        data: {cupSize: $('#update_cup').val(), age: $('#update_age').val(), id: $('#update_id').val()},
        success: function (data) {
            $('#update_p').html(JSON.stringify(data));
        },
        error: function (e) {
            console.log(e);
            alert(e.responseText);
        }
    })

}

function del() {
    $.ajax({
        type: 'delete',
        url: '/girls/' + $('#delete_id').val(),
        success: function (data) {
            $('#delete_p').html('删除成功');
            $('#delete').attr('disabled', true);
        },
        error: function (e) {
            console.log(e.responseText);
        }
    })
}

function query_age() {
    $.ajax({
        type: 'get',
        url: '/girls/age/' + $('#age_age').val(),
        success: function (data) {
            $('#age_p').html(JSON.stringify(data));
        },
        error: function (e) {
            console.log(e.responseText);
        }

    })

}