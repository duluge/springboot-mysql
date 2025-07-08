package com.bezkoder.spring.datajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.TutorialRepository;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/test-methods")
    public String testCustomMethods() {
        StringBuilder result = new StringBuilder();
        
        // 测试方法名解析
        result.append("=== 测试自定义Repository方法 ===\n\n");
        
        try {
            // 测试 findByPublished
            result.append("1. 调用 findByPublished(true):\n");
            List<Tutorial> publishedTutorials = tutorialRepository.findByPublished(true);
            result.append("   返回结果数量: ").append(publishedTutorials.size()).append("\n");
            result.append("   实际执行的SQL: SELECT t FROM Tutorial t WHERE t.published = true\n\n");
            
            // 测试 findByTitleContaining
            result.append("2. 调用 findByTitleContaining('test'):\n");
            List<Tutorial> titleTutorials = tutorialRepository.findByTitleContaining("test");
            result.append("   返回结果数量: ").append(titleTutorials.size()).append("\n");
            result.append("   实际执行的SQL: SELECT t FROM Tutorial t WHERE t.title LIKE '%test%'\n\n");
            
            // 显示Repository的实际类型
            result.append("3. Repository实际类型:\n");
            result.append("   ").append(tutorialRepository.getClass().getName()).append("\n");
            result.append("   这是一个Spring动态代理对象！\n\n");
            
            result.append("=== 结论 ===\n");
            result.append("这两个方法完全没有手写实现，全部由Spring Data JPA的\n");
            result.append("方法名解析机制自动生成SQL并执行！");
            
        } catch (Exception e) {
            result.append("错误: ").append(e.getMessage());
        }
        
        return result.toString();
    }
    
    @GetMapping("/repository-info")
    public String getRepositoryInfo() {
        StringBuilder info = new StringBuilder();
        
        info.append("=== Repository 详细信息 ===\n\n");
        
        // Repository接口信息
        Class<?> repoClass = tutorialRepository.getClass();
        info.append("1. 实际类名: ").append(repoClass.getName()).append("\n");
        info.append("2. 是否为代理: ").append(repoClass.getName().contains("Proxy")).append("\n");
        
        // 接口信息
        Class<?>[] interfaces = repoClass.getInterfaces();
        info.append("3. 实现的接口:\n");
        for (Class<?> intf : interfaces) {
            info.append("   - ").append(intf.getName()).append("\n");
        }
        
        info.append("\n=== 自定义方法解析 ===\n");
        info.append("findByPublished → WHERE published = ?\n");
        info.append("findByTitleContaining → WHERE title LIKE %?%\n");
        
        return info.toString();
    }
} 