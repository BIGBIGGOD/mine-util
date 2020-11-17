package com.mine.utils.httpClientUtil.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jiangqd on 2019/1/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadResult extends CommonResult{

    private byte[] imgByte;
    private String mediaName;

    public DownloadResult(Integer errorCode, String errorMessage,byte[] imgByte, String mediaName) {
        super(errorCode,errorMessage);
        this.imgByte = imgByte;
        this.mediaName = mediaName;
    }

    public DownloadResult(Integer errorCode, String errorMessage) {
        super(errorCode,errorMessage);
    }

}
