function trim(str) {
    // var regExp=/^\s*(.*?)\s*$/;
    // return str.replace(regExp, "$1");
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

// 对字符串增加方法
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

Array.prototype.remove = function (dx) {
    if (isNaN(dx) || dx > this.length) {
        return false;
    }
    for (var i = 0, n = 0; i < this.length; i++) {
        if (this[i] != this[dx]) {
            this[n++] = this[i]
        }
    }
    this.length -= 1
}

Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
}
//根据值移除数组中的元素  王晓超 2018年9月3日16:32:41
Array.prototype.ngtRemove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
}


function isInt(val) {

    var pattern = /^[1-9]\d*|0$/; // 匹配非负整数
    if (!pattern.test(val)) {
        return true;
    }
    return false;
}

function isfloat(oNum) {

    if (!oNum) return false;

    var strP = /^\d+(\.\d+)?$/;

    if (!strP.test(oNum)) return false;

    try {

        if (parseFloat(oNum) != oNum) return false;

    } catch (ex) {

        return false;

    }

    return true;

}

/*
 * 过滤特殊字符
 */

function filterStr(str) {
    var filterString = "'~!@#$%^&*()-+.";
    var ch;
    var i;
    var temp;
    var error = false;// 当包含非法字符时，返回True
    for (i = 0; i <= (filterString.length - 1); i++) {
        ch = filterString.charAt(i);
        temp = str.indexOf(ch);
        if (temp != -1) {
            error = true;
            break;
        }
    }
    return error;

}


/*
 * 是否为数字
 */
function isNum(str) {
    var re = /^[\d]+$/
    return re.test(str);

}

function hiddenLongText(text, lengthPara) {
    var displayLen = 30;
    var stext;
    if (null != lengthPara && undefined != lengthPara) {

        displayLen = lengthPara;

    }
    if ("" == text || null == text) {
        stext = "";
    }

    if (text.length > lengthPara) {
        stext = text.substring(0, displayLen) + "...";
    } else {
        stext = text;
    }
    return stext;
}


function addCookie(name, value, expiresHours) {
    var cookieString = name + "=" + escape(value) + ";domain=" + GetCookieDomain() + ";path=/";
    //判断是否设置过期时间 
    if (expiresHours > 0) {
        var date = new Date();
        date.setTime(date.getTime + expiresHours * 3600 * 1000);
        cookieString = cookieString + ";expires=" + date.toGMTString() + ";domain=" + GetCookieDomain() + ";path=/";
    }
    document.cookie = cookieString;
}

function GetCookieDomain() {
    var host = location.hostname;
    var ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    if (ip.test(host) == true || host == 'localhost') return host;
    var regex
        = /([^]*).*/;
    var match = host.match(regex);
    if (typeof match !=
        "undefined" && null != match) host = match[1];
    if (typeof host !=
        "undefined" && null != host) {
        var strAry = host.split(".");
        if
        (strAry.length > 1) {
            host = strAry[strAry.length - 2] + "." +
                strAry[strAry.length - 1];
        }
    }
    return '.' + host;
}

function getCookie(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name) return arr[1];
    }
    return "";
}

function deleteCookie(name) {
    var date = new Date();
    date.setTime(date.getTime() - 1);
    //document.cookie = name + "=a; expires=" + date.toGMTString()+";path=/";

    var delValue = getCookie(name);
    if (!!delValue) {
        document.cookie = name + '=;domain=' + getCookie() + ';expires=' + date.toGMTString();
    }
}

function param(id, name, unit, className) {
    this.id = id;
    this.name = name;
    this.value = '--';
    this.show = false;
    this.unit = unit;
    if (className) {
        this.className = className;
    } else {
        this.className = 'div-num';
    }
}

//下划线转换驼峰
function toHump(name) {
    return name.replace(/\_(\w)/g, function (all, letter) {
        return letter.toUpperCase();
    });
}

// 驼峰转换下划线
function toLine(name) {
    return name.replace(/([A-Z])/g, "_$1").toLowerCase();
}

function initRepair(id, name) {

    window.location.hash = '#repair/form';
    $("li.itemActive").removeClass("itemActive");
    $("a[data-url='repair/form']").parent("li").addClass("itemActive");
    if ($("a[data-url='repair/form']").attr("type") == '1') {
        $("a[data-url='repair/form']").parents("li.first").find("a")[0].click();
    }
    hxBack.getJson('repair/form', {}, function (data) {
        $("#content").html(data);
        hx.repairForm.initAdd();
        hx.repairForm.repairFormVO.equipmentName = name;
        hx.repairForm.repairFormVO.equipmentId = id;
    });
}




