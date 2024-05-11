// DOMContentLoaded表示HTML文档已被完全加载和解析
// addEventListener 是 document 对象的一个方法,用于向指定元素添加事件监听器
// 事件监听器是当特定事件（如点击、加载完成等）发生时被调用的函数
document.addEventListener('DOMContentLoaded', function () { //当文档加载并解析完成后，执行这里定义的函数
    //  URLSearchParams对象,表示URL查询字符串的键值对集合
    // 检查URL中是否有load参数,window.location.search获取当前URL中?后面的查询字符串部分
    var urlParams = new URLSearchParams(window.location.search);
    // 使用URLSearchParams对象的get方法来获取名为load的查询参数的值
    var loadPage = urlParams.get('load');//URL中包含?load=listStudent.jsp,loadPage变量将会被赋值为"listStudent.jsp"
    if (loadPage) {
        // 如果有 load 参数，则加载指定的页面
        loadContent(loadPage);
    }
    // 使用querySelectorAll选择所有在.nav-container类下的<a>标签，并将它们存储在变量links中
    var links = document.querySelectorAll('.nav-container a');
    // 遍历links数组中的每个元素
    for (var i = 0; i < links.length; i++) {
        // 为每个<a>标签添加点击事件监听器,事件被触发时,调用指定的回调函数
        // 回调函数会被传入一个参数event对象,含了与触发的事件相关的所有信息
        links[i].addEventListener('click', function (event) {
            // 当链接被点击时，阻止其默认的跳转行为
            event.preventDefault();
            // 获取被点击链接的href属性值，存储在变量url中
            var url = this.getAttribute('href');
            // 控制台输出调试信息，显示成功获取的url
            console.log('url获取成功:: ' + url);
            // 调用loadContent函数，并将获取到的url作为参数传递
            loadContent(url);
        });
    }
});

// 定义loadContent函数，用于加载指定URL的内容
function loadContent(url) {
    // 创建一个新的XMLHttpRequest对象,用于发送HTTP请求
    // 使网页能够动态地更新部分内容
    var xhr = new XMLHttpRequest();
    // 当XMLHttpRequest对象的readyState属性发生变化时,onreadystatechange事件被触发
    // onreadystatechange通常用于监听XMLHttpRequest的状态变化
    xhr.onreadystatechange = function () {
        // 如果请求完成（readyState的值为4）
        if (xhr.readyState === 4) {
            // 如果请求成功(HTTP状态码为200),xhr.status是XMLHttpRequest对象的一个属性,表示HTTP响应的状态码
            if (xhr.status === 200) {
                // 在控制台输出调试信息，显示加载成功
                console.log('加载成功'); // 调试信息
                // 将获取到的响应文本（即页面内容）设置为.main-content元素的innerHTML，innerHTML用于获取或设置HTML元素的内容
                // responseText是XMLHttpRequest对象的一个属性,包含了服务器响应的文本内容
                // 请求成功完成并且状态码为200时，可以使用responseText来访问服务器返回的数据
                document.querySelector('.main-content').innerHTML = xhr.responseText;
            } else {
                // 如果请求失败（HTTP状态码不是200）,显示错误信息
                // 在控制台输出错误信息，包括HTTP状态码
                console.error('加载失败: ' + xhr.status); // 调试信息
                // 将.main-content元素的innerHTML设置为一个错误提示信息
                document.querySelector('.main-content').innerHTML = '<p>加载内容出错，请重试。</p>';
            }
        }
    };
    // 初始化请求，指定HTTP方法为GET，请求URL为传入的url，并设置异步请求为true
    xhr.open('GET', url, true);
    // 发送请求
    xhr.send();
}