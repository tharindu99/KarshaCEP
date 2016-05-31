function StockPRC_graph(permno,id){
	var url = "stockprice?PERMNO="+permno;
	$.ajax({
		type : 'GET',
		url : url,
		dataType : 'json',
		success : function(data) {
			draw_pricegrap(id,data);
			},
			error : function(data, error) {
				console.log(error+"data doesnt loading correctly");
			},
			async : false
	});
}

function draw_pricegrap (id,data) {
    var chart = c3.generate({
        bindto: id,
        data: {
            x: 'AllDates',
    //        xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
            axes: {
                PRC: 'y',
                PseudoPrice: 'y',
                Turnover : 'y2'
            },
            json : data,
			mimeType : 'json'
            
           
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%Y-%m-%d'
                },
                label:'Time',
            },
            y:{
                label:'Price/PseudoPrice in $',
            } ,
            y2: {
                show: true,
                label:'Turnover'
            }
        },
        tooltip: {
            format: {
                value: function (value, ratio, id) {
                    var format = id === 'Turnover' ? d3.format(',') : d3.format('$');
                    return format(value);
                }
            }
        },
//            value: d3.format(',') // apply this format to both y and y2
    /*    subchart: {
      show: true
    },*/
    zoom: {
        enabled: true
    }
    });
   
}
