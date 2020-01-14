package com.sinotrans.qywechat.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public @Data class AccessTokenResponse extends Response{
	private String accessToken; // 获取到的凭证，最长为512字节
	private int expiresIn; // 凭证的有效时间（秒）
}
