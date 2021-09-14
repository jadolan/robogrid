package com.example.robogrid.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Actions {

    POSITION {
        @Override
        public void execute(String[] parameters, Robot robot) {

            if (parameters.length < 4) {
                LOG.warn(Actions.getErrorMessage(4, parameters.length));
                return;
            }

            int x = Integer.parseInt(parameters[1]);
            int y = Integer.parseInt(parameters[2]);
            int direction = DirectionHelper.translations.get(parameters[3]);
            robot.setPositionAndDirection(x, y, direction);
        }
    },
    TURNAROUND {
        @Override
        public void execute(String[] parameters, Robot robot) {robot.turnAround();}
    },
    RIGHT {
        @Override
        public void execute(String[] parameters, Robot robot) {
            robot.turnRight();
        }
    },
    FORWARD {
        @Override
        public void execute(String[] parameters, Robot robot) {

            if (parameters.length < 2) {
                LOG.warn(Actions.getErrorMessage(2, parameters.length));
                return;
            }
            robot.walk(Integer.parseInt(parameters[1]));
        }
    },
    WAIT {
        @Override
        public void execute(String[] parameters, Robot robot) {
        }
    };

    private static final Logger LOG = LoggerFactory.getLogger(Actions.class);

    public abstract void execute(String[] parameters, Robot robot);

    private static String getErrorMessage(int requiredLength, int actualLength) {

        return String.format("Executing task requires %d parameters, got %d.", requiredLength, actualLength);

    }
}
