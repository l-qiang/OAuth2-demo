package com.sinotrans.qywechat.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public @Data class UserResponse extends Response{
	private String userid;
	private String mobile;
	private String email;
	/**
	 * 其他返回值参考 https://work.weixin.qq.com/api/doc#90000/90135/90196
	 */
}
