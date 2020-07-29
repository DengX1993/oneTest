!function (e) {
    "use strict";
    var t = function () {
        this.v = "1.0.1"
    };
    t.fn = t.prototype;
    var n = document, o = t.fn.cache = {}, r = function () {
        var e = n.scripts, t = e[e.length - 1].src;
        return t.substring(0, t.lastIndexOf("/") + 1)
    }(), i = function (t) {
        e.console && console.error && console.error("Layui hint: " + t)
    }, l = "undefined" != typeof opera && "[object Opera]" === opera.toString(), u = {
        layer: "modules/layer",
        laydate: "modules/laydate",
        laypage: "modules/laypage",
        laytpl: "modules/laytpl",
        layim: "modules/layim",
        layedit: "modules/layedit",
        form: "modules/form",
        upload: "modules/upload",
        tree: "modules/tree",
        slide: "modules/slide",
        table: "modules/table",
        element: "modules/element",
        util: "modules/util",
        flow: "modules/flow",
        code: "modules/code",
        single: "modules/single",
        mobile: "modules/mobile",
        report: "modules/report",
        jquery: "lib/jquery",
        "layui.all": "dest/layui.all"
    };
    o.modules = {}, o.status = {}, o.timeout = 10, o.event = {}, t.fn.define = function (e, t) {
        var n = this, r = "function" == typeof e, i = function () {
            return "function" == typeof t && t(function (e, t) {
                layui[e] = t, o.status[e] = !0
            }), this
        };
        return r && (t = e, e = []), layui["layui.all"] ? i.call(n) : (n.use(e, i), n)
    }, t.fn.use = function (e, t, a) {
        function s(e, t) {
            var n = "PLaySTATION 3" === navigator.platform ? /^complete$/ : /^(complete|loaded)$/;
            ("load" === e.type || n.test((e.currentTarget || e.srcElement).readyState)) && (o.modules[y] = t, m.removeChild(v), function r() {
                return ++p > 1e3 * o.timeout / 4 ? i(y + " is not a valid module") : void (o.status[y] ? c() : setTimeout(r, 4))
            }())
        }

        function c() {
            a.push(layui[y]), e.length > 1 ? f.use(e.slice(1), t, a) : "function" == typeof t && t.apply(layui, a)
        }

        var f = this, d = o.dir = o.dir ? o.dir : r, m = n.getElementsByTagName("head")[0];
        e = "string" == typeof e ? [e] : e, window.jQuery && jQuery.fn.on && (f.each(e, function (t, n) {
            "jquery" === n && e.splice(t, 1)
        }), layui.jquery = jQuery);
        var y = e[0], p = 0;
        if (a = a || [], o.host = o.host || (d.match(/\/\/([\s\S]+?)\//) || ["//" + location.host + "/"])[0], 0 === e.length) return t();
        var v = n.createElement("script"),
            h = (u[y] ? "/assets/scripts/" : o.base || "/assets/scripts/modules/") + (f.modules[y] || y) + ".js";
        return v.async = !0, v.charset = "utf-8", v.src = h + function () {
            var e = o.version === !0 ? o.v || (new Date).getTime() : o.version || "";
            return e ? "?v=" + e : ""
        }(), o.modules[y] ? !function g() {
            return ++p > 1e3 * o.timeout / 4 ? i(y + " is not a valid module") : void ("string" == typeof o.modules[y] && o.status[y] ? c() : setTimeout(g, 4))
        }() : (m.appendChild(v), !v.attachEvent || v.attachEvent.toString && v.attachEvent.toString().indexOf("[native code") < 0 || l ? v.addEventListener("load", function (e) {
            s(e, h)
        }, !1) : v.attachEvent("onreadystatechange", function (e) {
            s(e, h)
        })), o.modules[y] = h, f
    }, t.fn.all = function (e) {
        return this.use("layui.all", e), this
    }, t.fn.getStyle = function (t, n) {
        var o = t.currentStyle ? t.currentStyle : e.getComputedStyle(t, null);
        return o[o.getPropertyValue ? "getPropertyValue" : "getAttribute"](n)
    }, t.fn.link = function (e, t, r) {
        var l = this, u = n.createElement("link"), a = n.getElementsByTagName("head")[0];
        "string" == typeof t && (r = t);
        var s = (r || e).replace(/\.|\//g, ""), c = u.id = "layuicss-" + s, f = 0;
        u.rel = "stylesheet", u.href = e + (o.debug ? "?v=" + (new Date).getTime() : ""), u.media = "all", n.getElementById(c) || a.appendChild(u), "function" == typeof t && !function d() {
            return ++f > 1e3 * o.timeout / 100 ? i(e + " timeout") : void (1989 === parseInt(l.getStyle(n.getElementById(c), "width")) ? function () {
                t()
            }() : setTimeout(d, 100))
        }()
    }, t.fn.addcss = function (e, t, n) {
        layui.link("/assets/styles/modules/" + e, t, n)
    }, t.fn.img = function (e, t, n) {
        var o = new Image;
        return o.src = e, o.complete ? t(o) : (o.onload = function () {
            o.onload = null, t(o)
        }, void (o.onerror = function (e) {
            o.onerror = null, n(e)
        }))
    }, t.fn.config = function (e) {
        e = e || {};
        for (var t in e) o[t] = e[t];
        return this
    }, t.fn.modules = function () {
        var e = {};
        for (var t in u) e[t] = u[t];
        return e
    }(), t.fn.extend = function (e) {
        var t = this;
        e = e || {};
        for (var n in e) t[n] || t.modules[n] ? i("模块名 " + n + " 已被占用") : t.modules[n] = e[n];
        return t
    }, t.fn.router = function (e) {
        for (var t, n = (e || location.hash).replace(/^#/, "").split("/") || [], o = {dir: []}, r = 0; r < n.length; r++) t = n[r].split("="), /^\w+=/.test(n[r]) ? function () {
            "dir" !== t[0] && (o[t[0]] = t[1])
        }() : o.dir.push(n[r]), t = null;
        return o
    }, t.fn.data = function (t, n) {
        if (t = t || "layui", e.JSON && e.JSON.parse) {
            if (null === n) return delete localStorage[t];
            n = "object" == typeof n ? n : {key: n};
            try {
                var o = JSON.parse(localStorage[t])
            } catch (r) {
                var o = {}
            }
            return n.value && (o[n.key] = n.value), n.remove && delete o[n.key], localStorage[t] = JSON.stringify(o), n.key ? o[n.key] : o
        }
    }, t.fn.device = function (t) {
        var n = navigator.userAgent.toLowerCase(), o = function (e) {
            var t = new RegExp(e + "/([^\\s\\_\\-]+)");
            return e = (n.match(t) || [])[1], e || !1
        }, r = {
            os: function () {
                return /windows/.test(n) ? "windows" : /linux/.test(n) ? "linux" : /iphone|ipod|ipad|ios/.test(n) ? "ios" : void 0
            }(), ie: function () {
                return e.ActiveXObject || "ActiveXObject" in e ? (n.match(/msie\s(\d+)/) || [])[1] || "11" : !1
            }(), weixin: o("micromessenger")
        };
        return t && !r[t] && (r[t] = o(t)), r.android = /android/.test(n), r.ios = "ios" === r.os, r
    }, t.fn.hint = function () {
        return {error: i}
    }, t.fn.each = function (e, t) {
        var n, o = this;
        if ("function" != typeof t) return o;
        if (e = e || [], e.constructor === Object) {
            for (n in e) if (t.call(e[n], n, e[n])) break
        } else for (n = 0; n < e.length && !t.call(e[n], n, e[n]); n++) ;
        return o
    }, t.fn.stope = function (t) {
        t = t || e.event, t.stopPropagation ? t.stopPropagation() : t.cancelBubble = !0
    }, t.fn.onevent = function (e, t, n) {
        return "string" != typeof e || "function" != typeof n ? this : (o.event[e + "." + t] ? o.event[e + "." + t].push(n) : o.event[e + "." + t] = [n], this)
    }, t.fn.event = function (e, t, n) {
        var r = this, i = null, l = t.match(/\(.*\)$/) || [], u = (t = e + "." + t).replace(l, ""),
            a = function (e, t) {
                var o = t && t.call(r, n);
                o === !1 && null === i && (i = !1)
            };
        return layui.each(o.event[u], a), l[0] && layui.each(o.event[t], a), i
    }, e.layui = new t
}(window);