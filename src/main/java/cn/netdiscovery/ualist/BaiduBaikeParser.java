package cn.netdiscovery.ualist;

import cn.netdiscovery.core.domain.Page;
import cn.netdiscovery.core.domain.ResultItems;
import cn.netdiscovery.core.parser.Parser;
import cn.netdiscovery.core.utils.URLParser;

public class BaiduBaikeParser implements Parser {
    @Override
    public void process(Page page) {

        ResultItems resultItems = page.getResultItems();
        String imgUrl=page.getHtml().xpath("//div[@class='side-content']/div[@class='summary-pic']/a/img/@src").toString();
        resultItems.put("img",imgUrl ==null ? "noFound": imgUrl);
        String suffix =  imgUrl ==null ? ".txt":imgUrl.substring(imgUrl.lastIndexOf("."));
        System.out.println(suffix);
        URLParser parser = page.getRequest().getUrlParser();

        resultItems.put("fileName",parser.getPath().substring(6)+suffix);
    }
}
