package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping
    public List<ProductDTO> index() {
        var products = productRepository.findAll().stream().map(productMapper::map).toList();
        return products;
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        var productDto = productMapper.map(product);
        return productDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO post(@RequestBody ProductCreateDTO productCreateDTO) {
        var product = productMapper.create(productCreateDTO);
        var savedProduct = productRepository.save(product);
        return productMapper.map(savedProduct);
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO productUpdateDTO) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        productMapper.update(productUpdateDTO, product);
        var updatedProduct = productRepository.save(product);
        return productMapper.map(updatedProduct);
    }
    // END
}
