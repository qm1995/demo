$(function (){
   window['dandan']={}
   var ing_user;//当前用户
   //浏览器
   function liulanqi(){
	  var h=$(window).height();
	  var w=$(window).width();
	  $("#top").width(w);
	  $("#foot").html(h);
	 
	  $(".box").height(h-135);
	  $("#mid_con").height(h-255);
	  $(".right_box").height(h-134);
	  $("#mid_say textarea").width(w-480);
	  
	  if($(".box").height()<350){
		$(".box").height(350)
		 }
	  if($("#mid_con").height()<230){
		 $("#mid_con").height(230)
		  }
	  if($(".right_box").height()<351){
		 $(".right_box").height(351)
		  }
	  if($("#mid_say textarea").width()<320){
		  $("#mid_say textarea").width(320)
		  }
	 
/*	 if($("#mid_foot").width()<400){
		 $("#mid_foot").width(400)
		 }  */
		  
	  	  
		  
	  if(w<=800){
		  $("#top").width(800);
		  $("#body").width(800)
		   }else{
		  $("#body").css("width","100%")  
		}	  
	  //$("#top").html(b_h);
	  
	  $(".my_show").height($("#mid_con").height()-30);//显示的内容的高度出现滚动条
	  //$(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());//让滚动滚到最底端.在这里不加这句了，没多用，可能还卡
	  
	  //右边的高度
	  r_h=$(".right_box").height()-40*3;
	  $("#right_top").height(r_h*0.25)
	  $("#right_mid").height(r_h*0.45)
	  $("#right_foot").height(r_h*0.3)
	  
   }
   window['dandan']['liulanqi']=liulanqi;
   
 //时间
function mytime(){
   var now=(new Date()).getHours();
    if(now>0&&now<=6){
	  return "午夜好";
    }else if(now>6&&now<=11){
	  return  "早上好";
    }else if(now>11&&now<=14){
	  return "中午好";
    }else if(now>14&&now<=18){
	  return "下午好";
   }else{
	  return "晚上好";
   }
}
window['dandan']['mytime']=mytime;   
   
function show(message,ing_id,t){
	t = timestampToTime(t);
	appendMessage(message,ing_id,t);
}
   
   
	//触发浏览器   
	$(window).scroll( function() { dandan.liulanqi();  } );//滚动触发
	$(window).resize(function(){ dandan.liulanqi(); return false; });//窗口触发
	dandan.liulanqi();

	//替换所有的回车换行   
	function trim2(content)   
	{   
	    var string = content;   
	    try{   
	        string=string.replace(/\r\n/g,"<br />")   
	        string=string.replace(/\n/g,"<br />");         
	    }catch(e) {   
	        alert(e.message);   
	    }   
	    return string;   
	} 	
	//替换所有的空格
	function trim(content)   
	{   
	    var string = content;   
	    try{   
	        string=string.replace(/ /g,"&nbsp;")        
	    }catch(e) {   
	        alert(e.message);   
	    }   
	    return string;   
	} 	
	
				 
				 
	function myenter(){
	    //回车键的时候换行，留以后可以有用
	}			 
				 
	//显示个数
	function user_geshu(){
	     var length1=$(".ul_1 > li").length;
	     var length2=$(".ul_2 > li").length;
	     $(".n_geshu_1").text(length1);
	     $(".n_geshu_2").text(length2);	
	}
	user_geshu()
	//alert(length1)
	
	//点击展开会员
	$(".h3").click(function (){
		 $(this).toggleClass("click_h3").next(".ul").toggle(600);
	});
	
	//鼠标经过会员的时候
	function hover_user($this){
	  $($this).hover(
	    function () {
	     $(this).addClass("hover");
	    },
	    function () {
	      $(this).removeClass("hover");
	    }
	  );
	}
	
	//经过输入文本框的时候
	$("#texterea").hover(
	  function () {
	    $(this).addClass("textarea2");
	  },
	  function () {
	    $(this).removeClass("textarea2");
	  }
	);
	//alert($admin_name);
	$("#right_foot").html('<p><img src="/demo/images/head.jpg" alt="头象" /></p>'+$admin_name);


	//过滤所有的空格
	function kongge(content)   
	{   
	    return content.replace(/^\s\s*/, '').replace(/\s\s*$/, '');   
	} 
	window['dandan']['kongge']=kongge;



/*******************************************************************************************/
	//创建新用户
	function newuser($this,arr,i,ing){
		var id="user";
		if(ing!=undefined){//创建最近聊天
		   //alert("最近聊天为真");
		   $($this).prepend('<li id="'+id+i+'">'+arr+'</li>');
		   $('#'+id+i).click(function(){title_newuser('title_'+id+ing,arr,arr[1]); });//给按钮加事件
		}else{//创建好友
		  $($this).append('<li id="'+id+i+'">'+arr+'</li>');
		  $('#'+id+i).click(function(){title_newuser('title_'+id+i,arr,arr[1]); });//给按钮加事件
		}
		hover_user('#'+id+i);//给经过触发	
		user_geshu();//更新人数
	}
	window['dandan']['newuser']=newuser;

	////更新最近聊天的位置
	function ing_my_user($this,arr,i,ing){
		var id="user";
		$("#"+id+i).remove();
		$($this).prepend('<li id="'+id+i+'">'+arr+'</li>');
		$('#'+id+i).click(function(){title_newuser('title_'+id+ing,arr,arr[1]); });//给按钮加事件
		hover_user('#'+id+i);//给经过触发	
	}

	//创建标题栏和主控制（原是有一个主控制，忘了，就合在一起了，哈哈）
	function title_newuser(id,user,img){
		  img="/demo/images/0.jpg";
		  if($("#"+id).length<1){
			  var $fid = id.substring(10,id.length);debugger
			  $("#mid_top").append('<div id="'+id+'" class="list"><table border="0" cellspacing="0" cellpadding="0"><tr><td id="zi'+id+'" class="td_user td_user_click">'+user+'</td><td id="zino'+id+'" class="td_hide td_hide_click">X</td></tr></table></div>');
			  //创建完成后给事件
			  $('#'+id).click(function(){title_newuser(id,user,img); });//给按钮加事件
			  //关闭
			  $("#zino"+id).click(function(){delete_user(id,user,img); return false });//关闭打开的
			  getMessageFromServer($uid,$fid);
		  }else{
			  $("#zino"+id).addClass("td_hide_click");//给自己加样式
			  $("#zi"+id).addClass("td_user_click");//给自己加样式
		  }
		  my_siblings("#"+id);//去掉兄弟样式
		  
		  //创建内容框
		  my_user_con(user,id);
		  
		  //创建头像
		  my_user_head(user,id,img);
		  
		  ing_user=id;//当前用户
		  //alert(ing_user);
		  
		  $("#right_mid").html("");//清空右边的内容
	}

	//去掉兄弟的样式
	function my_siblings($this){
	     $($this).siblings().children().children().children().children().removeClass("td_hide_click td_user_click");
	}

	//创建右边的头像
	function my_user_head(user,id,img){
		if($(".head"+id).length<1){
			if(!img){//头像为空的时候
				img="/demo/images/0.jpg";
			}
	       $("#right_top").append('<div class="head'+id+'"><p><img src="'+img+'" alt="'+user+'" /></p>'+user+'<div>');
		   $(".head"+id).hide();//默认是隐藏，让它有一点效果
		}
		sibli_hide(".head"+id);
	}
	//隐藏兄弟头像
	function sibli_hide($this){
	     $($this).show(500,function(){$(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());/*让滚动滚到最底端*/}).siblings().hide(500);//隐藏其他兄弟
	}
	//创建内容框
	function my_user_con(user,id){
		if($("#user_con"+id).length<1){
		   $(".con_box").append('<div id="user_con'+id+'"><font color="#CCCCCC">请在下面文本框里输入你想要聊天的内容，与用户【'+user+'】聊天</font></div>');
		   $("#user_con"+id).hide();//默认隐藏，增加效果
		}
		sibli_hide("#user_con"+id);//隐藏兄弟
	}

	//关闭打开的窗口
	function delete_user(id,user,img){
		if(ing_user==id){
			if(confirm('你确定要关闭 '+user+' 聊天窗口吗？\n 注意哦，关闭后你跟 '+user+' 的聊天记录就不见了哦')){
			$("#"+id).remove();//栏目栏目
			$("#user_con"+id).remove();//删除内容
			$(".head"+id).remove();//删除头像
			 if($(".list").length>0){
				 var eq=$(".list").length-1;
				 var old_id=$(".list:eq("+eq+")").attr("id");
				 sibli_hide(".head"+old_id);//显示最后一个的头像
				 sibli_hide("#user_con"+old_id);//显示最后一个的内容
				 $("#zino"+old_id).addClass("td_hide_click");//给最后一个加样式
		         $("#zi"+old_id).addClass("td_user_click");//给最后一个加样式
				 ing_user=old_id;//给聊天框定位
				 //alert(ing_user);
				 
			 }else{
			     //alert("已经全部删除");
				 $(".dandan_liaotian").show(500)
			 };
			
		    }
		}else{
			title_newuser(id,user,img);
		}
	}
	//将时间戳转为日期格式
	function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = (date.getDate() < 10 ? '0'+(date.getDate()):date.getDate()) + ' ';
        h = (date.getHours() < 10 ? '0'+(date.getHours()):date.getHours()) +':';
        m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()):date.getMinutes()) + ':';
        s = date.getSeconds() < 10 ? '0'+(date.getSeconds()):date.getSeconds();
        return Y+M+D+h+m+s;
    }
	/**
	 * 用于用户登录时，返回所有消息
	 * @param fromUser
	 * @param toUser
	 * @returns
	 */
	function getMessageFromServer(fromUser,toUser){
		$.ajax({
	    	url:'/demo/user/message/getAll.action',
	    	type:'post',
	    	data:{
	    		fromUser:fromUser,
	    		toUser:toUser
	    	},
	    	success:function(data){
	    		if(data.statusCode != 200){
	    			alert("error");
	    		}else{
	    			$.each(data.result,function(){
	    				appendMessage(this.content,this.uid,timestampToTime(this.sendtime));
	    			});
	    		}
	    		return true;
	    	},
	    	error:function(){
	    		alert("接收消息失败！");
	    		return false;
	    	}
	    });
	    return true;
	}
	//发送消息到服务器
	function sendMessageToServer(fromUser,toUser,message){
	    $.ajax({
	    	url:'/demo/user/message/save.action',
	    	type:'post',
	    	data:{
	    		content:message,
	    		uid:fromUser,
	    		fid:toUser
	    	},
	    	success:function(data){
	    		if(data.statusCode != 200){
	    			alert("error");
	    		}else{
	    			//alert("success");
	    		}
	    		return true;
	    	},
	    	error:function(XMLHttpRequest, textStatus, errorThrown){
	    		alert(XMLHttpRequest+"发送消息失败！请重发"+textStatus+","+errorThrown);
	    		return false;
	    	}
	    });
	    return true;
	}
	//ctrl+回车
    $("body").bind('keyup',function(event) {   
         if(event.ctrlKey&&event.keyCode==13){   
            saysay($uid);
        }
		if(!event.ctrlKey&&event.keyCode==13){
			myenter();
		}
    }); 
    //发送按钮 
    $("#mid_sand").click(function(){
           	saysay($uid);					   
    })
    function appendMessage(message,ing_id,t){
    	//alert(message+":"+ing_id+":"+t);
    	var name = $("#user"+ing_id).text();
    	if(name==""){
    		name=$admin_name;
    	}
    	$("#user_con"+ing_user).append('<div class="my_say_con"><font color=\"#0000FF\">'+name+":"+t+"</font><p><font color=\"#333333\">"+trim2(trim(message))+'</font></p></div>');
	    $("#right_mid").html($("#texterea").val());//右边显示刚发送的文字
	    $("#texterea").val("");
	    $(".my_show").scrollTop($(".con_box").height()-$(".my_show").height());//让滚动滚到最底端
	    
	    if($("#usering"+ing_id).length<1){//创建最近聊天人
	       dandan.newuser('.ul_1',$arr_user[ing_id],'ing'+ing_id,ing_id);//创建最近聊天
	    }else{
		    ing_my_user('.ul_1',$arr_user[ing_id],'ing'+ing_id,ing_id);//更新最近聊天的位置   
	    }
    }
	//发送后调用此方法
 	function saysay(uid){
 		if($(".list").length<1){
 			alert("你还没选中跟哪个聊天，请点左边好友选中一个再聊");
 			return false;
 		}
	    
	    var t=new Date().toLocaleTimeString();//当前时间
	    if($("#texterea").val()){
	    	var content = $("#texterea").val();
	    	var ing_id=ing_user.substring(10,ing_user.length);
	    	var isSuccess = sendMessageToServer(uid,ing_id,$("#texterea").val());
	    	if(!isSuccess){
	    		return;
	    	}
	    	appendMessage($("#texterea").val(),$uid,t);
	    	var msg={
	    			uid:uid,
	    			fid:ing_id,
	    			content:content
	    	};
	    	msgService.sendMessage(msg,function(){
	    		//alert("send success");
	    	});
        }else{
		 alert("你输入的内容为空")  
	    }
	    $("#texterea").focus();//光标焦点
	  }  

 	


//欢迎
//$("#top").html();

	//加载用户
	$(".ul").html("");//初始清空原来留在那里让w3c通过的
	for(i=0;i<$arr_user.length;i++){
	    dandan.newuser('.ul_2',$arr_user[i],i);//创建用户
		
	}
	
	
	//点击添加按钮,弹出添加页面
	$("#addFriend").click(function(){
		var height = 300;
		var width = 380;
		var top=($(window).height()-height)/2;
		var left=($(window).width()-width)/2;
		window.open('/demo/page/addFriend.action', '添加好友', 
				'height='+height+', width='+width+', top='+top+', left='+left+', toolbar=no, menubar=no, scrollbars=no, resizable=no,location=yes, status=no,titlebar=no,resizable');
		
		//$('#addFriend').modal('hide');
	});


})