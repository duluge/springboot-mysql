package com.bezkoder.spring.datajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Knife4j API文档配置类
 * 
 * <p>配置Knife4j增强的API文档界面</p>
 * 
 * @author bezkoder
 * @since 1.0.0
 */
@Configuration
@EnableKnife4j
public class OpenApiConfig {

    /**
     * 配置OpenAPI文档信息
     * 
     * @return OpenAPI配置对象
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("📚 Tutorial Management API")
                        .version("1.0.0")
                        .description("## 项目介绍\n" +
                                   "基于Spring Boot + JPA + MySQL的教程管理系统API文档\n\n" +
                                   "## 🚀 主要功能\n" +
                                   "- 教程的增删改查操作\n" +
                                   "- 支持按标题搜索教程\n" +
                                   "- 支持发布状态管理\n" +
                                   "- 提供丰富的查询方法\n\n" +
                                   "## 💡 技术栈\n" +
                                   "- **框架**: Spring Boot 3.1.5\n" +
                                   "- **数据库**: MySQL 8.4.4\n" +
                                   "- **ORM**: Spring Data JPA\n" +
                                   "- **文档**: Knife4j 4.4.0\n\n" +
                                   "## 📖 使用说明\n" +
                                   "1. 点击左侧接口列表查看API详情\n" +
                                   "2. 使用\"调试\"功能在线测试接口\n" +
                                   "3. 查看\"实体类\"了解数据模型\n" +
                                   "4. 支持导出离线文档")
                        .contact(new Contact()
                                .name("BezKoder Team")
                                .email("admin@bezkoder.com")
                                .url("https://www.bezkoder.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
} 