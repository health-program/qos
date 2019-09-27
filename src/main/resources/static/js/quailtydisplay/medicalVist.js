$(function(){
    //支付方式对比
         var arr = {
           eventIds:'13002,14001,13001'
         }


         //从大到小排序
         function compareBigToSmall(property){
             return function(a,b){
                 var value1 = a[property];
                 var value2 = b[property];
                 return  value2 - value1;
             }
         };






       $.ajax({
         type : "post",    //请求类型
        url : "/qos/analysis/data/get/day/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData) {

       var  unit13001Data=convertUnitChartData(rawData.result, '13001', false); //
       var  unit13002Data=convertUnitChartData(rawData.result, '13002', false); //
       var  unit13003Data=convertUnitChartData(rawData.result, '13003', false); //






        var  unit13003Data13003=rawData.result['13003'].sort(compareBigToSmall('count'));
        var unit13003DataArray= unit13003Data13003.slice(0,5)
         // 排名最高的已经有了



       var unit13001DatanewArray=[]
       for(var i=0;i<unit13003DataArray.length;i++){
            for(var j=0;j<rawData.result['13001'].length;j++){
                if(unit13003Data13003[i].unitId ==rawData.result['13001'][j].unitId){
                    unit13001DatanewArray.push(rawData.result['13001'][j])
                }
            }
       }



        var unit13002DatanewArray=[]
              for(var i=0;i<unit13003DataArray.length;i++){
                   for(var j=0;j<rawData.result['13002'].length;j++){
                       if(unit13003DataArray[i].unitId ==rawData.result['13002'][j].unitId){
                           unit13002DatanewArray.push(rawData.result['13002'][j])
                       }
                   }
              }

 var newast13001 = {}
    newast13001['13001']=unit13001DatanewArray
 var  unit13001DatanewArrayunit13001DatanewArray=convertUnitChartData(newast13001, '13001', false);


 var newast13002 = {}
     newast13002['13002']=unit13002DatanewArray
  var  unit13002DatanewArrayunit13002DatanewArray=convertUnitChartData(newast13002, '13002', false);



         //社区就诊开始

          var fontColor = 'rgba(255,255,255,0.5)';
            let data=[
             {
                 name:'急诊人次',
                 list:[
                     {xdate:'星期一',enName:"Mon",value:"3"},
                     {xdate:'星期二',enName:"Tues",value:"5"},
                     {xdate:'星期三',enName:"Wed",value:"7"},
                     {xdate:'星期四',enName:"Thurs",value:"2"},
                     {xdate:'星期五',enName:"Fri",value:"5"},
                     {xdate:'星期六',enName:"Sat",value:"3"},
                     {xdate:'星期日',enName:"sun",value:"9"},
                     ]
              },
              {
                 name:'入院人次',
                 list:[
                     {xdate:'星期一',enName:"Mon",value:"3"},
                     {xdate:'星期二',enName:"Tues",value:"5"},
                     {xdate:'星期三',enName:"Wed",value:"7"},
                     {xdate:'星期四',enName:"Thurs",value:"2"},
                     {xdate:'星期五',enName:"Fri",value:"5"},
                     {xdate:'星期六',enName:"Sat",value:"3"},
                     {xdate:'星期日',enName:"sun",value:"9"},
                     ]
               },
             {name:'普通门诊人次',
             list:[
                     {xdate:'星期一',enName:"Mon",value:"7"},
                     {xdate:'星期二',enName:"Tues",value:"2"},
                     {xdate:'星期三',enName:"Wed",value:"5"},
                     {xdate:'星期四',enName:"Thurs",value:"8"},
                     {xdate:'星期五',enName:"Fri",value:"1"},
                     {xdate:'星期六',enName:"Sat",value:"3"},
                     {xdate:'星期日',enName:"sun",value:"6"},
                     ]},
             ];
             let datelist = [];
             let safeList = [];
             let danger = [];
             let safeList1 = [];
             data[0].list.forEach(function(value,index){
                 datelist.push(data[0].list[index].enName);
                  safeList.push(data[0].list[index].value);
                  danger.push(data[1].list[index].value);
                  safeList1.push(data[2].list[index].value);
             });
         medicaladviceoption ={

         		grid: {
         	        left: '5%',
                     right: '5%',
                     top:'20%',
         	        bottom: '5%',
         	        containLabel: true
         		},
         		tooltip : {
         			show: true,
         			trigger: 'item'
         		},
         		legend: {
         			show:true,
         			x:'center',
         			y:'35',
         			icon: 'stack',
         			itemWidth:15,
         			itemHeight:5,
         			textStyle:{
         				color:'rgba(255,255,255,1)' ,
         				fontSize:15
         			},
         			nameTextStyle :{
         			   color:'rgba(255,255,255,1)'
         			},
         			data:[data[0].name,data[1].name,data[2].name]
         		},
         		xAxis : [
         	        {
         	            type : 'category',
         	            boundaryGap : false,
         	            axisLabel:{
         	            	color: fontColor,
         	            	fontSize:16
         	            },
         	            axisLine:{
                        		show:true,
                        		lineStyle:{
         		            	color:'rgba(255,255,255,0.1)',
         		            }
         				},
         				axisTick:{
         	            	show:false,
         	            },
         	            splitLine:{
         	            	show:true,
         		            lineStyle:{
         		            	color:'rgba(255,255,255,0.05)',
         		            }
         		        },
         	            data :datelist
         	        }
         	    ],
         	    yAxis : [
         			{
         				type : 'value',
         				axisLabel : {
         					formatter: '{value}',
         					textStyle:{
         						color:fontColor,
         						fontSize:16
         					}
         				},
         				axisLine:{
         					lineStyle:{
         						color:'rgba(255,255,255,0.1)',
         					}
         				},
         				axisTick:{
         	            	show:false,
         	            },
         				splitLine:{
         					show:true,
         					lineStyle:{
         						color:'rgba(255,255,255,0.05)',
         					}
         				}
         			}
         			],
         		series : [
         			{
         				name:data[0].name,
         				type:'line',
         				smooth: true , //true 为平滑曲线，false为直线
         				// smooth:true,  //这个是把线变成曲线
         	            itemStyle: {
         			        normal: {
         						color:'#0092f6',
         			            lineStyle: {
         							color: "#0092f6",
         							width:1
         			            },
         			            areaStyle: {
         							color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
         								offset: 0,
         								color: 'rgba(0,255,255,0)'
         							}, {
         								offset: 1,
         								color: 'rgba(0,255,255,1)'
         							}]),
         			            }
         			        }
         				},
         				data:safeList
         			},
         			{
         				name:data[2].name,
         				type:'line',
         				smooth: true , //true 为平滑曲线，false为直线
         				// smooth:true,  //这个是把线变成曲线
         	            itemStyle: {
         			        normal: {
         						color:'#0092f6',
         			            lineStyle: {
         							color: "#0092f6",
         							width:1
         			            },
         			            areaStyle: {
         							color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
         								offset: 0,
         								color: 'rgba(0,255,255,0)'
         							}, {
         								offset: 1,
         								color: 'rgba(0,255,255,1)'
         							}]),
         			            }
         			        }
         				},
         				data:safeList1
         			},
         			{
         				name:data[1].name,
         				type:'line',
         				smooth: true , //true 为平滑曲线，false为直线
         	            itemStyle: {
         			        normal: {
         			            color:'rgba(251,14,68,0.7)',
         			            lineStyle: {
         							color: "rgba(251,14,68,0.8)",
         							width:1
         			            },
         			            areaStyle: {
         							//color: '#94C9EC'
         							color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
         								offset: 0,
         								color: 'rgba(251,14,68,0)'
         							}, {
         								offset: 1,
         								color: 'rgba(251,14,68,0.9)'
         							}]),
         			            }
         			        }
         				},

         				data:danger
         			}
         		]
         	};
           var myChartMedicaladvice = echarts.init(document.getElementById('medicaladvice'));
         	  myChartMedicaladvice.setOption(medicaladviceoption);





                 //社区就诊结束


                //初始化echarts实例

                    window.addEventListener("resize", function () {
                         myChartMedicaladvice.resize();
                    });

         }
    });




})