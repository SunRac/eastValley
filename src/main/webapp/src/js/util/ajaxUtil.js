/**
 * ajax相关的参数
 * TYPE: {},请求方法
 * DATATYPE： {} 返回结果类型
 * SUCC: {SUCCESS: string, ERROR: string}  结果标识
 * BOOLEAN boolean值
 *
 */
var CONFIG = {
    TYPE: {
        GET: "GET",
        POST: "POST"
    },
    DATATYPE: {
        JSON: "json",
        HTML: "html",
        TEXT: "text",
        JSONP: "jsonp"
    },
    RESULT: {
        SUCCESS: '0',
        ERROR: "-9999"
    },
    BOOLEAN: {
        TRUE: true,
        FALSE: false
    }
};

/**
 * 基本ajax请求方法
 * @param {*} url 请求地址
 * @param {*} type 请求方式
 * @param {*} data 请求入参
 * @param {*} dataType 数据类型
 * @param {*} callback 回调函数
 * @param {*} async 是否异步
 */
var _ajaxBase = function (url, type, data, dataType,callback,async) {
    var cache = dataType == "html" ? true : false;
        return $.ajax({
            url: url,
            type: type,
            data: data,
            dataType: dataType,
            async: async,
            cache: cache,
            beforeSend: function (xhr) {
                xhr.overrideMimeType("text/plain; charset=utf-8")
            },
            success: function (result, textStatus, xhr) {
                //如果没有返回数据，快速结束
                if(!result) {
                    return;
                }
                //调用成功之后的处理逻辑
                callback(result);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.error(errorThrown);
            }
        })
}

/**
 * 基于deferred封装的ajax
 * @private
 * TODO 待实现
 */
var _ajaxDeferred = function() {

}

/**
 * jsonp跨域请求，jquery封装之后，success的代码即是回调函数体
 * @param {*} url 请求地址
 * @param {*} type 请求方式
 * @param {*} data 请求入参
 * @param {*} dataType 数据类型
 * @param {*} callback 回调函数
 * @param {*} async 是否异步
 * @param {*} jsonp 指定请求url中代表回调函数的查询字符串中的参数名，默认是：callback=?中的callback
 * @param {*} jsonpCallback 指定回调函数名
 * TODO 待验证
 */
var _ajaxJsonp = function (url, type, data, dataType,callback,async) {
    var cache = dataType == "html" ? true : false;
    return $.ajax({
        url: url,
        type: CONFIG.TYPE.GET,
        data: data,
        dataType: CONFIG.DATATYPE.JSONP,
        async: async,
        cache: cache,
        // json: "callback",使用默认的即可
        // jsonpCallback: "callbackHandler",使用默认的即可
        beforeSend: function (xhr) {
            xhr.overrideMimeType("text/plain; charset=utf-8")
        },
        success: function (result, textStatus, xhr) {
            //如果没有返回数据，快速结束
            if(!result) {
                return;
            }
            //调用成功之后的处理逻辑
            callback(result);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error(errorThrown);
        }
    })
}

/**
 * 封装了各种ajax方法的对象
 * TODO 待验证
 */
var ajax = {
    /**
     * "GET"请求方式返回json格式数据
     */
    getJson: function (url, data, callback) {
        _ajaxBase(url, CONFIG.TYPE.GET, data, CONFIG.DATATYPE.JSON, callback, true);
    },
    /**
     * "POST"请求方式返回json格式数据
     */
    postJson: function (url, data, callback) {
        _ajaxBase(url, CONFIG.TYPE.POST, data, CONFIG.DATATYPE.JSON, callback, true);
    },
    /**
     * 异步"GET"请求方式返回json格式数据
     */
    getJsonSync: function (url, data, callback) {
        _ajaxBase(url, CONFIG.TYPE.GET, data, CONFIG.DATATYPE.JSON, callback, false);
    },
    /**
     * 异步"POST"请求方式返回json格式数据
     */
    postJsonSync: function (url, data, callback) {
        _ajaxBase(url, CONFIG.TYPE.POST, data, CONFIG.DATATYPE.JSON, callback, false);
    }
}