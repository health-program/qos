var hospitalDataMap={},currentShowIndex= 0, eventIds =['V40001','V40002','V40003','V40004','V40005','V40006','V40007','V40008','V40009','V40010','V40011','V40012','V40013'];
function getData(){
       $.ajax({
            type : "post",    //请求类型
            url : URLPATH+"/home/page/qos/getTop10Disease",//请求的 URL地址
            data:{
                eventIds:'V40001,V40002,V40003,V40004,V40005,V40006,V40007,V40008,V40009,V40010,V40011,V40012,V40013'
            },
            success: function (res) {
                 var  data = res.result;

                 for(var o in data) {
                     hospitalDataMap[o] = eval(data[o]);
                 }
                setInterval(function(){
                    showData()
                },3000)
             }
        })
}

function showData(){
    if(currentShowIndex == eventIds.length) {
        currentShowIndex = 0;
    }
   var id= eventIds[currentShowIndex++];
    var data =  hospitalDataMap[id];

    $("#hospitalName").text(data[0].unitName);

    var html = "";
    for(var i=0;i<data.length;i++){
        html+='<li class="sameallareaRightdivRightLi text-ff0000 fz14">'+isNull(data[i].diseasecodeName)+'</li>';
    }
    $("#sameallareaRightdivRightUL").html(html);
}
var arrs={
    eventIds:'V40001,V40002,V40003,V40004,V40005,V40006,V40007,V40008,V40009,V40010,V40011,V40012,V40013'
}

      $.ajax({
          type: "post",    //请求类型
          url: URLPATH+"/home/page/qos/getTop10Disease",//请求的 URL地址
          data: arrs,
          success: function (res) {
              var resData = res.result;
              var html = ''
              $("#sameallareaRightdivRightUL").html('')
              for (var i = 0; i < resData.length; i++) {
                  html += '<li class="sameallareaRightdivLeftLI text-ff0000 fz14"><span>' + (i + 1) + '</span><span>、</span>' + resData[i].diseasecodeName + '</li>'
              }
          }
      })


  getFirst() //  获取第一人民医院数据并且写死

/**
 *@desc
 *@author DannWU
 *@date 2019/10/30 10:55:15    获取第一人民医院数据并且写死
 */
function getFirst(){
    var arrsOne={
        eventIds:'V40001,V40002,V40003,V40004,V40005,V40006,V40007,V40008,V40009,V40010,V40011,V40012,V40013,'
    }
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/getTop10Disease",//请求的 URL地址
        data:arrsOne,
        success: function (res) {
            var resData =res.result;
            var html=''
            $("#sameallarealeftdivRight").html('')
            for(var i=0;i<resData['V40001'].length;i++){
                html+='<li class="sameallareaRightdivLeftLI text-ff0000 fz14"><span>'+(i+1)+'</span><span>、</span>'+resData['V40001'][i].diseasecodeName+'</li>'
            }
            $("#sameallarealeftdivRight").append(html);
        }
    })
}

function isNull(a) {
    if(a == "" || a == null || a == undefined){
        return '未知'
    }
    return a;
}

