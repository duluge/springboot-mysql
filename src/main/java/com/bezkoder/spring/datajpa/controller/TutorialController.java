package com.bezkoder.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.TutorialRepository;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "📚 教程管理", description = "教程相关的CRUD操作API")
@ApiSupport(author = "BezKoder", order = 1)
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;

	@Operation(summary = "📋 获取所有教程", description = "获取所有教程列表，支持按标题过滤")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "✅ 成功获取教程列表"),
		@ApiResponse(responseCode = "204", description = "📭 没有找到教程"),
		@ApiResponse(responseCode = "500", description = "❌ 服务器内部错误")
	})
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(
			@Parameter(description = "标题关键字，用于过滤教程") 
			@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "🔍 根据ID获取教程", description = "通过教程ID获取单个教程详情")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "✅ 成功找到教程"),
		@ApiResponse(responseCode = "404", description = "❌ 教程不存在")
	})
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(
			@Parameter(description = "教程ID") 
			@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "➕ 创建新教程", description = "创建一个新的教程")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "✅ 教程创建成功"),
		@ApiResponse(responseCode = "500", description = "❌ 服务器内部错误")
	})
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(
			@Parameter(description = "教程信息") 
			@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "✏️ 更新教程", description = "根据ID更新指定教程的信息")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "✅ 教程更新成功"),
		@ApiResponse(responseCode = "404", description = "❌ 教程不存在")
	})
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(
			@Parameter(description = "教程ID") 
			@PathVariable("id") long id, 
			@Parameter(description = "更新的教程信息") 
			@RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "🗑️ 删除指定教程", description = "根据ID删除指定的教程")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "✅ 教程删除成功"),
		@ApiResponse(responseCode = "500", description = "❌ 服务器内部错误")
	})
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(
			@Parameter(description = "教程ID") 
			@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "🧹 删除所有教程", description = "删除数据库中的所有教程")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "✅ 所有教程删除成功"),
		@ApiResponse(responseCode = "500", description = "❌ 服务器内部错误")
	})
	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Operation(summary = "🚀 获取已发布教程", description = "获取所有已发布状态的教程列表")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "✅ 成功获取已发布教程列表"),
		@ApiResponse(responseCode = "204", description = "📭 没有找到已发布的教程"),
		@ApiResponse(responseCode = "500", description = "❌ 服务器内部错误")
	})
	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
