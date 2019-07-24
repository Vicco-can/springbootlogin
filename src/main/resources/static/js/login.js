$(function(){
    $("#user_insert_btn").click(function(){
        var kaptcha = $("#kaptcha").val();
        if (kaptcha.length == 0) {
            alert("您没有输入验证码！");
        } else {
            var name = $("#name").val();
            var password = $("#password").val();
            //发送ajax请求修改员工数据
            $.ajax({
                url:"logincheck",
                data:
                    {name:name,password:password},
                type:"post",
                success:function(msg){
                    console.log(msg);
                    //100表示成功
                    if (msg.code == 100) {
                        // window.location.href = "company?companyid="+msg.companyid;
                        window.location.href = "findcompany?listcompanyid="+msg.listcompanyid;
                    } else {
                        $('#prompt').text(msg.error);
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }
    });
});
