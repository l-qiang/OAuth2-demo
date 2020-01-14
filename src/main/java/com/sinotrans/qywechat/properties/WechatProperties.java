package com.sinotrans.qywechat.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {
	private @Setter@Getter String corpId;
	private @Setter@Getter String secret;
	private @Setter@Getter int agentId;
}
