


function getData(){


     var arr={
         //eventIds:'V40001,V40002,V40003,V40004,V40005,V40006,V40007,V40008,V40009,V400010,V400011,V40012,V40013'
         eventIds:'V40001,V40002'
     }

      var eventIdsArray = arr['eventIds'].split(',');



        $.ajax({
            type : "post",    //请求类型
            url : "http://10.9.1.41:18081/home/page/qos/getTop5Disease",//请求的 URL地址
            data:arr,
            success: function (res) {

                var resData = res.result;


                var html=''
                $("#sameallareaRightdivRightUL").html('')



                var arrayV40001=eval(resData['V40001'])
                for(var i=0;i<arrayV40001.length;i++){
                    html+='<li class="sameallareaRightdivRightLi text-ff0000 fz14">'+arrayV40001[i].diseasecodeName+'</li>'
                    $("#hospitalName").text(arrayV40001[i].unitName)
                }


                var arrayV40002=eval(resData['V40002'])
                for(var i=0;i<arrayV40002.length;i++){
                    html+='<li class="sameallareaRightdivRightLi text-ff0000 fz14">'+arrayV40002[i].diseasecodeName+'</li>'
                    $("#hospitalName").text(arrayV40002[i].unitName)
                }










               $("#sameallareaRightdivRightUL").append(html);

            }
        })

}




window.setInterval(function() {
    var arrayV40002=eval(resData['V40002'])
    for(var i=0;i<arrayV40002.length;i++){
        html+='<li class="sameallareaRightdivRightLi text-ff0000 fz14">'+arrayV40002[i].diseasecodeName+'</li>'
        $("#hospitalName").text(arrayV40002[i].unitName)
    }
},5000);




  getData()
  // getFirst() //  获取第一人民医院数据并且写死

/**
 *@desc
 *@author DannWU
 *@date 2019/10/30 10:55:15    获取第一人民医院数据并且写死
 */
function getFirst(){
    var arrsOne={
        eventIds:'V40001'
    }
    $.ajax({
        type : "post",    //请求类型
        url : "http://10.9.1.41:18081/home/page/qos/getTop5Disease",//请求的 URL地址
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


