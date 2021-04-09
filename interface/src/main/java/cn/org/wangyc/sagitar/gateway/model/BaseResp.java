package cn.org.wangyc.sagitar.gateway.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Wangyc
 */
@Getter
@Setter
public class BaseResp implements Serializable {

    private static final long serialVersionUID = -124651859938926986L;

    public static final int CODE_SUCCESS = 1000;
    public static final int CODE_FAILED = 9999;
    public static final String MSG_SUCCESS = "成功";
    public static final String MSG_OTHER_ERROR = "其他错误";

    private Integer code;

    private String msg;

    public BaseResp(){
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
    }

    public BaseResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isCodeOk() {
        return this.code != null && this.code.equals(CODE_SUCCESS);
    }

    public void otherError(String errMsg) {
        this.code = CODE_FAILED;
        if (errMsg != null) {
            this.msg = errMsg;
        } else {
            this.msg = MSG_OTHER_ERROR;
        }
    }

    public void otherError(Exception e) {
        String errMsg = null;
        if (e != null) {
            errMsg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        }
        this.otherError(errMsg);
    }
}
