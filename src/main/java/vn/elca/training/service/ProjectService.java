package vn.elca.training.service;

import java.util.List;
import java.util.Locale;

import vn.elca.training.dto.ProjectDTO;
import vn.elca.training.dto.page.PageListProjectDTO;
import vn.elca.training.exception.ConcurrencyUpdateException;
import vn.elca.training.exception.InvalidStatusOfProjectDeleteException;
import vn.elca.training.exception.NotEnoughEmployeeToCreateNewGroupException;
import vn.elca.training.exception.ProjectNumberAlreadyExistsException;
import vn.elca.training.exception.VisaNotExistedException;

public interface ProjectService {
    ProjectDTO save(ProjectDTO projectDTO, Locale locale) throws NotEnoughEmployeeToCreateNewGroupException,
        ProjectNumberAlreadyExistsException, VisaNotExistedException, ConcurrencyUpdateException;

    ProjectDTO findById(Long id, Locale locale);

    void delete(ProjectDTO projectDTO, Locale locale) throws ConcurrencyUpdateException, InvalidStatusOfProjectDeleteException;
    
    void deletes(List<ProjectDTO> projectDTOs, Locale locale) throws ConcurrencyUpdateException, InvalidStatusOfProjectDeleteException;

    PageListProjectDTO findProject(String searchString, String searchStatus, int page, Locale locale);

}