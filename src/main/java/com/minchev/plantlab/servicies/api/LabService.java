package com.minchev.plantlab.servicies.api;


import com.minchev.plantlab.models.forms.LabSaveForm;
import com.minchev.plantlab.models.view.LabListViewModel;

import java.security.Principal;
import java.util.List;


public interface LabService {
    Boolean save(LabSaveForm labSaveForm, Principal principal);

    //LabServiceEditModel edit(LabServiceEditModel labServiceEditModel);
    List<LabListViewModel> findAllLabs(Integer page, String sort);
}
