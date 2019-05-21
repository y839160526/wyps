var arr = new Array();
var flag = false;
var caijiflag = false;

$(document).ready(function() {
    $('#startcollectpagedataprocess').bind('click', function() {
        if (!caijiflag) {
            caijiflag = true;
			$('#startcollectpagedataprocess').addClass('disabled');
			$('#stopcollectpagedataprocess').removeClass('disabled');
            var confid = $('#startcollectpagedataprocess').val();
            getDataFromBg({
                type: 'startcollect',
                confid: confid
            }, function(resp) {});
        }


    });

    $('#stopcollectpagedataprocess').bind('click', function() {
        caijiflag = false;
        getDataFromBg({
            type: 'stopcollect'
        }, function(resp) {

        });
    });
    $('#restartcollectpagedataprocess').bind('click', function() {
        var confid = $('#startcollectpagedataprocess').val();
        var url = $('#url').val();
        getDataFromBg({
            url: url,
            confid: confid,
            type: 'confpageprocess'
        }, function(resp) {

        });
    });
    $('#confpagecolectdataregexprocess').bind('click', function() {
		var id = $.trim($('#startcollectpagedataprocess').val());
        var url = $('#url').val();
        if ($.trim(url) == '') {
            alert('错误');
            return;
        }
        getDataFromBg({
            url: url,
            confid: confid,
            type: 'confpageprocess'
        }, function(resp) {

        });
    });
    $(document).mouseover(function(e) {

        if (flag) {
			if($(e.target).attr('data-json')=='Y'){
				$($(e.target).attr('name')).css({ 'background-color':'yellow'});
			}
            $(e.target).css({
                outline: 'red solid 1px'
            });
        }

    });
    $(document).mouseout(function(e) {
        if (flag) {
			if($(e.target).attr('data-json')=='Y'){
				$($(e.target).attr('name')).css({ 'background-color':'transparent'});
			}
            $(e.target).css({
                outline: 'none'
            });
        }

    });
});

function readXPath(element) {
    if (element.id !== "") { //判断id属性，如果这个元素有id，则显 示//*[@id="xPath"]  形式内容
        return '//*[@id=\"' + element.id + '\"]';
    }
    //这里需要需要主要字符串转译问题，可参考js 动态生成html时字符串和变量转译（注意引号的作用）
    if (element == document.body) { //递归到body处，结束递归
        return '/html/' + element.tagName.toLowerCase();
    }
    var ix = 1, //在nodelist中的位置，且每次点击初始化
        siblings = element.parentNode.childNodes; //同级的子元素

    for (var i = 0, l = siblings.length; i < l; i++) {
        var sibling = siblings[i];
        //如果这个元素是siblings数组中的元素，则执行递归操作
        if (sibling == element) {
            return arguments.callee(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (ix) + ']';
            //如果不符合，判断是否是element元素，并且是否是相同元素，如果是相同的就开始累加
        } else if (sibling.nodeType == 1 && sibling.tagName == element.tagName) {
            ix++;
        }
    }
};

function xpathtocsspath(xpath) {
    // 开始转换 xpath 为 css path  
    // 转换 // 为 " "  
    xpath = xpath.replace(/\/\//g, " ");
    // 转换 / 为 >  
    xpath = xpath.replace(/\//g, ">");
    // 转换 [elem] 为 :eq(elem) ： 规则 -1  
    xpath = xpath.replace(/\[([^@].*?)\]/ig, function(matchStr, xPathIndex) {
        var cssPathIndex = parseInt(xPathIndex) - 1;
        return ":eq(" + cssPathIndex + ")";
    });
    // 1.2 版本后需要删除@  
    xpath = xpath.replace(/\@/g, "");
    // 去掉第一个 >  
    xpath = xpath.substr(1);
    // alert(xpath);  
    // 返回jQuery元素  
    return xpath;
};
$(document).dblclick(function(e) {

    if (flag) {
        var csspath = xpathtocsspath(readXPath(e.target));
        console.log(csspath);
        if ($("input[name='" + csspath + "']").length == 0) {

            $(csspath).css({
                "box-shadow": "0 0 0 1px red"
            });
            var html = "";
            html = html + "<div>";
            html = html + "<input style='display:block;'  data-json='Y' type='text' name='" + csspath + "' value='" + $(csspath).text() + "' class='text ui-widget-content ui-corner-all'><input type='button' ids='yes' value='删除'/></div>";
            $('#fieldset').append(html);
            $('[ids="yes"]').click(function() {
                $($(this).parent().find("input[type='text']").attr('name')).css({
                    "box-shadow": "none"
                });
                $(this).parent().remove();
            });

        } else {

            $(csspath).css({
                "box-shadow": "none"
            });
        }


    }
});

chrome.extension.onMessage.addListener(
    function(request, sender, sendResponse) {

        if (request.message == "calculate") {

console.log(request.pageconf.listSelector);
            var listurls = new Array();

            $(request.pageconf.listSelector).each(function(i, v) {
                listurls.push(fullurl($(v).attr('href')));
            });
            chrome.extension.sendRequest({
                    type: "save",
                    urls: listurls
                },
                function(response) {});
            // for(var i=0;i<len;i++){
            // $('.tracking-ad a')[i].click();

            // sleep(5000);
            // }

        } else if (request.message == "fetchDetail") {
            var confdata = '';
            console.log(request.fieldsconf);
            for (var i = 0; i < request.fieldsconf.length; i++) {
                confdata = confdata + $(request.fieldsconf[i]).text().trim() + '{{{{{}}}}}';
            }
            confdata = confdata.substring(0, confdata.lastIndexOf('{{{{{}}}}}'));
            sendResponse(confdata);
        } else if (request.message == 'nextpage') {

            for (var i = 0; i < $(request.nextSelector[0]).length; i++) {
                if ($($(request.nextSelector[0])[i]).text() == request.nextSelector[1]) {
                    $(request.nextSelector[0])[i].click();
                }
            }



        } else if (request.message == 'conflistpage') {
			
            console.log(localStorage.userid);
            parsealist(function(regex, nextPage) {
                loadding('列表页分析成功');
                if (!nextPage.hasNextConf) {
                    alert("分页标记获取失败,你可以在配置页面完成后，再手动修改!");
                }
                var index = Math.floor(Math.random() * $(regex).length);
                var contentregex = $(regex)[index];
                var url = $(contentregex).attr('href');
                var regexarr = new Array();

                for (var i = 0; i < $(regex).length; i++) {
                    regexarr.push($($(regex)[i]).text());
                }
                setTimeout(function() {
                    $.unblockUI();
                }, 2000);
                getDataFromBg({
                    type: 'confcontentpage',
                    url: fullurl(url),
                    regex: regex,
                    regexarr: regexarr,
                    targeurl: window.location.href,
                    nextPage: nextPage,
                    title: $('title').text(),
					userid:localStorage.userid
                }, function(resp) {

                });
            });

        } else if (request.message == 'confcontentpage') {
            $('a').each(function(i, v) {
                $(v).removeAttr('href');
                $(v).unbind("click");
            });
            //parsefiledselector();
            createDiv();
            openconfdilog();
            flag = true;
            $('#dialog-form').unbind('mouseover').unbind('mouseout');
            $("div[role='dialog']").css({
                "z-index": "10000"
            });
        }

    });

function parsefiledselector() {
    $('body').find('*').each(function(i, v) {
        if ($(v).is(":hidden")) {
            $(v).remove();
        }
    });
    var content = $('html').html();
    var screenWidth = $(document.body).width();
    var screenHeight = $(document.body).height();
    var data = {
        type: "getContentList",
        content: content
    };
    getDataFromBg(data, function(respData) {
        $.each(JSON.parse(respData), function(i, v) {
            $.each(v, function(vi, vv) {
                var data = new Object();
                if ($($(vv)[0].selector).is(":visible")) {
                    var X = $($(vv)[0].selector).offset().left;
                    var Y = $($(vv)[0].selector).offset().top;
                    var X1 = $($(vv)[0].selector).offset().left + $($(vv)[0].selector).width();
                    var Y1 = $($(vv)[0].selector).offset().top + $($(vv)[0].selector).height();
                    data.X = X;
                    data.Y = Y;
                    data.width = $($(vv)[0].selector).width();
                    data.width = $($(vv)[0].selector).height();
                    data.text = $(vv)[0].text;
                    data.selector = $(vv)[0].selector;
                    data.setborder = false;
                    arr.push(data);

                }

            });

        });
    });
}

function fullurl(url) {
    String.prototype.startWith = function(str) {
        var reg = new RegExp("^" + str);
        return reg.test(this);
    }
    var protocol = window.location.protocol;
    var host = window.location.host;
    if (url.startWith('//')) {
        url = protocol + url;
    } else if (url.startWith('/')) {
        url = protocol + '//' + host + url;
    }
    console.log(url);
    return url;
}

function parsealist(callback) {
    loadding('正在分析网页...');
    $('body').scrollTop($('body')[0].scrollHeight);
    var content = $('html').html();
    var data = {
        type: "getHtmlList",
        content: content
    };
    var textVal = "";
    var screenWidth = $(document.body).width() / 2;
    var screenHeight = $(document.body).height() / 2;
    getDataFromBg(data, function(respData) {
        var size = 0;

        console.log(JSON.parse(respData).nextPage);
        var regex = getlistregex(JSON.parse(respData).map);
        $(regex).css({
            "box-shadow": "0 0 0 1px red"
        });
        callback(regex, JSON.parse(respData).nextPage);
    });
}

function sleep(n) { // n表示的毫秒数
    var start = new Date().getTime();
    while (true)
        if (new Date().getTime() - start > n) break;
}

function getlistregex(data) {
    var screenWidth = $(document.body).width() / 2;
    var screenHeight = $(document.body).height() / 2;
    var pr = 0;
    var regex = '';
    for (key in data) {
 try{  
     if (screenHeight > $(data[key][0].selector).offset().top && screenHeight < $(data[key][data[key].length - 1].selector).offset().top) {
            var fontsize = $(data[key][0].regex).css('font-size').replace('px', '');
            fontsize = fontsize - 6;
            var size = data[key][0].wordAvgNum;
            if (fontsize * size > pr) {
                pr = fontsize * size;
                regex = data[key][0].regex;
            }
        }
  }catch(err){  
   continue;
  }  
       

    }
    return regex;

}

function getDataFromBg(data, callback) {
        chrome.extension.sendRequest(
            data
        , function(respData) {
            callback(respData);

        });
    }
    // 接收background消息
chrome.extension.onRequest.addListener(function(request, sender, sendResponse) {


    // $('#name').val(request.selectionText);
});
// var data=JSON.parse(respData);
// $.each(data,function(i,v){
// $.each(v,function(vi,vv){
// $($(vv)[0].regex).css({background:'yellow'});
// alert($(vv)[0].regex);
// });});
// $(data.list[0].regex).css({background:'yellow'});
// $(data.list[0].regex).each(function(i,v){
// var X = $(v).width();
// var Y = $(v).height();
// $(v).css({background:'yellow'});
// });
// alert(JSON.stringify(respData.list));

function createDiv() {
    var html = "<div id='dialog-form' title='设置采集字段' style='border:1px solid #888;' >";
    // html=html+"<p class='validateTips'>All form fields are
    // required.</p>";
    html = html + "<form>";
    html = html + "<fieldset id='fieldset'>";

    html = html + "</fieldset>";
    html = html + "</form>";
    html = html + "</div>";
    $('#dialog-form').remove();
    $('body').append(html);
    $('#dialog-form').trigger("create");
}

function openconfdilog() {
    var dialog, form,
        emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
        name = $("#name"),
        email = $("#email"),
        password = $("#password"),
        allFields = $([]).add(name).add(email).add(password),
        tips = $(".validateTips");

    function updateTips(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");
        setTimeout(function() {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function checkLength(o, n, min, max) {
        if (o.val().length > max || o.val().length < min) {
            o.addClass("ui-state-error");
            updateTips("Length of " + n + " must be between " +
                min + " and " + max + ".");
            return false;
        } else {
            return true;
        }
    }

    function checkRegexp(o, regexp, n) {
        if (!(regexp.test(o.val()))) {
            o.addClass("ui-state-error");
            updateTips(n);
            return false;
        } else {
            return true;
        }
    }

    function completeconf() {
		//$("#dialog-form").dialog("close");
        var fieldsconf = new Array();
        $('#fieldset').find("input[type='text']").each(function(i, v) {
            var o = new Object();
            o.selector = $(v).attr('name');
            o.text = $(v).val();
            fieldsconf.push(o);
        });
        if (fieldsconf.length == 0) {
            getDataFromBg({
                type: 'closeconf'
            }, function(resp) {

            });
        } else {
            getDataFromBg({
                type: 'completeconf',
                feildsconf: fieldsconf
            }, function(resp) {
			
            });
        }

    }

    dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: false,
        buttons: {
            "完成": completeconf,
            "取消": function() {
                $(this).dialog("close");
                getDataFromBg({
                    type: 'closeconf'
                }, function(resp) {

                });
            }
        },
        close: function() {
            form[0].reset();
            allFields.removeClass("ui-state-error");
        },
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close", $(this).parent()).hide();
        }
    });

    form = dialog.find("form").on("submit", function(event) {
        event.preventDefault();
        addUser();
    });

    // $( "#create-user" ).button().on( "click", function() {
    dialog.dialog("open");
    // });
}

function loadding(msg) {
    var imgURL = chrome.extension.getURL("busy.gif");
    $.blockUI({
        message: '<h1 style="font-size:18px;"><img width=40px src="' + imgURL + '" />' + msg + '</h1>'
    });
}

// $(regex).each(function(i,v){
// console.log(regex);
// getDataFromBg({type:'parseContent',url:$(v).attr('href')},function(resp){

// });
// });
// for(var i=0;i<$(regex).length;i++){
// $(regex)[i].click();
// sleep(20000);
// }