package audio.parse;

import cn.netdiscovery.core.Spider;
import cn.netdiscovery.core.domain.HttpRequestBody;
import cn.netdiscovery.core.domain.Request;
import cn.netdiscovery.ualist.AliPdfParser;
import cn.netdiscovery.ualist.SaveImgPipeline;
import io.vertx.core.http.HttpMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 《庆余年》 m4a 下载
 * 1~ 751 集
 * 地址 : https://ting55.com/book/13679
 * api: https://ting55.com/glink
 * export http_proxy=http://127.0.0.1:1087;export https_proxy=http://127.0.0.1:1087;
 */
public class QingYuNianM4A {

    public static void main(String[] args) {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", "127.0.0.1");
        System.getProperties().put("proxyPort", "1087");
        Request[] requests=  Arrays.asList(data()).stream().map(s ->{
            Request request = new Request("https://ting55.com/glink?"+s, "tom");
            request.header("accept","application/json, text/javascript, */*; q=0.01");
            request.header("accept-language","accept-language\":\"zh-CN,zh;q=0.9,en;q=0.8");
            request.header("content-type","application/x-www-form-urlencoded; charset=UTF-8");
            request.header("x-requested-with","XMLHttpRequest");
            request.header("Referer","https://ting55.com/book/13679-1");
            request.header("Connection","keep-alive");
            request.header("Accept","application/json, text/javascript, */*; q=0.01");
            request.header("xt","0d36f3d2b9c561ec72796416f8037fea");
            request.httpMethod(HttpMethod.POST);
            request.ua("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            request.addCookie("JSESSIONID=FCE663B350E935B4EA78DB3BBD6A948C");
            Map<String,Object> map = new HashMap<>();
            map.put("bookId","13679");
            map.put("isPay","0");
            map.put("page",s);
            request.httpRequestBody(HttpRequestBody.form(map,"UTF-8"));
            request.putExtra("fileName",s);
            request.sleep(16000);
//            request.downloadDelay(3000);
//            request.domainDelay(3000);
            return  request;
        }).collect(Collectors.toList()).toArray(new Request[]{});
        Spider.create().name("tom").request(requests)
                .parser(new AudioParser())
                .pipeline(new SaveAudioPipline())
                .run();
    }

    private static String[] data(){
        return IntStream.range(288,289).mapToObj(va -> Integer.toString(va)).collect(Collectors.toList()).toArray(new String[0]);
    }
}
