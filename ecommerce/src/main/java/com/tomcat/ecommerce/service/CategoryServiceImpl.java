package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.exception.NoCategoryFoundException;
import com.tomcat.ecommerce.exception.ResourceAlreadyExists;
import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.dto.payload.CategoryDTO;
import com.tomcat.ecommerce.model.dto.payload.CategoryResponseDTO;
import com.tomcat.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.modelmapper.Converters.Collection.map;

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
    public CategoryResponseDTO getAllCategories( int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sortByNameAndOrder = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new NoCategoryFoundException("No categories found in the database, please add some categories");
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sortByNameAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryDTO> categoryDTOs = categoryPage.getContent().stream()
                .map(cat -> modelMapper.map(cat, CategoryDTO.class)).toList();

        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setPageNumber(categoryPage.getNumber());
        responseDTO.setPageSize(categoryPage.getSize());
        responseDTO.setTotalPages(categoryPage.getTotalPages());
        responseDTO.setTotalElements((int) categoryPage.getTotalElements());
        responseDTO.setLastPage(categoryPage.isLast());
        responseDTO.setContent(categoryDTOs);

        return responseDTO;
    }

    @Override
    public Optional<CategoryDTO> addCategory(CategoryDTO categoryDTO) {

        // find if category with same name already exists using stream, if yes then throw exception
//        categoryRepository.findAll().stream()
//                .filter(cat->cat.getCategoryName().equalsIgnoreCase(category.getCategoryName()))
//                .findFirst()
//                .ifPresent(cat -> {
//                    throw new ResourceAlreadyExists("category with name '"+category.getCategoryName()+"' already exists, please try with different name");
//                });
//        return Optional.of(modelMapper.map(categoryRepository.save(modelMapper.map(category, Category.class)), CategoryModelDTO.class));


        // find if category with same name already exists using repository method name, if yes then throw exception
        categoryRepository.findByCategoryName(categoryDTO.getCategoryName())
                .ifPresent(cat -> {
                    throw new ResourceAlreadyExists("category with the name '" + categoryDTO.getCategoryName() + "' already exists, please try with different name");
                });
        Category categoryEntity = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(categoryEntity);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
        return Optional.of(savedCategoryDTO);
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
    public Optional<Boolean> updateCategory(long categoryId, CategoryDTO categoryDTO) {
       return categoryRepository.findById(categoryId)
               .map(cat -> {
                   cat.setCategoryName(categoryDTO.getCategoryName());
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
    public Page<Category> fetchCategoriesWithPagination(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sortByNameAndOrder = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // pagination using pageable interface
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByNameAndOrder);
        return categoryRepository.findAll(pageable);
    }


}
