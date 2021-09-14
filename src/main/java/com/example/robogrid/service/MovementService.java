package com.example.robogrid.service;

import com.example.robogrid.model.Actions;
import com.example.robogrid.model.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

    private static final Logger LOG = LoggerFactory.getLogger(MovementService.class);

    public Robot interpret(List<String> instructions) {

        Robot robot = new Robot();
        instructions.forEach(i -> executeInstruction(i, robot));

        return robot;
    }

    public Robot executeInstruction(String instruction, Robot robot) {

        String[] instructionParts = sanitizeInstructions(instruction);

        try {
            Actions action = Actions.valueOf(instructionParts[0]);

            action.execute(instructionParts, robot);

        } catch (IllegalArgumentException e) {

            LOG.warn("Invalid action {}, couldn't execute.", instructionParts[0]);

        }

        return robot;

    }

    private String[] sanitizeInstructions(String instruction) {

        int commentIndex = instruction.indexOf("/");

        if (commentIndex > 0) {
            return instruction.substring(0, commentIndex).split(" ");
        }
        return instruction.split(" ");

    }



}
