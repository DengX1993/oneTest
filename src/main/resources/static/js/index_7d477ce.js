var headerFrameView = Backbone.View.extend({
    el: ".topNavBar",
    events: {
        "click .topNav-search>p": "showSearch",
        "click .topNav-search .close": "hideSearch",
        "mouseenter .topNav-slide": "showSlide",
        "mouseleave .topNav-slide": "hideSlide",
        "click .topNav-signIn": "login",
        "click .quit": "quit",
        "keydown .search-btn": "onSearch",
        "click .topNav-idea": "onPost"
    },
    initialize: function () {
        $(".topNav-msg").length > 0 && myAjax.ajax("/services/userCenter/msg/getMsgIsData", {}, {
            success: function (e) {
                e.data.unreadNum > 0 && $(".topNav-msg span").addClass("topNav-unread")
            }
        }), myAjax.ajax("/services/webother/other/getHeaderInfo", {}, {
            success: function (e) {
                var e = e.data, a = "";
                if (e.isLogin) {
                    window.isHaierUser = e.user.isHaierUser;
                    var i = e.user.headImg ? e.user.headImg : "http://idiy.haier.com/upload/test/默认头像2.jpg",
                        r = e.user.nickname ? e.user.nickname : e.user.userName;
                    a = '<div class="topNav-user" data-userid="' + e.user.id + '"><input type="hidden" name="single-userId" value="' + e.user.id + '"><input type="hidden" name="single-userName" value="' + e.user.nickname + '"><img src="' + i + '" alt=""><p>' + r + '</p><ul><li><a href="/pc/profile/list">个人中心</a></li><li><a href="/pc/order/mine">我的订单</a></li><li><a href="/pc/profile/collect">我的收藏</a></li><li><a href="/pc/profile/attention">我的关注</a></li><li><a href="/pc/profile/coupon">优惠券</a><li><a href="javascript:;" class="quit">退出</a></li></ul></div><a href="/pc/order/cart" class="topNav-cart">购物车<span>（<em>' + e.cartNum + '</em>）</span></a><a href="/pc/profile/news" class="topNav-msg"><span></span>消息中心</a>'
                } else a = '<a href="javascript:void(0);" class="topNav-signIn">登录/注册</a>';
                $("#headerInfoPage").html(a);
                var t = $(".topNav-user").attr("data-userid");
                t && (window.USER_ID = t, e && e.data && 1 == e.data.isLogin && 0 == window.isHaierUser)
            }
        })
    },
    login: function () {
        location.href = loginUrl
    },
    showSearch: function () {
        $(".topNavBar").addClass("headerSearch"), $(".search-con input").focus()
    },
    hideSearch: function () {
        $(".topNavBar").removeClass("headerSearch")
    },
    showSlide: function (e) {
        var a = $(e.currentTarget);
        a.children("a").css({
            color: "#ffffff",
            "border-bottom": "2px solid #fff"
        }), a.find(".topNav-type").stop().slideDown()
    },
    hideSlide: function (e) {
        var a = $(e.currentTarget);
        a.children("a").attr("style", ""), a.find(".topNav-type").stop().slideUp()
    },
    quit: function () {
        $.get("/login/logout", function (e) {
            location.href = e.return_url
        })
    },
    onSearch: function (e) {
        var a = $(e.currentTarget), i = a.val();
        if (13 == e.keyCode) if (i) {
            var r = encodeURIComponent(i), t = encodeURIComponent(r), s = myutil.url.getQueryString("type");
            window.location.href = s ? "/pc/search/results?search=" + t + "&type=" + s : "/pc/search/results?search=" + t + "&type=1"
        } else layer.tips("请先输入搜索内容", ".search-btn", {tips: [1, "#ca4341"]})
    },
    onPost: function () {
        myutil.isLogin() || (window.location.href = "/raphael/works/post")
    },
    onHappymallBind: function () {
        happybind.showBind()
    }
});
$(function () {
    new headerFrameView
});