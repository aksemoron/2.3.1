$(document).ready(function () {
    user();
});

function user() {
    console.log("выыыыыыыыыыыыыыыы")
    $.ajax({
        url: '/admin/rest/infoOfUser',
        method: 'GET',
        success: function (data) {
            let userData = data;
            console.log("----------------------------------------------------");
            alert(data);
        },

        error: function (error) {
            alert("gettingListOfUsers Error");
        }
    })
}

