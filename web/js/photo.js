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


function fun() {
    window.alert("最早出现的启明星，在这深蓝色的天幕上闪烁起来了。它是那么大，那么亮，整个广漠的天幕上只有它一个在那里放射着令人注目的光辉，像一盏悬挂在高空的明灯。");
}

function fun1() {
    window.alert("这是多么凉爽清明的秋夜!星星比任何时候都要亮，都要大就像银灰色的天幕下缀满一颗颗夺目的宝石，撒下晶莹柔和的光辉，大地上的一切都变得那么雅致，那么幽静。");
}

function fun2() {
    window.alert("月亮是那么明亮，把大地照得一片雪青，树木、房屋、街道都像镀上了一层水银似的。");
}

function fun3() {
    window.alert("月亮出海了。在腾空的一瞬间，它仿佛猛地一跳，浑身披满水花，让多情的大海把它冲洗得分外明丽和洁净。");
}

function fun4() {
    window.alert("天空满天星斗，像一粒粒珍珠，似一把把碎金，撒落在碧玉盘上。此刻是那么的宁静、安详，树叶在沙沙作响，星星在不停地眨着眼睛。");
}

function fun5() {
    window.alert("一轮圆月正冉冉升起，那银色的月光映着几丝儿羽毛般的轻云，美妙极了。");
}

function fun6() {
    window.alert("一缕清柔的月光透过窗子，洒在了窗台上，窗台宛若镀了银。");
}

function fun7() {
    window.alert("淘气的小星星在蓝幽幽的夜空划出一道金色的弧光，像织女抛出一道锦线。");
}

function fun8() {
    window.alert("点点的繁星好似颗颗明珠，镶嵌在天幕下，闪闪地发着光。");
}