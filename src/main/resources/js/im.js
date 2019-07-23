var ws_protocol = 'ws'; // ws 或 wss
var ip = '127.0.0.1'
var port = 9876

var heartbeatTimeout = 5000; // 心跳超时时间，单位：毫秒
var reconnInterval = 1000; // 重连间隔时间，单位：毫秒

var binaryType = 'blob'; // 'blob' or 'arraybuffer';//arraybuffer是字节
var handler = new IMHandler()
var mtime="";

var tiows
function initWs () {
  var queryString = 'name=123456';
   
  var param = "";
  tiows = new tio.ws(ws_protocol, ip, port, queryString, param, handler, heartbeatTimeout, reconnInterval, binaryType)
  tiows.connect()
}

//回车事件绑定
document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        sendMsg();
    }
}

// function showTime()
// {
// 	var myDate = new Date();
// 	var m=myDate.getMinutes();
// 	if (m<10) m="0"+m;
// 	var s=myDate.getSeconds();
// 	if (s<10) s="0"+s;
// 	mtime=myDate.getHours()+":"+m+":"+s;
//
// 	document.getElementById("mtime").innerText=mtime;
// }

function sendMsg () {
  var msg = document.getElementById('txtInput');
  tiows.send(msg.value);
  msg.value="";
}

function clearMsg () {
  document.getElementById('contentId').innerHTML = ''
}

initWs();
// setInterval(showTime,1000);