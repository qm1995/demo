var operation={
				url:{
					loginUrl:'/demo/login.action',
					registerUrl:'/demo/register.action',
					checkUrl:'/demo/check.action'
				},
				check:function(){
					$.ajax({
						url:operation.url.checkUrl,
						type:'get',
						data:{
							username:$("#username").val()
						},
						success:function(data){
							var status = data.result;
							return status;debugger
						},
						error:function(){
							alert("error");
							return false;
						}
					});
					return false;
				},
				login:function(){
					alert(operation.check());debugger
					if(!operation.check()){
						$("#msg").text("用户名不存在");
					}else{
						$.ajax({
							url:operation.url.loginUrl,
							type:'get',
							data:{
								username:$("#username").val(),
								password:$("#password").val()
							},
							success:function(data){
								var code = data.statusCode;
								if(code == 200){
									alert("success");
								}else{
									$("#msg").text(data.msg);
								}
							}
						});
					}
				},
				register:function(){
					
				}
		};


