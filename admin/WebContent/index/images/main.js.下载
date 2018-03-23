/* 
 name : yanggang;
 QQ:392017299;
 E-mail:yanggang1@conew.com;
 */
var testStyle=document.createElement('div').style,
camelCase=function(str){
    return str.replace(/^-ms-/, "ms-").replace(/-([a-z]|[0-9])/ig, function(all, letter){
        return (letter+"").toUpperCase();
    });
},
//获取css3前缀
cssVendor=(function(){
    var ts=testStyle,
        cases=['-o-','-webkit-','-moz-','-ms-',''],i=0;
    do {
        if(camelCase(cases[i]+'transform') in ts){
            return cases[i];
        }
    } while(++i<cases.length);
    
    return '';
})(),
transitionend=(function(){
    return ({
        '-o-':'otransitionend',
        '-webkit-':'webkitTransitionEnd',
        '-moz-':'transitionend',
        '-ms-':'MSTransitionEnd transitionend',
        '':'transitionend'
    })[cssVendor];
})(),
//检测是否支持css属性
isCSS=function(property){
    var ts=testStyle,
        name=camelCase(property),
        _name=camelCase(cssVendor+property);
    return (name in ts) && name || (_name in ts) && _name || '';
}
var jsLiebao = {
    liebaoSta: function() {
        var setTimeObj,
            iDown = $('#iPhonebate'),
            aDown = $('#Androidbate'),
            wDown = $('#wzbate'),
            logIDown = $('#logIphone'),
            logADown = $('#logAndroid');
        /* 首页iphone跳转地址 */
        iDown.click(function(e) {
            e.preventDefault();
            try {
                _hmt.push(['jslbEvent', 'js-liebao', 'click', 'iPhonebate', '1']);
            } catch (e) {}
            setTimeObj = setTimeout(function() {
                window.open('https://itunes.apple.com/cn/app/id641522896');
                return false;
            },200)
        });
        /* 首页急速版下载地址 */
        aDown.click(function(e) {
            var ua = navigator.userAgent.toLowerCase();
            // 判断是否是微信打开
            if(ua.match(/MicroMessenger/i)=="micromessenger"){
                $('#wxTips').show();
                return false;
            }else{
                e.preventDefault();
                try {
                    _hmt.push(['jslbEvent', 'js-liebao', 'click', 'Androidbate', '2']);
                } catch (e) {}
                setTimeObj = setTimeout(function() {
                    window.location.href = 'http://dl.liebao.cn/android/fast/cheetah_2.33.2.apk';
                    return false;
                },200)  
            }
        });
        /* 首页完整版下载地址 */
        wDown.click(function(e) {
            e.preventDefault();
            try {
                _hmt.push(['jslbEvent', 'js-liebao', 'click', 'wzbate', '3']);
            } catch (e) {}
            setTimeObj = setTimeout(function() {
                window.location.href = 'http://dl.liebao.cn/android/lb/kbrowser_3.9.7.apk';
                return false;
            },200)
        });
        /* 更新日志页iphone跳转地址 */
        logIDown.click(function(e) {
            e.preventDefault();
            try {
                _hmt.push(['jslbEvent', 'js-liebao', 'click', 'logiPhone', '4']);
            } catch (e) {}
            setTimeObj = setTimeout(function() {
                window.open('https://itunes.apple.com/cn/app/id641522896');
                return false;
            },200)
        });
        /* 更新日志页急速版下载地址 */
        logADown.click(function(e) {
            e.preventDefault();
            try {
                _hmt.push(['jslbEvent', 'js-liebao', 'click', 'logAndroid', '5']);
            } catch (e) {}
            setTimeObj = setTimeout(function() {
                window.location.href = 'http://dl.liebao.cn/android/fast/cheetah_2.33.2.apk';
                return false;
            },200)
        });
    },
    windowHeight: function() {
        var winHeight = $(window).height(),
            autoBan = $('.banner'),
            maxIcon = $('.max-icon'),
            proInfo = $('.pro-info'),
            androidBtn = $('.android-btn'),
            iphoneBtn = $('.iphone-btn'),
            topCode = $('.code'),
            batei = $('.bate-i'),
            batea = $('.bate-a'),
            elHtml = $('html');
        if (elHtml.hasClass('ie')) {
            if (winHeight < 780 && winHeight > 730) { autoBan.css({ height: '810px'});
                maxIcon.css({ width: '378px', height: '292px', top: '130px', marginLeft: '-189px'});
                proInfo.css({ top: '420px' });
            } else if (winHeight < 729 && winHeight > 680) {
                autoBan.css({ height: '780px' });
                maxIcon.css({ width: '338px', height: '261px', top: '120px', marginLeft: '-169px'});
                proInfo.css({ top: '390px' });
            } else if (winHeight < 679 && winHeight > 630) {
                autoBan.css({ height: '750px' });
                maxIcon.css({ width: '318px', height: '245px', top: '120px', marginLeft: '-159px' });
                proInfo.css({ top: '370px' });
                proInfo.find('h2').css({ fontSize: '64px', textAlign: 'right' });
                androidBtn.css({ top: '170px' });
                iphoneBtn.css({ top: '170px' });
                topCode.css({ top: '162px' });
                batei.css({ top: '252px' });
                batea.css({ top: '252px' });
            } else if (winHeight < 629 && winHeight > 550) {
                autoBan.css({ height: '640px' });
                maxIcon.css({ width: '260px', height: '201px', top: '100px', marginLeft: '-130px' });
                proInfo.css({ top: '310px' });
                proInfo.find('h2').css({ fontSize: '54px', textAlign: 'right' });
                proInfo.find('h4').css({ fontSize: '24px', textAlign: 'right' });
                androidBtn.css({ top: '150px' });
                iphoneBtn.css({ top: '150px' });
                topCode.css({ top: '142px' });
                batei.css({ top: '232px' });
                batea.css({ top: '232px' });
            } else {
                return false;
            }
        }
    },
    lbAnimate: function() {
        $(window).scroll(function() {
            var sTop = $(document).scrollTop();
            if (sTop > 1) {
                jsLiebao.headerAddClass();
            } else {
                jsLiebao.headerRemoveClass();
            }
        });
        if ($(document).scrollTop() > 1) {
            jsLiebao.headerAddClass();
        } else {
            jsLiebao.headerRemoveClass();
        }
    },
    headerAddClass: function() {
        $('.header').addClass('fixed');
        $('.nav').addClass('scrollb');
        $('.nav-link').addClass('scrollc');
        $('.logo').addClass('scrolld');
    },
    headerRemoveClass: function() {
        $('.header').removeClass('fixed');
        $('.nav').removeClass('scrollb');
        $('.nav-link').removeClass('scrollc');
        $('.logo').removeClass('scrolld');
    }
};
// LazyLoad
(function(ROOT, NS, Struct, undefined) {
    "use strict";
    var DOC = ROOT.document,
        getOffset = function(elem) {
            var top = 0,
                left = 0,
                offset;
            if ("getBoundingClientRect" in elem) {
                offset = elem.getBoundingClientRect();
                top = offset.top + WST;
                left = offset.left + WSL;
            } else { //maybe need ???
                top += elem.scrollTop || 0;
                left += elem.scrollLeft || 0;
                while (elem) {
                    left += elem.offsetLeft || 0;
                    top += elem.offsetTop || 0;
                    elem = elem.offsetParent;
                }
            }
            return {
                top: top,
                left: left
            };
        },
        addEvent = function(elem) {
            try {
                var win = ROOT,
                    resize = Data(elem, 'resize');
                if (win.addEventListener) {
                    win.addEventListener('resize', resize, false);
                    elem.addEventListener('scroll', resize, false);
                } else {
                    win.attachEvent('onresize', resize);
                    elem.attachEvent('onscroll', resize);
                }
            } catch (e) {
                return false;
            }
            return true;
        },
        removeEvent = function(elem) {
            try {
                var win = ROOT,
                    resize = Data(elem, 'resize');
                if (win.removeEventListener) {
                    win.removeEventListener('resize', resize, false);
                    elem.removeEventListener('scroll', resize, false);
                } else {
                    win.detachEvent('onresize', resize);
                    elem.detachEvent('onscroll', resize);
                }
            } catch (e) {
                return false;
            }
            return true;
        },
        Data = function(elem, key, value) {
            if (key == null) {
                return elem.lazyData || (elem.lazyData = {
                    ret: [],
                    bind: null,
                    timer: null,
                    tick: function() {
                        var i = 0,
                            win = elem,
                            data = Data(win),
                            doc = DOC.documentElement,
                            body = DOC.body,
                            isWin = win != null && win == win.window;
                        /* tick由浏览器resize或者scroll时触发，所以此刻更新相关数值 */
                        data.WST = (isWin ? win.pageYOffset || doc && doc.scrollTop || body.scrollTop : getOffset(win).top) || 0;
                        data.WSL = (isWin ? win.pageXOffset || doc && doc.scrollLeft || body.scrollLeft : getOffset(win).left) || 0;
                        data.WH = (isWin ? win.innerHeight || doc && doc.clientHeight || body.clientHeight : win.clientHeight) || 0;
                        data.WW = (isWin ? win.innerWidth || doc && doc.clientWidth || body.clientWidth : win.clientWidth) || 0;
                        if (isWin) {
                            WST = data.WST;
                            WSL = data.WSL;
                        }
                        while (i < data.ret.length) {
                            data.ret[i].length ? data.ret[i++].check() : delete data.ret.splice(i, 1)[0].checking;
                        }!data.ret.length && (data.bind = !removeEvent(win)); //队列为空则取消事件绑定
                    },
                    resize: function() {
                        var data = Data(elem);
                        clearTimeout(data.timer);
                        data.timer = setTimeout(data.tick, 100);
                    }
                });
            }
            if (value == null) {
                return Data(elem)[key];
            }
            return Data(elem)[key] = value;
        },
        isHidden = function(elem) {
            var doc = DOC.documentElement;
            return (elem.currentStyle || getComputedStyle(elem, null) || elem.style).display == 'none' || doc !== elem && !(
                doc.contains ? doc.contains(elem) : doc.compareDocumentPosition && doc.compareDocumentPosition(b) & 16
            );
        },
        WST = 0,
        WSL = 0;
    Struct.fn = Struct.prototype = {
        constructor: Struct,
        length: 0,
        splice: [].splice,
        dcb: function() { //默认回调函数
            var orig = this.getAttribute('data-original');
            if (orig) this.src = orig;
        },
        init: function(elem, cfg) {
            var func, container, range,
                type = typeof cfg;
            if (type == 'function') {
                func = cfg;
            } else if (type == 'object') {
                func = cfg.callback;
                container = cfg.container;
                range = parseFloat(cfg.range);
                if (this.isArrayLike(container)) {
                    container = container[0];
                }
                if (typeof container == 'string') {
                    container = DOC.getElementById(container);
                }
                if (container == null || container.nodeType != 1 || container.nodeName.toLowerCase() == 'body' || container.nodeName.toLowerCase() == 'html') {
                    container = ROOT;
                }
            }
            this.cb = func || this.dcb;
            this.range = range || 0;
            this.container = container || ROOT;
            return this.push(elem);
        },
        push: function(elem) {
            if (typeof elem == 'string') {
                elem = DOC.getElementById(elem);
            }
            this.merge(elem);
            if (this.length) {
                var data = Data(this.container);
                if (!this.checking) {
                    data.ret.push(this);
                    this.checking = true;
                }
                data.resize();
                !data.bind && (data.bind = addEvent(this.container))
            }
            return this;
        },
        isArrayLike: function(elem) {
            var type = typeof elem;
            return !!elem && type != 'function' && type != 'string' && (elem.length === 0 || elem.length && (elem.length - 1) in elem);
        },
        merge: function(elem) {
            var i = this.length,
                j = 0,
                arr = this.isArrayLike(elem) ? elem : [elem];
            while (j < arr.length) {
                if (arr[j] && arr[j].nodeType == 1) //确保是DOM节点
                    this[i++] = arr[j];
                j++;
            }
            this.length = i;
            return this;
        },
        check: function() {
            var i = 0,
                range = this.range,
                data = Data(this.container),
                elem,
                offset;
            while (i < this.length) {
                elem = this[i];
                offset = getOffset(elem);
                if (!isHidden(elem) && offset.top + elem.offsetHeight + range >= data.WST && offset.top - range <= data.WST + data.WH && offset.left + elem.offsetWidth + range >= data.WSL && offset.left - range <= data.WSL + data.WW) {
                    this.cb.call(this.splice(i, 1)[0]);
                } else i++;
            }
            return this;
        }
    }
    Struct.fn.init.prototype = Struct.fn;
    return ROOT[NS] = Struct;
})(window, 'LazyLoad', function(elem, cfg) {
    return new arguments.callee.fn.init(elem, cfg);
});
function videoPlay(doma,domb){
    var videoCodea = '<video width="100%" controls="controls" autoplay="autoplay"><source src="images/android-lb.mp4" type="video/mp4"></source></video>';
        videoCodeb = '<video width="100%" controls="controls" autoplay="autoplay"><source src="images/ios-lb.mp4" type="video/mp4"></source></video>';
    doma.show().addClass('videoa');
    /*doma.show().addClass('videoa').html(videoCodea);*/
    domb.show().addClass('videoa');
    /*注释视频切换
    doma.click(function(){
        var myThis = $(this);
        domb.css({ zIndex: '99'}).find('video').remove();
        myThis.css({ zIndex: '111'}).html(videoCodea);
    });
    domb.click(function(){
        var myThis = $(this);
        doma.css({ zIndex: '99'}).find('video').remove();
        myThis.css({ zIndex: '111'}).html(videoCodeb);
    });
    */
}
function opacityObj(ele){
    ele.show().css({ opacity: '0' }).stop(true, true).animate({ opacity: '1' }, 800);
}
function offsetObj(ele,num){
    ele.show().css({left: num, opacity: '0'}).stop(true, true).animate({ left: '50%', opacity: '1'}, 800);
}
$(function() {
    var timesa = 800, loadz = 400,animation=isCSS('animation');
    var hideAnimation = $('.fda-text,.fdb-text,.faa-img,.fab-img,.bottom-code,.fcb-img,.fba-text,.fbb-text');
    hideAnimation.hide();
    $('.max-icon').css({ opacity: '0' }).stop(true, true).animate({ opacity: '1' }, 500);
    /* 适配屏幕高度 */
    jsLiebao.windowHeight();
    jsLiebao.liebaoSta();
    if($(window).width() > 767){
        jsLiebao.lbAnimate();
        // 第一块
        LazyLoad($('#fun-header'), {
            callback: function() {
                var  fontDom = $('.pro-info h2'), maxLogo = $('.max-icon');
                if (animation) {
                    fontDom.addClass('fonta');
                    maxLogo.show().addClass('max-logo');
                } else {
                    opacityObj(fontDom);
                }
            },
            range: -loadz
        });
        // 第二块
        LazyLoad($('#funa'), {
            callback: function() {
                var timeOut, faaDom = $('.faa-img'), fabDom = $('.fab-img'), rippleBox = $('.ripple-box'), rippleDiv = $('.ripple');
                if (animation) {
                    faaDom.show().addClass('ym-animate');
                    timeOut = setTimeout(function(){
                        rippleBox.show();
                        for(var i=0; i<rippleDiv.length; i++){
                            rippleDiv.eq(i).css(animation,'rippleAni 5s ease '+ i +'s normal none infinite');
                        }
                    },1000)
                } else {
                    rippleBox.hide();
                    opacityObj(faaDom);
                    opacityObj(fabDom);
                }
            },
            range: -loadz
        });
        // 第三块
        LazyLoad($('#funb'), {
            callback: function() {
                var fbDom = $('.fba-text,.fbb-text'), rockMain = $('.rock'), yunEle = $('.yun');
                if (animation) {
                    fbDom.show().addClass('ks-animate');
                    rockMain.addClass('fba-rock');
                    yunEle.addClass('fbb-yun');
                } else {
                    offsetObj(fbDom,'55%');
                }
            },
            range: -loadz
        });
        // 第四块
        LazyLoad($('#func'), {
            callback: function() {
                var setTimeb, fcaDom = $('.fcb-img'), videoIcon = $('.fce-img');
                if (animation) {
                    fcaDom.show().addClass('revolve');
                    setTimeb = setTimeout(function(){
                        videoIcon.show().addClass('video-icon');
                    },500)
                } else {
                    opacityObj(fcaDom);
                    opacityObj(videoIcon);
                }
            },
            range: -loadz
        });
        // 第五块
        LazyLoad($('#fund'), {
            callback: function() {
                var fcDom = $('.fda-text,.fdb-text'),fbCode = $('.bottom-code'), phA = $('.ph-a'), phI = $('.ph-i');
                if (animation) {
                    fcDom.show().addClass('ks-animate');
                    fbCode.show().addClass('ks-animate');
                    videoPlay(phA,phI);
                } else {
                    offsetObj(fcDom,'55%');
                    opacityObj(fbCode);
                    opacityObj(phA);
                    opacityObj(phI);
                }
            },
            range: -loadz
        });
        // 第六块
        LazyLoad($('#fune'), {
            callback: function() {
                var feaDom = $('.fea-img'),febDom = $('.feb-img'), timeoutA = null;
                clearTimeout(timeoutA);
                if (animation) {
                    feaDom.addClass('scalea');
                    febDom.addClass('scalea');
                    timeoutA = setTimeout(function(){
                        feaDom.addClass('fea-animation');
                    },500)
                    feaDom.show();
                    febDom.show();
                } else {
                    opacityObj(feaDom);
                    opacityObj(febDom);          
                }
            },
            range: -loadz
        });
    }else{
        hideAnimation.show();
    }
});