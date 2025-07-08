package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Tutorial;

/**
 * Tutorial数据访问层接口
 * 
 * <p>基于Spring Data JPA实现的Tutorial实体数据访问层，
 * 提供基础CRUD操作及自定义查询方法</p>
 * 
 * @author bezkoder
 * @since 1.0.0
 */
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	/**
	 * 根据发布状态查询教程列表
	 * 
	 * @param published 发布状态，true-已发布，false-未发布
	 * @return 符合条件的教程列表
	 */
	List<Tutorial> findByPublished(Boolean published);

	/**
	 * 根据标题关键字模糊查询教程列表
	 * 
	 * @param title 标题关键字，支持模糊匹配
	 * @return 标题包含关键字的教程列表
	 */
	List<Tutorial> findByTitleContaining(String title);

	/**
	 * 根据标题精确匹配查询教程
	 * 
	 * @param title 教程标题，精确匹配
	 * @return 匹配的教程实体，可能为null
	 */
	Tutorial findByTitle(String title);

	/**
	 * 根据描述关键字模糊查询教程列表
	 * 
	 * @param keyword 描述关键字，支持模糊匹配
	 * @return 描述包含关键字的教程列表
	 */
	List<Tutorial> findByDescriptionContaining(String keyword);

	/**
	 * 根据标题或描述关键字模糊查询教程列表
	 * 
	 * @param titleKeyword 标题关键字
	 * @param descKeyword 描述关键字
	 * @return 标题或描述包含关键字的教程列表
	 */
	List<Tutorial> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descKeyword);

	/**
	 * 根据发布状态和标题关键字查询教程列表
	 * 
	 * @param published 发布状态
	 * @param titleKeyword 标题关键字
	 * @return 符合条件的教程列表
	 */
	List<Tutorial> findByPublishedAndTitleContaining(Boolean published, String titleKeyword);

	/**
	 * 根据发布状态和描述关键字查询教程列表
	 * 
	 * @param published 发布状态
	 * @param descKeyword 描述关键字
	 * @return 符合条件的教程列表
	 */
	List<Tutorial> findByPublishedAndDescriptionContaining(Boolean published, String descKeyword);

	/**
	 * 查询标题不为空的教程列表
	 * 
	 * @return 标题不为空的教程列表
	 */
	List<Tutorial> findByTitleIsNotNull();

	/**
	 * 根据发布状态查询教程列表并按标题升序排序
	 * 
	 * @param published 发布状态
	 * @return 符合条件的教程列表，按标题升序排序
	 */
	List<Tutorial> findByPublishedOrderByTitleAsc(Boolean published);

	/**
	 * 根据ID范围查询教程列表
	 * 
	 * @param startId 起始ID（包含）
	 * @param endId 结束ID（包含）
	 * @return ID在指定范围内的教程列表
	 */
	List<Tutorial> findByIdBetween(Long startId, Long endId);

	/**
	 * 统计指定发布状态的教程数量
	 * 
	 * @param published 发布状态
	 * @return 符合条件的教程数量
	 */
	long countByPublished(Boolean published);

	/**
	 * 检查指定标题的教程是否存在
	 * 
	 * @param title 教程标题
	 * @return true-存在，false-不存在
	 */
	boolean existsByTitle(String title);
}
