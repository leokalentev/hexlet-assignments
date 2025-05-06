package exercise.mapper;

// BEGIN

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = IGNORE
)
public abstract class ProductMapper {
    public abstract ProductDTO map(Product product);
    public abstract Product create(ProductCreateDTO productCreateDTO);
    public abstract ProductDTO map(ProductCreateDTO productCreateDTO);
    public abstract Product update(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);

}
// END
