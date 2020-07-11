$(document).ready(function () {
    getEmailOfUser();
    getRoles();
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
