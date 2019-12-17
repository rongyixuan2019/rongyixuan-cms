package com.rongyixuan.cms.dao;

import java.util.List;

import com.rongyixuan.cms.domain.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

	/** 根据栏目查询分类
	 * @Title: selectsByChannelId 
	 * @Description: TODO
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
}