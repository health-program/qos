window.onload = function(){
    var oDiv = document.getElementById('familyTable');
    var oUl = document.getElementById('familyTableUl');
    var timer = null;
    var speed = -1;
    oUl.innerHTML += oUl.innerHTML;
    setTimeout(move,3000);

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
