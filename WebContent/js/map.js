$(function () {
    //label CSS style
    //.label{font-size:12px;background:rgba(22,22,22,0.6);color:#fff;padding:.25em}
    $('#map').tinyMap({
    center: '110台灣台北市信義區逸仙路2',
    zoom: 13,
	
    marker: [
        {
            addr: '110台灣台北市信義區逸仙路28號',
            text: '110台灣台北市信義區逸仙路28號',
            label: '請點我',
            css: 'maplab',
            // 自訂 marker click 事件
            event: {
                'click' : function (e) {    $('#map01').modal({
    keyboard: true
    })}
            },
			icon: {
                url: 'img/big.png'
            },
            // 動畫效果
            animation: 'DROP|BOUNCE'
        },{addr: ['25.037467', '121.564077'], text: '台北市政府'},
        {addr: ['25.100295', '121.549494'], text: '國立故宮博物院'}
    ]
});
});