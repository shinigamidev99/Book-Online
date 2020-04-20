function checkRePassword() {
    var newPassword = document.getElementById('newPassword');
    var rePassword = document.getElementById('rePassword');

    if (newPassword.equals(rePassword)) {
        return true;
    } else {
        return false;
    }
}
