function requestOauthToken(username, password) {

    var success = false;

    $.ajax({
        url: 'auth/oauth/token',
        datatype: 'json',
        type: 'post',
        headers: {'Authorization': 'Basic dWk6cmFkb24='},
        async: false,
        data: {
            scope: 'ui',
            username: username,
            password: password,
            grant_type: 'password'
        },
        success: function (data) {
            localStorage.setItem('token', data.access_token);
            success = true;
        },
        error: function () {
            removeOauthTokenFromStorage();
        }
    });

    return success;
}

function getOauthTokenFromStorage() {
    return localStorage.getItem('token');
}

function removeOauthTokenFromStorage() {
    return localStorage.removeItem('token');
}

function getCurrentAccount() {

    var token = getOauthTokenFromStorage();
    var account = null;

    if (token) {
        $.ajax({
            url: 'order/current',
            datatype: 'json',
            type: 'get',
            headers: {'Authorization': 'Bearer ' + token},
            async: false,
            success: function (data) {
                account = data;
            },
            error: function () {
                removeOauthTokenFromStorage();
            }
        });
    }

    return account;
}