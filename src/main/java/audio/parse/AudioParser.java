package audio.parse;

import audio.parse.response.ExchangeResponse;
import cn.netdiscovery.core.config.Constant;
import cn.netdiscovery.core.domain.Page;
import cn.netdiscovery.core.domain.ResultItems;
import cn.netdiscovery.core.parser.Parser;
import cn.netdiscovery.core.utils.SerializableUtils;
import cn.netdiscovery.core.utils.URLParser;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AudioParser implements Parser {
    Logger logger = LoggerFactory.getLogger(AudioParser.class);
    @Override
    public void process(Page page) {
        ResultItems resultItems = page.getResultItems();
        String jsonStr = page.getHtml().getDocument().getElementsByTag("body").eachText().get(0);
           JsonObject jsonObject = new JsonObject(jsonStr);
        String audioSrc = jsonObject.getString("ourl");
        logger.info("音频地址："+ audioSrc);

        resultItems.put("audio",audioSrc==null ? "noFound": audioSrc);
        String fileName = (String) page.getRequest().getExtra("fileName");
        resultItems.put("fileName",fileName+".m4a");

    }
}
