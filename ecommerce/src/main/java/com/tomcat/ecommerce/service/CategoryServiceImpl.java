package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.exception.NoCategoryFoundException;
import com.tomcat.ecommerce.exception.ResourceAlreadyExists;
import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.dto.payload.CategoryModelDTO;
import com.tomcat.ecommerce.model.dto.payload.CategoryModelResponseDTO;
import com.tomcat.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

//    List<Category> categories = new ArrayList<>(List.of(
//            new Category(1L, "Electronics"),
//            new Category(2L, "Books"),
//            new Category(3L, "Clothing") ,
//            new Category(4L, "Home & Kitchen"),
//            new Category(5L, "Sports & Outdoors"),
//            new Category(6L, "Health & Personal Care"),
//            new Category(7L, "Toys & Games")
//    ));

    @Override
    public CategoryModelResponseDTO getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new NoCategoryFoundException("No categories found in the database, please add categories");
        }
        List<CategoryModelDTO> categoryModelDTOS = categories.stream()
                .map((cat)->modelMapper.map(cat, CategoryModelDTO.class))
                .toList();
        return new CategoryModelResponseDTO(categoryModelDTOS);
    }

    @Override
    public Optional<Category> addCategory(Category category) {

        // find if category with same name already exists using stream, if yes then throw exception
//        categoryRepository.findAll().stream()
//                .filter(cat->cat.getCategoryName().equalsIgnoreCase(category.getCategoryName()))
//                .findFirst()
//                .ifPresent(cat -> {
//                    throw new ResourceAlreadyExists("category with name '"+category.getCategoryName()+"' already exists, please try with different name");
//                });
//        return Optional.of(categoryRepository.save(category));


        // find if category with same name already exists using repository method name, if yes then throw exception
        categoryRepository.findByCategoryName(category.getCategoryName())
                .ifPresent(cat -> {
                    throw new ResourceAlreadyExists("category with the name '" + category.getCategoryName() + "' already exists, please try with different name");
                });
        return Optional.of(categoryRepository.save(category));
    }

    @Override
    public Optional<Boolean> deleteCategory(Long categoryId) {
        return categoryRepository.findAll().stream()
                .filter(cat -> cat.getCategoryId().equals(categoryId))
                .findFirst()
                .map(cat -> {
                    categoryRepository.deleteById(categoryId);
                    return true;
                });
    }

    @Override
    public Optional<Boolean> updateCategory(long categoryId, Category category) {
       return categoryRepository.findById(categoryId)
               .map(cat -> {
                   cat.setCategoryName(category.getCategoryName());
                   categoryRepository.save(cat);
                   return true;
               });
    }

    @Override
    public List<Category> addBatchCategories(List<Category> categories) {
        List<String> existingCategoryNames =  categoryRepository.findAll().stream()
                .map(Category::getCategoryName)
                .toList();

        List<Category> newCategories = categories.stream()
                .filter(cat -> !existingCategoryNames.contains(cat.getCategoryName()))
                .collect(Collectors.toList());

        if (newCategories.isEmpty()) {
            throw new ResourceAlreadyExists("All categories already exist, please try with different names");
        }

        return categoryRepository.saveAll(newCategories);
    }

    @Override
    public Page<Category> fetchCategoriesWithPagination(int pageNumber, int pageSize) {
       // pagination using stream api
//         return categoryRepository.findAll().stream()
//                .skip((long) pageNumber * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());

        // pagination using pageable interface
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return categoryRepository.findAll(pageable);
    }


}
