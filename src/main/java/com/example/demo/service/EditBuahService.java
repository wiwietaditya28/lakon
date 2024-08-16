package com.example.demo.service;

import com.example.demo.dto.EditBuahReqDto;
import com.example.demo.model.BaseResponseDto;
import com.example.demo.model.Buah;
import com.example.demo.repository.BuahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EditBuahService {

    @Autowired
    private BuahRepository buahRepository;

    public BaseResponseDto<Buah> editBuah(EditBuahReqDto reqDto) {

        BaseResponseDto response = new BaseResponseDto<>();

        var data = buahRepository.findByIdAndIsDeleted(reqDto.getId(), Boolean.FALSE);
        if (data.isEmpty()) {
            response.setResponseCode("04");
            response.setMessage("Data tidak ditemukan!");

            return response;
        }

        Buah dataDelete = data.get();
        dataDelete.setName(reqDto.getName());
        dataDelete.setUpdatedDate(new Date());
        dataDelete.setUpdatedBy("user");

        buahRepository.save(dataDelete);

        response.setResponseCode("00");
        response.setMessage(dataDelete.getName() + " berhasil disimpan");

        return response;
    }
}
