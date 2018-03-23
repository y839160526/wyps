var conf = new Object();
//var serverurl = 'http://www.wangye.io/';
var serverurl = 'http://localhost:8080/hams/';
var requestType = '';
var contentTabId = '';
var listtabid = '';
var conflisttabid = '';
var confcontenttabid = '';
var managertabid = '';
var managertaburl = '';
var currentwinid = '';
var stopflag = false;
var tabids = new Array();
var confid;
var targeturl = 'http://blog.csdn.net/web/newarticle.html?&page=8';
var pageconf = new Object();

chrome.browserAction.onClicked.addListener(function() {
    chrome.windows.getCurrent(function(win) {
        chrome.tabs.getSelected(win.id, function(tab) {
            chrome.tabs.sendMessage(tab.id, {
                message: 'conflistpage'
            }, function(response) {
                conflisttabid = win.id;
            });
            console.log(tab.url);
        });
    });
});

function parsepagelist(tabid) {
    var msg = "calculate";

    chrome.tabs.sendMessage(tabid, {
        message: msg
    }, function(response) {

    });

}

// +++++++++++++++++++加载页面+++++++++++++++++++++++
chrome.tabs.onUpdated.addListener(function(tabId, changeInfo, tab) {
    if (changeInfo.status == "loading" && tabids.indexOf(tab.id) != -1) {
       
        chrome.tabs.sendMessage(tabId, {
            message: 'fetchDetail',
            fieldsconf: pageconf.fieldsSelector.split('{{{{{}}}}}')
        }, function(response) {
           
            $.ajax({
                url: serverurl + "savePageConfData.htm",
                type: 'post',
                datatype: 'json',
                data: {
                    "content": response,
                    "confId": pageconf.id
                },
                timeout: 100000,
                success: function(data, status) {

                    
                },
                fail: function(err, status) {
                    //console.log(err)
                }
            })


            tabids.splice(tabids.indexOf(tab.id), 1);
            chrome.tabs.remove(tabId);
        });

    } else if (changeInfo.status == "complete" && tabId == listtabid) {
        chrome.tabs.sendMessage(tabId, {
            message: 'calculate',
            pageconf: pageconf
        }, function(response) {

        });
    } else if (changeInfo.status == "complete" && tabId == conflisttabid) {

        chrome.tabs.sendMessage(tabId, {
            message: 'conflistpage'
        }, function(response) {

        });
    } else if (changeInfo.status == "complete" && tabId == confcontenttabid) {
        chrome.tabs.sendMessage(tabId, {
            message: 'confcontentpage'
        }, function(response) {

        });
    }

});
function parselistcontent(targeturl,pageconf){
	$.get(targeturl, function(data){
		$.ajax({
            url: serverurl + "parseUrlsFromContent.htm",
            type: 'post',
            datatype: 'text',
            data: {
                "content": data,"listSelector":pageconf.listSelector,"confId":pageconf.id,"nextSelector":pageconf.nextSelector,"targeturl":targeturl
            },
            timeout: 100000,
            success: function(data, status) {
				data=JSON.parse(data);
                var nexturl=data.nexturl;
				var urls=data.urls;
				$.each(urls,function(i,v){
					$.get(v, function(data1){
						parsefieldscontent(pageconf,data1);
					});
				});
				if(nexturl!=''){
					parselistcontent(nexturl,pageconf)
				}
            },
            fail: function(err, status) {
                
            }
        });
	});
	
}
function parsefieldscontent(pageconf,content){
	$.ajax({
            url: serverurl + "parseFieldsFromContent.htm",
            type: 'post',
            datatype: 'text',
            data: {
                "content": content,"fieldsSelector":pageconf.fieldsSelector,"confId":pageconf.id
            },
            timeout: 100000,
            success: function(data, status) {
                
            },
            fail: function(err, status) {
                
            }
        });
}

// 接收content js消息
chrome.extension.onRequest.addListener(function(request, sender, callback) {
    if (request.type == "save") {
        chrome.windows.getCurrent(function(win) {
            chrome.tabs.getAllInWindow(win.id, function(tabs) {
                currentwinid = win.id;

                opentab(request.urls);
            });
        });
    } else if (request.type == 'startcollect') {
        stopflag = false;
        managertabid = sender.tab.id;
        managertaburl = sender.tab.url;

        $.ajax({
            url: serverurl + "getConfbyId.htm",
            type: 'get',
            datatype: 'json',
            data: {
                "content": request.confid
            },
            timeout: 100000,
            success: function(data, status) {
                pageconf = JSON.parse(data).pageConf;
				if(pageconf.type=='1'){
					parselistcontent(pageconf.targetUrl,pageconf);
				}else{
					chrome.tabs.create({
                    url: pageconf.targetUrl,
                    selected: false
                }, function(tab) {
                    listtabid = tab.id;
                });
                callback(data);
                //console.log(data)
				}
                
            },
            fail: function(err, status) {
                //console.log(err)
            }
        })
    } else if (request.type == 'stopcollect') {
        stopflag = true;
    } else if (request.type == 'nextpageend') {
        chrome.tabs.remove(listtabid);
        chrome.tabs.update(managertabid, {
            url: managertaburl,
            highlighted: true
        });
    } else if (request.type == 'confpageprocess') {
        //console.log('_____'+request.data.confid);
        confid = request.confid;
        chrome.tabs.create({
            url: request.url
        }, function(tab) {
            conflisttabid = tab.id;
            callback(conflisttabid);
        });
    } else if (request.type == "getHtmlList") {
        //console.log(request.data.content);
        $.ajax({
            url: serverurl + "getHtmlList.htm",
            type: 'post',
            datatype: 'text',
            data: {
                "content": request.content
            },
            timeout: 100000,
            success: function(data, status) {
                callback(data);
                //console.log(data)
            },
            fail: function(err, status) {
                //console.log(err)
            }
        })

    } else if (request.type == "confcontentpage") {
        console.log(request.userid);
		conf.userid=request.userid;
        conf.title = request.title;
        conf.regex = request.regex;
        conf.regexarr = request.regexarr;
        conf.targeurl = request.targeurl;
        conf.nextPage = request.nextPage;
        chrome.tabs.create({
            url: request.url
        }, function(tab) {
            console.log(conflisttabid);
			 try{  
            if (conflisttabid != '') {
                chrome.tabs.remove(conflisttabid);
               
            }
             
        }catch(err){  
        } 
		    confcontenttabid = tab.id;
            callback(confcontenttabid);
        });

    } else if (request.type == "getContentList") {
        //console.log(request.data.content);
        $.ajax({
            url: serverurl + "getContentList.htm",
            type: 'post',
            datatype: 'text',
            data: {
                "content": request.content
            },
            timeout: 100000,
            success: function(data, status) {
                callback(data);
                //console.log(data)
            },
            fail: function(err, status) {
                //console.log(err)
            }
        })
    } else if (request.type == "completeconf") {
        console.log(conf);
        conf.fieldsconf = request.feildsconf;

        var fieldsSelector = '';
        var fieldsName = '';
        var listName = '';
        for (var i = 0; i < conf.fieldsconf.length; i++) {
            fieldsSelector += conf.fieldsconf[i].selector + '{{{{{}}}}}';
        }
        for (var i = 0; i < conf.fieldsconf.length; i++) {
            fieldsName += conf.fieldsconf[i].text + '{{{{{}}}}}';

        }

        for (var i = 0; i < conf.regexarr.length; i++) {
            listName += conf.regexarr[i] + '{{{{{}}}}}';
        }
        fieldsSelector = fieldsSelector.substring(0, fieldsSelector.lastIndexOf('{{{{{}}}}}'));
        fieldsName = fieldsName.substring(0, fieldsName.lastIndexOf('{{{{{}}}}}'));
        listName = listName.substring(0, listName.lastIndexOf('{{{{{}}}}}'));

        var confdef = new Object;
        confdef.id = confid;
        confdef.title = conf.title;
        //console.log(conf.title);
        confdef.userId = conf.userid;
        confdef.targetUrl = conf.targeurl;
        confdef.listSelector = conf.regex;
        confdef.listName = listName;
        confdef.fieldsSelector = fieldsSelector;
        confdef.fieldsName = fieldsName;
        confdef.nextSelector = conf.nextPage.nextStep + '{{{{{}}}}}' + conf.nextPage.nextPageType;
        confdef.waitTime = 0;
        confdef.createtime = '';
        confdef.updatetime = '';
        console.log(confdef);

        $.ajax({
            url: serverurl + "saveConf.htm",
            type: 'post',
            datatype: 'json',
            data: {
                "content": JSON.stringify(confdef)
            },
            timeout: 100000,
            success: function(data, status) {
				var resp=JSON.parse(data);
				confid='';
				 if(resp.status=="fail"){ 
			alert('请先登录！');
			 chrome.tabs.create({
                url: 'http://localhost:8080/hams/login.jsp',
                selected: true
            }, function(tab) {
               
            });

			
} else{
	chrome.tabs.create({
                url: 'http://localhost:8080/hams/pageConf.htm?id='+resp.confId,
                selected: true
            }, function(tab) {
               
            });

}
                callback(JSON.parse(data));
                //console.log(data)
            },
            fail: function(err, status) {
                //console.log(err)
            }
        })
        chrome.tabs.remove(confcontenttabid);
    }else if(request.type=='closeconf'){
		confid='';
		chrome.tabs.remove(sender.tab.id);
		chrome.tabs.remove(confcontenttabid);
	}
});

function opentab(urls) {
    if (stopflag) {
        chrome.tabs.remove(listtabid);
        chrome.tabs.update(managertabid, {
            url: managertaburl,
            highlighted: true
        });
        return;
    }
    if (urls.length > 0) {
        chrome.tabs.getAllInWindow(currentwinid, function(tabs) {
            sleep(1000 * tabs.length / 5);

            var currentUrl = urls.shift();
            chrome.tabs.create({
                url: currentUrl,
                selected: false
            }, function(tab) {
                tabids.push(tab.id);
                opentab(urls);
            });

        });

    } else {
        chrome.tabs.sendMessage(listtabid, {
            message: 'nextpage',
            nextSelector: pageconf.nextSelector.split('{{{{{}}}}}')
        }, function(response) {

        });
    }
}

function genericOnClick(info, tab) {
    alert(info.linkUrl);
}

function selectionOnClick(info, tab) {
        // 发送给contentjs
        chrome.tabs.sendRequest(tab.id, {
            selectionText: info.selectionText
        }, function(data) {});
    }
   
function sleep(n) { // n表示的毫秒数
    var start = new Date().getTime();
    while (true)
        if (new Date().getTime() - start > n)
            break;
}