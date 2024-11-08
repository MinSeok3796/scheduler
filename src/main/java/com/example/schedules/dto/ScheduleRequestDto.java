package com.example.schedules.dto;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class ScheduleRequestDto {

    private String title;
    private String userName;
    private String password;
    private LocalDate startDate;
    private LocalDate modiDate;


}
