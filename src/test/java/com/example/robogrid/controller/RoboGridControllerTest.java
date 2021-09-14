package com.example.robogrid.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoboGridControllerTest {

	@Autowired
	MockMvc mockMvc;

	private static final String ROBOT_API = "/robot/move";

	@Test
	void testMoveRobot_Success() throws Exception {

		String testScript = "POSITION 1 3 EAST //sets the initial position for the robot\n" +
				"FORWARD 3 //lets the robot do 3 steps forward\n" +
				"WAIT //lets the robot do nothing\n" +
				"TURNAROUND //lets the robot turn around\n" +
				"FORWARD 1 //lets the robot do 1 step forward\n" +
				"RIGHT //lets the robot turn right\n" +
				"FORWARD 2 //lets the robot do 2 steps forward";

		callApiAndCheckResult(testScript, 3, 1, 0);
	}

	@Test
	void testMoveRobot_ignoreInvalidActions() throws Exception {

		String testScript = "POSITION 1 3 EAST //sets the initial position for the robot\n" +
				"FORWARD 3 //lets the robot do 3 steps forward\n" +
				"WAIT //lets the robot do nothing\n" +
				"TURNAROUND //lets the robot turn around\n" +
				"\n" +
				"//" +
				"SIT 1 //lets the robot sit down\n" +
				"RIGHT //lets the robot turn right\n" +
				"FORWARD 2 //lets the robot do 2 steps forward";

		callApiAndCheckResult(testScript, 4, 1, 0);
	}

	@Test
	void testMoveRobot_exceedGridLimit() throws Exception {

		String testScript = "POSITION 1 3 EAST //sets the initial position for the robot\n" +
				"RIGHT //lets the robot turn right\n" +
				"FORWARD 8 //lets the robot do 8 steps forward";

		callApiAndCheckResult(testScript, 1, 1, 2);
	}

	@Test
	void testMoveRobot_InvalidParameterLength() throws Exception {


		String testScript = "POSITION 1 //sets the initial position for the robot\n" +
				"RIGHT //lets the robot turn right\n" +
				"FORWARD 8 //lets the robot do 8 steps forward";

		callApiAndCheckResult(testScript, 3, 0, 1);
	}

	void callApiAndCheckResult (String testScript, int expectedPositionX, int expectedPositionY, int expectedDirection) throws Exception {

		mockMvc.perform(post(ROBOT_API)
						.contentType(MediaType.TEXT_PLAIN_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.content(testScript))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.xPosition").value(expectedPositionX))
				.andExpect(jsonPath("$.yPosition").value(expectedPositionY))
				.andExpect(jsonPath("$.direction").value(expectedDirection))
				.andReturn();
	}
}
