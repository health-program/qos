window.onload = function(){
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/quailtydisplay/getSignList",//请求的 URL地址
        success: function (res) {
            var data = res.result;
            var html = "";
            $.each(data, function(i, item) { //这里的函数参数是键值对的形式，k代表键名，v代表值
                var type=convert(item.signType);
                var date=formatDate(item.signDate);
                html += '<li>';
                html += '<span class="text-c1f3ff ellipsis256 d-inline-block">'+isNull(item.signOrg)+'</span>';
                html += '<span class="text-c1f3ff ellipsis28 d-inline-block">'+isNull(item.signName)+'</span>';
                html += '<span class="text-c1f3ff ellipsis28 d-inline-block">'+isNull(item.signGender)+'</span>';
                html += '<span class="text-c1f3ff ellipsis290 d-inline-block">'+isNull(date)+'</span>';
                html += '<span class="text-c1f3ff ellipsis256 d-inline-block">'+isNull(item.signTeam)+'</span>';
                html += '<span class="text-c1f3ff ellipsis256 d-inline-block" id="isNew">'+isNull(type)+'</span>';
                html += '</li>';
            });
            $("#dataList").append(html);



            function convert(data){
                var message;
                if(data=="1"){
                    message="收费";
                }
                if(data=="" || data==null){
                    message="免费";
                }
                return message;
            }

            function formatDate(date){
                da = new Date(date);
                var year = da.getFullYear();
                var month = da.getMonth()+1;
                var day = da.getDate();
               return  year+"-"+month+"-"+day;
            }


        }
    });



    var oDiv = document.getElementById('scroll');
    var oUl = oDiv.getElementsByTagName('ul')[0];
    var timer = null;
    var speed = -1;
    oUl.innerHTML += oUl.innerHTML;
    setTimeout(move,1500);

    oUl.onmouseover = function(){
        clearInterval(timer);
    };
    oUl.onmouseout = function(){
        move();
    };
    function move(){
        timer = setInterval(function(){
            oUl.style.top = oUl.offsetTop + speed + 'px';
            if(oUl.offsetTop <= - oUl.offsetHeight / 2){
                oUl.style.top = '0';
            }else if(oUl.offsetTop >= 0){
                oUl.style.top = - oUl.offsetHeight / 2 + 'px';
            };
        },30);
    };
};


function isNull(a) {
    if(a == "" || a == null || a == undefined){
        return '未知'
    }
    return a;
}