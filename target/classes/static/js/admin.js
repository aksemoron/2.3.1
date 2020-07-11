$(document).ready(function () {
    getEmailOfUser();
    getRoles();
    getTableOfUsers();
    getInfoOfUser();
});

function getEmailOfUser() {
    $.ajax({
        url: '/user/rest/emailOfUser',
        success: function (data) {
            $("#email").text(data);
        },

        error: function (error) {
            alert("getEmail Error");
        }
    });
}

function getRoles() {
    $.ajax({
        url: '/user/rest/RolesOfUser',
        success: function (data) {
            $("#roles").text(data);
        },

        error: function (error) {
            alert("getRoles Error");
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
                userData += `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.family}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
                <td> <button type="button" id="updateButton" class="btn btn-info"
                data-toggle="modal" data-target="#updateModal" data-id="${user.id}">Edit</button> </td>

                <td> <button type="button" id="deleteButton" class="btn btn-danger" 
                    data-toggle="modal" data-target="#deleteModal" data-id="${user.id}">Delete</button> </td>`;
            });

            $('#userTable').html(userData);
        },

        error: function (error) {
            alert("getAllUsers Error");
        }
    })
}

$(document).on("click", "#addNewUser", function () {
    const select = $('#roleNewUser option:selected');
    const val = select.val();
    let nameOFUser = "USER";
    if (val == 1) {
        nameOFUser = "ADMIN"
    }
    let addNewUser = {
        name: $('#name').val(),
        family: $('#family').val(),
        email: $('#emailNewUser').val(),
        password: $('#password').val(),
        age: $('#age').val(),
        roles: [
            {
                "id": val,
                "name": nameOFUser
            }
        ]
    }
    $.ajax({
        url: '/admin/rest/addNewUser',
        type: 'POST',
        data: JSON.stringify(addNewUser),
        dataType: 'json',
        contentType: "application/json",
        success: function (responseData, status, jqXHR) {
            getTableOfUsers()
        },
        error: function () {
            alert('AddUser Error');
        }
    })
})

$(document).on("click", "#edit", function () {
    const select = $('#roleUpdate option:selected');
    const val = select.val();
    let nameOFUser = "USER";
    if (val == 1) {
        nameOFUser = "ADMIN"
    }
    let updateUser = {
        id: $('#idUpdate').val(),
        name: $('#nameUpdate').val(),
        family: $('#familyUpdate').val(),
        email: $('#emailUpdate').val(),
        password: $('#passwordUpdate').val(),
        age: $('#ageUpdate').val(),
        roles: [
            {
                "id": val,
                "name": nameOFUser
            }
        ]
    }
    $.ajax({
        url: '/admin/rest/updateUser',
        type: 'POST',
        data: JSON.stringify(updateUser),
        dataType: 'json',
        contentType: "application/json",
        success: function (response) {
            $('.close').click();
            getTableOfUsers()
        },

        error: function (responseData, status, jqXHR) {
            alert("ошибка")
        }
    })
})

$(document).on("click", "#updateButton", function () {
    const id = $(this).data('id');

    $.ajax({
        url: '/admin/rest/getUser/' + id,
        type: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#idUpdate').val(user.id);
            $('#nameUpdate').val(user.name);
            $('#familyUpdate').val(user.family);
            $('#ageUpdate').val(user.age);
            $('#emailUpdate').val(user.email);
            $('#passwordUpdate').val(user.password);
        },

        error: function () {
            alert("error");
        }
    })
})

$(document).on("click", "#deleteButton", function () {   //вот тут заполнение динамическое если делать без js вообще не представляю как это возможно
    const id = $(this).data('id');

    $.ajax({
        url: '/admin/rest/getUser/' + id,
        type: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#idDelete').val(user.id);
            $('#nameDelete').val(user.name);
            $('#familyDelete').val(user.family);
            $('#ageDelete').val(user.age);
            $('#emailDelete').val(user.email);
            $('#passwordDelete').val(user.password);
        },

        error: function () {
            alert("error");
        }
    })
})


$(document).on("click", "#delete", function () {
    $.ajax({
        url: '/admin/rest/deleteUser/' + $('#idDelete').val(),
        type: 'GET',
        dataType: 'json',
        contentType: "application/json",
        success: function (responseData, status, jqXHR) {
            $('.close').click();
            getTableOfUsers()
        },
        error: function () {
            $('.close').click();
            getTableOfUsers()
        }
    })
})


function getInfoOfUser() {
    $.ajax({
        url: '/user/rest/getUser',
        success: function (user) {
            let userData = '';
            let userRoles = user.roles;
            let roles = '';

            for (let role of userRoles) {
                roles += " " + role.name;
            }
            userData += `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.family}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>`
            $('#userInfoTable').html(userData);
        },

        error: function (error) {
            alert("getUser Error");
        }
    })
}