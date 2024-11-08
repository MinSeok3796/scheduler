package com.example.schedules.controller;


import com.example.schedules.dto.ScheduleRequestDto;
import com.example.schedules.dto.ScheduleResponseDto;
import com.example.schedules.entitiy.Schedule;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final Map<Long, Schedule> schedules = new HashMap<>();

    // 스케줄 생성
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto dto) {

        Long id = schedules.isEmpty() ? 1 : Collections.max(schedules.keySet()) + 1;

        Schedule schedule =new Schedule(id, dto.getTitle(), dto.getUserName(), dto.getPassword(), LocalDateTime.now(),LocalDateTime.now());

        schedules.put(id, schedule);

        return new ScheduleResponseDto(schedule);
    }

    //스케줄 전체 조회
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules(@RequestParam(required = false) String userName, @RequestParam(required = false) String modiDate) {

        //날짜형식 수정
        LocalDate parsedModiDate = null;
        if (modiDate != null) {
            parsedModiDate = LocalDate.parse(modiDate);
        }

        //전체 목록 출력을 위한 리스트
        List<Schedule> filteredSchedules = new ArrayList<>();

        // 조건 필터링
        for (Schedule schedule : schedules.values()) {
            boolean matchesAuthor = (userName == null || schedule.getUserName().equals(userName));
            boolean matchesModiDate = (parsedModiDate == null || schedule.getModiDate().toLocalDate().equals(parsedModiDate));

            if (matchesAuthor && matchesModiDate) {
                filteredSchedules.add(schedule);
            }
        }
        //내림차순 정령
        Collections.sort(filteredSchedules, new Comparator<Schedule>() {
            @Override
            public int compare(Schedule s1, Schedule s2) {
                return s2.getModiDate().compareTo(s1.getModiDate());
            }
        });
        //List<ScheduleResponseDto>타입의 response생성
        List<ScheduleResponseDto> response = new ArrayList<>();
        for (Schedule schedule : filteredSchedules) {
            response.add(new ScheduleResponseDto(schedule));
        }

        return response;
    }

    //스케줄 개별 조회
    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable Long id){
        return schedules.get(id);
    }

    //스케줄 수정
    @PatchMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = schedules.get(id);
        if(schedule.getPassword().equals(dto.getPassword())){
            schedule.update(dto);
        }
        return new ScheduleResponseDto(schedule);
    }
    
    //스케줄 삭제
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id){
        schedules.remove(id);
    }


}
