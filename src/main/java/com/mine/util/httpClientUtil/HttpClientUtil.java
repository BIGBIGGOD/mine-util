package com.mine.util.httpClientUtil;

import com.google.gson.Gson;
import com.mine.common.enums.CommonEnum;
import com.mine.common.exception.BasesException;
import com.mine.util.httpClientUtil.arg.Arg;
import com.mine.util.httpClientUtil.arg.DownloadArg;
import com.mine.util.httpClientUtil.arg.UploadArg;
import com.mine.util.httpClientUtil.result.CommonResult;
import com.mine.util.httpClientUtil.result.DownloadResult;
import com.mine.util.httpClientUtil.result.HttpResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description HttpClient工具类
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
public class HttpClientUtil {

    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";

    static {
        //创建HttpClient对象
        httpClient = createSSLClientDefault();
    }

    /**
     * @Description 创建HttpClient对象
     * RequestConfig 配置请求超时方式及时间
     * setConnectTimeout -建立与远程主机的连接的时间
     * setSocketTimeout -的时间等待数据-建立连接之后; 两个数据包之间不活动的最长时间
     * Connection Manager Timeout -connect Manager获取Connection 超时时间。单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * SSLConnectionSocketFactory 配置SSL实现网站https化
     */
    public static CloseableHttpClient createSSLClientDefault() {

        RequestConfig config = RequestConfig.custom().setConnectTimeout(5 * 60 * 1000).setSocketTimeout(1 * 60 * 1000).build();

        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf =
                new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            return HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config).build();
        } catch (Exception e) {
            log.error("init httpClient error, details:", e);
        }

        return HttpClients.createDefault();
    }

    /**
     * @param url
     * @param arg
     * @throws BasesException
     * @Description 根据json发送普通post请求
     */
    public static <T extends CommonResult> T sendPostByJson(String url, Arg arg, Class<T> clazz, String contentType) {

        HttpResponseResult httpResponseResult = null;

        if (StringUtils.isEmpty(url)) {
            log.info("url={}", url);
            httpResponseResult = new HttpResponseResult(CommonEnum.PARAMS_ERROR.getCode(), CommonEnum.PARAMS_ERROR.getMessage());
            return getResult(httpResponseResult, clazz);
        }

        String paramsJson = new Gson().toJson(arg);
        log.info("sendPostByJson,url={},paramsJson={}", url, paramsJson);
        HttpEntity entity = null;
        StringEntity stringEntity = new StringEntity(paramsJson, CHARSET);

        CloseableHttpResponse response = null;
        try {
            HttpUriRequest request = RequestBuilder.post()
                .setUri(url)
                .setHeader(HttpHeaders.CONTENT_TYPE, contentType)
                .setEntity(stringEntity)
                .build();

            response = httpClient.execute(request);

            int httpCode = response.getStatusLine().getStatusCode();
            entity = response.getEntity();
            //判断接口连接是否成功，与具体请求结果正确与否无关
            if (httpCode == HttpStatus.OK.value() && entity != null) {
                //{"corpAccessToken":"AF53033FA47A636AD424B893F4FC0B66","corpId":"FSCID_4A1B1DF46B4C06FBD15A334F92596A2E","expiresIn":4328,"errorCode":0,"errorMessage":"success"}
                httpResponseResult = new HttpResponseResult(httpCode, null, EntityUtils.toString(entity, CHARSET));
            } else {
                httpResponseResult = new HttpResponseResult(httpCode, HttpStatus.valueOf(httpCode).getReasonPhrase(), null);
            }

        } catch (IOException e) {
            log.info("IOException={}", e);
            httpResponseResult = new HttpResponseResult(CommonEnum.INTERFAC_EERROR.getCode(), CommonEnum.INTERFAC_EERROR.getMessage());
            return getResult(httpResponseResult, clazz);
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                log.info("资源关闭异常，e={}", e);
            }
        }
        return getResult(httpResponseResult, clazz);
    }

    /**
     * @param url
     * @param list
     * @Description 根据list发送普通post请求
     * 注意其中list集合的创建，如下:
     *  List<NameValuePair> dataList = new ArrayList<NameValuePair>();
     *  dataList.add(new BasicNameValuePair("address", address));
     */
    public static <T extends CommonResult> T sendPostByList(String url, List<NameValuePair> list, Class<T> clazz, String contentType) throws UnsupportedEncodingException {

        HttpResponseResult httpResponseResult = null;

        if (StringUtils.isEmpty(url)) {
            log.info("url={}", url);
            httpResponseResult = new HttpResponseResult(CommonEnum.PARAMS_ERROR.getCode(), CommonEnum.PARAMS_ERROR.getMessage());
            return getResult(httpResponseResult, clazz);
        }

        log.info("sendPostByJson,url={},paramsJson={}", url, list);
        HttpEntity entity = null;
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, CHARSET);

        CloseableHttpResponse response = null;
        try {
            HttpUriRequest request = RequestBuilder.post()
                .setUri(url)
                .setHeader(HttpHeaders.CONTENT_TYPE, contentType)
                .setEntity(urlEncodedFormEntity)
                .build();

            response = httpClient.execute(request);

            int httpCode = response.getStatusLine().getStatusCode();
            entity = response.getEntity();
            //判断接口连接是否成功，与具体请求结果正确与否无关
            if (httpCode == HttpStatus.OK.value() && entity != null) {
                //{"corpAccessToken":"AF53033FA47A636AD424B893F4FC0B66","corpId":"FSCID_4A1B1DF46B4C06FBD15A334F92596A2E","expiresIn":4328,"errorCode":0,"errorMessage":"success"}
                httpResponseResult = new HttpResponseResult(httpCode, null, EntityUtils.toString(entity, CHARSET));
            } else {
                httpResponseResult = new HttpResponseResult(httpCode, HttpStatus.valueOf(httpCode).getReasonPhrase(), null);
            }

        } catch (IOException e) {
            log.info("IOException={}", e);
            httpResponseResult = new HttpResponseResult(CommonEnum.INTERFAC_EERROR.getCode(), CommonEnum.INTERFAC_EERROR.getMessage());
            return getResult(httpResponseResult, clazz);
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                log.info("资源关闭异常，e={}", e);
            }
        }
        return getResult(httpResponseResult, clazz);
    }

    /**
     * @param httpResponseResult
     * @param clazz              返回结果类类型
     * @Description 解析普通Post请求返回信息再返回详细信息
     */
    public static <T extends CommonResult> T getResult(HttpResponseResult httpResponseResult, Class<T> clazz) {

        T t = null;
        Gson gson = new Gson();

        try {
            if (httpResponseResult.getErrorCode() == HttpStatus.OK.value()) {
                t = gson.fromJson(httpResponseResult.getContent(), clazz);
            } else {
                t = gson.fromJson(gson.toJson(httpResponseResult), clazz);
            }

        } catch (Exception e) {
            log.error("getResult error,exception={}", e);
        }
        return t;
    }

    /**
     * @param url
     * @param uploadArg
     * @param clazz
     * @param <T>
     * @return
     * @Description 文件上传
     */
    public static <T extends CommonResult> T fileUpload(String url, UploadArg uploadArg, Class<T> clazz) {

        //设置参数
        HttpResponseResult httpResponseResult = null;
        HttpEntity entity = null;

        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.RFC6532)
                .addPart("media", new ByteArrayBody(uploadArg.getMedia(), ContentType.MULTIPART_FORM_DATA, uploadArg.getFileName()))
                .addTextBody("corpAccessToken", uploadArg.getCorpAccessToken())
                .addTextBody("corpId", uploadArg.getCorpId())
                .addTextBody("type", uploadArg.getType());
            entity = builder.build();
        } catch (Exception e) {
            log.error("MultipartEntityBuilder is wrong,uploadArg={}", uploadArg);
            httpResponseResult = new HttpResponseResult(CommonEnum.FAILURE.getCode(), CommonEnum.FAILURE.getMessage());
            return getResult(httpResponseResult, clazz);
        }

        CloseableHttpResponse response = null;
        try {
            HttpUriRequest request = RequestBuilder.post()
                .setUri(url)
                .setEntity(entity)
                .build();

            response = httpClient.execute(request);

            int httpCode = response.getStatusLine().getStatusCode();
            entity = response.getEntity();
            //判断接口连接是否成功，与具体请求结果正确与否无关
            if (httpCode == HttpStatus.OK.value() && entity != null) {
                httpResponseResult = new HttpResponseResult(httpCode, null, EntityUtils.toString(entity, CHARSET));
            } else {
                httpResponseResult = new HttpResponseResult(httpCode, HttpStatus.valueOf(httpCode).getReasonPhrase(), null);
            }

        } catch (IOException e) {
            log.error("httpClient.execute(request) is wrong,exception={}", e);
            httpResponseResult = new HttpResponseResult(CommonEnum.FAILURE.getCode(), CommonEnum.FAILURE.getMessage());
            return getResult(httpResponseResult, clazz);
        }

        return getResult(httpResponseResult, clazz);

    }

    public static DownloadResult fileDownload(String url, DownloadArg downloadArg) {

        //设置参数
        DownloadResult downloadResult = null;
        HttpResponseResult httpResponseResult = null;
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        String fileName = null;
        InputStream inStream = null;
        StringEntity stringEntity = new StringEntity(new Gson().toJson(downloadArg), CHARSET);

        try {
            HttpUriRequest request = RequestBuilder.post()
                .setUri(url)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setEntity(stringEntity)
                .build();

            response = httpClient.execute(request);

            int httpCode = response.getStatusLine().getStatusCode();
            entity = response.getEntity();

            //判断接口连接是否成功，与具体请求结果正确与否无关
            if (httpCode == HttpStatus.OK.value() && entity != null) {
                //获取文件名称
                Header[] filenames = response.getAllHeaders();
                for (Header header : filenames) {
                    if ("Content-Disposition".equals(header.getName())) {
                        HeaderElement[] headEls = header.getElements();
                        for (HeaderElement hm : headEls) {
                            if (hm.getName().equals("attachment")) {
                                NameValuePair[] namVal = hm.getParameters();
                                for (NameValuePair pair : namVal) {
                                    if ("filename".equals(pair.getName())) {
                                        fileName = URLDecoder.decode(pair.getValue(), StandardCharsets.UTF_8.toString());
                                    }
                                }
                            }
                        }
                    }
                }

                if (StringUtils.isNotEmpty(fileName)) {
                    //下载附件
                    int index = 0;
                    inStream = entity.getContent();
                    int count = 0;
                    //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
                    List<Integer> imbbyteLst = new ArrayList<>();
                    byte[] imgbyteTmp = new byte[1];
                    while ((index = inStream.read()) != -1) {
                        imbbyteLst.add(index);
                        count++;
                    }
                    byte[] imgbyte = new byte[count];
                    int idx = 0;
                    for (int bytes : imbbyteLst) {
                        imgbyte[idx] = (byte) bytes;
                        idx++;
                    }
                    //配置返回参数
                    return new DownloadResult(CommonEnum.SUCCESS.getCode(), CommonEnum.SUCCESS.getMessage(), imgbyte, fileName);
                } else {
                    return new Gson().fromJson(EntityUtils.toString(entity, CHARSET), DownloadResult.class);
                }

            } else {
                return new DownloadResult(httpCode, HttpStatus.valueOf(httpCode).getReasonPhrase());
            }

        } catch (IOException e) {
            log.error("httpClient.download(request) is wrong,exception={}", e);
            downloadResult.setErrorCode(CommonEnum.FAILURE.getCode());
            downloadResult.setErrorMessage(CommonEnum.FAILURE.getMessage());
            return new DownloadResult(CommonEnum.FAILURE.getCode(), CommonEnum.FAILURE.getMessage());
        } finally {
            log.info("返回值downloadResult={}", downloadResult);
        }
    }

}
