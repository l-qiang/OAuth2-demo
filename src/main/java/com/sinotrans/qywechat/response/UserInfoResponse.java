package com.sinotrans.qywechat.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public @Data class UserInfoResponse extends Response{
	private String UserId;
}
