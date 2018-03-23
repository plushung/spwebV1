/**
 * 
 */
	$(document).ready(function(){
		$("button#ssb").on("click",function(da){
			alert("but")
		$.ajax({
			
			url:"/spweb/REST/spit.xml",
			type:"post",
			dataType:'xml',
			contentType:"application/xml",
			data:JSON.stringify(obj),
			sucsess:function(da,sta,xhr){
				alert(da);
			}
			
		});
		});
		$("#err").on("click",function(){
			$.ajax({
				url:$("#errText").val(),
				type:"get",
				dataType:"json",
				contentType:"application/json",
				data:{"name":"errText"},
				error:function(da){
					alert(da.responseText)
					var ob=JSON.parse(da.responseText);
					alert(ob.name)
					alert(ob.type)
				}
			})
		})
		$("input#sm1").on("click",function(da){
			da.preventDefault();
			var obj={username:"hung",type:3,sex:"man",QQ:"6199",id:101}
			$.ajax({
			    url:"/spweb/REST/spit.json",
			    type:'post',
			    dataType:'json',
			    contentType:"application/json",
			    data:JSON.stringify(obj),
			    success:function(da,sta,xhr){
					alert(sta);
					var ta = $("<table></table>");
					$(ta).attr("id","usertab")
					$("#tb").append(ta);
					$("#f1").remove();
					$.each(da,function(k,v){
						var tr=$("<tr></tr>");
						tr.attr("id",k);
						var tdk=$("<td></td>");
						tdk.text(k);
						$(tr).append(tdk).append($("<td>"+v+"</td>"))
						$(ta).append(tr)
					})
				}
			});
			
		/*	$.post("/spweb/REST/spit.json",obj,function(da,sta,xhr){
				alert(sta);
				var ta = $("<table></table>");
				$(ta).attr("id","usertab")
				$("#tb").append(ta);
				$("#f1").remove();
				$.each(da,function(k,v){
					var tr=$("<tr></tr>");
					tr.attr("id",k);
					var tdk=$("<td></td>");
					tdk.text(k);
					$(tr).append(tdk).append($("<td>"+v+"</td>"))
					$(ta).append(tr)
				})
			})*/
			})
		})