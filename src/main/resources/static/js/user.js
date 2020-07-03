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
            userData += '<tr>';
            userData += '<td>' + user.id + '</td>';
            userData += '<td>' + user.name + '</td>';
            userData += '<td>' + user.family + '</td>';
            userData += '<td>' + user.age + '</td>';
            userData += '<td>' + user.email + '</td>';
            userData += '<td>' + roles + '</td>';
            $('#userInfoTable').html(userData);
        },

        error: function (error) {
            alert("getUser Error");
        }
    })
}
