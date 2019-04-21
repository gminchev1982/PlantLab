package com.minchev.plantlab.servicies;

import com.minchev.plantlab.databases.entities.LabEntity;
import com.minchev.plantlab.databases.repositories.LabRepository;
import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.databases.repositories.UserRepository;
import com.minchev.plantlab.models.forms.LabSaveForm;
import com.minchev.plantlab.models.service.LabServiceModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.service.UserServiceModel;
import com.minchev.plantlab.models.view.LabListViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabServiceImpl implements LabService {

    private final PlantRepository plantRepository;
    private final ModelMapper modelMapper;
    private final LabRepository labRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public LabServiceImpl(PlantRepository plantRepository,
                          LabRepository lapRepository,
                          ProductRepository productRepository,
                          UserRepository userRepository,
                          ModelMapper modelMapper) {
        this.plantRepository = plantRepository;
        this.modelMapper = modelMapper;
        this.labRepository = lapRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean save(LabSaveForm labSaveForm, Principal principal) {

        LabServiceModel labServiceModel = this.modelMapper.map(labSaveForm, LabServiceModel.class);
        PlantServiceModel plantServiceModel =  this.modelMapper.map(this.plantRepository.findById(labSaveForm.getPlant()).orElse(null), PlantServiceModel.class);
        ProductServiceModel productServiceModel = this.modelMapper.map(this.productRepository.findById(labSaveForm.getProduct()).orElse(null), ProductServiceModel.class);
        UserServiceModel userServiceModel = this.modelMapper.map(this.userRepository.findByUsername(principal.getName()).orElse(null), UserServiceModel.class);
        labServiceModel.setPlant(plantServiceModel);
        labServiceModel.setProduct(productServiceModel);
        labServiceModel.setUser(userServiceModel);
       /* labServiceModel.setProduct(this.productRepository.findById(labSaveForm.getProduct()).orElse(null));
        labServiceModel.setUser(this.userRepository.findByUsername(principal.getName()).orElse(null));*/


        try {
            LabEntity labEntity = this.modelMapper.map(labServiceModel, LabEntity.class);


            labEntity = this.labRepository.saveAndFlush(labEntity);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }


    @Override
    public List<LabListViewModel> findAllLabs(Integer page, String sort) {

        return this.labRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, LabListViewModel.class))
                .collect(Collectors.toList());



    }
}
