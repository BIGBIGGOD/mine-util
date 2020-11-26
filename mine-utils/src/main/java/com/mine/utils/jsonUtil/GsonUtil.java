package com.mine.utils.jsonUtil;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mine.common.entity.User;
import com.mine.utils.httpClientUtil.result.CommonResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqd
 * @date 2019/3/21
 */
@Slf4j
public class GsonUtil {

    private static final Gson GSON = new Gson();

    /**
     * 将json转化成对应的Bean类
     * 参数中的T与返回类型中的T要一致，所以需要实现CommonResult类，可根据实际需求更改
     */
    public static <T extends CommonResult> T jsonToBean(String json, Class<T> clazz) {
        T t = null;
        t = GSON.fromJson(json, clazz);
        return t;
    }

    /**
     * 将Bean类转化成json
     */
    public String beanToJson(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * 将json转java集合
     */
    public List<String> jsonToList(String json) {
        Type type = new TypeToken<List<String>>() {
        }.getType();
        json = "[\"78C002A389FE441582292E41FA75A6E0\",\"AB7C1AB226ED47E788FE243B58E12D23\",\"E491B305D69F4176BA40E2A3CB320FBA\",\"1D436CA3C4704C168087A44DB90E96E4\",\"A270BCE1EAEB4DBE80A046F53E52EA2D\",\"9A1AFE3DE2E04F4DBCAF0C000C9CBD08\",\"9F94C41AD2FF4FA49A19BEFF4E977ABD\",\"3DFC637F3A2644FE8A8B65617FB9FEEB\",\"9603F7C3B3DC46C5B57D25524FD8F5BE\",\"5048985C452D44F8AB80F56E28CE9799\",\"1E1E9E46162E44529BE2CDA6C94169D8\",\"AECC98ACB65F44C4A9FB03DECE29F8BE\",\"413335A9D6C84FFCA91A133D33E5C4F9\",\"F135B04DA5A54EC7AA419D156021A11A\",\"914F916E96134E5D96760DF671A98DF9\",\"918DBD952822472289241B6E6242816B\",\"F1722D0222C74888B34CF6762B62C90A\",\"1A14829058C04CA89753479B268CFCD5\",\"531087E098DD47278AEA32EA6B3998AA\",\"80C64C235B2649A6B3FF16D03C567CF5\",\"21DB02F816F54BDB8C0EFF3E6EBF2BB9\",\"C90A5D70E16C4E228C1BB2B16E9753EA\",\"FC949452002C410BBB2854E2BCBEEBF3\",\"9E80A66F9486401AB206A9E6D3A55C30\",\"DE9291B1980E489EB2EE97AAEFDB24FA\",\"B7B2EBBA0E08453A9F6549AB86C18993\",\"2241F19E25514AD4A219BB7BFA3458A2\",\"F111535FADDD491E98F05FA477D85B5B\",\"F2E03DE89E344D52BAB3C92942CF9624\",\"86839290480948D6ABC3AF3326686932\"]\n";
        List<String> openidList = GSON.fromJson(json, type);
        log.info("openidList={}", openidList);
        return openidList;
    }


    /**
     * json部分特殊情况
     */
    public static void judge() {
        //对象转json，gson不特殊设置时，对象中为空的引用类型属性不会出现在json中，为空的或者说是未赋值基础类型的属性会以默认值出现在json中
        User user = new User().setName("测试");
        System.out.println(GSON.toJson(user));

        //需要将空的属性转换成json时如下设置
        System.out.println(new GsonBuilder().serializeNulls().create().toJson(user));

        String json = "{\"retCode\":0,\"test\":null,\"msg\":\"success\",\"model\":[{\"list\":[{\"name\":\"男\",\"count\":213,\"tagId\":\"asdad\"},{\"name\":\"男\",\"count\":213,\"tagId\":\"asdad\"},{\"name\":\"男\",\"count\":213,\"tagId\":\"asdad\"}],\"date\":\"2020-11-12T09:56:31.327+0000\"}]}\n";
        //json转jsonObject
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        //jsonObject获取存在和不存在的元素，不存在的元素返回null不会报异常，此时不能进行获取值操作
        System.out.println(jsonObject.get("retCode").getAsBigInteger());
        System.out.println(jsonObject.get("xxx"));

        //jsonObject获取集合
        JsonArray jsonArray = jsonObject.getAsJsonArray("model");

        //当集合不存在时获取集合，结果返回null,不会抛异常
        JsonArray jsonArray2 = jsonObject.getAsJsonArray("xxx");

        //直接将json转换成jsonArray,测试时发现无法将数组形式的json直接转换成jsonArray或者jsonObject
        String json2 = "[{\"name\":\"男\",\"count\":213,\"tagId\":\"asdad\"},{\"name\":\"男\",\"count\":213,\"tagId\":\"asdad\"},{\"name\":\"男\",\"count\":213,\"tagId\":\"asdad\"}]";
//        JsonArray jsonArray3 = new JsonParser().parse(json).getAsJsonArray();

        //jsonObject添加元素
        jsonObject.addProperty("xxx", "faosdfj");

        //移除不存在的元素时返回null，不报错
        jsonObject.remove("xxx");
        jsonObject.remove("model");


        //获取json中值为null的元素,获取到的值为字符串的“null”,json中不会存在键值为kong或者空字符串的情况，否则转化成jsonObjec的时候会报错
        System.out.println(jsonObject.get("test"));
        System.out.println("");
    }

    public static void main(String[] args) {
        judge();
    }
}
