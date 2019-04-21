// 微信内浏览器调起支付
function onBridgeReady() {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": "",
            "timeStamp": "", // seconds
            "nonceStr": "",
            "package": "prepay_id=",
            "signType": "MD5",
            "paySign": ""
        },
        function (res) {
            if (res.err_msg === "get_brand_wcpay_request:ok") {
                // maybe success ?
            }
        });
}
if (typeof WeixinJSBridge == "undefined") {
    if (document.attachEvent) {
        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    } else if (document.addEventListener) {
        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    }
} else {
    onBridgeReady();
}