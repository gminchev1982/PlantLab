package com.minchev.plantlab.models.view;

import com.minchev.plantlab.config.mappings.IHaveCustomMappings;
import com.minchev.plantlab.database.entities.LabEntity;
import org.modelmapper.ModelMapper;

public class LabListViewModel implements IHaveCustomMappings {

    private String id;
    private String plantId;
    private String productId;
    private String userId;
    private String status;
    private String comment;
    private String quantity;

    public LabListViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(LabEntity.class, LabListViewModel.class)
                .addMapping(
                        entity -> entity.getProduct().getName(),
                        (dto, value) -> dto.setProductId((String) value)
                )
                .addMapping(
                        entity -> entity.getUser().getName(),
                        (dto, value) -> dto.setUserId((String) value)
                );
    }
}
