package exercise.mapper;

// BEGIN

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
    @Mapping(source = "name",   target = "title")
    @Mapping(source = "cost",   target = "price")
    @Mapping(source = "barcode",target = "vendorCode")
    public abstract ProductDTO map(Product product);

    @Mapping(target = "id",      ignore = true)
    @Mapping(source = "title",   target = "name")
    @Mapping(source = "price",   target = "cost")
    @Mapping(source = "vendorCode", target = "barcode")
    public abstract Product create(ProductCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "price", target = "cost")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product product);
}
// END
