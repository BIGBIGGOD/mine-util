package com.mine.util.httpClientUtil;

import com.google.gson.Gson;
import com.mine.BaseJunit4Test;
import com.mine.util.httpClientUtil.arg.ConnectArg;
import com.mine.util.httpClientUtil.arg.DownloadArg;
import com.mine.util.httpClientUtil.arg.UploadArg;
import com.mine.util.httpClientUtil.result.ConnectResult;
import com.mine.util.httpClientUtil.result.DownloadResult;
import com.mine.util.httpClientUtil.result.UploadResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * Created by jiangqd on 2019/1/16.
 */
@Slf4j
public class HttpClientUtilTest extends BaseJunit4Test {

    @Test
    public void testSendPostByJson() {
        String url = "https://open.fxiaoke.com/cgi/corpAccessToken/get/V2";
        String contentType = "application/json";
        ConnectArg connectArg = new ConnectArg("FSAID_1317822", "84984e733ad143c38a6584ebbf2ed7da", "686C83A287108A548AA3B3177E4635AC");
        ConnectResult connectResult = HttpClientUtil.sendPostByJson(url, connectArg, ConnectResult.class, contentType);
        System.out.println(connectResult.getErrorCode());
        System.out.println(connectResult.getErrorMessage());
        System.out.println(connectResult);
        log.info("httpResponseResult = {}", connectResult);
    }

    @Test
    public void testFileUpload() {
        String url = "https://open.fxiaoke.com/media/upload";
        //创建文件流
        File file = new File("D:\\测试文件夹\\测试.xls");
        byte[] bytes = fileToBinStr(file);
        UploadArg uploadArg =new UploadArg("document", bytes, "测试.xls");
        uploadArg.setCorpAccessToken("7C042911520D5C5E2B5F58AF4C9E7377");
        uploadArg.setCorpId("FSCID_4A1B1DF46B4C06FBD15A334F92596A2E");
        UploadResult uploadResult = HttpClientUtil.fileUpload(url,uploadArg,UploadResult.class);
        log.info("httpResponseResult = {}", uploadResult);
    }

    /**
     * 文件转为二进制数组
     * @param file
     * @return
     */
    public static byte[] fileToBinStr(File file){
        try {
            InputStream fis = new FileInputStream(file);
//            InputStreamReader osw2 = new InputStreamReader(fis, "GBK");

            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return bytes;
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin String 出错",ex);
        }
    }


    @Test
    public void testFileDownload() {
        String url = "https://open.fxiaoke.com/media/download";
        String mediaId = "3fa70771-e646-46b4-9667-16045f7c3490";
        DownloadArg downloadArg = new DownloadArg(mediaId);
        downloadArg.setCorpAccessToken("64A46687FF905AF0EA5C1A3F8C1185B9");
        downloadArg.setCorpId("FSCID_4A1B1DF46B4C06FBD15A334F92596A2E");
        DownloadResult downloadResult = HttpClientUtil.fileDownload(url, downloadArg);
        log.info("httpResponseResult = {}", downloadResult);
//        String fileName = downloadResult.getMediaName();
        String fileName = "suibiandfjiafj.xls";

        ByteArrayInputStream bis = null;
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fos = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File("D:\\测试文件夹\\"+fileName);
            bis = new ByteArrayInputStream(downloadResult.getImgByte());
            bufferedInputStream = new BufferedInputStream(bis);
            fos = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fos);
            // 实例化OutputString 对象
            byte[] buffer = new byte[1024];
            int length = bufferedInputStream.read(buffer);
            while (length != -1) {
                bufferedOutputStream.write(buffer, 0, length);
                length = bufferedInputStream.read(buffer);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            log.error("生成文件异常，e={}",e);
        }finally {
            try {
                bufferedOutputStream.close();
                bufferedInputStream.close();
                fos.close();
                bis.close();
            } catch (IOException e) {
                log.error("流关闭异常，e={}",e);
            }
        }
    }

}
