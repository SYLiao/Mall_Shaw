package me.shaw.mall.service.impl;

import me.shaw.mall.model.ProductBrand;
import me.shaw.mall.repository.ProductBrandRepository;
import me.shaw.mall.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    private ProductBrandRepository productBrandRepository;

    @Override
    public List<ProductBrand> listAllBrand() {
        Iterable<ProductBrand> result = productBrandRepository.findAll();
        return (List<ProductBrand>) result;
    }

    @Override
    public int createBrand(ProductBrand pb) {
        try{
            productBrandRepository.save(pb);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }


    @Override
    public int updateBrand(Long id, ProductBrand pb) {
        pb.setId(id);
        try{
            productBrandRepository.save(pb);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteBrand(Long id, ProductBrand pb) {
        pb.setId(id);
        try{
            productBrandRepository.delete(pb);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Page<ProductBrand> pageBrand(int pageNum, int PageSize) {
        Pageable page = PageRequest.of(pageNum, PageSize, Sort.Direction.ASC, "id");
        return productBrandRepository.findAll(page);
    }

    public List<ProductBrand> listBrand(Page<ProductBrand> page){
        return page.getContent();
    }

    @Override
    public ProductBrand getBrand(Long id) {
        ProductBrand pb = productBrandRepository.findById(id).orElse(null);
        return pb;
    }
}
