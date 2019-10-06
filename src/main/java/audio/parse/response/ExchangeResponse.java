package audio.parse.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class ExchangeResponse extends HttpResponse<ExchangeResponse.Exchange> {

    @Data
    public static class Exchange implements Serializable {
        public Exchange() {
        }

        public Exchange(String ourl, String plink, String status, String url) {
            this.ourl = ourl;
            this.plink = plink;
            this.status = status;
            this.url = url;
        }

        private String ourl;
        private String plink;
        private String status;
        private String url;

        public String getOurl() {
            return ourl;
        }

        public void setOurl(String ourl) {
            this.ourl = ourl;
        }

        public String getPlink() {
            return plink;
        }

        public void setPlink(String plink) {
            this.plink = plink;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}