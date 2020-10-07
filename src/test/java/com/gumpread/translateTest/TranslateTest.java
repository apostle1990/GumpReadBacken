package com.gumpread.translateTest;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.gumpread.config.Translate;
import com.gumpread.domain.translate.TranslateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranslateTest {

    @Resource(name = "translate")
    private Translate translate;


    @Test
    public void testTranslateToChinese(){
        String s1 = "Although digital watermarking technology can provide security for multimedia data,embedding additional data into the host image will change the host image. For a goodwatermarking algorithm, it is necessary to ensure that there is no obvious visual differencebetween the host image before and after embedding the watermarking, so the less theembedded watermarking data, the less the impact on the host image, the higher the invisibilityof the watermarking. However, considering the robustness of the algorithm, there is no doubtthat the more the watermarking data, the better. In that way, when the host image is attacked,even if the extracted watermarking is not complete, effective information can be extracted fromsome correct data. Considering the above two aspects, it is difficult to satisfy both invisibilityand robustness when designing a watermarking algorithm";
        System.out.println(s1.length());

//        String text = "Digital zero-watermarking technology is an effective measure to protect image copyright,and many algorithms have been proposed based on zero-watermark";
//        String result = translate.translateToChinese(text);
//        System.out.println(result);
//        TranslateResult parse = JSON.parseObject(result, new TypeReference<TranslateResult>() {});
//        System.out.println(parse);

//        JSONObject parse = (JSONObject) JSON.parse(result);
//        System.out.println(parse );
//        JSONArray jsonArray = parse.getJSONArray("trans_result");
//        Iterator<Object> iterator = jsonArray.iterator();
//        while (iterator.hasNext()){
//            JSONObject next = (JSONObject) iterator.next();
//            System.out.println(next.get("dst"));
//        }
    }
}
