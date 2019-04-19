package com.minchev.plantlab.servicies;

import com.minchev.plantlab.databases.entities.PlantEntity;
import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.models.service.PlantServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PlantServiceImpl(PlantRepository plantRepository, ModelMapper modelMapper) {
        this.plantRepository = plantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PlantServiceModel findByBarcode(String barcode) {
        return null;
    }

    @Override
    public PlantServiceModel save(PlantServiceModel plantServiceModel) {
        PlantEntity plant= this.modelMapper.map(plantServiceModel, PlantEntity.class);
        return this.modelMapper.map(this.plantRepository.saveAndFlush(plant), PlantServiceModel.class);
    }

    @Override
    public List<PlantServiceModel> findAllPlants(Pageable pageable) {
        return this.plantRepository.findAll().stream().map(u -> this.modelMapper.map(u, PlantServiceModel.class)).collect(Collectors.toList());

    }
}
