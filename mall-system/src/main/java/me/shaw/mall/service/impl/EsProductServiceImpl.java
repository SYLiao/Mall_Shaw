package me.shaw.mall.service.impl;

import me.shaw.mall.dao.EsProductDao;
import me.shaw.mall.elasticsearch.document.EsProduct;
import me.shaw.mall.model.Product;
import me.shaw.mall.elasticsearch.repository.EsProductRepository;
import me.shaw.mall.repository.ProductRepository;
import me.shaw.mall.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private ProductRepository productReposit;

    @Autowired
    private EsProductRepository productRepository;

    @Override
    public int importAll() {
        productRepository.deleteAll();
        Iterable<Product> iter = productReposit.findAll();
        List<EsProduct> esProductList = new ArrayList<>();
        Long index = 1L;
        for(Product product : iter) {
            esProductList.add(new EsProduct(index, product.getProductSn(), product.getBrandName(), product.getProductCategoryName(), product.getName(), product.getSubTitle(), product.getKeywords()));
            index++;
        }
//        esProductList.add(new EsProduct(1L, "001", "Nike", "shoes", "Leborn III", "James", "Athlete"));
//        esProductList.add(new EsProduct(2L, "002", "Adidas", "shoes", "Ducan II", "II", "Athlete"));
//        esProductList.add(new EsProduct(3L, "003", "nintendo", "video game", "Switch", "NS", "game"));
//        esProductList.add(new EsProduct(4L, "004", "Sony", "video game", "Play Station", "PS", "game"));
//        esProductList.add(new EsProduct(5L, "005", "Microsoft", "video game", "XBox", "XB", "game"));
//        esProductList.add(new EsProduct(6L, "006", "Apple", "Laptop", "Mac Book", "Pro", "computer"));
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        int result = 0;
       for(EsProduct product : esProductIterable) {
            result++;
            System.out.println(product.getName());
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
//        EsProduct result = null;
//        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
//        if (esProductList.size() > 0) {
//            EsProduct esProduct = esProductList.get(0);
//            result = productRepository.save(esProduct);
//        }
//        return result;
        return null;
    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
