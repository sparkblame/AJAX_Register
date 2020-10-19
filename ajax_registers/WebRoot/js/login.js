var xmlHttp;
function createXmlHttp(){
    if(window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }
    else{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
}




function changeCode(){
    var codeImg = document.getElementById("verifyCodeImage");
    codeImg.src="createImage.do?t="+Math.random();
}
function ajaxCheckLogin() {
    var userName=document.getElementById("userName").value;
    var passsword=document.getElementById("password").value;
    var vcode=document.getElementById("verifyCode").value;
    var checkBox=document.getElementById("checkBox").value;
    createXmlHttp();
    xmlHttp.open("post","ajaxLoginCheck.do",true);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlHttp.send("userName="+userName+"&password="+passsword+"&vcode="+vcode+"&checkBox="+checkBox);
    xmlHttp.onreadystatechange=function(){//回调函数
        if(xmlHttp.readyState==4&&xmlHttp.status==200){
            var response=xmlHttp.responseText;
            var json=JSON.parse(response);//调用系统函数将字符串转换为json对象
            if(json.code==0){//登录成功
                window.location.href="main.jsp";
            }else{//登录失败
                document.getElementById("checkError").innerText=json.info;//显示返回错误信息
            }
        }
    }
}
function checkNull(e) {
    if (e.value.match(/^[ ]*$/)) {
        document.getElementById(e.id + 'Error').innerText = e.name + '不能为空';
    } else {
        document.getElementById(e.id + 'Error').innerText = '';
    }
}