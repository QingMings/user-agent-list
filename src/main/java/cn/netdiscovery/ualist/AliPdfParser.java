package cn.netdiscovery.ualist;

import cn.netdiscovery.core.domain.Page;
import cn.netdiscovery.core.domain.ResultItems;
import cn.netdiscovery.core.parser.Parser;
import cn.netdiscovery.core.utils.URLParser;

public class AliPdfParser implements Parser {
    @Override
    public void process(Page page) {
        ResultItems resultItems = page.getResultItems();
//        String imgUrl=page.getHtml().xpath("//div[@class='side-content']/div[@class='summary-pic']/a/img/@src").toString();
        resultItems.put("img",page.getUrl() ==null ? "noFound": page.getUrl());
        URLParser parser = page.getRequest().getUrlParser();

        resultItems.put("fileName",parser.getPath().substring(parser.getPath().lastIndexOf("/")+1,parser.getPath().length())+".png");
    }
}
