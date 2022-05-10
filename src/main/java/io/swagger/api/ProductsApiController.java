package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.model.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.entity.Product;
import io.swagger.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-28T12:35:57.278Z[GMT]")
@RestController
@Api(tags = {"users", "admins"})
@Log
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ProductService productService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ProductDTO> addProduct(@Parameter(in = ParameterIn.DEFAULT, description = "Product to add", schema=@Schema()) @Valid @RequestBody ProductDTO body) {

        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(body, Product.class);

        product = productService.add(product);

  

        ProductDTO response = modelMapper.map(product, ProductDTO.class);

        return new ResponseEntity<ProductDTO>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ProductDTO>> getAllProducts(@Parameter(in = ParameterIn.QUERY, description = "pass an optional search string for looking up products" ,schema=@Schema()) @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@Parameter(in = ParameterIn.QUERY, description = "number of records to skip for pagination" ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @Parameter(in = ParameterIn.QUERY, description = "maximum number of records to return" ,schema=@Schema(allowableValues={  }, maximum="50"
)) @Valid @RequestParam(value = "limit", required = false) Integer limit) {

        List<Product> products = productService.getAll();

        ModelMapper modelMapper = new ModelMapper();

        List<ProductDTO> dtos = products
                .stream()
                .map(user -> modelMapper.map(user, ProductDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<List<ProductDTO>>(dtos, HttpStatus.OK);
    }

}
