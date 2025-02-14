package com.az.edadi.university.service.impl;

import com.az.edadi.common_service.service.NullFinder;
import com.az.edadi.dal.entity.university.University;
import com.az.edadi.dal.repository.UniversityRepository;
import com.az.edadi.university.exception.UniversityNotFoundException;
import com.az.edadi.university.mapper.UniversityMapper;
import com.az.edadi.university.model.request.UniversityReq;
import com.az.edadi.university.model.response.UniversityRes;
import com.az.edadi.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {
    public UniversityServiceImpl(UniversityRepository universityRepository, MessageSource messageSource) {
        this.universityRepository = universityRepository;
        this.messageSource = messageSource;

    }

    private final UniversityRepository universityRepository;
    private final MessageSource messageSource;
    @Override
    public UniversityRes createUniversity(UniversityReq universityReq) {
        University university = UniversityMapper.INSTANCE.toEntity(universityReq);
        universityRepository.save(university);
        return UniversityMapper.INSTANCE.toResponse(university);
    }

    @Override
    public UniversityRes getUniversityById(UUID id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new UniversityNotFoundException(getMessage("university.not.found")));
        return UniversityMapper.INSTANCE.toResponse(university);
    }

    @Transactional
    @Override
    public UniversityRes update(UUID id, UniversityReq universityReq) {
        University university = universityRepository.
                findById(id)
                .orElseThrow(()-> new UniversityNotFoundException(getMessage("university.not.found")));
        BeanUtils.copyProperties(university,universityReq, NullFinder.getNullFieldNames(universityReq));
        return UniversityMapper.INSTANCE.toResponse(universityRepository.save(university));
    }

    @Override
    public String delete(UUID id) {
        University university = universityRepository.
                findById(id)
                .orElseThrow(()-> new UniversityNotFoundException(getMessage("university.not.found")));
        universityRepository.deleteById(id);
        return university.getNameAz() + " is deleted";
    }


    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
//                .stream()
//                .map(UniversityMapper.INSTANCE::toResponse)
//                .collect(Collectors.toList());
    }

    private String getMessage(String key){
        return messageSource.getMessage(key,null, Locale.getDefault());
    }

}
