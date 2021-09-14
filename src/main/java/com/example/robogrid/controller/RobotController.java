package com.example.robogrid.controller;

import com.example.robogrid.model.Response;
import com.example.robogrid.model.Robot;
import com.example.robogrid.service.MovementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/robot")
public class RobotController {

    MovementService movementService;

    public RobotController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping(value = "/move", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response moveRobot(@RequestBody String script) {

        List<String> movements = List.of(script.split("\n"));
        Robot robot = movementService.interpret(movements);
        return new Response(robot.getX(), robot.getY(), robot.getDirection());
    }

}
