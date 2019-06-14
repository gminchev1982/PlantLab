package com.minchev.plantlab.servicies;

import com.minchev.plantlab.components.PageIndex;
import com.minchev.plantlab.components.SortPage;
import com.minchev.plantlab.databases.entities.PlantEntity;
import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.errors.PlantNotFoundException;
import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.view.PlantLabViewModel;
import com.minchev.plantlab.models.view.PlantListViewModel;
import com.minchev.plantlab.validations.services.PlantValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;
    private final PageIndex pageIndex;
    private final SortPage sortPage;
    private final PlantValidationService plantValidationService;
    private Pageable pagebleRequest;
    private List<Integer> pageNumbers = null;

    @Autowired
    public PlantServiceImpl(PlantRepository plantRepository,
                            PlantValidationService plantValidationService,
                            PageIndex pageIndex,
                            SortPage sortPage,
                            ModelMapper modelMapper) {
        this.plantRepository = plantRepository;
        this.plantValidationService = plantValidationService;
        this.modelMapper = modelMapper;
        this.pageIndex = pageIndex;
        this.sortPage = sortPage;

    }

    @Override
    public PlantServiceModel findByBarcode(String barcode) {
        return null;
    }

    @Override
    public Boolean save(PlantServiceModel plantServiceModel) {

        if (plantValidationService.isValid(plantServiceModel)) {
            try {
                PlantEntity plant = this.modelMapper.map(plantServiceModel, PlantEntity.class);
                this.plantRepository.saveAndFlush(plant);
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }  else return  false;
    }

    @Override
    public Boolean edit(PlantServiceEditModel plantServiceEditModel) {
        PlantServiceModel plantServiceModel = this.modelMapper.map(plantServiceEditModel, PlantServiceModel.class);

        if (plantValidationService.isValid(plantServiceModel)) {
            try {
                PlantEntity plantEntity = this.modelMapper.map(plantServiceModel, PlantEntity.class);
                this.plantRepository.saveAndFlush(plantEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else return  false;
    }

    @Override
    public List<PlantListViewModel> findAllPlants(Integer page, String sort, String search) {

        this.pageIndex.setCurrentPage(page);
        this.sortPage.setSort(sort);

        try {
            PageRequest pageable = PageRequest.of(this.pageIndex.getCurrentPage(), this.pageIndex.PAGE_LIMIT, this.sortPage.getSortBy());
            Page<PlantEntity> records = this.plantRepository.findAll(pageable);
            this.setPagingNumber(records.getTotalPages());
            return records.getContent()
                    .stream()
                    .map(u -> this.modelMapper.map(u, PlantListViewModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public List<PlantLabViewModel> findAllPlantsActive() {

        return this.plantRepository.findAll()
                .stream()
                .filter(f->f.isActive()==true)
                .map(u -> this.modelMapper.map(u, PlantLabViewModel.class))
                .collect(Collectors.toList());
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
