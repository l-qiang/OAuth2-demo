package com.sinotrans.qywechat.api;

import static com.sinotrans.common.utils.Jackson.json2Obj;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.sinotrans.qywechat.response.AccessTokenResponse;
import com.sinotrans.qywechat.response.UserInfoResponse;
import com.sinotrans.qywechat.response.UserResponse;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.var;

@Component
public class WorkApi {
	
	/**
	 * 获取access_token
	 * @param corpid 企业ID
	 * @param corpsecret 应用的凭证密钥
	 * @return {@link AccessTokenResponse}
	 */
	public AccessTokenResponse getToken(String corpid, String corpsecret) {
		var url = String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s", corpid, corpsecret);
		return json2Obj(httpGet(url), AccessTokenResponse.class);
	}
	
	/**
	 * 获取用户id
	 * @param token 调用接口凭证
	 * @param code 登陆code
	 * @return {@link UserInfoResponse}
	 */
	public UserInfoResponse getUserInfo(String token, String code) {
		var url = String.format("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=%s&code=%s", token, code);
		return json2Obj(httpGet(url), UserInfoResponse.class);
	}
	
	/**
	 * 获取用户详细信息
	 * @param token 调用接口凭证
	 * @param userId 用户id
	 * @return {@link UserResponse}
	 */
	public UserResponse getUser(String token, String userId) {
		var url = String.format("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=%s", token, userId);
		return json2Obj(httpGet(url), UserResponse.class);
	}

	@SneakyThrows
	private String httpPost(String url, String body) {
		var post = new HttpPost(url);
		var entity = new StringEntity(body, Charset.forName("UTF-8"));
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		@Cleanup var client = HttpClients.createDefault();
		@Cleanup var response = client.execute(post);
		return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
	}

	@SneakyThrows
	private String httpGet(String url) {
		@Cleanup var client = HttpClients.createDefault();
		@Cleanup var response = client.execute(new HttpGet(url));
		return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
	}
	
	
}
