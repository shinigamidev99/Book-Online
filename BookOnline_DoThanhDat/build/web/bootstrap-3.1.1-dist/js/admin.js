var availId = document.getElementById("availId");
var newId = document.getElementById('newId');
var bookIdAvail = document.getElementById('bookIdAvail');
var bookIdNew = document.getElementById('bookIdNew');
availId.onclick = function () {
    bookIdAvail.disabled = false;
    bookIdNew.disabled = true;
};

newId.onclick = function () {
    bookIdAvail.disabled = true;
    bookIdNew.disabled = false;
};
