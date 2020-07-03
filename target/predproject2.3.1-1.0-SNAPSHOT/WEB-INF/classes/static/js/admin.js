$(document).ready(function () {
    getEmailOfUser();
    getRoles();
    getTableOfUsers();
});

function getEmailOfUser() {
    $.ajax({
        url: '/admin/rest/emailOfUser',
        success: function (data) {
            $("#email").text(data);
        },

        error: function (error) {
            alert("gettingListOfUsers Error");
        }
    });
}

function getRoles() {
    $.ajax({
        url: '/admin/rest/RolesOfUser',
        success: function (data) {
            $("#roles").text(data);
        },

        error: function (error) {
            alert("gettingListOfUsers Error");
        }
    });
}

function getTableOfUsers() {
    $.ajax({
        url: '/admin/rest/tableOfUsers',

        success: function (listOfUsers) {
            let userData = '';
            $.each(listOfUsers, function (i, user) {
                let userRoles = user.roles;
                let roles = '';

                for (let role of userRoles) {
                    roles += " " + role.name;
                }
                userData += '<tr>';
                userData += '<td>' + user.id + '</td>';
                userData += '<td>' + user.name + '</td>';
                userData += '<td>' + user.family + '</td>';
                userData += '<td>' + user.age + '</td>';
                userData += '<td>' + user.email + '</td>';
                userData += '<td>' + roles + '</td>';
                userData += '<td> <button type="button" id="updateButton" class="btn btn-info" ' +
                    'data-toggle="modal" data-target="#updateModal" data-id="' + user.id + '">Edit</button> </td>';

                userData += '<td> <button type="button" id="deletingButton" class="btn btn-danger" ' +
                    'data-toggle="modal" data-target="#deleteModal" data-id="' + user.id + '">Delete</button> </td>';
            });

            $('#userTable').html(userData);
        },

        error: function (error) {
            alert("gettingListOfUsers Error");
        }
    })
}

$(document).on("click", "#addNewUser", function(){
    const select = $('#roleNewUser option:selected');
    const val = select.val();
    let addNewUser = {
        name: $('#name').val(),
        family: $('#family').val(),
        email: $('#emailNewUser').val(),
        password: $('#password').val(),
        age: $('#age').val(),
        balans: val   //тут изменить роли нужно передавать сет ролей?
    }
    $.ajax({
        url: '/admin/rest/addNewUser',
        type: 'POST',
        data: JSON.stringify(addNewUser),
        dataType: 'json',
        contentType: "application/json",
        success: function (responseData, status, jqXHR) {
            window.location.replace("/admin");
        },
        error: function () {
            alert('Adding Error');
        }
    })
})

$(document).on("click", "#edit", function(){
    const select = $('#roleUpdate option:selected');
    const val = select.val();
    let updateUser = {
        id:$('#idUpdate').val(),
        name: $('#nameUpdate').val(),
        family: $('#familyUpdate').val(),
        email: $('#emailUpdate').val(),
        password: $('#passwordUpdate').val(),
        age: $('#ageUpdate').val(),
        balans: val   //тут изменить роли нужно передавать сет ролей?
    }
    $.ajax({
        url: '/admin/rest/updateUser',
        type: 'POST',
        data: JSON.stringify(updateUser),
        dataType: 'json',
        contentType: "application/json",
        success: function (responseData, status, jqXHR) {
            window.location.replace("/admin");
        },
        error: function () {
            alert('Update Error');
        }
    })
})
