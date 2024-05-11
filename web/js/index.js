var index = 0;

function ChangeImg() {
    index++;
    var a = document.getElementsByClassName("img-slide");
    if (index >= a.length)
        index = 0;
    for (var i = 0; i < a.length; i++) {
        a[i].style.display = 'none';
    }
    a[index].style.display = 'block';
}

setInterval(ChangeImg, 1000);


function toDate(n) {
    if (n < 10) {
        return '0' + n;
    } else {
        return '' + n;
    }
}

window.onload = function () {
    function trick() {
        var oDate = new Date();
        var H = toDate(oDate.getHours());
        var M = toDate(oDate.getMinutes());
        var S = toDate(oDate.getSeconds());
        var t = H + ":" + M + ":" + S;

        // 确保元素存在
        var timeElement = document.getElementById('p1');
        if (timeElement) {
            timeElement.innerHTML = t;
        } else {
            console.error('Element with id "p1" not found.');
        }
    }

    setInterval(trick, 1000);
};