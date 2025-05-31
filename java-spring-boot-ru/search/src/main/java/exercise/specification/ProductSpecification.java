package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.lang.reflect.Method;
import java.util.Arrays;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                categoryId == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceGt(Integer price) {
        return (root, query, cb) ->
                price == null ? cb.conjunction() : cb.greaterThan(root.get("price"), price);
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, cb) ->
                priceLt == null ? cb.conjunction() : cb.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null ? cb.conjunction() :
                cb.greaterThan(root.get("rating"), ratingGt);
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) ->
                titleCont == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + titleCont.toLowerCase() + "%");
    }
}
// END
