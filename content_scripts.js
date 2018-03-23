function readXPath(e){if(""!==e.id)return'//*[@id="'+e.id+'"]'
if(e==document.body)return"/html/"+e.tagName.toLowerCase()
for(var t=1,o=e.parentNode.childNodes,a=0,n=o.length;n>a;a++){var r=o[a]
if(r==e)return arguments.callee(e.parentNode)+"/"+e.tagName.toLowerCase()+"["+t+"]"
1==r.nodeType&&r.tagName==e.tagName&&t++}}function xpathtocsspath(e){return e=e.replace(/\/\//g," "),e=e.replace(/\//g,">"),e=e.replace(/\[([^@].*?)\]/gi,function(e,t){var o=parseInt(t)-1
return":eq("+o+")"}),e=e.replace(/\@/g,""),e=e.substr(1)}function parsefiledselector(){$("body").find("*").each(function(e,t){$(t).is(":hidden")&&$(t).remove()})
var e=$("html").html(),t=($(document.body).width(),$(document.body).height(),{type:"getContentList",content:e})
getDataFromBg(t,function(e){$.each(JSON.parse(e),function(e,t){$.each(t,function(e,t){var o={}
if($($(t)[0].selector).is(":visible")){var a=$($(t)[0].selector).offset().left,n=$($(t)[0].selector).offset().top
$($(t)[0].selector).offset().left+$($(t)[0].selector).width(),$($(t)[0].selector).offset().top+$($(t)[0].selector).height()
o.X=a,o.Y=n,o.width=$($(t)[0].selector).width(),o.width=$($(t)[0].selector).height(),o.text=$(t)[0].text,o.selector=$(t)[0].selector,o.setborder=!1,arr.push(o)}})})})}function fullurl(e){String.prototype.startWith=function(e){var t=RegExp("^"+e)
return t.test(this)}
var t=window.location.protocol,o=window.location.host
return e.startWith("//")?e=t+e:e.startWith("/")&&(e=t+"//"+o+e),console.log(e),e}function parsealist(e){loadding("正在分析网页..."),$("body").scrollTop($("body")[0].scrollHeight)
var t=$("html").html(),o={type:"getHtmlList",content:t}
$(document.body).width()/2,$(document.body).height()/2
getDataFromBg(o,function(t){console.log(JSON.parse(t).nextPage)
var o=getlistregex(JSON.parse(t).map)
$(o).css({"box-shadow":"0 0 0 1px red"}),e(o,JSON.parse(t).nextPage)})}function sleep(e){for(var t=(new Date).getTime();;)if((new Date).getTime()-t>e)break}function getlistregex(e){var t=($(document.body).width()/2,$(document.body).height()/2),o=0,a=""
for(key in e)try{if(t>$(e[key][0].selector).offset().top&&t<$(e[key][e[key].length-1].selector).offset().top){var n=$(e[key][0].regex).css("font-size").replace("px","")
n-=6
var r=e[key][0].wordAvgNum
n*r>o&&(o=n*r,a=e[key][0].regex)}}catch(i){continue}return a}function getDataFromBg(e,t){chrome.extension.sendRequest(e,function(e){t(e)})}function createDiv(){var e="<div id='dialog-form' title='设置采集字段' style='border:1px solid #888;' >"
e+="<form>",e+="<fieldset id='fieldset'>",e+="</fieldset>",e+="</form>",e+="</div>",$("#dialog-form").remove(),$("body").append(e),$("#dialog-form").trigger("create")}function openconfdilog(){function e(){var e=[]
$("#fieldset").find("input[type='text']").each(function(t,o){var a={}
a.selector=$(o).attr("name"),a.text=$(o).val(),e.push(a)}),0==e.length?getDataFromBg({type:"closeconf"},function(e){}):getDataFromBg({type:"completeconf",feildsconf:e},function(e){})}var t,o,a=$("#name"),n=$("#email"),r=$("#password"),i=$([]).add(a).add(n).add(r)
$(".validateTips")
t=$("#dialog-form").dialog({autoOpen:!1,height:400,width:350,modal:!1,buttons:{"完成":e,"取消":function(){$(this).dialog("close"),getDataFromBg({type:"closeconf"},function(e){})}},close:function(){o[0].reset(),i.removeClass("ui-state-error")},open:function(e,t){$(".ui-dialog-titlebar-close",$(this).parent()).hide()}}),o=t.find("form").on("submit",function(e){e.preventDefault(),addUser()}),t.dialog("open")}function loadding(e){var t=chrome.extension.getURL("busy.gif")
$.blockUI({message:'<h1 style="font-size:18px;"><img width=40px src="'+t+'" />'+e+"</h1>"})}var arr=[],flag=!1,caijiflag=!1
$(document).ready(function(){$("#startcollectpagedataprocess").bind("click",function(){if(!caijiflag){caijiflag=!0,$("#startcollectpagedataprocess").addClass("disabled"),$("#stopcollectpagedataprocess").removeClass("disabled")
var e=$("#startcollectpagedataprocess").val()
getDataFromBg({type:"startcollect",confid:e},function(e){})}}),$("#stopcollectpagedataprocess").bind("click",function(){caijiflag=!1,getDataFromBg({type:"stopcollect"},function(e){})}),$("#restartcollectpagedataprocess").bind("click",function(){var e=$("#startcollectpagedataprocess").val(),t=$("#url").val()
getDataFromBg({url:t,confid:e,type:"confpageprocess"},function(e){})}),$("#confpagecolectdataregexprocess").bind("click",function(){var e=($.trim($("#startcollectpagedataprocess").val()),$("#url").val())
return""==$.trim(e)?void alert("错误"):void getDataFromBg({url:e,confid:confid,type:"confpageprocess"},function(e){})}),$(document).mouseover(function(e){flag&&("Y"==$(e.target).attr("data-json")&&$($(e.target).attr("name")).css({"background-color":"yellow"}),$(e.target).css({outline:"red solid 1px"}))}),$(document).mouseout(function(e){flag&&("Y"==$(e.target).attr("data-json")&&$($(e.target).attr("name")).css({"background-color":"transparent"}),$(e.target).css({outline:"none"}))})}),$(document).dblclick(function(e){if(flag){var t=xpathtocsspath(readXPath(e.target))
if(console.log(t),0==$("input[name='"+t+"']").length){$(t).css({"box-shadow":"0 0 0 1px red"})
var o=""
o+="<div>",o=o+"<input style='display:block;'  data-json='Y' type='text' name='"+t+"' value='"+$(t).text()+"' class='text ui-widget-content ui-corner-all'><input type='button' ids='yes' value='删除'/></div>",$("#fieldset").append(o),$('[ids="yes"]').click(function(){$($(this).parent().find("input[type='text']").attr("name")).css({"box-shadow":"none"}),$(this).parent().remove()})}else $(t).css({"box-shadow":"none"})}}),chrome.extension.onMessage.addListener(function(e,t,o){if("calculate"==e.message){console.log(e.pageconf.listSelector)
var a=[]
$(e.pageconf.listSelector).each(function(e,t){a.push(fullurl($(t).attr("href")))}),chrome.extension.sendRequest({type:"save",urls:a},function(e){})}else if("fetchDetail"==e.message){var n=""
console.log(e.fieldsconf)
for(var r=0;r<e.fieldsconf.length;r++)n=n+$(e.fieldsconf[r]).text().trim()+"{{{{{}}}}}"
n=n.substring(0,n.lastIndexOf("{{{{{}}}}}")),o(n)}else if("nextpage"==e.message)for(var r=0;r<$(e.nextSelector[0]).length;r++)$($(e.nextSelector[0])[r]).text()==e.nextSelector[1]&&$(e.nextSelector[0])[r].click()
else"conflistpage"==e.message?(console.log(localStorage.userid),parsealist(function(e,t){loadding("列表页分析成功"),t.hasNextConf||alert("分页标记获取失败,你可以在配置页面完成后，再手动修改!")
for(var o=Math.floor(Math.random()*$(e).length),a=$(e)[o],n=$(a).attr("href"),r=[],i=0;i<$(e).length;i++)r.push($($(e)[i]).text())
setTimeout(function(){$.unblockUI()},2e3),getDataFromBg({type:"confcontentpage",url:fullurl(n),regex:e,regexarr:r,targeurl:window.location.href,nextPage:t,title:$("title").text(),userid:localStorage.userid},function(e){})})):"confcontentpage"==e.message&&($("a").each(function(e,t){$(t).removeAttr("href"),$(t).unbind("click")}),createDiv(),openconfdilog(),flag=!0,$("#dialog-form").unbind("mouseover").unbind("mouseout"),$("div[role='dialog']").css({"z-index":"10000"}))}),chrome.extension.onRequest.addListener(function(e,t,o){})
