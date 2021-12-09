package com.example.taskmanager.network.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the task dto
 *
 * @author 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String name;

    private String description;

    private TaskStatus status;

    private String assignedTo;

    private Date dueDate;

    private Date createdAt;

}
