package com.ne.perfmance.util;


import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: 发送http post请求
 * author:devzhong
 * Date:2019/6/5 15:09
 */
public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final String oauthUrl = PropertiestUtil.getValue("server.oauthUrl");
    private static final String adminsUrl = PropertiestUtil.getValue("server.adminsUrl");
    private static final String roles = "dbc244957c5d412485b5c7f71d067cfd";
    private static final String groups = "4b11dd246f35405f944c7bebc9ac49b6";
    private static final String organId = "0d02ca1cf748aa03bfc9a83d17d06c41";
    private static final String org = "0d02ca1cf748aa03bfc9a83d17d06c41";
    private static final String roleIds = "dbc244957c5d412485b5c7f71d067cfd";
    private static final String groupIds = "4b11dd246f35405f944c7bebc9ac49b6";
    private static final String status = "ENABLE";
    private static final String systemId = "OPERATION";

    public static void main(String[] args) {
        int count = 0;
        long phone = 13000000059L;
        for (int i = 0; i <= 1; i++) {
            JSONObject params = new JSONObject();
            params.put("roles", roles);
            params.put("groups", groups);
            params.put("organId", organId);
            params.put("org", org);
            params.put("roleIds", roleIds);
            params.put("groupIds", groupIds);
            params.put("status", status);
            params.put("systemId", systemId);
            params.put("username", Long.toString(phone));
            params.put("password", "11111111");
            params.put("confirmPassword", "11111111");
            params.put("jobNumber", Long.toString(phone));
            params.put("email", Long.toString(phone) + "@qq.com");
            params.put("phone", Long.toString(phone));
            params.put("name", Long.toString(phone));
            System.out.println(doPost(oauthUrl, params));
            System.out.println(doPost(adminsUrl, params));
            phone = phone + 1;
            count = count + 1;
            logger.info("create user:", count);
        }
        /*JSONObject params = new JSONObject();
        params.put("roles", roles);
        params.put("groups", groups);
        params.put("organId", organId);
        params.put("org", org);
        params.put("roleIds", roleIds);
        params.put("groupIds", groupIds);
        params.put("status", status);
        params.put("systemId", systemId);
        params.put("username", "18000000006");
        params.put("password", "11111111");
        params.put("confirmPassword", "11111111");
        params.put("jobNumber", "18000000006");
        params.put("email", "18000000006@qq.com");
        params.put("phone", "18000000006");
        params.put("name", "18000000006");
        System.out.println(doPost(oauthUrl, params));
        System.out.println(doPost(adminsUrl, params));*/

    }

    //发送post请求
    private static JSONObject doPost(String url, JSONObject json) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        logger.info("send post request");
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            logger.error("err send post request error!");
            throw new RuntimeException(e);
        }
        logger.info("send post request complete");
        return response;
    }


}

