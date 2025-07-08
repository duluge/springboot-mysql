package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "教程实体类")
@Entity
@Table(name = "tutorials")
public class Tutorial {

	@Schema(description = "教程ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Schema(description = "教程标题", example = "Spring Boot入门教程", required = true)
	@Column(name = "title")
	private String title;

	@Schema(description = "教程描述", example = "这是一个关于Spring Boot基础知识的教程")
	@Column(name = "description")
	private String description;

	@Schema(description = "发布状态", example = "false")
	@Column(name = "published")
	private Boolean published;

	public Tutorial() {

	}

	public Tutorial(String title, String description, Boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isPublished() {
		return published;
	}

	public void setPublished(Boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}

}
