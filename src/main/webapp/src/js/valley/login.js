//根据上午、中午、下午、晚上设置背景图片
/*
var bodyBg = $(".bodyBg");
var nowDate = new Date();
var hour = nowDate.getHours();
if(hour >=6 && hour <11){
    bodyBg.css("background", "url(src/assets/img/strawberry.jpg) no-repeat 100% 100%");
}
else if(hour >=11 && hour <14){
    bodyBg.css("background", "url(src/assets/img/berrygrow.jpg) no-repeat");
}
else if(hour >=14 && hour <=18){
    bodyBg.css("background", "url(src/assets/img/strawberry.jpg) no-repeat 100% 100%");
} else {
    bodyBg.css("background", "url(src/assets/img/berrygrow.jpg) no-repeat 100% 100%");
}*/

//页面加载完成后，生成验证码
$(function () {
    $("#yzmImg").attr("src", '/eastValley/validateCodeServlet?'+ new Date().getTime());
});
