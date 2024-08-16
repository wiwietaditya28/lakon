package com.example.demo.controller;

import com.example.demo.dto.AddBuahReqDto;
import com.example.demo.dto.BuahRespDto;
import com.example.demo.model.BaseResponseDto;
import com.example.demo.model.Buah;
import com.example.demo.service.AddBuahService;
import com.example.demo.service.ListBuahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/buah")
public class BuahController {

    @Autowired
    private ListBuahService listBuahService;

    @Autowired
    private AddBuahService addBuahService;

    @PostMapping("/list")
    @ResponseBody
    List<BuahRespDto> buahList() {
        return listBuahService.buahList();
    }

    @PostMapping("/save")
    BaseResponseDto<Buah> saveBuah(@RequestBody AddBuahReqDto addBuahReqDto) {
        return addBuahService.addBuah(addBuahReqDto);
    }

    @PostMapping("/delete")
    BaseResponseDto<Buah> deleteBuah(Long id) {
//        return addBuahService.addBuah(id);
        return null;
    }
}
