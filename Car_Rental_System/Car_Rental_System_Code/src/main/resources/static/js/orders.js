// // // let but = document.querySelector('.but');
// // let but = document.querySelector('.but');
// // let shade = document.querySelector('.shade');
// // let loginBox = document.querySelector('.login-box');
// // let title = document.querySelector('.title');
// // let exit = document.querySelector('.exit');
// //
// // // 立即登录按钮点击事件
// // but.addEventListener('click', function () {
// //     // shade.style.display = "block";
// //     loginBox.style.display = "block";
// //     console.log("第二个按钮被点击了" );
// //     console.error("yeah");
// //     console.warn("ok");
// // });
// //
// // // 关闭模态框事件
// // // exit.addEventListener('click', function () {
// // //     // // shade.style.display = "none";
// // //     // loginBox.style.display = "none";
// // // });
// //
// // exit.addEventListener('click', function () {
// //     loginBox.style.display = "none";
// //     loginBox.remove(); // 从DOM中删除模态框元素
// // });
// //
// // // 拖动标题区域可移动模态框
// // title.addEventListener('mousedown', function (event) {
// //     // 计算鼠标在登录框中坐标
// //     let x = event.pageX - loginBox.offsetLeft;
// //     let y = event.pageY - loginBox.offsetTop;
// //     // 给页面添加鼠标移动事件
// //     document.addEventListener('mousemove', loginBoxMove);
// //     function loginBoxMove(event) {
// //         loginBox.style.left = (event.pageX - x) + "px";
// //         loginBox.style.top = (event.pageY - y) + "px";
// //     }
// //     // 鼠标松开后移除页面的鼠标移动事件
// //     document.addEventListener('mouseup', function () {
// //         document.removeEventListener('mousemove', loginBoxMove);
// //     })
// // });
// //
// // buts.forEach(function(but) {
// //     but.addEventListener('click', function() {
// //         // 获取当前点击的按钮对应的模态框
// //         let loginBox = this.parentElement.querySelector('.login-box');
// //
// //         loginBox.style.display = "block";
// //         console.log("按钮被点击了");
// //         console.error("yeah");
// //         console.warn("ok");
// //     });
// // });
//
// let buts = document.querySelectorAll('.but');
// let shade = document.querySelector('.shade');
// let title = document.querySelector('.title');
// let exit = document.querySelector('.exit');
//
// buts.forEach(function(but) {
//     but.addEventListener('click', function() {
//         // 获取当前点击的按钮对应的模态框
//         let loginBox = this.parentElement.querySelector('.login-box');
//
//         loginBox.style.display = "block";
//         console.log("按钮被点击了");
//         console.error("yeah");
//         console.warn("ok");
//     });
// });
//
// exit.addEventListener('click', function() {
//     loginBox.style.display = "none";
//     loginBox.remove(); // 从DOM中删除模态框元素
// });
//
// title.addEventListener('mousedown', function(event) {
//     // 计算鼠标在登录框中坐标
//     let x = event.pageX - loginBox.offsetLeft;
//     let y = event.pageY - loginBox.offsetTop;
//     // 给页面添加鼠标移动事件
//     document.addEventListener('mousemove', loginBoxMove);
//     function loginBoxMove(event) {
//         loginBox.style.left = (event.pageX - x) + "px";
//         loginBox.style.top = (event.pageY - y) + "px";
//     }
//     // 鼠标松开后移除页面的鼠标移动事件
//     document.addEventListener('mouseup', function() {
//         document.removeEventListener('mousemove', loginBoxMove);
//     })
// });
let buts = document.querySelectorAll('.but');
let shade = document.querySelector('.shade');
let title = document.querySelector('.title');
let exits = document.querySelectorAll('.exit');

buts.forEach(function(but) {
    but.addEventListener('click', function() {
        let loginBox = this.parentElement.querySelector('.login-box');
        loginBox.style.display = "block";
        console.log("按钮被点击了");
        console.error("yeah");
        console.warn("ok");
    });
});

exits.forEach(function(exit) {
    exit.addEventListener('click', function() {
        let loginBox = this.parentElement.parentElement; // 获取当前点击的 × 按钮对应的模态框
        loginBox.style.display = "none";
    });
});

title.addEventListener('mousedown', function(event) {
    let loginBox = this.parentElement;
    let x = event.pageX - loginBox.offsetLeft;
    let y = event.pageY - loginBox.offsetTop;
    document.addEventListener('mousemove', loginBoxMove);
    function loginBoxMove(event) {
        loginBox.style.left = (event.pageX - x) + "px";
        loginBox.style.top = (event.pageY - y) + "px";
    }
    document.addEventListener('mouseup', function() {
        document.removeEventListener('mousemove', loginBoxMove);
    })
});

// 获取所有垃圾桶图标元素
const trashIcons = document.querySelectorAll('.trah_location');

trashIcons.forEach(function(trashIcon) {
    trashIcon.addEventListener('click', function(event) {
        event.preventDefault();
        const entry = this.closest('.entry'); // 获取当前点击的垃圾桶图标对应的 entry
        const orderId = entry.getAttribute('data-order-id'); // 获取订单的ID，需要在HTML中添加 data-order-id 属性

        // 向服务器发送删除请求
        fetch(`http://120.27.132.100:3306/api/order/${order.id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    // 如果服务器成功删除数据，从DOM中删除相应的 entry
                    entry.remove();
                } else {
                    console.error('删除失败');
                }
            })
            .catch(error => {
                console.error('请求失败:', error);
            });
    });
});