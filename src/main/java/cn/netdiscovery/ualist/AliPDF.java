package cn.netdiscovery.ualist;

import cn.netdiscovery.core.Spider;
import cn.netdiscovery.core.domain.Request;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AliPDF {



    public static void main(String[] args) {

        Request[] requests=  Arrays.asList(data()).stream().map(s -> new Request("https://acforum.oss-cn-hangzhou.aliyuncs.com/activities/2019-04-19/ccaf08dd-03f2-46d1-9f89-ccda1e41b6fe/doc/I/"+s,"tom")).collect(Collectors.toList()).toArray(new Request[]{});
        Spider.create().name("tom").request(requests)
                .parser(new AliPdfParser())
                .pipeline(new SaveImgPipeline())
                .run();
    }

    private static String[] data(){
       return IntStream.range(1,40).mapToObj(va -> Integer.toString(va)).collect(Collectors.toList()).toArray(new String[0]);
    }
}
