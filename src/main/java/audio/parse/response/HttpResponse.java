package audio.parse.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tony on 2019-05-28.
 */
@Data
public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = -2380590440348557129L;

    private String msg;
    private int code;
    private T data;
    private int currpage;
    private int maxpage;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCurrpage() {
        return currpage;
    }

    public void setCurrpage(int currpage) {
        this.currpage = currpage;
    }

    public int getMaxpage() {
        return maxpage;
    }

    public void setMaxpage(int maxpage) {
        this.maxpage = maxpage;
    }
}