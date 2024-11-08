package com.example.schedules.entitiy;

import com.example.schedules.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor //나중에 No랑 차이 알아보기
public class Schedule {
    //할일, 작성자명, 비밀번호, 작성일, 수정일
    private Long id;
    private String title;
    private String userName;
    private String password;
    private LocalDateTime startDate;
    private LocalDateTime modiDate;

    public void update(ScheduleRequestDto dto){
        this.userName = dto.getUserName();
        this.title = dto.getTitle();
        this.modiDate = LocalDateTime.now();
    }

}
