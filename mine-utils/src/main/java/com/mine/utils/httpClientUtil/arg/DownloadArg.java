package com.mine.utils.httpClientUtil.arg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jiangqd on 2019/1/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadArg extends BaseArg {

    private String mediaId;

}
