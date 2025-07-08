# Spring Boot + JPA + MySQL 学习项目

> 基于Spring Boot + JPA + MySQL的教程管理系统，集成Knife4j API文档

## 📖 项目介绍

这是一个用于学习Spring Boot、JPA和MySQL集成的完整示例项目。项目实现了教程管理的CRUD操作，并集成了现代化的API文档工具。

**本项目基于开源项目进行学习和改进，原项目来自 [BezKoder](https://github.com/bezkoder/spring-boot-data-jpa-mysql)，在此基础上进行了功能扩展和优化。**

## 🚀 技术栈

- **框架**: Spring Boot 3.1.5
- **数据库**: MySQL 8.4.4
- **ORM**: Spring Data JPA / Hibernate
- **API文档**: Knife4j 4.4.0 (替换原有的Swagger)
- **构建工具**: Maven
- **Java版本**: JDK 17

## ✨ 项目特色

### 🎯 核心功能
- ✅ 教程的完整CRUD操作
- ✅ 支持按标题模糊搜索
- ✅ 发布状态管理
- ✅ RESTful API设计

### 🔧 扩展功能
- 🎨 **升级到Knife4j** - 现代化的API文档界面，支持中文
- 📚 **扩展Repository方法** - 添加了多个实用的查询方法
- 🧪 **调试控制器** - 用于测试和验证功能
- 📋 **完整的API注解** - 详细的接口文档说明
- 🌍 **中文界面** - 完整的中文本地化支持

### 🎭 Repository方法扩展
```java
// 基础查询
Tutorial findByTitle(String title);
List<Tutorial> findByDescriptionContaining(String keyword);

// 组合查询  
List<Tutorial> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descKeyword);
List<Tutorial> findByPublishedAndTitleContaining(Boolean published, String titleKeyword);

// 排序和范围查询
List<Tutorial> findByPublishedOrderByTitleAsc(Boolean published);
List<Tutorial> findByIdBetween(Long startId, Long endId);

// 统计和存在性检查
long countByPublished(Boolean published);
boolean existsByTitle(String title);
```

## 🛠️ 快速开始

### 环境准备
- JDK 17+
- Maven 3.6+
- Docker (用于运行MySQL)

### 1. 启动MySQL数据库

```bash
# 使用Docker启动MySQL容器
docker run -d \
  --name springboot-mysql \
  -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=123456 \
  -e MYSQL_DATABASE=testdb \
  -v mysql_data:/var/lib/mysql \
  mysql:8.4.4
```

### 2. 克隆并运行项目

```bash
# 克隆项目
git clone https://github.com/duluge/springboot-mysql.git
cd springboot-mysql

# 启动应用
mvn spring-boot:run
```

### 3. 访问应用

- **API文档界面**: http://localhost:8080/doc.html
- **调试接口**: http://localhost:8080/debug/test-methods
- **示例API**: http://localhost:8080/api/tutorials

## 📚 API接口说明

### 主要接口
| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/tutorials` | 获取所有教程，支持标题过滤 |
| POST | `/api/tutorials` | 创建新教程 |
| GET | `/api/tutorials/{id}` | 根据ID获取教程 |
| PUT | `/api/tutorials/{id}` | 更新教程 |
| DELETE | `/api/tutorials/{id}` | 删除指定教程 |
| DELETE | `/api/tutorials` | 删除所有教程 |
| GET | `/api/tutorials/published` | 获取已发布的教程 |

### 测试数据示例
```json
{
  "title": "Spring Boot入门教程",
  "description": "学习Spring Boot基础知识",
  "published": false
}
```

## 🎨 Knife4j 文档界面

访问 [http://localhost:8080/doc.html](http://localhost:8080/doc.html) 体验增强的API文档功能：

- 🎨 **现代化界面** - 美观的深色/浅色主题
- 🇨🇳 **中文支持** - 完整的中文界面
- 📱 **响应式设计** - 支持手机、平板访问
- 🔍 **智能搜索** - 快速定位API接口
- 📋 **在线测试** - 直接在浏览器中测试API
- 📥 **文档导出** - 支持导出多种格式

## 📝 项目结构

```
src/main/java/com/bezkoder/spring/datajpa/
├── SpringBootDataJpaApplication.java    # 启动类
├── config/
│   └── OpenApiConfig.java              # Knife4j配置
├── controller/
│   ├── TutorialController.java         # 主要API控制器
│   └── DebugController.java            # 调试控制器
├── model/
│   └── Tutorial.java                   # 实体类
└── repository/
    └── TutorialRepository.java         # 数据访问层
```

## 🔧 配置说明

### 数据库配置
```properties
spring.datasource.url=jdbc:mysql://localhost:3307/testdb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```

### Knife4j配置
```properties
knife4j.enable=true
knife4j.setting.language=zh-cn
knife4j.setting.enable-version=true
knife4j.setting.enable-swagger-models=true
```

## 🎯 学习要点

1. **Spring Data JPA** - 理解Repository模式和方法名解析
2. **实体映射** - JPA注解的使用
3. **RESTful设计** - REST API的最佳实践
4. **API文档** - Knife4j的集成和配置
5. **数据库集成** - Spring Boot与MySQL的连接

## 📋 开发规范

项目遵循Java后端开发规约：
- ✅ 使用包装类型（Boolean、Integer等）
- ✅ 完整的JavaDoc注释
- ✅ 统一的代码格式化
- ✅ 清晰的分层架构

## 🙏 致谢

- 感谢 [BezKoder](https://www.bezkoder.com) 提供的原始项目框架
- 感谢 [Knife4j](https://gitee.com/xiaoym/knife4j) 提供的优秀API文档工具

## 📄 许可证

本项目仅用于学习目的，遵循MIT许可证。