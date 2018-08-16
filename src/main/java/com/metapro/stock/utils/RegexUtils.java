package com.metapro.stock.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private final static String STOCK_MARKET_QUOTE_REGEX = "<table id=\"stock_quoteinfo\"[\\s\\S]{0,5000}</table>";
    private final static String STOCK_QUOTE_JK_REGEX = "<span id='stock_quoteinfo_jk'>[0-9,.]+</span>";
    private final static String STOCK_QUOTE_ZS_REGEX = "<span id='stock_quoteinfo_zs'>[0-9,.]+</span>";
    private final static String STOCK_QUOTE_ZG_REGEX = "<span id='stock_quoteinfo_zg'>[0-9,.]+</span>";
    private final static String STOCK_QUOTE_ZD_REGEX = "<span id='stock_quoteinfo_zd'>[0-9,.]+</span>";
    private final static String STOCK_QUOTE_VAL_REGEX = ">(.*?)<";

    public static String matcherQuote(String source) {
        return matcher(STOCK_MARKET_QUOTE_REGEX, source);
    }

    public static String matcherQuoteJK(String source) {
        String data = matcher(STOCK_QUOTE_JK_REGEX, source);
        return matcherNumeric(STOCK_QUOTE_VAL_REGEX, data);
    }

    public static String matcherQuoteZS(String source) {
        String data = matcher(STOCK_QUOTE_ZS_REGEX, source);
        return matcherNumeric(STOCK_QUOTE_VAL_REGEX, data);
    }
    public static String matcherQuoteZG(String source) {
        String data = matcher(STOCK_QUOTE_ZG_REGEX, source);
        return matcherNumeric(STOCK_QUOTE_VAL_REGEX, data);
    }
    public static String matcherQuoteZD(String source) {
        String data = matcher(STOCK_QUOTE_ZD_REGEX, source);
        return matcherNumeric(STOCK_QUOTE_VAL_REGEX, data);
    }

    private static String matcher(String regex, String data){
        String target = StringUtils.EMPTY;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            target = matcher.group();
        }
        return target;
    }

    private static String matcherNumeric(String regex, String data){
        String result = matcher(regex, data);
        if(StringUtils.isBlank(result)) return "";
        result = result.trim();
        result = result.replaceAll("<", "").replaceAll(">", "");
        return result;
    }

    public static void main(String[] a) {
        String result = matcherQuote(data);
        System.out.println(result);
        result = matcherQuoteJK(quoteInfo);
        System.out.println(result);
        result = matcherQuoteZS(quoteInfo);
        System.out.println(result);
        result = matcherQuoteZG(quoteInfo);
        System.out.println(result);
        result = matcherQuoteZD(quoteInfo);
        System.out.println(result);
    }

    static String quoteInfo = "<table id=\"stock_quoteinfo\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "      <tbody>\n" +
            "\t  <tr><td>今开：<span id='stock_quoteinfo_jk'>27.66</span></td><td>最高：<span id='stock_quoteinfo_zg'>28.25</span></td><td>成交：<span id='stock_quoteinfo_cj'>35.87万手</span> </td><td>市盈：<span id='stock_quoteinfo_sy'>0.00</span> </td><td><a href='http://index.quote.stockstar.com/000001.shtml'>上证指数：</a><a href='http://index.quote.stockstar.com/000001.shtml'><span id='stock_quoteinfo_000001_xj'>2779.37</span></a> </td><td class='upInfo'><span id='stock_quoteinfo_000001_zdf'>&nbsp;&nbsp;2.74%</span></td><td class='time'><span id='stock_quoteinfo_date'>2018-08-07</span></td></tr><tr><td>昨收：<span id='stock_quoteinfo_zs'>27.61</span> </td><td>最低：<span id='stock_quoteinfo_zd'>26.93</span> </td><td>换手：<span id='stock_quoteinfo_hs'>0.00%</span></td><td>振幅：<span id='stock_quoteinfo_zf'>0.00</span> </td><td><strong><a href='http://index.quote.stockstar.com/399001.shtml'>深证指数：</a></strong><a href='http://index.quote.stockstar.com/399001.shtml'><span id='stock_quoteinfo_399001_xj'>8674.03</span></a></td><td class='upInfo'><span id='stock_quoteinfo_399001_zdf'>&nbsp;&nbsp;2.98%</span></td><td class='time'><span id='stock_quoteinfo_time'>15:02:03</span></td></tr>\n" +
            "      </tbody>\n" +
            "    </table>";



    static String data =
            "</div><div class=\"h_7\"></div>" +
                    "<div id=\"head\">" +
                    "<div class=\"logo fl\"><a href=\"//www.stockstar.com\" title=\"证券之星\">证券之星</a></div>" +
                    "<div id=\"ad_1\" class=\"ad fr\"> " +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\"></div>" +
                    "<div id=\"sta_menu\" class=\"menu\">" +
                    "<ul>" +
                    "<li class=\"menu1\"><a href=\"/002230.shtml\" title=\"个股资料\" target=\"_self\"" +
                    "class=\"cur\"><span>个股资料</span></a></li>" +
                    "<li class=\"menu2\">" +
                    "<a href=\"/info_002230.shtml\" title=\"新闻动态\" target=\"_self\" >" +
                    "<span>新闻动态</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=10\">" +
                    "公司新闻</a> | <a href=\"//stock.quote.stockstar.com/info/notice_002230.shtml\">" +
                    "交易提示</a> | <a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=76\">" +
                    "最新公告</a> | <a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=2835\">" +
                    "定期报告</a> | <a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=3491\">" +
                    "研究报告</a> | <a href=\"//stock.stockstar.com/stock/checkIndustry.aspx?Industry=I\">" +
                    "行业新闻</a> | <a href=\"//stock.quote.stockstar.com/info/billboard_002230.shtml\">" +
                    "龙虎榜单</a> | <a href=\"//stock.quote.stockstar.com/info/financing_002230.shtml\">" +
                    "融资融券</a> | <a href=\"//stock.quote.stockstar.com/info/blocktrade_002230.shtml\">" +
                    "大宗交易</a>" +
                    "</div>" +
                    "</li>" +
                    "<li class=\"menu3\">" +
                    "<a href=\"/corp_002230.shtml\" title=\"公司资料\" target=\"_self\" >" +
                    "<span>公司资料</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<a href=\"//stock.quote.stockstar.com/corp/brief_002230.shtml\">" +
                    "公司简介</a> | <a href=\"//stock.quote.stockstar.com/corp/rating_002230.shtml\">" +
                    "公司评级</a> | <a href=\"//stock.quote.stockstar.com/corp/rating_002230.shtml\">" +
                    "行业评级</a> | <a href=\"//stock.quote.stockstar.com/corp/business_002230.shtml\">" +
                    "主营业务</a> | <a href=\"//stock.quote.stockstar.com/corp/executives_002230.shtml\">" +
                    "公司高管</a> | <a href=\"//stock.quote.stockstar.com/corp/restructure_002230.shtml\">" +
                    "资产重组</a> | <a href=\"//stock.quote.stockstar.com/corp/litigation_002230.shtml\">" +
                    "诉讼仲裁</a> | <a href=\"//stock.quote.stockstar.com/corp/sharetrade_002230.shtml\">" +
                    "股权转让</a>" +
                    "</div>" +
                    "</li>" +
                    "<li class=\"menu4\">" +
                    "<a href=\"/dividend_002230.shtml\" title=\"融资分红\" target=\"_self\"" +
                    "><span>融资分红</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<div class=\"nspace\">" +
                    "<a href=\"//stock.quote.stockstar.com/dividend/plan_002230.shtml\">" +
                    "分配预案</a> | <a href=\"//stock.quote.stockstar.com/dividend/financing_002230.shtml\">" +
                    "新股发行</a> | <a href=\"//stock.quote.stockstar.com/dividend/bonus_002230.shtml\">" +
                    "分红送配</a> | <a href=\"//stock.quote.stockstar.com/dividend/financing_002230.shtml\">" +
                    "增发</a> | <a href=\"//stock.quote.stockstar.com/dividend/financing_002230.shtml\">" +
                    "转债</a>" +
                    "</div>" +
                    "</div>" +
                    "</li>" +
                    "<li class=\"menu5\">" +
                    "<a href=\"/share_002230.shtml\" title=\"股本股东\" target=\"_self\" >" +
                    "<span>股本股东</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<div class=\"nspace\">" +
                    "<a href=\"//stock.quote.stockstar.com/share/structure_002230.shtml\">" +
                    "股本结构</a> | <a href=\"//stock.quote.stockstar.com/share/holdertop10_002230.shtml\">" +
                    "十大股东</a> | <a href=\"//stock.quote.stockstar.com/share/circulate_002230.shtml\">" +
                    "流通股东</a> | <a href=\"//stock.quote.stockstar.com/share/restricted_002230.shtml\">" +
                    "限售解禁</a> | <a href=\"//stock.quote.stockstar.com/share/fund_002230.shtml\">" +
                    "基金持仓</a> | <a href=\"//stock.quote.stockstar.com/share/ownership_002230.shtml\">" +
                    "高管持股</a>" +
                    "</div>" +
                    "</div>" +
                    "</li>" +
                    "<li class=\"menu6\">" +
                    "<a href=\"/finance_002230.shtml\" title=\"财务分析\" target=\"_self\" >" +
                    "<span>财务分析</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<div class=\"nspace\">" +
                    "<a href=\"//stock.quote.stockstar.com/finance/summary_002230.shtml\">" +
                    "主要指标</a> | <a href=\"//stock.quote.stockstar.com/finance/profit_002230.shtml\">" +
                    "利润表</a> | <a href=\"//stock.quote.stockstar.com/finance/balance_002230.shtml\">" +
                    "资产负债表</a> | <a href=\"//stock.quote.stockstar.com/finance/cashflow_002230.shtml\">" +
                    "现金流量表</a> | <a href=\"//stock.quote.stockstar.com/finance/performance_002230.shtml\">" +
                    "业绩预告</a> | <a href=\"//stock.quote.stockstar.com/finance/predict_002230.shtml\">" +
                    "机构预测</a>" +
                    "</div>" +
                    "</div>" +
                    "</li>" +
                    "" +
                    "<li class=\"menu7\">" +
                    "<a href=\"/capital_002230.shtml\" title=\"资金流向\" target=\"_self\" >" +
                    "<span>资金流向</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<div class=\"nspace\">" +
                    "<a href=\"//quote.stockstar.com/cashflow/industry/\">行业资金流向排名</a> | <a href=\"//quote.stockstar.com/cashflow/stock/\">" +
                    "个股资金流向排名</a> | <a href=\"//quote.stockstar.com/cashflow/main/\">主力资金流向排名</a>" +
                    "| <a href=\"//quote.stockstar.com/cashflow/continue/\">资金连续流入流出排名</a> | <a href=\"//stock.quote.stockstar.com/capital/flow_600000.shtml\">" +
                    "个股资金流向明细</a>" +
                    "</div>" +
                    "</div>" +
                    "</li>" +
                    "<li class=\"menu8\">" +
                    "<a href=\"/tech_002230.shtml\" title=\"技术分析\" target=\"_self\" >" +
                    "<span>技术分析</span></a>" +
                    "<div class=\"childMenu\" style=\"display: ;\">" +
                    "<div class=\"nspace\">" +
                    "<a href=\"//stock.quote.stockstar.com/tech_002230.shtml\">价格分析</a>" +
                    "| <a href=\"//quote.stockstar.com/Radar/external_special_1_5.htm\">五日金叉</a> |" +
                    "<a href=\"//quote.stockstar.com/Radar/external_special_8.htm\">连续上涨</a> | <a href=\"//quote.stockstar.com/Radar/external_special_23.htm\">" +
                    "向上跳空</a>" +
                    "</div>" +
                    "</div>" +
                    "</li>" +
                    "" +
                    "<li class=\"menu9\"><a href=\"/realtime_002230.shtml\" title=\"实时行情\"" +
                    "target=\"_self\" ><span>实时行情</span></a></li>" +
                    "</ul>" +
                    "</div>" +
                    "<!--子菜单延时 显示显示 start-->" +
                    "<script language=\"javascript\">" +
                    "(function($) {" +
                    "$.fn.hoverDelay = function(options) {" +
                    "var defaults = {" +
                    "hoverDuring: 200," +
                    "outDuring: 200," +
                    "hoverEvent: function() {" +
                    "$.noop();" +
                    "}," +
                    "outEvent: function() {" +
                    "$.noop();" +
                    "}" +
                    "};" +
                    "var sets = $.extend(defaults, options || {});" +
                    "var hoverTimer, outTimer;" +
                    "return $(this).each(function() {" +
                    "$(this).hover(function() {" +
                    "clearTimeout(outTimer);" +
                    "hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);" +
                    "}," +
                    "function() {" +
                    "clearTimeout(hoverTimer);" +
                    "outTimer = setTimeout(sets.outEvent, sets.outDuring);" +
                    "});" +
                    "});" +
                    "}" +
                    "})(jQuery);" +
                    "var $li = $(\"#sta_menu li\");" +
                    "var $this_cur_a;" +
                    "$li.each(function() {" +
                    "var $this = $(this);" +
                    "if ($this.children(\"a\").attr(\"class\") == \"cur\") {" +
                    "$this_cur_a = $this.children(\"a\");" +
                    "}" +
                    "$this.hoverDelay({" +
                    "outDuring: 200," +
                    "hoverDuring: 200," +
                    "hoverEvent: function() {" +
                    "$this.children(\"a\").addClass(\"cur\");" +
                    "$this.addClass(\"ShowChildMenu\");" +
                    "}," +
                    "outEvent: function() {" +
                    "$this.removeClass(\"ShowChildMenu\");" +
                    "if ($this_cur_a.html() != $this.children(\"a\").html()) {" +
                    "$this.children(\"a\").removeClass(\"cur\");" +
                    "}" +
                    "}" +
                    "});" +
                    "});" +
                    "</script> " +
                    "<!--子菜单延时 显示显示 start-->" +
                    "<div id=\"sta_1\" class=\"positionWrap\">" +
                    "<div class=\"position\">" +
                    "当前位置：<a href=\"//www.stockstar.com\">首页</a> - <a href=\"//quote.stockstar.com\">行情中心</a> - <a href=\"/002230.shtml\">" +
                    "科大讯飞(002230)</a> - <strong><a href=\"/002230.shtml\">个股资料</a></strong>" +
                    "</div>" +
                    "<div class=\"link\" id=\"newsIndex\" style=\"display: none;\">" +
                    "<ul>" +
                    "<li><a href='//stock.stockstar.com/SS2018080500000054.shtml'>科大讯飞：酷音短视频是公司智能语音..</a><span>08-05</span></li><li><a href='//stock.stockstar.com/SS2018072900000015.shtml'>科大讯飞与贵州省检察院合作共建联合..</a><span>07-28</span></li><li><a href='//stock.stockstar.com/SS2018072400000401.shtml'>科大讯飞与深圳大鹏新区战略合作 将..</a><span>07-24</span></li>" +
                    "</ul>" +
                    "</div>" +
                    "<div class=\"search\" id=\"divSearchBox\">" +
                    "<!-- 股票搜索框-->" +
                    "<!--<div class=\"ac_results\" style=\"z-index: 100; top: 23px; left: 0px;\"></div>" +
                    "<div class=\"searchText\">" +
                    "<input class=\"text\" autocomplete=\"off\" type=\"text\"" +
                    " value=\"代码/名称/拼音\" onclick=\"AutoComplete.Clear('divSearchBox');\" onkeyup=\"AutoComplete.Show(event,'divSearchBox',0,'quotesearch');\" onblur=\"AutoComplete.OnBlur('divSearchBox');\" /></div>" +
                    "<input type=\"submit\" class=\"newbtn redshbtn\" value=\"查询\" onclick=\"AutoComplete.Submit('divSearchBox');\" />" +
                    "<div class=\"infomesage\" style=\"display:none;\"></div> " +
                    "<form action=\"http://quote.stockstar.com/stock/exdir.aspx\" method=\"post\">" +
                    "<input type=\"hidden\" name=\"code\" />" +
                    "<input type=\"hidden\" name=\"mk\" />" +
                    "<input type=\"hidden\" name=\"securtytype\" />" +
                    "<input type=\"hidden\" name=\"target\" value=\"quotesearch\" />" +
                    "</form>-->" +
                    "<!-- 2013-01-21 new modify add by xb.zhu-->" +
                    "<div class=\"searchText\"><input id=\"ajaxinput_q\" class=\"text\" type=\"text\" /></div>" +
                    "<input id=\"ajaxinputquery_q\" type=\"submit\" class=\"newbtn redshbtn\" value=\"查询\"/>" +
                    "<script src=\"//j.ssajax.cn/js/lib/StockSuggest.min.1.8.js\" type=\"text/javascript\"></script>" +
                    "<script type=\"text/javascript\">" +
                    "new StockSuggest(\"ajaxinput_q\", {" +
                    "width: \"193px\", //下拉层宽度" +
                    "autoSubmit: true, //是否允许自动提交" +
                    "className: 'searchText', //自定义样式" +
                    "types: [101, 102, 103, 104, 201,105, 300, 401, 403, 405, 501, 502, 503, 504,801,802], //展示的证券类型" +
                    "status: [1, 2, 3,4]," +
                    "opacity: 1, //透明度" +
                    "shortcutKey: true,//行情软件中的快捷键 " +
                    "text: '代码/简称/拼音', //默认文字" +
                    "header: { show: true, columns: [\"简称\", \"类型\"] }, //显示字段" +
                    "orderBy: 2, //指数代码优先" +
                    "evt: 'ajaxinputquery_q' //触发事件" +
                    "});" +
                    "</script>" +
                    "</div>" +
                    "</div>" +
                    "<!--行情信息-->" +
                    "" +
                    "<!--行情报价_2018-8-7 9:47:43-->" +
                    "<div id=\"sta_2\" class=\"bom_bor w\">" +
                    " <div class=\"stockInfo\">" +
                    "<div class=\"name\">" +
                    "<h2>科大讯飞</h2>" +
                    "<h2 class=\"h1\">(002230)</h2>" +
                    "</div>" +
                    "<div class=\"num\">" +
                    "<p id=\"stockclose\" class=\"stock\"><span id=\"stock_quoteinfo_xj\" class=\"red\">27.77</span><span class=\"img\"></span></p>" +
                    "<p class=\"stock_num\"><span id=\"stock_quoteinfo_zde\" class=\"red\" >0.16</span>&nbsp;&nbsp;<span id=\"stock_quoteinfo_zdf\" class=\"red\">(0.58%)</span></p>" +
                    "</div>" +
                    "<table id=\"stock_quoteinfo\"border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                    "<tbody>" +
                    "\t<tr><td>今开：<span id='stock_quoteinfo_jk'>27.66</span></td><td>最高：<span id='stock_quoteinfo_zg'>28.10</span></td><td>成交：<span id='stock_quoteinfo_cj'>4.33万手</span> </td><td>市盈：<span id='stock_quoteinfo_sy'>0.00</span> </td><td><a href='http://index.quote.stockstar.com/000001.shtml'>上证指数：</a><a href='http://index.quote.stockstar.com/000001.shtml'><span id='stock_quoteinfo_000001_xj'>2719.23</span></a> </td><td class='upInfo'><span id='stock_quoteinfo_000001_zdf'>&nbsp;&nbsp;0.52%</span></td><td class='time'><span id='stock_quoteinfo_date'>2018-08-07</span></td></tr><tr><td>昨收：<span id='stock_quoteinfo_zs'>27.61</span> </td><td>最低：<span id='stock_quoteinfo_zd'>27.66</span> </td><td>换手：<span id='stock_quoteinfo_hs'>0.00%</span></td><td>振幅：<span id='stock_quoteinfo_zf'>0.00</span> </td><td><strong><a href='http://index.quote.stockstar.com/399001.shtml'>深证指数：</a></strong><a href='http://index.quote.stockstar.com/399001.shtml'><span id='stock_quoteinfo_399001_xj'>8483.81</span></a></td><td class='upInfo'><span id='stock_quoteinfo_399001_zdf'>&nbsp;&nbsp;0.72%</span></td>" +
                    "<td class='time'><span id='stock_quoteinfo_time'>09:47:12</span></td></tr>" +
                    "</tbody>" +
                    "</table>" +
                    "<div class='bid'><p>集合</p><p>竞价</p></div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\"></div>" +
                    "<div id=\"sta_3\" class=\"wrap warrantPage\">" +
                    "<div id=\"sta_3_L\" class=\"main\">" +
                    "<div id=\"L_1\" class=\"bom_bor quote\">" +
                    "<div class=\"title\" style=\"position: relative;\">" +
                    "<ul>" +
                    "<li class=\"first\"><a href=\"javascript:void(0)\" onclick=\"tabShow('quote_menu_',1,5);AddParametersForViewBigImage(0);return false;\"" +
                    "id=\"quote_menu_1\" class=\"cur\">分时图</a></li>" +
                    "<li><a href=\"javascript:void(0)\" onclick=\"tabShow('quote_menu_',5,5);AddParametersForViewBigImage(0);return false;\"" +
                    "id=\"quote_menu_5\">5日</a></li>" +
                    "<li><a href=\"javascript:void(0)\" onclick=\"tabShow('quote_menu_',2,5);AddParametersForViewBigImage(4);return false;\"" +
                    "id=\"quote_menu_2\">日K</a></li>" +
                    "<li><a href=\"javascript:void(0)\" onclick=\"tabShow('quote_menu_',3,5);AddParametersForViewBigImage(5);return false;\"" +
                    "id=\"quote_menu_3\">周K</a></li>" +
                    "<li><a href=\"javascript:void(0)\" onclick=\"tabShow('quote_menu_',4,5);AddParametersForViewBigImage(6);return false;\"" +
                    "id=\"quote_menu_4\">月K</a></li>" +
                    "</ul>" +
                    "<div class=\"lr\" style=\"padding-right: 0px\">" +
                    "<style type=\"text/css\">" +
                    ".wrap .main .quote .title .lr" +
                    "{" +
                    "_width: 348px;" +
                    "_height: 26px;" +
                    "overflow: hidden;" +
                    "}" +
                    ".wrap .main .quote .title .lr a.add" +
                    "{" +
                    "margin-left: 3px;" +
                    "width: 50px;" +
                    "}" +
                    ".wrap .main .quote .title .lr a.red" +
                    "{" +
                    "color: #CC0000;" +
                    "}" +
                    ".wrap .main .quote .title .lr span" +
                    "{" +
                    "width: 141px;" +
                    "}" +
                    "</style>" +
                    " " +
                    "<a href=\"http://www.szzy888.com/product/alpha/profile?pub=true\" id=\"guanggao\" class=\"guanggao\" style=\"display:inline-block; width:144px;height:26px;line-height:26px;*margin-left:50px;color:#10468e;overflow:hidden;text-decoration: none;text-align:right;padding:0 6px 0 0\"><font color=red>炒股神器:1分钟看股票买点</font></a> " +
                    "<span style=\"margin-left:0px;\">五档盘口</span>" +
                    "<div style=\"clear: both;\"></div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con conSpecial\">" +
                    "<div class=\"conPic\" style=\"display: inline;\">" +
                    "<div id=\"flashBox\">" +
                    "<div id=\"loading\" class=\"loading\"></div>" +
                    "<div style=\"display:inline\" class=\"imgContainer\">" +
                    "<img id=\"img_1\" src=\"//charts.stockstar.com/hs/qmpic_002230_2_1\" style=\"display: none\" onload=\"ImgPicOnloadFirst(this)\" class=\"dynamic\" />" +
                    "</div>" +
                    "</div>" +
                    "<!--分时图 显示集合竞价时间段 start-->" +
                    "<div id=\"TimeSharingBidding\" class=\"cons\" style=\"width: 500px; padding:0;\">" +
                    "<span>" +
                    "<input onclick=\"TimeSharingBidding_Onclick()\" type=\"checkbox\" name=\"checkbox\" id=\"checkbox\"" +
                    "style=\"vertical-align: middle;margin-right: 4px;\" />" +
                    "<label for=\"checkbox\" style=\"vertical-align: middle;font-family:\\5FAE\\8F6F\\96C5\\9ED1\">" +
                    "显示集合竞价时间段</label>" +
                    "<label>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;<a class=\"ViewBigImage\"" +
                    "href=\"/realtime_002230.shtml\" class=\"add\" style=\"text-decoration: underline;font-family:\\5FAE\\8F6F\\96C5\\9ED1\">查看大图</a></label>" +
                    "</span>" +
                    "<!--退市说明 start-->" +
                    "<em class=\"red\">" +
                    "</em>" +
                    "<!--退市说明 end-->" +
                    "</div>" +
                    "<!--分时图 显示集合竞价时间段 end-->" +
                    "<!--日K、周K、月K 拉长缩短K线 start-->" +
                    "<div id=\"KLineLongShort\" class=\"cons\" style=\"display: none; padding: 0px\">" +
                    "<label style=\"float: right\">" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;<a class=\"ViewBigImage\"" +
                    "href=\"/realtime_002230.shtml\" style=\"text-decoration: underline\">查看大图</a></label>" +
                    "<span>" +
                    "<a href=\"javascript:void(0)\" onclick=\"GetLongOrShortKLine('longer');return false;\">[拉长K线]</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"GetLongOrShortKLine('shorter');return false;\">[缩短K线]</a></span>" +
                    "<!--退市说明 start-->" +
                    "<em class=\"red\">" +
                    "</em>" +
                    "<!--退市说明 end-->" +
                    "</div>" +
                    "<!--日K、周K、月K 拉长缩短K线 end-->" +
                    "<div id=\"dianping\" class=\"dianping conComment\" style=\"padding:13px 10px;margin-top:6px;\">" +
                    "<ul class=\"clearfix\" style=\"margin-left: -10px;clear: both;overflow: hidden;\" id=\"zixungai\">" +
                    "<li><a style=\"color: #c00;\" href=\"//live.gushidaoshi.com\">咨询高手</a></li>" +
                    "<li><a href=\"//pub.szzy888.com/alpha/DiaStock.aspx?tgcode=hqggyan1\">个股诊断</a></li>" +
                    "<li><a href=\"//pub.stockstar.com/feedback/neican/2/?tgcode=hqggyan2\">内参点评</a></li>" +
                    "<li><a href=\"//stock.quote.stockstar.com/info/comment_002230.shtml\">千股千评</a></li>" +
                    "<li> <a href=\"http://news.stockstar.com/info/dstock.aspx?id=4957&code=002230\">利好消息</a></li>" +
                    "<li><a href=\"javascript:void(0);\" onclick=\"AddToFavStock2('002230_2_1');return false;\">加入自选股</a></li>" +
                    "<div style=\"clear: both;\"></div>" +
                    "</ul>" +
                    "<div style=\"clear: both;\"></div>" +
                    " " +
                    "</div>" +
                    "<div style=\"clear: both;\"></div>" +
                    "</div>" +
                    "<div class=\"conList\" style=\"float:left;\">" +
                    "<div class=\"conListTitle\">" +
                    "委比<span id=\"wdpk_wb\">--</span>&nbsp;委差<span id=\"wdpk_wc\">--</span>" +
                    "</div>" +
                    "<div class=\"conListTable\">" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
                    "<tr>" +
                    "<td width=\"50\" align=\"center\">卖⑤" +
                    "</td>" +
                    "<td width=\"50\">" +
                    "<span id=\"wdpk_sell_val_5\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_num_5\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">卖④" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_val_4\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_num_4\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">卖③" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_val_3\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_num_3\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">卖②" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_val_2\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_num_2\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">卖①" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_val_1\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_sell_num_1\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">" +
                    "<strong>当前价</strong>" +
                    "</td>" +
                    "<td colspan=\"2\">" +
                    "<strong class=\"red\"><span id=\"wdpk_np\"><span></strong>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">买①" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_val_1\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_num_1\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">买②" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_val_2\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_num_2\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">买③" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_val_3\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_num_3\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">买④" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_val_4\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_num_4\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td align=\"center\">买⑤" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_val_5\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"wdpk_buy_num_5\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mytable2\">" +
                    "<tr>" +
                    "<td>外:" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"realtime_quote_wp\" class=\"red\">--</span>" +
                    "</td>" +
                    "<td>内:" +
                    "</td>" +
                    "<td>" +
                    "<span id=\"realtime_quote_np\" class=\"green\">--</span>" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td colspan=\"4\" align=\"right\">" +
                    "<a href=\"//stock.quote.stockstar.com/realtime_002230.shtml\">查看成交明细&gt;&gt;</a>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\">" +
                    "</div>" +
                    "<div id=\"L_2\" class=\"link\">" +
                    "<div class=\"list\">" +
                    "<div class=\"top\"></div>" +
                    "<dl class=\"con\">" +
                    "<dt><span class=\"bg\">交易</span>必读</dt>" +
                    "<dd> <a href=\"//stock.stockstar.com/daily_list/1251.shtml\">交易提示</a></dd>" +
                    "<dd class=\"last\"> <a href=\"//stock.stockstar.com/daily/\">每日必读</a></dd>" +
                    "<dd> <a href=\"//stock.stockstar.com/daily_list/1253.shtml\">龙虎榜</a></dd>" +
                    "<dd class=\"last\"> <a href=\"//stock.stockstar.com/stock/studio.htm\">盘中直播</a></dd>" +
                    "</dl>" +
                    "<div class=\"bot\"></div>" +
                    "</div>" +
                    "<div class=\"list\">" +
                    "<div class=\"top\"></div>" +
                    "<dl class=\"con\">" +
                    "<dt class=\"name2\"><span class=\"bg\">产品</span>推荐</dt>" +
                    "<dd> <a href=\"http://live.gushidaoshi.com/\">股市导师</a></dd>" +
                    "<dd class=\"last\"> <a href=\"//quote.stockstar.com/cashflow/\">资金流向</a></dd>" +
                    "<dd> <a href=\"//store.stockstar.com/\">股票商店</a></dd>" +
                    "<dd class=\"last\"> <a href=\"//quote.stockstar.com/stock/datajournal.htm\">数据日刊</a></dd>" +
                    "</dl>" +
                    "<div class=\"bot\"></div>" +
                    "</div>" +
                    "<div class=\"list last\">" +
                    "<div class=\"top\"></div>" +
                    "<dl class=\"con\">" +
                    "<dt class=\"name3\"><span class=\"bg\">相关</span>服务</dt>" +
                    "<dd> <a href=\"//stock.stockstar.com/list/4015.shtml\">独家视点</a></dd>" +
                    "<dd class=\"last\"> <a href=\"//stock.stockstar.com/list/share.htm\">个股掘金</a></dd>" +
                    "<dd> <a href=\"//stock.stockstar.com/list/live.htm\">股市直击</a></dd>" +
                    "<dd class=\"last\"> <a href=\"//stock.stockstar.com/list/main.htm\">主力研究</a></dd>" +
                    "</dl>" +
                    "<div class=\"bot\"></div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\">" +
                    "</div>" +
                    " <!-- 650*90 begin -->" +
                    "<div id=\"adv_650_90\" class=\"bom_bor skinBox\"> " +
                    "<iframe width=\"650\" height=\"90\" frameborder=\"0\" scrolling=\"no\" marginwidth=\"0\" marginheight=\"0\" src=\"//same1.stockstar.com/s?z=stockstar&c=579&op=1\" ></iframe>" +
                    "</div>" +
                    "<div class=\"h_7\">" +
                    "</div>" +
                    " <!-- 650*90 begin -->" +
                    "<!-- 新闻资讯 begin -->" +
                    "<div id=\"L_3\" class=\"bom_bor skinBox\">" +
                    "<div class=\"title\">" +
                    "<div class=\"inner\">" +
                    "<h3 class=\"gszl\">" +
                    "<a href=\"//stock.quote.stockstar.com/info_002230.shtml\">新闻资讯</a></h3>" +
                    "<div class=\"lr\">" +
                    "<div class=\"lr_wrap\">" +
                    "<a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=10\">公司新闻</a><a href=\"//stock.stockstar.com/stock/checkIndustry.aspx?Industry=I\">行业新闻</a>" +
                    "<a href=\"//news.stockstar.com/info/dstock.aspx?id=76&code=002230\">最新公告</a><a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=3491\">研究报告</a>" +
                    "<a href=\"//news.stockstar.com/info/dstock.aspx?id=2835&code=002230\">定期报告</a><a href=\"//stock.quote.stockstar.com/info_002230.shtml\"" +
                    "class=\"more\">更多>></a>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con\">" +
                    "<div class=\"con_left\">" +
                    "" +
                    "<!--个股公司新闻_Stock_Company_News_2018-8-6 11:46:14-->" +
                    "<h2 class=\"spe\"><a href=\"//news.stockstar.com/info/dstock.aspx?code=002230\">更多&gt;&gt;</a>公司新闻</h2><ul><li><span>08-06</span><a href=\"//stock.stockstar.com/FB2018080600000739.shtml\">场内散户资金重点关注的50只个股</a></li><li><span>08-06</span><a href=\"//stock.stockstar.com/FB2018080600000730.shtml\">主力开始撤退的50只个股</a></li><li><span>08-06</span><a href=\"//stock.stockstar.com/FB2018080600000724.shtml\">超大账户减持的50只个股</a></li><li><span>08-05</span><a href=\"//stock.stockstar.com/SS2018080500000054.shtml\">科大讯飞：酷音短视频是公司智能语音技术...</a></li><li><span>08-03</span><a href=\"//stock.stockstar.com/FB2018080300000991.shtml\">今日场内散户资金重点关注的50只个股</a></li></ul>" +
                    "" +
                    "<!--个股-新闻资讯--行业新闻-_Stock_Insdurstry_News_2018-8-6 18:29:42-->" +
                    "<h2><a href=\"//stock.stockstar.com/stock/checkIndustry.aspx?Industry=I\">更多&gt;&gt;</a>行业新闻</h2><ul><li><span>03-28</span><a href=\"//stock.stockstar.com/JC2018032800001867.shtml\">新疆今年将实现深度贫困村光纤宽带网络...</a></li><li><span>03-17</span><a href=\"//stock.stockstar.com/JC2018031900000929.shtml\">苏宁易购张近东：今年开启新十年“场景互...</a></li><li><span>03-16</span><a href=\"//stock.stockstar.com/JC2018031900000960.shtml\">东方网络拟6000万转让所持北京永旭良辰股份</a></li><li><span>05-23</span><a href=\"//stock.stockstar.com/JC2017052300001428.shtml\">接口不能共享敢称共享充电?</a></li><li><span>05-23</span><a href=\"//stock.stockstar.com/JC2017052300001544.shtml\">深圳率先开展5G试点</a></li></ul>" +
                    "</div>" +
                    "<div class=\"con_right\">" +
                    "" +
                    "<!--个股--新闻资讯--最新公告_Stock_Announcement_News_2018-8-7 2:01:33-->" +
                    "<h2 class=\"spe\"><a href=\"//news.stockstar.com/info/dstock.aspx?id=76&code=002230\">更多&gt;&gt;</a>最新公告</h2><ul><li><span>05-15</span><a href=\"//stock.stockstar.com/notice/SS2018051500001953.shtml\">三六零：拟定增不超108亿元拓展人工...</a></li><li><span>03-28</span><a href=\"//stock.stockstar.com/notice/SS2018032800002172.shtml\">科大讯飞：拟10转5派1元 葛卫东新...</a></li><li><span>11-09</span><a href=\"//stock.stockstar.com/notice/JC2017110800002110.shtml\">科大讯飞：关于签署战略合作协议的公告...</a></li><li><span>08-10</span><a href=\"//stock.stockstar.com/notice/JC2017080900001974.shtml\">科大讯飞：2017年半年度报告</a></li><li><span>08-10</span><a href=\"//stock.stockstar.com/notice/JC2017080900001973.shtml\">科大讯飞：2017年半年度报告摘要</a></li></ul>" +
                    "" +
                    "<!--个股--新闻资讯--研究报告_Stock_ResearchReport_News_2018-8-7 1:52:35-->" +
                    "<h2><a href=\"//news.stockstar.com/info/dstock.aspx?code=002230&id=3491\">更多&gt;&gt;</a>研究报告</h2><ul><li><span>05-07</span><a href=\"//stock.stockstar.com/JC2018050700000745.shtml\">科大讯飞:大投入仍在持续,短期业绩低...</a></li><li><span>05-02</span><a href=\"//stock.stockstar.com/JC2018050200001087.shtml\">浪潮信息2018年一季报点评:业绩表...</a></li><li><span>04-17</span><a href=\"//stock.stockstar.com/JC2018041700000715.shtml\">科大讯飞:平台赛道突飞猛进,AI应用...</a></li><li><span>04-04</span><a href=\"//stock.stockstar.com/JC2018040400000685.shtml\">广汽集团点评报告:自主高速增长,合资...</a></li><li><span>03-30</span><a href=\"//stock.stockstar.com/JC2018033000001886.shtml\">广汽集团:17年业绩高增长,发展格局...</a></li></ul>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\">" +
                    "</div>" +
                    "<!-- 新闻资讯 end --->" +
                    "<!-- 公司资料 begin -->" +
                    "" +
                    "<div id=\"L_3\" class=\"bom_bor skinBox\">" +
                    "<!--公司资料_Stock_CompanyInfo_2018-8-7 6:02:34-->" +
                    " <div class=\"title\">" +
                    "<div class=\"inner\">" +
                    "<h3 class=\"gszl\"><a href=\"/corp_002230.shtml\">公司资料</a></h3>" +
                    "<div class=\"lr\">" +
                    "<div class=\"lr_wrap\">" +
                    "<a href=\"/corp/brief_002230.shtml\">简介</a><a href=\"/corp/business_002230.shtml\">主营</a><a href=\"/corp/executives_002230.shtml\">高管</a><a href=\"/corp/restructure_002230.shtml\">重组</a><a href=\"/corp/litigation_002230.shtml\">诉讼</a><a href=\"/corp/sharetrade_002230.shtml\">股权</a><a href=\"/corp/invest_002230.shtml\" class=\"last\">投资</a><a href=\"/corp_002230.shtml\" class=\"more\">更多>></a></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con gszl_wrap\">" +
                    "<div class=\"info\">" +
                    "<div class=\"lf\"> " +
                    "<p><b>公司名称：</b>科大讯飞股份有限公司 <!--200000002--></p><p><b>主营业务：</b><a href='/corp/brief_002230.shtml'>人工智能技术研究、软件及芯片产品开发、知识服务 </a></p><p><b>最新总股本：</b><a href='/share/structure_002230.shtml'>208248.05万股</a></p><p><b>最新流通A股：</b><a href='/share/structure_002230.shtml'>181510.49万股</a></p><p><b>公司网址：</b>http://www.iflytek.com</p><p><b>所属板块：</b><a href=\"http://quote.stockstar.com/stock/blockperformance_5_400121968_3_1_1.html\" >融资融券标的</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400122020_3_1_1.html\" >预盈预增</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400124636_3_1_1.html\" >皖江区域</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400124719_3_1_1.html\" >车联网</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400125123_3_1_1.html\" >智慧城市</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400125265_3_1_1.html\" >机器人</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400125359_3_1_1.html\" >高校</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400126767_3_1_1.html\" >大数据</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400128426_3_1_1.html\" >智能穿戴</a>-<a href=\"http://quote.stockstar.com/stock/blockperformance_5_400128439_3_1_1.html\" >汇金概...</a></a></a></a></a></a></a></a></a></a> </p><p><span><b>董事长：</b><a target=\"_self\" gaoguanname=\"刘庆峰\" onclick=\"common_page.gaoguan(event,this);return false;\" href=\"javascript:void(0);\">刘庆峰</a></span><span>&nbsp;&nbsp;&nbsp;&nbsp;<b>董秘：</b><a target=\"_self\" gaoguanname=\"江涛\" onclick=\"common_page.gaoguan(event,this);return false;\" href=\"javascript:void(0);\">江涛</a></span></p><p><b>交易提示：</b><a href='/info/notice_002230.shtml'>科大讯飞 2018年05月31日 发布方案进度：股东大会通过，增发简述：向不超过10名特...</a></p><p><b>最新预约披露：</b>2018年一季报于2018年04月24日披露。</p> " +
                    "</div>" +
                    "<div class=\"lr bg\">" +
                    "<p><span class=\"fl\">主营业务分布</span><span class=\"fr\">报告期2017年度</span></p><div class=\"table\"><div class=\"img\"><div id=\"_FusionChart_Div_366475\" align=\"center\">----</div>" +
                    "<script type=\"text/javascript\">" +
                    "var _FusionChart_366475 = new FusionCharts(location.protocol+\"//i.ssimg.cn/images/quote2011/stockinfo/Charts_full_3_2/Pie3D.swf?34726\", \"FusionChart_Swf_366475\", \"219\", \"128\",\"0\",\"0\");" +
                    "_FusionChart_366475.setDataXML(\"<chartshowAboutMenuItem='0'showLegend='0' legendNumColumns='2' interactiveLegend='0' animation='0' defaultAnimation='1' showPercentValues='1' showPercentInToolTip='0' showLabels='0' showValues='0' slicingDistance='0' pieRadius='90' use3DLighting='1' baseFontSize='12' numberSuffix='%' decimals='2'><set label='软件和信息技术服务业'value='100' color='8ADEF0' /></chart>\");" +
                    "_FusionChart_366475.render(\"_FusionChart_Div_366475\");</script>" +
                    " </div><ul class=\"img_about\"><li><em style='background-color:#8ADEF0'></em>软件和信100.00%</li></ul></div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"clear\"></div>" +
                    "<div class=\"info2\">" +
                    " " +
                    "<div class=\"fr pj\">" +
                    "<p>最新评级</p>" +
                    "" +
                    "近三个月该公司无评级" +
                    "\t\t\t" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp; </p>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\"></div>" +
                    "" +
                    "" +
                    "<!-- 公司资料 end --->" +
                    "<!-- 资金流向 begin-->" +
                    "" +
                    "<div id=\"L_4\" class=\"bom_bor skinBox\">" +
                    "<!--资金流向_Stock_CapitalFlows_2018-8-7 9:26:04-->" +
                    "<div class=\"title\">" +
                    "<div class=\"inner\">" +
                    "<h3 class=\"zjlx\">" +
                    "<a href=\"/capital_002230.shtml\">资金流向</a></h3>" +
                    "<div class=\"lr\">" +
                    "<div class=\"lr_wrap\">" +
                    "<a href=\"/capital_002230.shtml\">资金流向分析</a><a href=\"http://quote.stockstar.com/stock/flow/industry.htm\">行业资金流向排名</a><a" +
                    "href=\"http://quote.stockstar.com/stock/flow/stockout.htm\" class=\"last\">个股资金流向排名</a><a" +
                    "href=\"/capital_002230.shtml\" class=\"more\">更多>></a></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con zjlx_wrap\">" +
                    "<div class=\"info\">" +
                    "<div class=\"left\">" +
                    "<p class=\"black\">" +
                    "<span>2018-08-06</span></p>" +
                    "" +
                    "<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#D7D6DB\"" +
                    "class=\"trHover\">" +
                    "<thead class=\"tbody_right\">" +
                    "<tr>" +
                    "<td>" +
                    "&nbsp;" +
                    "</td>" +
                    "<td>" +
                    "<p>" +
                    "买入</p>" +
                    "<p>" +
                    "(万元)</p>" +
                    "</td>" +
                    "<td>" +
                    "<p>" +
                    "卖出</p>" +
                    "<p>" +
                    "(万元)</p>" +
                    "</td>" +
                    "<td>" +
                    "<p>" +
                    "净流入</p>" +
                    "<p>" +
                    "(万元)</p>" +
                    "</td>" +
                    "<td>" +
                    "<p>" +
                    "占盘比</p>" +
                    "<p>" +
                    "(%)</p>" +
                    "</td>" +
                    "</tr>" +
                    "</thead>" +
                    "<tr><td class=\"name\">散户</td><td>14132.15</td><td>17732.26</td><td><span class=\"green\">-3600.10</span></td><td>3.96</td></tr><tr><td class=\"name\">主力</td><td>22787.99</td><td>36210.50</td><td><span class=\"green\">-13422.51</span></td><td>14.77</td></tr><tr><td class=\"name\">汇总</td><td>36920.14</td><td>53942.75</td><td><span class=\"green\">-17022.61</span></td><td>18.73</td></tr>" +
                    "</table>" +
                    "" +
                    "</div>" +
                    "<div class=\"right\">" +
                    "<div class=\"black\">" +
                    "今日资金流向" +
                    "</div>" +
                    "" +
                    "<div class=\"new-table\">" +
                    "<div class=\"img\">" +
                    " <div id=\"_FusionChart_Div_zjlx_jinri\" align=\"center\">----</div>" +
                    "<script type=\"text/javascript\">" +
                    "var _FusionChart_zjlx_jinri = new FusionCharts(location.protocol+\"//i.ssimg.cn/images/quote2011/stockinfo/Charts_full_3_2/MSColumn2D.swf?04285\", \"FusionChart_Swf_zjlx_jinri\", \"235\", \"114\",\"0\",\"0\");" +
                    "_FusionChart_zjlx_jinri.setDataXML(\"<chartshowAboutMenuItem='0' yAxisMaxValue='4' plotGradientColor='' showLegend='0' bgColor='ffffff' canvasBorderThickness='1'showBorder='0' canvasBgColor='ffffff' canvasBaseColor='ffffff' chartLeftMargin='0' formatNumberScale='0' formatNumber='0' decimals='2' numberSuffix='亿' showValues='0' baseFontSize='12'><categories><category label='主力' /><category label='散户' /></categories><dataset color='B03635' seriesName='买入(亿元)'><set value='2.28' /><set value='1.41' /></dataset><dataset color='008000' seriesName='卖出(亿元)'><set value='3.62' /><set value='1.77' /></dataset></chart>\");" +
                    "_FusionChart_zjlx_jinri.render(\"_FusionChart_Div_zjlx_jinri\");</script>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"clear\">" +
                    "</div>" +
                    "<div class=\"info2\">" +
                    "<div class=\"black\">" +
                    "近期资金流向</div>" +
                    "" +
                    "<div class=\"flow-table\">" +
                    "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">" +
                    "<tr>" +
                    "<th>" +
                    "日期" +
                    "</th>" +
                    "<th>" +
                    "主力买入(万元)" +
                    "</th>" +
                    "<th>" +
                    "主力卖出(万元)" +
                    "</th>" +
                    "<th>" +
                    "散户买入(万元)" +
                    "</th>" +
                    "<th>" +
                    "散户卖出(万元)" +
                    "</th>" +
                    "<th>" +
                    "净流入(万元)" +
                    "</th>" +
                    "</tr>" +
                    "<tr><td>2018-08-06</td><td>22787.99 </td><td>36210.50 </td><td>14132.15 </td><td>17732.26 </td><td>-17022.61 </td></tr><tr><td>2018-08-03</td><td>12484.28 </td><td>22105.43 </td><td>11772.43 </td><td>12658.02 </td><td>-10506.75 </td></tr><tr><td>2018-08-02</td><td>25096.81 </td><td>55693.39 </td><td>16865.88 </td><td>16565.97 </td><td>-30296.68 </td></tr><tr><td>2018-08-01</td><td>15662.27 </td><td>19201.35 </td><td>11668.80 </td><td>13031.63 </td><td>-4901.91 </td></tr><tr><td>2018-07-31</td><td>13513.33 </td><td>36662.36 </td><td>10529.79 </td><td>10003.44 </td><td>-22622.67 </td></tr>" +
                    "</table>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\">" +
                    "</div>" +
                    "</div>" +
                    "" +
                    "<!-- 资金流向 end--->" +
                    "<!-- 技术分析 begin-->" +
                    "" +
                    "<div id=\"L_5\" class=\"bom_bor skinBox\">" +
                    "<!--技术分析_Stock_Analysis_2018-8-7 11:02:19-->" +
                    " <div class=\"title\">" +
                    "<div class=\"inner\">" +
                    "<h3 class=\"jsfx\"><a href=\"/tech_002230.shtml\">技术分析</a></h3>" +
                    "<div class=\"lr\">" +
                    "<div class=\"lr_wrap\"><a href=\"/tech_002230.shtml\">价格分析</a><a href=\"http://quote.stockstar.com/stock/external_special_1.htm\">5日金叉</a><a href=\"http://quote.stockstar.com/stock/external_special_8.htm\">连续上涨 </a><a href=\"http://quote.stockstar.com/stock/external_special_23.htm\" class=\"last\">向上跳空</a><a href=\"/tech_002230.shtml\" class=\"more\">更多>></a></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con jsfx_wrap\">" +
                    "<div class=\"info\">" +
                    "<div class=\"left\">" +
                    "<p class=\"black\">价格分析</p>" +
                    "<table width=\"100%\"border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#D7D6DB\" class=\"trHover\">" +
                    "<thead>" +
                    "<tr>" +
                    "<td>&nbsp;</td>" +
                    "<td>5日涨幅</td>" +
                    "<td>10日涨幅</td>" +
                    "<td>20日涨幅</td>" +
                    "</tr>" +
                    "</thead>" +
                    "<tr><td class=\"name\">科大讯飞</td><td>-14.33% </td><td>-14.78% </td><td>-13.58% </td></tr>" +
                    "<tr><td class=\"name\">信息技术业</td><td>-8.58% </td><td>-12.04% </td><td>-11.78% </td></tr>" +
                    "</table>" +
                    "<div class=\"h_5\"></div>" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp;近5日均价为29.83元， 最高价为32.15元，最低价为27.28元，平均涨跌幅为-14.33%，平均成交量为264052手。</p><div class=\"h_5\"></div>" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp;近5日行业平均涨跌幅为-8.58%，最高涨幅为21.64%，最低涨幅为-42.27%。</p>" +
                    "</div>" +
                    "<div class=\"right\">" +
                    "<div class=\"black\"><span class=\"fl\">指标分析</span><span class=\"fr\">2018-08-06</span></div>" +
                    "<div class=\"table\">" +
                    "<div class=\"img\"> <div id=\"_FusionChart_Div_636375\" align=\"center\">----</div>" +
                    "<script type=\"text/javascript\">" +
                    "var _FusionChart_636375 = new FusionCharts(location.protocol+\"//i.ssimg.cn/images/quote2011/stockinfo/Charts_full_3_2/Pie3D.swf?19952\", \"FusionChart_Swf_636375\", \"130\", \"130\",\"0\",\"0\");" +
                    "_FusionChart_636375.setDataXML(\"<chartshowAboutMenuItem='0'showLegend='0' legendNumColumns='2' interactiveLegend='0' animation='0' slicingDistance='0' bgColor='ffffff' pieRadius='60' showBorder='0' showPercentValues='1' showPercentInToolTip='0' showLabels='0' showValues='0' decimals='2' numberSuffix='个' use3DLighting='1' showShadow='0' showZeroPies='0' baseFontSize='12' ><set label='不确定'value='15' color='8ADEF0' /><set label='空头排列'value='4' color='026ECE' /><set label='多头排列'value='2' color='FF9451' /></chart>\");" +
                    "_FusionChart_636375.render(\"_FusionChart_Div_636375\");</script>" +
                    "</div>" +
                    "<ul><li><em style='background:#8ADEF0;'></em><span>不确定</span></li><li><em style='background:#026ECE;'></em><span>空头排列</span></li><li><em style='background:#FF9451;'></em><span>多头排列</span></li></ul>" +
                    "</div>" +
                    "<div class=\"img_about\">" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp;目前17个指标中，有2个指标为多头排列，4个指标为空头排列，15个指标为不确定。</p><p><!--科大讯飞-->&nbsp;&nbsp;&nbsp;&nbsp;昨日主要技术指标：MACD为空头排列，RSI为不确定，KDJ为多头排列。</p></div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"clear\"></div>" +
                    "<div class=\"info2\">" +
                    "<div class=\"lf\">" +
                    "<div class=\"black\"><span class=\"fl\">成本统计</span><span class=\"fr\"></span></div>" +
                    "<table width=\"100%\"border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#D7D6DB\" class=\"trHover\">" +
                    "<thead>" +
                    "<tr>" +
                    "<td>&nbsp;</td>" +
                    "<td><p>主力平均</p>" +
                    "<p>成本</p></td>" +
                    "<td>盈亏(%)</td>" +
                    "<td><p>散户平均</p>" +
                    "<p>成本</p></td>" +
                    "<td> 盈亏(%)</td>" +
                    "</tr>" +
                    "</thead>" +
                    "" +
                    "</table>" +
                    "</div>" +
                    "<div class=\"lr\">" +
                    "<div class=\"black\"> <spanclass=\"fl\">5日内筹码统计</span> <span class=\"fr\"></span> </div>" +
                    "<div class=\"table\">" +
                    "<div class=\"img\"></div>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\"></div>" +
                    "" +
                    "<!-- 技术分析 end--->" +
                    "<!-- 融资分红 begin-->" +
                    "" +
                    "<div id=\"L_6\" class=\"bom_bor skinBox\">" +
                    "<!--融资分红_Stock_DivStat_2018-8-7 6:49:54-->" +
                    "<div class=\"title\">" +
                    "<div class=\"inner\">" +
                    "<h3 class=\"rzfh\"><a href=\"/dividend_002230.shtml\">融资分红</a></h3>" +
                    "<div class=\"lr\">" +
                    "<div class=\"lr_wrap\"><a href=\"/dividend/plan_002230.shtml\">预案</a><a href=\"/dividend/financing_002230.shtml\">新股</a><a href=\"/dividend/bonus_002230.shtml\">分红</a><a href=\"/dividend/bonus_002230.shtml\">送股</a><a href=\"/dividend/financing_002230.shtml\">增发</a><a class=\"last\" href=\"/dividend/financing_002230.shtml\">转债</a><a href=\"/dividend_002230.shtml\" class=\"more\">更多>></a></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con rzfh_wrap\">" +
                    "<div class=\"info\">" +
                    "" +
                    " " +
                    "<div class=\"lf\">" +
                    "<div class=\"black\"> <span class=\"fl\">历史发行分配记录</span></div>" +
                    "<table width=\"100%\"border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#D7D6DB\" class=\"trHover\">" +
                    "<thead class=\"thead_2\">" +
                    "<tr>" +
                    "<td>除权日</td>" +
                    "<td><p>分红</p>" +
                    "<p>(每10股)</p></td>" +
                    "<td><p>送股</p>" +
                    "<p>(每10股)</p></td>" +
                    "<td><p>转增股</p><p>(每10股)</p></td>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody class=\"tbody_right\">" +
                    "<tr><td class=\"align_center\">2018-05-14</td><td>1.00</td><td>--</td><td>5.00</td></tr><tr><td class=\"align_center\">2017-04-21</td><td>1.00</td><td>--</td><td>--</td></tr><tr><td class=\"align_center\">2016-04-19</td><td>1.00</td><td>--</td><td>--</td></tr><tr><td class=\"align_center\">2015-04-16</td><td>1.50</td><td>--</td><td>5.00</td></tr><tr><td class=\"align_center\">2014-04-16</td><td>2.00</td><td>--</td><td>7.00</td></tr><tr><td class=\"align_center\">2013-09-10</td><td>1.50</td><td>--</td><td>--</td></tr>" +
                    "</tbody>" +
                    "</table>" +
                    "<div class=\"h_5\"></div>" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp;科大讯飞上市以来，共分红10次，共分红8.15亿元。</p>" +
                    "</div>" +
                    "" +
                    "" +
                    "<div class=\"lr\">" +
                    "<div class=\"img\"><div id=\"_FusionChart_Div_rongzifenhong\" align=\"center\">----</div>" +
                    "<script type=\"text/javascript\">" +
                    "var _FusionChart_rongzifenhong = new FusionCharts(location.protocol+\"//i.ssimg.cn/images/quote2011/stockinfo/Charts_full_3_2/Bar2D.swf?54288\", \"FusionChart_Swf_rongzifenhong\", \"290\", \"65\",\"0\",\"0\");" +
                    "_FusionChart_rongzifenhong.setDataXML(\"<chartshowAboutMenuItem='0'plotGradientColor='' alternateVGridColor='ffffff' chartTopMargin='0' chartBottomMargin='0' showBorder='0' bgColor='ffffff' canvasBorderThickness='1'decimals='2'showValues='0' yAxisValuesPadding='10' baseFontSize='12'><set color='BBCCEE' label='科大讯飞' value='16.338459' /><set color='F0F2AC'label='市场平均' value='54.156244793161910144108505227' /></chart>\");" +
                    "_FusionChart_rongzifenhong.render(\"_FusionChart_Div_rongzifenhong\");</script>" +
                    " </div>" +
                    "<ul class=\"img_about\">" +
                    "<li class=\"color_1\"><em></em>科大讯飞</li>" +
                    "<li class=\"color_2\"><em></em>市场平均</li>" +
                    "</ul>" +
                    "<div class=\"h_5\"></div>" +
                    "<div class=\"img_info\">" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp; 科大讯飞上市以来总计向公司股东派现8.15亿元；募资共49.90亿元。</p><p>&nbsp;&nbsp;&nbsp;&nbsp; 派现金额占募资金额的16.34%，在全部A股中名列第1840位，低于市场平均水平。</p>" +
                    "<p><a href='/dividend/plan_002230.shtml'>2018年期间分配预案</a>:以公司现有总股本138832.0328万股为基数,每10股转增5股并派发现金红利1...</p>" +
                    "</div>" +
                    "</div>" +
                    "" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\"></div>" +
                    "" +
                    "<!-- 融资分红 end--->" +
                    "<!-- 股本股东 begin -->" +
                    "" +
                    "<div id=\"L_7\" class=\"bom_bor skinBox\">" +
                    "<!--股本股东_Stock_CapitalStockStruct_2018-8-7 5:46:46-->" +
                    "<div class=\"title\">" +
                    "<div class=\"inner\">" +
                    "<h3 class=\"gbgd\"><a href=\"/share_002230.shtml\">股本股东</a></h3>" +
                    "<div class=\"lr\">" +
                    "<div class=\"lr_wrap\"><a href=\"/share/structure_002230.shtml\">股本</a><a href=\"/share/holdertop10_002230.shtml\">股东</a><a href=\"/share/restricted_002230.shtml\">限售</a><a href=\"/share/fund_002230.shtml\">基金持仓</a><a href=\"/share/ownership_002230.shtml\" class=\"last\">高管持股</a><a href=\"/share_002230.shtml\" class=\"more\">更多>></a></div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"con gbgd_wrap\">" +
                    "<div class=\"info\">" +
                    "<div class=\"lf\">" +
                    "<div class=\"black\"> 截至2018-05-14，股本构成为： </div>" +
                    "<table width=\"100%\"border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#D7D6DB\" class=\"trHover\">" +
                    "<thead>" +
                    "<tr>" +
                    "<td width=\"50%\"></td>" +
                    "<td class=\"align_right\"><p>股本数量</p><p>(万股)</p> </td>" +
                    "</tr>" +
                    "</thead>" +
                    "<tr><td width=\"100\" class=\"name\">流通股</td><td>181510.49</td></tr><tr><td class=\"name\">&nbsp;&nbsp;流通A股</td><td>181510.49</td></tr><tr><td class=\"name\">&nbsp;&nbsp;流通B股 </td><td>0.00</td></tr><tr><td class=\"name\">&nbsp;&nbsp;流通H股</td><td>0.00</td></tr><tr><td class=\"name\">&nbsp;&nbsp;其他</td><td> 0.00</td></tr><tr><td class=\"name\">非流通股</td><td>26737.56</td></tr><tr><td class=\"name\">合计</td><td>208248.05</td></tr>" +
                    "</table>" +
                    "</div>" +
                    "<div class=\"lr\">" +
                    "<div class=\"black\">股本构成</div>" +
                    "<div class=\"table\">" +
                    "\t<div class=\"img\"><div id=\"_FusionChart_Div_gubengoucheng_1\" align=\"center\">----</div>" +
                    "<script type=\"text/javascript\">" +
                    "var _FusionChart_gubengoucheng_1 = new FusionCharts(location.protocol+\"//i.ssimg.cn/images/quote2011/stockinfo/Charts_full_3_2/Pie3D.swf?46392\", \"FusionChart_Swf_gubengoucheng_1\", \"172\", \"171\",\"0\",\"0\");" +
                    "_FusionChart_gubengoucheng_1.setDataXML(\"<chartshowAboutMenuItem='0'showLegend='0' legendNumColumns='2' interactiveLegend='0' animation='0' slicingDistance='0' bgColor='ffffff' pieRadius='70' showBorder='0' showPercentValues='1' showPercentInToolTip='0' showLabels='0' showValues='0' decimals='2' numberSuffix='亿' use3DLighting='1' showShadow='0' showZeroPies='0' baseFontSize='12' ><set label='流通A股' color='8ADEF0' value='18.15'/><set label='流通B股' color='026ECE' value='0'/><set label='流通H股' color='FF9451' value='0'/><set label='非流通股' color='0F9494' value='2.67'/></chart>\");" +
                    "_FusionChart_gubengoucheng_1.render(\"_FusionChart_Div_gubengoucheng_1\");</script>" +
                    " </div>" +
                    "<ul class=\"img_about\">" +
                    "<li class=\"color_1\"><em style='background:#8ADEF0;'></em>流通A股</li>" +
                    "<li class=\"color_2\"><em style='background:#026ECE;'></em>流通B股</li>" +
                    "<li class=\"color_3\"><em style='background:#FF9451;'></em>流通H股</li>" +
                    "<li class=\"color_4\"><em style='background:#0F9494;'></em>非流通股</li>" +
                    "</ul>" +
                    " \t </div>" +
                    "</div>" +
                    "</div>" +
                    "" +
                    "<div class=\"info2\">" +
                    "<div class=\"black\"><span class=\"fl\">十大流通股东</span><span class=\"fr\">报告期：2018年第一季度</span> </div>" +
                    "<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bordercolor=\"#d7d6db\" class=\"trHover\">" +
                    "<thead>" +
                    "<tr class=\"tbody_right\">" +
                    "<td width=\"240\" class=\"align_left\">股东名称</td>" +
                    "<td width=\"100\">持股数量(万股)</td>" +
                    "<td width=\"100\">占总股本比例(%)</td>" +
                    "<td width=\"120\">上期持股变化(万股)</td>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody class=\"tbody_right\">" +
                    "<tr><td class=\"name\">中国移动通信有限公司</td><td>17919.85</td><td>12.90</td><td>0.00</td></tr><tr><td class=\"name\">中科大资产经营有限责任公司</td><td>5566.52</td><td>4.01</td><td>0.00</td></tr><tr><td class=\"name\">刘庆峰</td><td>2626.68</td><td>1.89</td><td>0.00</td></tr><tr><td class=\"name\">王仁华</td><td>2379.71</td><td>1.71</td><td>0.00</td></tr><tr><td class=\"name\">葛卫东</td><td>2358.10</td><td>1.70</td><td>0.00</td></tr><tr><td class=\"name\">香港中央结算有限公司</td><td>2262.87</td><td>1.63</td><td>434.64</td></tr><tr><td class=\"name\">中央汇金资产管理有限责任公司</td><td>1762.49</td><td>1.27</td><td>0.00</td></tr><tr><td class=\"name\">王萍</td><td>616.91</td><td>0.44</td><td>0.00</td></tr><tr><td class=\"name\">郭超</td><td>574.07</td><td>0.41</td><td>-25.50</td></tr><tr><td class=\"name\">莫建军</td><td>487.76</td><td>0.35</td><td>-70.24</td></tr>" +
                    "</tbody>" +
                    "</table>" +
                    "<span >&nbsp;&nbsp;&nbsp;&nbsp;截至2018年03月31日，有1个股东增持，共增持434.64万股，占总股本的0.21%，有3个股东减持，共减持95.74万股，占总股本的0.05%。</span> " +
                    "</div>" +
                    "" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"h_7\"></div>" ;
}
