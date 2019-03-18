package com.mine.util.httpClientUtil.arg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jiangqd on 2019/1/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadArg extends BaseArg {

    private String type;
    private byte[] media;
    private String fileName;

}
