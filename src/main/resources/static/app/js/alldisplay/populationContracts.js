 
 var populationContractsID = echarts.init(document.getElementById('populationContracts'));

$(function(){
    var dataLength=[];

    var xLabel=[];
    var arr = {
        eventIds:'V40000'
    }

    function sortKey(array, key) {
        return array.sort(function(a, b) {
            var x = a[key];
            var y = b[key];
            return x < y ? -1 : x > y ? 1 : 0;
        });
    }
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/get/total/data",//请求的 URL地址
        data:arr,
        success: function (rawData) {
            var result=eval(rawData.message);
            result=sortKey(result,"MONTH");
            // var xLabel=[];
            // var yData=[];
            // for(var i=0;i<result.length;i++){
            //     var date = new Date();
            //     var year = date.getFullYear();
            //     if (year==result[i].MONTH.substring(0,4)){
            //         xLabel.push(result[i].MONTH);
            //         dataLength.push(result[i].MONTH);
            //         yData.push(result[i].ZHQYS);
            //     }
            // }



                var seriesData = [],xAxisData = [];
                var data = monthDate();
                var b;
                if(result.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        b = false;
                        for (var j = 0; j < result.length; j++) {
                            if (data[i] == result[j].MONTH) {
                                xAxisData.push(data[i]);
                                seriesData.push({name: data[i], value: result[j].ZHQYS});
                                b = true;
                            }
                        }
                        if (!b) {
                            xAxisData.push(data[i]);
                            seriesData.push({name: data[i], value: 0});
                        }
                    }
                }

                    var populationContractsOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            top: '15%',
            right: '3%',
            left: '10%',
            bottom: '20%'
        },
        xAxis: [{
            type: 'category',
            data: xAxisData,
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,0.12)'
                }
            },
            axisLabel: {
                color: '#e2e9ff',
                textStyle: {
                    fontSize: 10
                },
                interval:0,
                rotate:40
            },
        }],
        yAxis: [{
            axisLabel: {
                formatter: '{value}',
                color: '#e2e9ff',
            },
            axisLine: {
                show: false
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,0.12)'
                }
            }
        }],
        series: [{
            type: 'bar',
            data: seriesData,
            barWidth: '10px',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#df561c' // 0% 处的颜色
                    }, {
                        offset: 1,
                        color: '#fdbb01' // 100% 处的颜色
                    }], false),
                    barBorderRadius: [30, 30, 30, 30],
                    shadowColor: 'rgba(0,160,221,1)',
                    shadowBlur: 4,
                }
            }
        }]
    };
  
    populationContractsID.setOption(populationContractsOption);

	}
	
})
    
    var index = 0; //播放所在下标
    var mTime = setInterval(function() {
        populationContractsID.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: index
        });
        index++;
        if(index > xLabel.length) {
            index = 0;
        }
    }, 5000)
     window.addEventListener("resize", function () {
         populationContractsID.resize();
    });
})

function monthDate(){
	var dataArr = [];
	var data = new Date();
	var year = data.getFullYear();
	data.setMonth(data.getMonth()+1, 1)//获取到当前月份,设置月份
	for (var i = 0; i < 13; i++) {
		data.setMonth(data.getMonth() - 1);//每次循环一次 月份值减1
		var m = data.getMonth() + 1;
		m = m < 10 ? "0" + m : m;
		dataArr.push(data.getFullYear() + "-" + (m))
	}
 
	return dataArr.sort().slice(0,12);
}
			