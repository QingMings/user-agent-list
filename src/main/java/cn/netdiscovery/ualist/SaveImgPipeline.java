package cn.netdiscovery.ualist;

import cn.netdiscovery.core.Spider;
import cn.netdiscovery.core.domain.Request;
import cn.netdiscovery.core.domain.ResultItems;
import cn.netdiscovery.core.downloader.file.FileDownloadAfterRequest;
import cn.netdiscovery.core.pipeline.Pipeline;
import cn.netdiscovery.core.vertx.VertxUtils;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class SaveImgPipeline extends Pipeline {
    @Override
    public void process(ResultItems resultItems) {
        String img_url = resultItems.get("img");
        String name = resultItems.get("fileName");
        if (img_url.equals("noFound")) {
            VertxUtils.getVertx().fileSystem().writeFile("nofound/"+name, Buffer.buffer(name), result -> {

                if(result.succeeded()) {

                    System.out.println("File written");
                }
            });

        } else {
            saveRemoteImage(img_url, Paths.get("").toAbsolutePath() + "/img/" + name);
        }
    }

    private boolean saveRemoteImage(String imgUrl, String filePath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            URL url = new URL(imgUrl);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);

            in = connection.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            out = new FileOutputStream(filePath);
            while ((len = in.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                out.flush();
                out.close();
                in.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
