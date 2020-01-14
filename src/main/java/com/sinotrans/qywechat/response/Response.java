package com.sinotrans.qywechat.response;

import lombok.Data;

public @Data class Response {
	protected int errcode; // 出错返回码，为0表示成功，非0表示调用失败
	protected String errmsg; // 返回码提示语
}
