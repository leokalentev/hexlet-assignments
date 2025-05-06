package exercise.mapper;

// BEGIN

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = IGNORE
)
public abstract class ProductMapper {

    @Mapping(source = "name", target = "title")  // Преобразуем поле name в title
    public abstract ProductDTO map(Product product);

    @Mapping(target = "id", ignore = true)  // id не устанавливаем вручную
    public abstract Product create(ProductCreateDTO productCreateDTO);

    public abstract ProductDTO map(ProductCreateDTO productCreateDTO);

    @Mapping(target = "id", ignore = true)  // id игнорируем при обновлении
    public abstract Product update(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);
}

// END
