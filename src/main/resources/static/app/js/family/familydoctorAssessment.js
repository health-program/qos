$(function(){


    //指定图标的配置和数据
    // 21001 综合健康管理服务包签约率（收费）
       var arr = {
           eventIds:'V60001,V60002,V60003,V60004,V60005,V60006,V60007,V60008,V60009,V60010,V60011,V60012,V60013,V60014,V60015,V60016'
       }

      $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/get/family/data",//请求的 URL地址
        data:arr,
        success: function (res) {
            var rawData = res.result;
             $("#eventIdsV60001").text(rawData.V60001)
             $("#eventIdsV60002").text(rawData.V60002)
             $("#eventIdsV60003").text(rawData.V60003)
             $("#eventIdsV60004").text(rawData.V60004)
             $("#eventIdsV60005").text(rawData.V60005)
             $("#eventIdsV60006").text(rawData.V60006)
             $("#eventIdsV60007").text(rawData.V60007)
             $("#eventIdsV60008").text(rawData.V60008)
             $("#eventIdsV60009").text(rawData.V60009)
             $("#eventIdsV60010").text(rawData.V60010)
             $("#eventIdsV60011").text(rawData.V60011)
             $("#eventIdsV60012").text(rawData.V60012)
             $("#eventIdsV60013").text(rawData.V60013)
             $("#eventIdsV60014").text(rawData.V60014)
             $("#eventIdsV60015").text(rawData.V60015)
             $("#eventIdsV60016").text(rawData.V60016)


            var mySwiper = new Swiper ('.swiper-container', {
                loop: true, // 循环模式选项
                speed: 300,
                autoplay:true,
                // 如果需要分页器
                pagination:{
                    el:'.swiper-pagination',
                }//这样写小圆点就有了
            })


         }
    });



})