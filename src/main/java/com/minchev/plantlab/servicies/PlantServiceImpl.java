package com.minchev.plantlab.servicies;

import com.minchev.plantlab.databases.entities.PlantEntity;
import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.errors.PlantNotFoundException;
import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;
    private Pageable pagebleRequest;
    private List<Integer> pageNumbers = null;

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
        PlantEntity plant = this.modelMapper.map(plantServiceModel, PlantEntity.class);
        return this.modelMapper.map(this.plantRepository.saveAndFlush(plant), PlantServiceModel.class);
    }

    @Override
    public PlantServiceEditModel edit(PlantServiceEditModel plantServiceEditModel) {
        PlantEntity plant = this.plantRepository.findById(plantServiceEditModel.getId()).orElseThrow(() -> new PlantNotFoundException("Plant not found!"));
        plant.setBarcode(plantServiceEditModel.getBarcode());
        plant.setActive(plantServiceEditModel.isActive());
        return this.modelMapper.map(this.plantRepository.saveAndFlush(plant), PlantServiceEditModel.class);
    }

    @Override
    public List<PlantServiceModel> findAllPlants(Integer page, String sort, String search) {
        Integer limit = 2;
        if (page-1<0) page =0; else page= page-1;
        String[]  sortType = sort.split(",");
        Sort sortBy = null;
        if (sortType.length>0 && sortType[1].equals("asc")){
            sortBy= new Sort(Sort.Direction.ASC, sortType[0]);
        }
        if (sortType.length>0 && sortType[1].equals("desc")){
            sortBy= new Sort(Sort.Direction.DESC, sortType[0]);
        }
        PageRequest pageable = PageRequest.of(page, limit, sortBy);
        Page<PlantEntity>  records =   this.plantRepository.findAll(pageable);

        this.setPagingNumber(records.getTotalPages());
        return records.getContent()
                .stream()
                .map(u -> this.modelMapper.map(u, PlantServiceModel.class))
                .collect(Collectors.toList())
        ;
    }

    private List<Integer> setPagingNumber(Integer totalPages) {

        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }

    @Override
    public List<Integer> getPagingNumber() {
        return pageNumbers;
    }

    @Override
    public PlantServiceModel findPlantById(String id) {
        return this.plantRepository.findById(id)
                .map(u -> this.modelMapper.map(u, PlantServiceModel.class))
                .orElseThrow(() -> new PlantNotFoundException("Plant  not found!"));
    }

}
