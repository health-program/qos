var sameallareaRightdivRight = document.getElementById('sameallareaRightdivRight');
var sameallareaRightdivRightUL = document.getElementById('sameallareaRightdivRightUL');
var timer1 = null;
var speed1 = -1;
sameallareaRightdivRightUL.innerHTML += sameallareaRightdivRightUL.innerHTML;
setTimeout(move1,3000);

sameallareaRightdivRightUL.onmouseover = function(){
    clearInterval(timer1);
};
sameallareaRightdivRightUL.onmouseout = function(){
    move1();
};
function move1(){
    timer1 = setInterval(function(){
        sameallareaRightdivRightUL.style.top = sameallareaRightdivRightUL.offsetTop + speed1 + 'px';
        console.log(sameallareaRightdivRightUL.style.top)
        console.log(sameallareaRightdivRightUL.offsetTop + speed1)
        if(sameallareaRightdivRightUL.offsetTop <= - sameallareaRightdivRightUL.offsetHeight / 2){
            sameallareaRightdivRightUL.style.top = '0';
        }else if(sameallareaRightdivRightUL.offsetTop >= 0){
            sameallareaRightdivRightUL.style.top = - sameallareaRightdivRightUL.offsetHeight / 2 + 'px';
        };
    },30);
};