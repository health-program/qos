$(function(){
    var arrV3004 = []
    var arrV3004Total = []
    var arrV3005 = []
    var arrV3005Total = []
    var arrArea = []
    var arrV30009 = []
    var arrV30010 = []
    var newObj={}
    var newArray = []
    $.ajax({
        type : "post",
        url : URLPATH+"/home/page/qos/pressureAndSugar",//请求的 URL地址
        success: function (rawData) {
             // unitName
            var rawDataResult= rawData.result;
            console.log(rawDataResult);
            var resV30004 =  rawDataResult['V30004'];
            var resV30005 =  rawDataResult['V30005'];
            var resV30009 =  rawDataResult['V30009'];
            var resV30010 =  rawDataResult['V30010'];
            for(var i in resV30009){
                arrV30009.push((resV30009[i].pressuremanagenumber/resV30009[i].pressurefollownumber).toFixed(2))
            }
            for(var i in resV30010){
                arrV30010.push((resV30010[i].sugarmanagenumber/resV30010[i].sugarfollownumber).toFixed(2))
            }

            for(var i in resV30004){
                arrV3004.push((resV30004[i].REACHNUMBER/resV30004[i].TOTAL).toFixed(2))
            }

            for(var i in resV30005){
                arrV3005.push((resV30005[i].SUGARNUMBER/resV30005[i].TOTAL).toFixed(2))
            }

            for(var i in resV30004){
                arrV3004Total.push((resV30004[i].TOTAL))
            }

            for(var i in resV30005){
                arrArea.push((resV30005[i].unitName))
            }

            for(var i in resV30005){
                arrV3005Total.push((resV30005[i].TOTAL))
            }
            newObj.arrV3004 = arrV3004;
            newObj.arrV3005 = arrV3005;
            newObj.arrV3004Total = arrV3004Total;
            newObj.arrV30009 = arrV30009;
            newObj.arrV30010 = arrV30010;
            for(var j in newObj){
                newArray.push(newObj)
            }

          var dataArray =   Array.from(new Set(newArray))
            var html='';

            dataArray.forEach(function(val, index, arr){
              for(var i=0;i<arrV3004.length;i++){
                    html+='<tr class="hei8">';
                    html+='<td class="fz14">'+arrArea[i]+'</td>';
                    html+='<td class="fz14">'+arrV3004Total[i]+'</td>';
                    html+='<td class="fz14">'+arrV3004[i]+'</td>';
                    html+='<td class="fz14">'+arrV30009[i]+'</td>';
                    html+='<td class="fz14">'+arrV3005Total[i]+'</td>';
                    html+='<td class="fz14">'+arrV3005[i]+'</td>';
                    html+='<td class="fz14">'+arrV30010[i]+'</td>';
                    html+='</tr>';
                  }
           });
     $("#tbody").append(html)
         }
    })
})