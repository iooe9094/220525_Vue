package com.example.dongsungsi.controller;

import com.example.dongsungsi.model.Tutorial;
import com.example.dongsungsi.service.TutorialServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * packageName : com.example.dongsungsi.controller
 * fileName : TutorialController
 * author : Seok
 * date : 2022-05-25
 * description : 일종의 메뉴 (URL 정보)
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         Seok          최초 생성
 */

// RestController: REST API 호출을 위한 어노테이션, json 형태로 들어옴
@RestController
// http://localhost:8080/api
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialServiceImpl tutorialService; // 객체 정의 (null)

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // ResponseEntity : frontend 요청 시 결과를 전달할 객체
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {

        logger.info("tutorial {}", tutorial);
        // insert or update 호출 (id값을 체크)
        boolean bSuccess = tutorialService.save(tutorial);

        try {
            if(bSuccess == true) {
                // ResponseEntity<> (매개변수객체, 상태정보)
                return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
            }
            // 정상 실행이 안 된 경우 : Not Found 프론트엔드로 전송
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // DB 에러가 났을 경우 : Internal Server Error 프론트엔드로 전송
            return new ResponseEntity<>((HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial>
    getTutorialById(@PathVariable("id") long id) {
        // Optional<Tutorial> : Tutorial 이 null 이면 "" 바꿔줌
        // 목적 : null 포인트 에러 방지
        Optional<Tutorial> tutorial = tutorialService.findById(id);

        // Optional 메소드 : 값이 있으면
        if (tutorial.isPresent()) {
            // ResponseEntity<>(객체, 상태정보) -> 프론트엔드로 전송
            return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } else {
            // 프론트엔드로 전송 : NOT_FOUND
            return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }
    }
}