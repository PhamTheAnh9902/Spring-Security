package com.be.inventorymaneger.controller;

import com.be.inventorymaneger.model.Category;
import com.be.inventorymaneger.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // Show List Form

    @GetMapping("/list")
    public String showListForm(Model model){
        return showListFormPaging(model,1);
    }
    @GetMapping("/list/{pageNum}")
    public String showListFormPaging(Model model, @PathVariable("pageNum") int pageNum){
        Page<Category> page = categoryService.findAll(pageNum);
        List<Category> categories = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("categories",categories);
        return "category/list";
    }
    // Show Add Form
    @GetMapping("/addCategory")
    public String addCategoryForm(Model model){
        model.addAttribute("category",new Category());
        model.addAttribute("title","Add Category");
        return "category/addCategory";
    }

    // Add or update Category
    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,Model model){
        model.addAttribute("title","Add Category");
        if(result.hasErrors()){
            return "category/addCategory";
        }
        categoryService.save(category);
        return "redirect:/list";
    }

    // Show update form
    @GetMapping("/updateCategory/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model){
        Category category = categoryService.findCategoryById(id);
        model.addAttribute("category",category);
        model.addAttribute("title","Update Category");
        return "category/addCategory";
    }

    // Delete Category
    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") String id){
        Category category = categoryService.findCategoryById(id);
        categoryService.deleteCategoryById(category);
        return "redirect:/list";
    }

    // Show Find Form

    @GetMapping("/findCategory")
    public String showFindForm(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/findCategory";
    }

    @PostMapping("/findCategory")
    public String findCategory(@RequestParam("keyword") String keyword, Model model){
        List<Category> categories = categoryService.search(keyword);
        model.addAttribute("categories_find",categories);
        return "category/listFind";
    }

}
