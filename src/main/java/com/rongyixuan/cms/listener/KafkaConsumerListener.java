/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: KafkaConsumerListener.java 
 * @Prject: rongyixuan-cms
 * @Package: com.rongyixuan.cms.listener 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年12月18日 下午7:26:12 
 * @version: V1.0   
 */
package com.rongyixuan.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.rongyixuan.cms.domain.ArticleWithBLOBs;
import com.rongyixuan.cms.service.ArticleService;

/** 
 * @ClassName: KafkaConsumerListener 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年12月18日 下午7:26:12  
 */
@Component
public class KafkaConsumerListener implements MessageListener<String, String>{

	@Resource
	private ArticleService articleService;
	/* (non Javadoc) 
	 * @Title: onMessage
	 * @Description: TODO
	 * @param data 
	 * @see org.springframework.kafka.listener.GenericMessageListener#onMessage(java.lang.Object) 
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		String key = record.key();
		//添加数据
				if(key != null && key.equals("article_add")) {
					String value = record.value();
					
					//转换成对象
					ArticleWithBLOBs article = JSON.parseObject(value, ArticleWithBLOBs.class);
					
					//存入mysql数据库
					articleService.insertSelective(article);
				}
		
	}

}
