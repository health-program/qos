 
 var populationContractsID = echarts.init(document.getElementById('populationContracts'));

$(function(){
    var xLabel=[];
$.post('http://10.9.1.41:18081/home/page/qos/population/signing',function(data){
	var result = data.result;
	var seriesData = [],xAxisData = [];
	var data = monthDate();
	if(result.length > 0){
		for(var i=0;i<data.length;i++){	
			b = false;
			for(var j=0;j<result.length;j++){
				if(data[i] == result[j].month){
					xAxisData.push(data[i]);
                    xLabel.push(data[i]);
					seriesData.push({name:data[i],value:result[j].count});
					b = true;
				}				
			}
			if(!b){
					xAxisData.push(data[i]);
                    xLabel.push(data[i]);
					seriesData.push({name:data[i],value:0});
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
			